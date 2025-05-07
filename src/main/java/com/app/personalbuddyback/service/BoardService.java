package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BoardService {

    // 게시글 전체 목록 조회
    public List<BoardListViewDTO> getAllBoards();

    //  HOT게시글 목록 조회(좋아요 수 많은 10개만)
    public List<BoardListViewDTO> getHotBoards();

    //  게시글 검색 + 정렬 + 해시태그 필터링
    public List<BoardListViewDTO> getBoardsBySearch(Map<String, Object> params);

    //  게시글 상세 조회
    public Optional<BoardViewDTO> getBoardById(Long id);

    //  게시글 작성
    public void writeBoard(BoardVO boardVO);

    //  게시글 이미지 등록
    public void addBoardImage(BoardImgVO boardImgVO);

    //  게시글 수정
    public void updateBoard(BoardVO boardVO);

    //  게시글 이미지 삭제(전체)
    public void removeAllBoardImages(Long boardId);

    //  특정 이미지 1개 삭제
    public void removeBoardImageById(Long id);

    //  게시글 삭제
    public void removeBoard(Long id);

    //  게시글 조회수 1 증가
    public void increaseBoardViews(Long id);

    //  게시글 좋아요 추가
    public void increaseLikeBoard(BoardLikeVO boardLikeVO);

    //  게시글 좋아요 취소
    public void deleteLikeBoard(BoardLikeVO boardLikeVO);

    //  게시글 좋아요 여부 조회
    public int isBoardLiked(BoardLikeVO boardLikeVO);

    //  게시글 댓글 전체 목록
    public List<BoardCommentViewDTO> getBoardComments(Map<String, Object> params);

    //  댓글 작성
    public void writeComment(BoardCommentVO boardcommentVO);

    //  댓글 수정
    public void updateComment(BoardCommentVO boardcommentVO);

    //  댓글 삭제
    public void deleteComment(Long id);

    //  댓글 좋아요
    public void likeComment(BoardCommentLikeVO boardCommentLikeVO);

    //  댓글 좋아요 취소
    public void deleteLikeComment(BoardCommentLikeVO boardCommentLikeVO);

    //  댓글 좋아요 여부
    public int isBoardCommentLiked(BoardCommentLikeVO boardCommentLikeVO);
}
