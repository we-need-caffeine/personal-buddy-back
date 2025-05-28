package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.mapper.BoardMapper;
import com.app.personalbuddyback.repository.BoardCommentDAO;
import com.app.personalbuddyback.repository.BoardDAO;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;
    private final BoardCommentDAO boardCommentDAO;

    @Override
    public List<BoardDTO> getBoards(Map<String, Object> params) {
        return boardDAO.findBoards(params);
    }

    @Override
    public List<BoardDTO> getBoardsHot() {
        return boardDAO.findHotBoards();
    }

    // 게시글 전체 목록 조회
    @Override
    public List<BoardListViewDTO> getAllBoards() {
        return boardDAO.findAll();
    }

    // 내가 쓴 게시글 목록 조회 (마이페이지)
    @Override
    public List<BoardListViewDTO> getBoardsByMemberId(Long memberId) {
        return boardDAO.findBoardsByMemberId(memberId);
    }

    // HOT 게시글 목록 조회(좋아요 수 많은 10개만)
    @Override
    public List<BoardListViewDTO> getHotBoards() {
        return boardDAO.findHotList();
    }

    // 게시글 검색 + 정렬 + 해시태그 필터링
    @Override
    public List<BoardListViewDTO> getBoardsBySearch(Map<String, Object> params) {
        return boardDAO.findBoardListBySearch(params);
    }

    // 게시글 상세 조회
    @Override
    public Optional<BoardViewDTO> getBoardById(Long id) {
        BoardViewDTO boardViewDTO = new BoardViewDTO();

        boardDAO.findById(id).ifPresent(board -> {
            boardViewDTO.setId(board.getId());
            boardViewDTO.setBoardTitle(board.getBoardTitle());
            boardViewDTO.setBoardContent(board.getBoardContent());
            boardViewDTO.setBoardImgPath(board.getBoardImgPath());
            boardViewDTO.setBoardImgName(board.getBoardImgName());
            boardViewDTO.setBoardContentViews(board.getBoardContentViews());
            boardViewDTO.setBoardLikeCount(board.getBoardLikeCount());
            boardViewDTO.setBoardCreateDate(board.getBoardCreateDate());
            boardViewDTO.setBoardComments(board.getBoardComments());
            boardViewDTO.setMemberId(board.getMemberId());
            boardViewDTO.setMemberNickName(board.getMemberNickName());
            boardViewDTO.setMemberImgPath(board.getMemberImgPath());
            boardViewDTO.setMemberImgName(board.getMemberImgName());
            boardViewDTO.setBoardImages(boardDAO.findBoardImagesByBoardId(board.getId()));
            boardViewDTO.setBoardComments(boardCommentDAO.findComments(board.getId()));
        });
        return Optional.ofNullable(boardViewDTO);
    }

    // 게시글 작성
    @Override
    public void writeBoard(BoardVO boardVO) {
        boardDAO.saveBoard(boardVO);
    }

    // 게시글 이미지 등록
    @Override
    public void addBoardImage(BoardImgVO boardImgVO) {
        boardDAO.saveImage(boardImgVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void writeBoardWithImages(BoardVO boardVO, List<BoardImgVO> images) {
        boardDAO.saveBoard(boardVO);
        Long boardId = boardVO.getId();
        if (images != null && !images.isEmpty()) {
            for (BoardImgVO img : images) {
                img.setBoardId(boardId);
                boardDAO.saveImage(img);
            }
        }
    }

    // 게시글 수정
    @Override
    public void updateBoard(BoardVO boardVO) {
        boardDAO.updateBoard(boardVO);
    }

    // 게시글 이미지 삭제(전체)
    @Override
    public void removeAllBoardImages(Long boardId) {
        boardDAO.deleteImages(boardId);
    }

    // 특정 이미지 1개 삭제
    @Override
    public void removeBoardImageById(Long id) {
        boardDAO.deleteImageById(id);
    }

    @Override
    public void saveBoardImage(Long boardId, MultipartFile image) {
        if (image != null && !image.isEmpty()) {
            try {
                String basePath = "C:/personalbuddy/";
                String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                String folderPath = "images/board/" + datePath;
                File saveDir = new File(basePath + folderPath);
                if (!saveDir.exists()) saveDir.mkdirs();

                String uuid = UUID.randomUUID().toString();
                String originalName = image.getOriginalFilename();
                String savedName = uuid + "_" + originalName;

                // 원본 저장
                image.transferTo(new File(saveDir, savedName));

                // 썸네일 저장
                if (image.getContentType().startsWith("image")) {
                    File thumbnail = new File(saveDir, "t_" + savedName);
                    try (FileOutputStream out = new FileOutputStream(thumbnail)) {
                        Thumbnailator.createThumbnail(image.getInputStream(), out, 100, 100);
                    }
                }

                // DB 저장
                BoardImgVO imgVO = new BoardImgVO();
                imgVO.setBoardId(boardId);
                imgVO.setBoardImgPath(folderPath);
                imgVO.setBoardImgName(savedName);
                boardDAO.saveImage(imgVO);
            } catch (IOException e) {
                e.printStackTrace(); // 예외 로그 출력
            }
        }
    }



    // 게시글 삭제
    @Override
    public void removeBoard(Long id) {
        // 댓글 먼저 삭제
        boardCommentDAO.deleteByBoardId(id);

        // 이미지 파일 및 DB 삭제
        List<BoardImgVO> images = boardDAO.findBoardImagesByBoardId(id);
        for (BoardImgVO img : images) {
            String basePath = "C:/personalbuddy/";
            File origin = new File(basePath + img.getBoardImgPath(), img.getBoardImgName());
            File thumb = new File(basePath + img.getBoardImgPath(), "t_" + img.getBoardImgName());

            if (origin.exists()) origin.delete();
            if (thumb.exists()) thumb.delete();
        }
        boardDAO.deleteImages(id);

        // 마지막으로 게시글 삭제
        boardDAO.deleteBoard(id);
    }


    // 게시글 이미지 파일명으로 단일 삭제
    @Override
    public void removeBoardImageByName(String name) {
        boardDAO.deleteImageByName(name);
    }

    @Override
    public void deleteBoardImages(Long boardId, List<String> removedImageNames) {
        if (removedImageNames == null || removedImageNames.isEmpty()) return;

        for (String name : removedImageNames) {
            boardDAO.deleteImageByName(name); // DB 삭제

            // 실제 파일 삭제
            File[] baseFolders = {
                    new File("C:/personalbuddy/images/board/" + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"))), // 오늘 경로
                    new File("C:/personalbuddy/images/board/") // 혹시 모를 이전 경로 커버
            };

            for (File folder : baseFolders) {
                File origin = new File(folder, name);
                File thumb = new File(folder, "t_" + name);

                if (origin.exists()) origin.delete();
                if (thumb.exists()) thumb.delete();
            }
        }
    }




    // 게시글 조회수 1 증가
    @Override
    public void increaseBoardViews(Long id) {
        boardDAO.increaseView(id);
    }

    // 게시글 좋아요 추가
    @Override
    public void increaseLikeBoard(BoardLikeVO boardLikeVO) {
        boardDAO.saveLike(boardLikeVO);
    }

    // 게시글 좋아요 취소
    @Override
    public void deleteLikeBoard(BoardLikeVO boardLikeVO) {
        boardDAO.deleteLike(boardLikeVO);
    }

    // 게시글 좋아요 여부 조회
    @Override
    public int isBoardLiked(BoardLikeVO boardLikeVO) {
        return boardDAO.isLiked(boardLikeVO);
    }

    // 마이페이지 (내가 쓴 게시글)
    @Override
    public List<BoardListViewDTO> getBoardByMemberId(Long memberId) {
        return boardDAO.findBoardsByMemberId(memberId);
    }
}
