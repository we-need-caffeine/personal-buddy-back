package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface BoardService {

    // ---------------- 게시글 ----------------

    // 게시글 전체 목록 조회
    List<BoardListViewDTO> getAllBoards();

    // HOT 게시글 목록 조회 (좋아요 수 많은 상위 10개)
    List<BoardListViewDTO> getHotBoards();

    // 게시글 검색 + 정렬 + 해시태그 필터링 조회
    List<BoardListViewDTO> getBoardsBySearch(Map<String, Object> params);

    // 게시글 상세 조회
    Optional<BoardViewDTO> getBoardById(Long id);

    // 게시글 단독 등록
    void writeBoard(BoardVO boardVO);

    // 게시글 이미지 단독 등록
    void addBoardImage(BoardImgVO boardImgVO);

    // 게시글 + 이미지 함께 등록
    void writeBoardWithImages(BoardVO boardVO, List<BoardImgVO> images);

    // 게시글 수정
    void updateBoard(BoardVO boardVO);

    // 게시글 이미지 전체 삭제
    void removeAllBoardImages(Long boardId);

    // 게시글 이미지 단일 삭제
    void removeBoardImageById(Long id);

    // 게시글 단독 삭제
    void removeBoard(Long id);


    // 게시글 조회수 증가
    void increaseBoardViews(Long id);

    // 게시글 좋아요 추가
    void increaseLikeBoard(BoardLikeVO boardLikeVO);

    // 게시글 좋아요 취소
    void deleteLikeBoard(BoardLikeVO boardLikeVO);

    // 게시글 좋아요 여부 확인
    int isBoardLiked(BoardLikeVO boardLikeVO);

    // ---------------- 댓글 ----------------

    // 댓글 전체 목록 조회
    List<BoardCommentViewDTO> getBoardComments(Map<String, Object> params);

    // 댓글 작성
    void writeComment(BoardCommentVO boardcommentVO);

    // 댓글 수정
    void updateComment(BoardCommentVO boardcommentVO);

    // 댓글 삭제
    void deleteComment(Long id);

    // 댓글 좋아요 추가
    void likeComment(BoardCommentLikeVO boardCommentLikeVO);

    // 댓글 좋아요 취소
    void deleteLikeComment(BoardCommentLikeVO boardCommentLikeVO);

    // 댓글 좋아요 여부 확인
    int isBoardCommentLiked(BoardCommentLikeVO boardCommentLikeVO);
}