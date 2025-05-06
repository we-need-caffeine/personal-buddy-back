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

    // 진행중 이벤트 3개(슬라이드배너) 테스트
    @Test
    public void testSelectCurrentEvent(){
        List<EventListViewDTO> events = eventMapper.selectCurrentEvents();
        log.info("현재 진행중인 이벤트 : {}", events.size());
    }

    // 커밍순 이벤트 테스트
    @Test
    public void testSelectComingEvents() {
        List<EventListViewDTO> events = eventMapper.selectComingEvents();
        log.info("커밍순 이벤트 : {}", events.size());
    }

    // 참여 가능한 이벤트 테스트
    @Test
    public void testSelectAvailableEvents() {
        List<EventListViewDTO> events = eventMapper.selectAvailableEvents();
        log.info("참여 가능한 이벤트 : {}", events.size());
    }

    // 이벤트 참여 등록 테스트
//    @Test
//    public void testInsertEventJoin() {
//        EventJoinVO joinVO = new EventJoinVO();
//        joinVO.setEventId(1L);
//        joinVO.setMemberId(1L);
//        eventMapper.insertEventJoin(joinVO);
//        log.info("이벤트 참여 등록 ");
//    }

    // 이벤트 참여 여부 조회 테스트
    @Test
    public void testIsJoined() {
        int result = eventMapper.isJoined(1L, 999L);
        log.info("참여 여부 (1이면 참여함, 0이면 참여 안함): {}", result);
    }

    // 이벤트 상세 조회 테스트
    @Test
    public void testSelectEventDetail(){
        EventViewDTO event = eventMapper.selectEventDetail(1L);
        log.info("이벤트 상세 조회 : {}", event);
    }

    // 조회수 1 증가 테스트
    @Test
    public void testUpdateEventView() {
        eventMapper.updateEventView(1L);
        log.info("조회수 1 증가");
    }

    // 게시글 좋아요 추가 테스트
//    @Test
//    public void testInsertEventLike(){
//        EventLikeVO eventLikeVO = new EventLikeVO();
//        eventLikeVO.setEventId(1L);
//        eventLikeVO.setMemberId(999L);
//        eventMapper.insertEventLike(eventLikeVO);
//        log.info("이벤트 게시글 좋아요 추가");
//    }

    // 게시글 좋아요 취소 테스트
    @Test
    public void testDeleteEventLike() {
        EventLikeVO likeVO = new EventLikeVO();
        likeVO.setEventId(1L);
        likeVO.setMemberId(1L);
        eventMapper.deleteEventLike(likeVO);
        log.info("이벤트 좋아요 삭제");
    }

    // 게시글 좋아요 여부 조회 테스트
    @Test
    public void testIsEventLiked() {
        EventLikeVO likeVO = new EventLikeVO();
        likeVO.setEventId(1L);
        likeVO.setMemberId(1L);
        int result = eventMapper.isEventLiked(likeVO);
        log.info("이벤트 좋아요 여부: {}", result);
    }

    // 게시글 좋아요 수 조회 테스트
    @Test
    public void testCountEventLike() {
        int count = eventMapper.countEventLike(1L);
        log.info("이벤트 좋아요 수: {}", count);
    }

    // 게시글 댓글 전체 목록 테스트
    @Test
    public void testEventCommentList(){
        List<EventCommentViewDTO> comments = eventMapper.selectEventCommentsByEventId(1L,"like");
        log.info("댓글 수 : {}", comments.size());
    }

    // 댓글 작성 테스트
//    @Test
//    public void testInsertEventComment() {
//        EventCommentVO commentVO = new EventCommentVO();
//        commentVO.setEventId(1L);
//        commentVO.setMemberId(1L);
//        commentVO.setEventCommentDescription("댓글 테스트");
//        eventMapper.insertEventComment(commentVO);
//        log.info("댓글 작성 완료");
//    }

    // 유저 댓글 작성 여부 조회(중복 방지용) 테스트
    @Test
    public void testUserCommented() {
        EventCommentVO commentVO = new EventCommentVO();
        commentVO.setEventId(1L);
        commentVO.setMemberId(1L);
        int result = eventMapper.userCommented(commentVO);
        log.info("댓글 작성 여부: {}", result);
    }

    // 댓글 좋아요 테스트
//    @Test
//    public void testInsertEventCommentLike() {
//        EventCommentLikeVO likeVO = new EventCommentLikeVO();
//        likeVO.setEventCommentId(1L);
//        likeVO.setMemberId(999L);
//        eventMapper.insertEventCommentLike(likeVO);
//        log.info("댓글 좋아요 등록");
//    }

    // 댓글 좋아요 취소 테스트
    @Test
    public void testDeleteEventCommentLike() {
        EventCommentLikeVO likeVO = new EventCommentLikeVO();
        likeVO.setEventCommentId(1L);
        likeVO.setMemberId(999L);
        eventMapper.deleteEventCommentLike(likeVO);
        log.info("댓글 좋아요 취소");
    }

    // 댓글 좋아요 여부 테스트
    @Test
    public void testIsEventCommentLiked() {
        EventCommentLikeVO likeVO = new EventCommentLikeVO();
        likeVO.setEventCommentId(1L);
        likeVO.setMemberId(1L);
        int result = eventMapper.isEventCommentLiked(likeVO);
        log.info("댓글 좋아요 여부: {}", result);
    }

    // 댓글 좋아요 수 조회 테스트
    @Test
    public void testCountEventCommentLike() {
        int count = eventMapper.countEventCommentLike(1L);
        log.info("댓글 졸아요 수 : {}", count);
    }

    // 기상 챌린지 05~06시 테스트
    @Test
    public void testWakeUpChallengeTime() {
        int result = eventMapper.wakeUpChallengeTime();
        log.info("현재 시간 05~06시 여부 (1이면 맞음): {}", result);
    }

    // 힐링데이 베스트 댓글 테스트
    @Test
    public void testSelectBestComments() {
        List<EventCommentViewDTO> comments = eventMapper.selectBestComments(1L);
        log.info("힐링데이 베스트 댓글 수 : {}", comments.size());
    }

    // 루틴 공유 이벤트 조건 테스트
    @Test
    public void testSelectRouotineComments() {
        List<EventCommentVO> comments = eventMapper.selectRoutineComments(1L);
        log.info("루틴 공유 댓글 수: {}", comments.size());
    }
}
