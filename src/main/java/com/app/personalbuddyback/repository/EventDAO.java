package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventDAO {

    private final EventMapper eventMapper;

    // 진행 중 이벤트 3개 (슬라이드배너)
    public List<EventListViewDTO> findCurrentEvents() {
        return eventMapper.selectCurrentEvents();
    }

    // 커밍순 이벤트 리스트
    public List<EventListViewDTO> findComingEvents() {
        return eventMapper.selectComingEvents();
    }

    // 참여 가능한 이벤트 리스트
    public List<EventListViewDTO> findAvailableEvents() {
        return eventMapper.selectAvailableEvents();
    }

    // 이벤트 상세 정보 조회
    public EventViewDTO findEventDetail(Long id) {
        return eventMapper.selectEventDetail(id);
    }

    // 조회수 증가
    public void increaseView(Long id) {
        eventMapper.updateEventView(id);
    }

    // 좋아요 추가
    public void addEventLike(EventLikeVO likeVO) {
        eventMapper.insertEventLike(likeVO);
    }

    // 좋아요 취소
    public void removeEventLike(EventLikeVO likeVO) {
        eventMapper.deleteEventLike(likeVO);
    }

    // 좋아요 여부 확인
    public boolean isEventLiked(EventLikeVO likeVO) {
        return eventMapper.isEventLiked(likeVO) > 0;
    }

    // 좋아요 수 조회
    public int countEventLikes(Long eventId) {
        return eventMapper.countEventLike(eventId);
    }

    // 이벤트 등록
    public void save(EventVO eventVO) {
        eventMapper.insertEvent(eventVO);
    }

    // 이벤트 수정
    public void update(EventVO eventVO) {
        eventMapper.updateEvent(eventVO);
    }

    // 이벤트 삭제
    public void delete(Long id) {
        eventMapper.deleteEvent(id);
    }
}
