package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;

import java.util.List;
import java.util.Map;

public interface BoardCommentService {

    // 댓글 전체 목록 조회
    public List<BoardCommentViewDTO> getBoardComments(Map<String, Object> params);

    // 마이페이지 (내가 쓴 댓글)
    public List<BoardCommentViewDTO> getCommentsByMemberId(Long memberId);

    // 댓글 작성
    public void writeComment(BoardCommentVO boardcommentVO);

    // 댓글 수정
    public void updateComment(BoardCommentVO boardcommentVO);

    // 댓글 삭제
    public void deleteComment(Long id);

    // 댓글 좋아요 추가
    public void likeComment(BoardCommentLikeVO boardCommentLikeVO);

    // 댓글 좋아요 취소
    public void deleteLikeComment(BoardCommentLikeVO boardCommentLikeVO);

    // 댓글 좋아요 여부 확인
    public int isBoardCommentLiked(BoardCommentLikeVO boardCommentLikeVO);

    // 베스트 댓글 3개 조회
    public List<BoardCommentViewDTO> getBestCommentsByBoardId(Long boardId);
}
