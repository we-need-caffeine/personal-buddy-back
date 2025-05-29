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
                // 저장 경로 설정
                String basePath = "C:/personalbuddy/";
                String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
                String folderPath = "images/board/" + datePath;
                File saveDir = new File(basePath + folderPath);
                if (!saveDir.exists()) {
                    boolean created = saveDir.mkdirs();
                    if (!created) throw new IOException("디렉토리 생성 실패: " + saveDir.getAbsolutePath());
                }

                // 파일명 설정
                String originalName = image.getOriginalFilename();
                if (originalName == null) throw new IllegalArgumentException("파일 이름이 null입니다.");

                String uuid = UUID.randomUUID().toString();
                String savedName = uuid + "_" + originalName;

                // 실제 파일 저장
                File savedFile = new File(saveDir, savedName);
                image.transferTo(savedFile); // <-- 여기서 오류 나면 catch에서 잡음

                // 썸네일 생성 (이미지인 경우)
                if (image.getContentType() != null && image.getContentType().startsWith("image")) {
                    File thumbFile = new File(saveDir, "t_" + savedName);
                    try (FileOutputStream out = new FileOutputStream(thumbFile)) {
                        Thumbnailator.createThumbnail(image.getInputStream(), out, 100, 100);
                    }
                }

                // DB 저장
                BoardImgVO imgVO = new BoardImgVO();
                imgVO.setBoardId(boardId);
                imgVO.setBoardImgPath(folderPath);
                imgVO.setBoardImgName(savedName);
                boardDAO.saveImage(imgVO);

            } catch (Exception e) {
                e.printStackTrace(); // 로그 확인용
                throw new RuntimeException("이미지 저장 중 오류 발생: " + e.getMessage());
            }
        }
    }

    // 게시글 삭제
    @Override
    public void removeBoard(Long id) {
        // 1. 댓글 삭제 (만약 댓글도 FK로 연결되어 있으면)
        boardCommentDAO.deleteByBoardId(id);

        // 2. 이미지 삭제
        boardDAO.deleteImages(id);

        // 3. 게시글 삭제 (자식 다 지우고 부모 지우기)
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
            // 1. DB 삭제
            boardDAO.deleteImageByName(name);

            // 2. 실제 파일 삭제
            String basePath = "C:/personalbuddy/";
            String folderPath = "images/board/"; // DB에 저장된 경로가 있다면 그걸로 대체

            // 실제 파일 삭제
            File original = new File(basePath + folderPath + name);
            File thumbnail = new File(basePath + folderPath + "t_" + name);
            if (original.exists()) original.delete();
            if (thumbnail.exists()) thumbnail.delete();
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
