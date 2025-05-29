package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.EventCommentLikeVO;
import com.app.personalbuddyback.domain.EventCommentVO;
import com.app.personalbuddyback.domain.EventCommentViewDTO;

import java.util.List;
import java.util.Map;

public interface EventCommentService {

    // 이벤트 댓글 전체 목록
    public List<EventCommentViewDTO> getEventComments(Map<String, Object> params);

    // 댓글 작성
    public void writeEventComment(EventCommentVO eventCommentVO);

    // 댓글 중복 작성 여부 확인
    public boolean isEventCommented(EventCommentVO eventCommentVO);

    // 댓글 좋아요
    public void likeEventComment(EventCommentLikeVO eventCommentLikeVO);

    // 댓글 좋아요 취소
    public void deleteLikeEventComment(EventCommentLikeVO eventCommentLikeVO);

    // 댓글 좋아요 여부 확인
    public boolean isEventCommentLiked(EventCommentLikeVO eventCommentLikeVO);

    // 댓글 좋아요 수
    public int getEventCommentLikeCount(Long eventCommentId);

    // 댓글 수정
    public void updateEventComment(EventCommentVO commentVO);

    // 댓글 삭제
    public void deleteEventComment(Long id);

    // 힐링데이 베스트 댓글 (좋아요 순 상위 3개)
    public List<EventCommentViewDTO> getBestComments(Long eventId);

    // 루틴 공유 조건 댓글 목록
    public List<EventCommentViewDTO> getRoutineComments(Long eventId);
}
