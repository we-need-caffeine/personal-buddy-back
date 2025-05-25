package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.repository.EventDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventDAO eventDAO;

    // 진행 중 이벤트 3개 조회
    @Override
    public List<EventListViewDTO> getCurrentEvents() {
        return eventDAO.findCurrentEvents();
    }

    // 커밍순 이벤트 조회
    @Override
    public List<EventListViewDTO> getComingEvents() {
        return eventDAO.findComingEvents();
    }

    // 참여 가능한 이벤트 조회
    @Override
    public List<EventListViewDTO> getAvailableEvents() {
        return eventDAO.findAvailableEvents();
    }

//    // 모든 이벤트 조회
//    @Override
//    public List<EventListViewDTO> getAllEvents() {
//        return eventDAO.findAllEvents();
//    }

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

    // 좋아요 추가
    @Override
    public void likeEvent(EventLikeVO eventLikeVO) {
        eventDAO.addEventLike(eventLikeVO);
    }

    // 좋아요 취소
    @Override
    public void deleteLikeEvent(EventLikeVO eventLikeVO) {
        eventDAO.removeEventLike(eventLikeVO);
    }

    // 좋아요 여부
    @Override
    public boolean isEventLiked(EventLikeVO eventLikeVO) {
        return eventDAO.isEventLiked(eventLikeVO);
    }

    // 좋아요 수
    @Override
    public int getEventLikeCount(Long eventId) {
        return eventDAO.countEventLikes(eventId);
    }

    // 이벤트 등록
    @Override
    public void registerEvent(EventVO eventVO) {
        eventDAO.save(eventVO);
    }

    // 이벤트 수정
    @Override
    public void updateEvent(EventVO eventVO) {
        eventDAO.update(eventVO);
    }

    // 이벤트 삭제
    @Override
    public void deleteEvent(Long id) {
        eventDAO.delete(id);
    }
}
