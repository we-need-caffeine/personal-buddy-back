package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.mapper.EventMapper;
import com.app.personalbuddyback.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventDAO {

    private final EventMapper eventMapper;

    // 진행중 이벤트 3개(슬라이드 배너)
    public List<EventListViewDTO> findSurrentEventList() {
        return eventMapper.selectCurrentEvents();
    }

    // 커밍순 이벤트 리스트
    public List<EventListViewDTO> findComingEvent() {
        return eventMapper.selectComingEvents();
    }

    // 참여 가능한 이벤트 리스트
    public List<EventListViewDTO> findAvailableEvent() {
        return eventMapper.selectAvailableEvents();
    }

    // 이벤트 참여 등록
    public void saveJoin(EventJoinVO eventJoinVO) {
        eventMapper.insertEventJoin(eventJoinVO);
    }

    // 이벤트 참여 여부 조회
    public boolean joinedEvent(Long eventId, Long memberId) {
        return eventMapper.isJoined(eventId, memberId) == 0;
    }

    // 이벤트 상세 조회
    public EventViewDTO findEventDetail(Long id) {
        return eventMapper.selectEventDetail(id);
    }

    // 조회수 1 증가
    public void increaseView(Long id) {
        eventMapper.updateEventView(id);
    }

    // 게시글 좋아요 추가
    public void addEventLike(EventLikeVO eventLikeVO) {
        eventMapper.insertEventLike(eventLikeVO);
    }

    // 게시글 좋아요 취소
    public void removeEventLike(EventLikeVO eventLikeVO) {
        eventMapper.deleteEventLike(eventLikeVO);
    }

    // 게시글 좋아요 여부 조회
    public boolean isEventLiked(EventLikeVO eventLikeVO) {
        return eventMapper.isEventLiked(eventLikeVO) == 0;
    }

    // 게시글 좋아요 수 조회
    public int countEventLikes(Long eventId) {
        return eventMapper.countEventLike(eventId);
    }

    // 게시글 댓글 전체 목록
    public List<EventCommentViewDTO> findEventComment(Long eventId, String sort) {
        return eventMapper.selectEventCommentsByEventId(eventId, sort);
    }

    // 댓글 작성
    public void saveEventComment(EventCommentVO eventCommentVO) {
        eventMapper.insertEventComment(eventCommentVO);
    }

    // 유저 댓글 작성 여부 조회(중복 방지용)
    public boolean hasEventCommented(EventCommentVO eventCommentVO) {
        return eventMapper.userCommented(eventCommentVO) == 0;
    }

    // 댓글 좋아요
    public void likeEventComment(EventCommentLikeVO eventCommentLikeVO) {
        eventMapper.insertEventCommentLike(eventCommentLikeVO);
    }

    // 댓글 좋아요 취소
    public void unlikeEventComment(EventCommentLikeVO eventCommentLikeVO) {
        eventMapper.deleteEventCommentLike(eventCommentLikeVO);
    }

    // 댓글 좋아요 여부
    public boolean isEventCommentLiked(EventCommentLikeVO eventCommentLikeVO) {
        return eventMapper.isEventCommentLiked(eventCommentLikeVO) == 0;
    }

    // 댓글 좋아요 수 조회
    public int countEventCommentLikes(Long eventCommentId) {
        return eventMapper.countEventCommentLike(eventCommentId);
    }

    // 기상 이벤트 시간 조건
    public boolean isWakeUpTime() {
        return eventMapper.wakeUpChallengeTime() == 1;
    }

    // 힐링데이 이벤트 베스트 댓글 TOP3
    public List<EventCommentViewDTO> findBestComments(Long eventId) {
        return eventMapper.selectBestComments(eventId);
    }

    // 루틴 공유 이벤트 조건
    public List<EventCommentVO> findRoutineComments(Long eventId) {
        return eventMapper.selectRoutineComments(eventId);
    }

}
