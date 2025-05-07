package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.repository.EventDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventDAO eventDAO;

    // 진행 중인 이벤트 3개 조회 (슬라이드용)
    @Override
    public List<EventListViewDTO> getCurrentEvents() {
        return eventDAO.findSurrentEventList();
    }

    // 커밍순 이벤트 리스트
    @Override
    public List<EventListViewDTO> getComingEvents() {
        return eventDAO.findComingEvent();
    }

    // 참여 가능한 이벤트 리스트
    @Override
    public List<EventListViewDTO> getAvailableEvents() {
        return eventDAO.findAvailableEvent();
    }

    // 이벤트 상세 조회
    @Override
    public Optional<EventViewDTO> getEventDetail(Long id) {
        return Optional.ofNullable(eventDAO.findEventDetail(id));
    }

    // 조회수 증가
    @Override
    public void increaseEventViews(Long id) {
        eventDAO.increaseView(id);
    }

    // 이벤트 참여
    @Override
    public void joinEvent(EventJoinVO eventJoinVO) {
        eventDAO.saveJoin(eventJoinVO);
    }

    // 이벤트 참여 여부 확인 (0이면 참여한 적 없는 것 --> true 반환)
    @Override
    public boolean isEventJoined(Long eventId, Long memberId) {
        return eventDAO.joinedEvent(eventId, memberId);
    }

    // 이벤트 좋아요 추가
    @Override
    public void likeEvent(EventLikeVO eventLikeVO) {
        eventDAO.addEventLike(eventLikeVO);
    }

    // 이벤트 좋아요 취소
    @Override
    public void deleteLikeEvent(EventLikeVO eventLikeVO) {
        eventDAO.removeEventLike(eventLikeVO);
    }

    // 이벤트 좋아요 여부 확인
    @Override
    public boolean isEventLiked(EventLikeVO eventLikeVO) {
        return eventDAO.isEventLiked(eventLikeVO);
    }

    // 이벤트 좋아요 수
    @Override
    public int getEventLikeCount(Long eventId) {
        return eventDAO.countEventLikes(eventId);
    }

    // 이벤트 댓글 전체 조회 (정렬 기준 포함)
    @Override
    public List<EventCommentViewDTO> getEventComments(Map<String, Object> params) {
        Long eventId = (Long) params.get("eventId");
        String sort = (String) params.get("sort");
        return eventDAO.findEventComment(eventId, sort);
    }

    // 댓글 작성
    @Override
    public void writeEventComment(EventCommentVO eventCommentVO) {
        eventDAO.saveEventComment(eventCommentVO);
    }

    // 댓글 중복 여부 확인
    @Override
    public boolean isEventCommented(EventCommentVO eventCommentVO) {
        return eventDAO.hasEventCommented(eventCommentVO);
    }

    // 댓글 좋아요 추가
    @Override
    public void likeEventComment(EventCommentLikeVO eventCommentLikeVO) {
        eventDAO.likeEventComment(eventCommentLikeVO);
    }

    // 댓글 좋아요 취소
    @Override
    public void deleteLikeEventComment(EventCommentLikeVO eventCommentLikeVO) {
        eventDAO.unlikeEventComment(eventCommentLikeVO);
    }

    // 댓글 좋아요 여부 확인
    @Override
    public boolean isEventCommentLiked(EventCommentLikeVO eventCommentLikeVO) {
        return eventDAO.isEventCommentLiked(eventCommentLikeVO);
    }

    // 댓글 좋아요 수
    @Override
    public int getEventCommentLikeCount(Long eventCommentId) {
        return eventDAO.countEventCommentLikes(eventCommentId);
    }

    // 기상 챌린지 시간대 여부
    @Override
    public boolean isWakeUpChallengeTime() {
        return eventDAO.isWakeUpTime();
    }

    // 힐링데이 베스트 댓글 조회
    @Override
    public List<EventCommentViewDTO> getBestComments(Long eventId) {
        return eventDAO.findBestComments(eventId);
    }

    // 루틴 공유 조건 댓글 조회
    @Override
    public List<EventCommentVO> getRoutineComments(Long eventId) {
        return eventDAO.findRoutineComments(eventId);
    }
}
