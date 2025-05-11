package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.EventCommentLikeVO;
import com.app.personalbuddyback.domain.EventCommentVO;
import com.app.personalbuddyback.domain.EventCommentViewDTO;
import com.app.personalbuddyback.repository.EventCommentDAO;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class EventCommentServiceImpl implements EventCommentService {

    private final EventCommentDAO eventCommentDAO;

    // 이벤트 댓글 전체 목록 조회 (정렬 조건 포함)
    @Override
    public List<EventCommentViewDTO> getEventComments(Map<String, Object> params) {
        Long eventId = Long.parseLong(params.get("eventId").toString());
        String sort = (String) params.getOrDefault("sort", "new");
        return eventCommentDAO.findEventComments(eventId, sort);
    }

    // 이벤트 댓글 작성
    @Override
    public void writeEventComment(EventCommentVO eventCommentVO) {
        eventCommentDAO.saveEventComment(eventCommentVO);
    }

    // 사용자가 해당 이벤트에 이미 댓글을 작성했는지 여부 확인 (중복 방지용)
    @Override
    public boolean isEventCommented(EventCommentVO eventCommentVO) {
        return eventCommentDAO.hasUserCommented(eventCommentVO);
    }

    // 댓글 좋아요 등록
    @Override
    public void likeEventComment(EventCommentLikeVO eventCommentLikeVO) {
        eventCommentDAO.likeEventComment(eventCommentLikeVO);
    }

    // 댓글 좋아요 취소
    @Override
    public void deleteLikeEventComment(EventCommentLikeVO eventCommentLikeVO) {
        eventCommentDAO.unlikeEventComment(eventCommentLikeVO);
    }

    // 사용자가 해당 댓글에 좋아요를 눌렀는지 확인
    @Override
    public boolean isEventCommentLiked(EventCommentLikeVO eventCommentLikeVO) {
        return eventCommentDAO.isCommentLiked(eventCommentLikeVO);
    }

    // 특정 댓글의 좋아요 수 조회
    @Override
    public int getEventCommentLikeCount(Long eventCommentId) {
        return eventCommentDAO.countCommentLikes(eventCommentId);
    }

    // 힐링데이 이벤트: 좋아요 Top 3 베스트 댓글 (작성일 빠른 순)
    @Override
    public List<EventCommentViewDTO> getBestComments(Long eventId) {
        return eventCommentDAO.findBestComments(eventId);
    }

    // 루틴 공유 이벤트: 20자 이상 + "루틴" 포함 댓글 목록
    @Override
    public List<EventCommentViewDTO> getRoutineComments(Long eventId) {
        return eventCommentDAO.findRoutineComments(eventId);
    }
}
