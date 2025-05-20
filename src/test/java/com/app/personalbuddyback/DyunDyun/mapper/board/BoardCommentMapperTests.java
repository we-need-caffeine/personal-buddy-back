package com.app.personalbuddyback.DyunDyun.mapper.board;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.mapper.BoardCommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class BoardCommentMapperTests {

    @Autowired
    private BoardCommentMapper boardCommentMapper;

    // 댓글 목록 조회 테스트
    @Test
    public void testSelectCommentList() {
        Map<String, Object> params = new HashMap<>();
        params.put("boardId", 1L);
        params.put("sort", "like");
        List<BoardCommentViewDTO> comments = boardCommentMapper.selectBoardCommentsByBoardId(1L);
        if (comments == null || comments.isEmpty()) {
            log.info("댓글이 없습니다.");
            return;
        }
        for (BoardCommentViewDTO comment : comments) {
            log.info("댓글 내용: " + comment.getBoardCommentContent());
        }
    }

    // 댓글 작성 테스트
    @Test
    public void testInsertComment() {
        BoardCommentVO comment = new BoardCommentVO();
        comment.setBoardId(1L);
        comment.setMemberId(1L);
        comment.setBoardCommentContent("댓글 내용");

        boardCommentMapper.insertBoardComment(comment);
        log.info("댓글 등록 완료");
    }

    // 댓글 수정 테스트
    @Test
    public void testUpdateComment() {
        BoardCommentVO comment = new BoardCommentVO();
        comment.setId(1L);
        comment.setBoardCommentContent("수정된 댓글");
        boardCommentMapper.updateBoardComment(comment);
        log.info("댓글 수정 완료");
    }

    // 댓글 삭제 테스트
//    @Test
//    public void testDeleteComment() {
//        boardCommentMapper.deleteBoardComment(1L);
//        log.info("댓글 삭제 완료");
//    }

    // 댓글 좋아요 추가 테스트
    @Test
    public void testInsertCommentLike() {
        BoardCommentLikeVO like = new BoardCommentLikeVO();
        like.setBoardCommentId(999L);
        like.setMemberId(999L);
        boardCommentMapper.insertBoardCommentLike(like);
        log.info("댓글 좋아요 완료");
    }

    // 댓글 좋아요 삭제 테스트
    @Test
    public void testDeleteCommentLike() {
        BoardCommentLikeVO like = new BoardCommentLikeVO();
        like.setBoardCommentId(1L);
        like.setMemberId(1L);
        boardCommentMapper.deleteBoardCommentLike(like);
        log.info("댓글 좋아요 취소 완료");
    }

    // 댓글 좋아요 여부 확인 테스트
    @Test
    public void testCheckCommentLike() {
        BoardCommentLikeVO like = new BoardCommentLikeVO();
        like.setBoardCommentId(1L);
        like.setMemberId(1L);
        int result = boardCommentMapper.checkBoardCommentLike(like);
        if (result > 0) {
            log.info("댓글 좋아요 누름");
        } else {
            log.info("댓글 좋아요 안 누름");
        }
    }
}
