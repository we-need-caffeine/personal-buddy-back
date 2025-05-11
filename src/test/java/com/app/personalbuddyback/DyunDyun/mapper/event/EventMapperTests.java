package com.app.personalbuddyback.DyunDyun.mapper.event;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.mapper.EventMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EventMapperTests {

    @Autowired
    private EventMapper eventMapper;

    // 진행 중인 이벤트 3개 조회 (슬라이드 배너용)
    @Test
    public void testSelectCurrentEvents() {
        List<EventListViewDTO> events = eventMapper.selectCurrentEvents();
        log.info("진행 중 이벤트 수: {}", events.size());
    }

    // 커밍순 이벤트 조회
    @Test
    public void testSelectComingEvents() {
        List<EventListViewDTO> events = eventMapper.selectComingEvents();
        log.info("커밍순 이벤트 수: {}", events.size());
    }

    // 현재 참여 가능한 이벤트 목록 조회
    @Test
    public void testSelectAvailableEvents() {
        List<EventListViewDTO> events = eventMapper.selectAvailableEvents();
        log.info("참여 가능 이벤트 수: {}", events.size());
    }

    // 특정 이벤트의 상세 정보 조회
    @Test
    public void testSelectEventDetail() {
        EventViewDTO detail = eventMapper.selectEventDetail(1L);
        log.info("이벤트 상세: {}", detail);
    }

    // 이벤트 조회수 1 증가
    @Test
    public void testUpdateEventView() {
        eventMapper.updateEventView(1L);
        log.info("조회수 증가 완료");
    }

    // 이벤트 좋아요 등록
//    @Test
//    public void testInsertEventLike() {
//        EventLikeVO like = new EventLikeVO();
//        like.setEventId(1L);
//        like.setMemberId(2L);
//        eventMapper.insertEventLike(like);
//        log.info("좋아요 등록");
//    };

    // 이벤트 좋아요 취소
    @Test
    public void testDeleteEventLike() {
        EventLikeVO like = new EventLikeVO();
        like.setEventId(1L);
        like.setMemberId(2L);
        eventMapper.deleteEventLike(like);
        log.info("좋아요 취소");
    }

    // 좋아요 여부 확인 (0 또는 1 반환)
    @Test
    public void testIsEventLiked() {
        EventLikeVO like = new EventLikeVO();
        like.setEventId(1L);
        like.setMemberId(2L);
        int liked = eventMapper.isEventLiked(like);
        log.info("좋아요 여부: {}", liked);
    }

    // 해당 이벤트의 총 좋아요 수 조회
    @Test
    public void testCountEventLike() {
        int count = eventMapper.countEventLike(1L);
        log.info("좋아요 수: {}", count);
    }
}
