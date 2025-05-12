package com.app.personalbuddyback.DyunDyun.mapper.event;

import com.app.personalbuddyback.domain.EventCommentLikeVO;
import com.app.personalbuddyback.domain.EventCommentVO;
import com.app.personalbuddyback.domain.EventCommentViewDTO;
import com.app.personalbuddyback.mapper.EventCommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class EventCommentMapperTests {

    @Autowired
    private EventCommentMapper eventCommentMapper;

    // 게시글 댓글 전체 목록 테스트
    @Test
    public void testEventCommentList(){
        List<EventCommentViewDTO> comments = eventCommentMapper.selectEventCommentsByEventId(1L,"like");
        log.info("댓글 수 : {}", comments.size());
    }

    // 댓글 작성 테스트
//    @Test
//    public void testInsertEventComment() {
//        EventCommentVO commentVO = new EventCommentVO();
//        commentVO.setEventId(1L);
//        commentVO.setMemberId(999L);
//        commentVO.setEventCommentDescription("댓글 테스트");
//        eventCommentMapper.insertEventComment(commentVO);
//        log.info("댓글 작성 완료");
//    }

    // 유저 댓글 작성 여부 조회(중복 방지용) 테스트
    @Test
    public void testUserCommented() {
        EventCommentVO commentVO = new EventCommentVO();
        commentVO.setEventId(1L);
        commentVO.setMemberId(1L);
        int result = eventCommentMapper.userCommented(commentVO);
        log.info("댓글 작성 여부: {}", result);
    }

    // 댓글 좋아요 테스트
//    @Test
//    public void testInsertEventCommentLike() {
//        EventCommentLikeVO likeVO = new EventCommentLikeVO();
//        likeVO.setEventCommentId(1L);
//        likeVO.setMemberId(1L);
//        eventCommentMapper.insertEventCommentLike(likeVO);
//        log.info("댓글 좋아요 등록");
//    }

    // 댓글 좋아요 취소 테스트
    @Test
    public void testDeleteEventCommentLike() {
        EventCommentLikeVO likeVO = new EventCommentLikeVO();
        likeVO.setEventCommentId(1L);
        likeVO.setMemberId(999L);
        eventCommentMapper.deleteEventCommentLike(likeVO);
        log.info("댓글 좋아요 취소");
    }

    // 댓글 좋아요 여부 테스트
    @Test
    public void testIsEventCommentLiked() {
        EventCommentLikeVO likeVO = new EventCommentLikeVO();
        likeVO.setEventCommentId(1L);
        likeVO.setMemberId(1L);
        int result = eventCommentMapper.isEventCommentLiked(likeVO);
        log.info("댓글 좋아요 여부: {}", result);
    }

    // 댓글 좋아요 수 조회 테스트
    @Test
    public void testCountEventCommentLike() {
        int count = eventCommentMapper.countEventCommentLike(1L);
        log.info("댓글 졸아요 수 : {}", count);
    }

    // 힐링데이 베스트 댓글 테스트
    @Test
    public void testSelectBestComments() {
        List<EventCommentViewDTO> comments = eventCommentMapper.selectBestComments(1L);
        log.info("힐링데이 베스트 댓글 수 : {}", comments.size());
    }

    @Test
    public void testSelectRoutineComments() {
        List<EventCommentViewDTO> comments = eventCommentMapper.selectRoutineComments(1L);
        log.info("루틴 공유 댓글 수: {}", comments.size());
    }
}
