package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.repository.BoardDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;

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
        return boardDAO.findById(id);
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

    // 게시글 삭제
    @Override
    public void removeBoard(Long id) {
        boardDAO.deleteBoard(id);
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
