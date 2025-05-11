package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EventMapper {

    // 진행중 이벤트 3개(슬라이드배너)
    public List<EventListViewDTO> selectCurrentEvents();

    // 커밍순 이벤트
    public List<EventListViewDTO> selectComingEvents();

    // 참여 가능한 이벤트
    public List<EventListViewDTO> selectAvailableEvents();

    // 이벤트 상세 조회
    public EventViewDTO selectEventDetail(Long id);

    // 조회수 1 증가
    public void updateEventView(Long id);

    // 좋아요 추가
    public void insertEventLike(EventLikeVO eventLikeVO);

    // 좋아요 취소
    public void deleteEventLike(EventLikeVO eventLikeVO);

    // 좋아요 여부 조회
    public int isEventLiked(EventLikeVO eventLikeVO);

    // 좋아요 수 조회
    public int countEventLike(Long eventId);

    // 이벤트 등록
    public void insertEvent(EventVO eventVO);

    // 이벤트 수정
    public void updateEvent(EventVO eventVO);

    // 이벤트 삭제
    public void deleteEvent(Long id);
}
