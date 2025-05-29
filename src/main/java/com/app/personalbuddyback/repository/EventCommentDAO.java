package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.EventCommentLikeVO;
import com.app.personalbuddyback.domain.EventCommentVO;
import com.app.personalbuddyback.domain.EventCommentViewDTO;
import com.app.personalbuddyback.mapper.EventCommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventCommentDAO {

    private final EventCommentMapper eventCommentMapper;

    // 댓글 전체 목록
    public List<EventCommentViewDTO> findEventComments(Long eventId, String sort) {
        return eventCommentMapper.selectEventCommentsByEventId(eventId, sort);
    }

    // 댓글 작성
    public void saveEventComment(EventCommentVO commentVO) {
        eventCommentMapper.insertEventComment(commentVO);
    }

    // 유저 댓글 작성 여부 조회 (중복 방지용)
    public boolean hasUserCommented(EventCommentVO commentVO) {
        return eventCommentMapper.userCommented(commentVO) > 0;
    }

    // 댓글 좋아요 추가
    public void likeEventComment(EventCommentLikeVO likeVO) {
        eventCommentMapper.insertEventCommentLike(likeVO);
    }

    // 댓글 좋아요 취소
    public void unlikeEventComment(EventCommentLikeVO likeVO) {
        eventCommentMapper.deleteEventCommentLike(likeVO);
    }

    // 댓글 좋아요 여부 확인
    public boolean isCommentLiked(EventCommentLikeVO likeVO) {
        return eventCommentMapper.isEventCommentLiked(likeVO) > 0;
    }

    // 댓글 수정
    public void updateEventComment(EventCommentVO commentVO) {}

    // 댓글 삭제
    public void deleteEventComment(Long id) {}

    // 댓글 좋아요 수 조회
    public int countCommentLikes(Long commentId) {
        return eventCommentMapper.countEventCommentLike(commentId);
    }

    // 힐링데이 베스트 댓글 (Top 3)
    public List<EventCommentViewDTO> findBestComments(Long eventId) {
        return eventCommentMapper.selectBestComments(eventId);
    }

    // 루틴 공유 조건 만족 댓글
    public List<EventCommentViewDTO> findRoutineComments(Long eventId) {
        return eventCommentMapper.selectRoutineComments(eventId);
    }

}
