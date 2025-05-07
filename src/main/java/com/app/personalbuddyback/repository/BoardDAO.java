package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardDAO {

    private final BoardMapper boardMapper;

    // 게시글 전체 목록
    public List<BoardListViewDTO> findAll() {
        return boardMapper.selectBoardList();
    }

    // HOT 게시글
    public List<BoardListViewDTO> findHotList() {
        return boardMapper.selectHotBoardList();
    }

    // 게시글 검색 + 정렬 + 해시태그 필터링
    public List<BoardListViewDTO> findBoardListBySearch(Map<String,Object> params) {
        return boardMapper.selectBoardListBySearch(params);
    }

    // 게시글 상세 조회
    public Optional<BoardViewDTO>findById(Long id) {
        return boardMapper.selectBoardById(id);
    }

    // 게시글 작성
    public void saveBoard(BoardVO boardVO) {
        boardMapper.insertBoard(boardVO);
    }

    // 게시글 이미지 등록
    public void saveImage(BoardImgVO boardImgVO) {
        boardMapper.insertBoardImage(boardImgVO);
    }

    // 게시글 수정
    public void updateBoard(BoardVO boardVO) {
        boardMapper.updateBoard(boardVO);
    }

    // 게시글 이미지 전체 삭제
    public void deleteImages(Long boardId) {
        boardMapper.deleteBoardImages(boardId);
    }

    // 게시글 이미지 단건 삭제
    public void deleteImageById(Long id) {
        boardMapper.deleteBoardImageById(id);
    }

    // 게시글 삭제
    public void delete(Long id) {
        boardMapper.deleteBoard(id);
    }

    // 게시글 조회수 1 증가
    public void increaseView(Long id) {
        boardMapper.updateBoardViews(id);
    }

    // 게시글 좋아요 추가
    public void increaseLike(BoardLikeVO likeVO) {
        boardMapper.insertBoardLike(likeVO);
    }

    // 게시글 좋아요 취소
    public void deleteLike(BoardLikeVO likeVO) {
        boardMapper.deleteBoardLike(likeVO);
    }

    // 게시글 좋아요 여부 조회
    public int isLiked(BoardLikeVO likeVO) {
        return boardMapper.checkBoardLike(likeVO);
    }

    // 게시글 댓글 전체 목록
    public List<BoardCommentViewDTO> findComments(Map<String, Object> params) {
        return boardMapper.selectBoardCommentsByBoardId(params);
    }

// ---------------------------- [댓글] ----------------------------

    // 댓글 작성
    public void saveComment(BoardCommentVO commentVO) {
        boardMapper.insertBoardComment(commentVO);
    }

    // 댓글 수정
    public void updateComment(BoardCommentVO commentVO) {
        boardMapper.updateBoardComment(commentVO);
    }

    // 댓글 삭제
    public void deleteComment(Long id) {
        boardMapper.deleteBoardComment(id);
    }

    // 댓글 좋아요
    public void likeComment(BoardCommentLikeVO likeVO) {
        boardMapper.insertBoardCommentLike(likeVO);
    }

    // 댓글 좋아요 취소
    public void deleteLikeComment(BoardCommentLikeVO likeVO) {
        boardMapper.deleteBoardCommentLike(likeVO);
    }

    // 댓글 좋아요 여부
    public int isCommentLiked(BoardCommentLikeVO likeVO) {
        return boardMapper.checkBoardCommentLike(likeVO);
    }
}
