package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.repository.BoardDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardDAO boardDAO;

    // 게시글 전체 목록 조회
    @Override
    public List<BoardListViewDTO> getAllBoards() {
        return boardDAO.findAll();
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
        boardDAO.delete(id);
    }

    // 게시글 조회수 1 증가
    @Override
    public void increaseBoardViews(Long id) {
        boardDAO.increaseView(id);
    }

    // 게시글 좋아요 추가
    @Override
    public void increaseLikeBoard(BoardLikeVO boardLikeVO) {
        boardDAO.increaseLike(boardLikeVO);
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

    // 게시글 댓글 전체 목록
    @Override
    public List<BoardCommentViewDTO> getBoardComments(Map<String, Object> params) {
        return boardDAO.findComments(params);
    }

    // 댓글 작성
    @Override
    public void writeComment(BoardCommentVO boardcommentVO) {
        boardDAO.saveComment(boardcommentVO);
    }

    // 댓글 수정
    @Override
    public void updateComment(BoardCommentVO boardcommentVO) {
        boardDAO.updateComment(boardcommentVO);
    }

    // 댓글 삭제
    @Override
    public void deleteComment(Long id) {
        boardDAO.deleteComment(id);
    }

    // 댓글 좋아요
    @Override
    public void likeComment(BoardCommentLikeVO boardCommentLikeVO) {
        boardDAO.likeComment(boardCommentLikeVO);
    }

    // 댓글 좋아요 취소
    @Override
    public void deleteLikeComment(BoardCommentLikeVO boardCommentLikeVO) {
        boardDAO.deleteLikeComment(boardCommentLikeVO);
    }

    // 댓글 좋아요 여부
    @Override
    public int isBoardCommentLiked(BoardCommentLikeVO boardCommentLikeVO) {
        return boardDAO.isCommentLiked(boardCommentLikeVO);
    }
}
