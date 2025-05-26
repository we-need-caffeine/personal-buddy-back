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

//    // 전체 이벤트 조회 (진행중 + 커밍순)
//    public List<EventListViewDTO> getAllEvents();

    // 이벤트 조회수 증가
    public void increaseEventViews(Long id);

    // 이벤트 좋아요 추가
    public void likeEvent(EventLikeVO eventLikeVO);

    // 이벤트 좋아요 취소
    public void deleteLikeEvent(EventLikeVO eventLikeVO);

    // 이벤트 좋아요 여부 확인
    public boolean isEventLiked(EventLikeVO eventLikeVO);

    // 이벤트 좋아요 수 조회
    public int getEventLikeCount(Long eventId);

    // 이벤트 등록
    void registerEvent(EventVO eventVO);

    // 이벤트 수정
    void updateEvent(EventVO eventVO);

    // 이벤트 삭제
    void deleteEvent(Long id);

}