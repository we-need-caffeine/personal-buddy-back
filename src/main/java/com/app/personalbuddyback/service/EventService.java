package com.app.personalbuddyback.service;


import com.app.personalbuddyback.domain.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface EventService {

    // 진행 중 이벤트 3개 (슬라이드용)
    public List<EventListViewDTO> getCurrentEvents();

    // 커밍순 이벤트
    public List<EventListViewDTO> getComingEvents();

    // 참여 가능한 이벤트 전체 리스트
    public List<EventListViewDTO> getAvailableEvents();

    // 이벤트 상세
    public Optional<EventViewDTO> getEventDetail(Long id);

    // 이벤트 조회수 증가
    public void increaseEventViews(Long id);

    // 이벤트 참여
    public void joinEvent(EventJoinVO eventJoinVO);

    // 이벤트 참여 여부 확인
    public boolean isEventJoined(Long eventId, Long memberId);

    // 이벤트 좋아요 추가
    public void likeEvent(EventLikeVO eventLikeVO);

    // 이벤트 좋아요 취소
    public void deleteLikeEvent(EventLikeVO eventLikeVO);

    // 이벤트 좋아요 여부 확인
    public boolean isEventLiked(EventLikeVO eventLikeVO);

    // 이벤트 좋아요 수 조회
    public int getEventLikeCount(Long eventId);

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

    // 기상 챌린지 시간 여부 (05:00~06:00)
    public boolean isWakeUpChallengeTime();

    // 힐링데이 베스트 댓글 (좋아요 순 상위 3개)
    public List<EventCommentViewDTO> getBestComments(Long eventId);

    // 루틴 공유 조건 댓글 목록
    public List<EventCommentVO> getRoutineComments(Long eventId);
}