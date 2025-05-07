package com.app.personalbuddyback.DyunDyun.mapper.board;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.mapper.BoardMapper;
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
public class BoardMapperTests {

    @Autowired
    private BoardMapper boardMapper;

    //    게시글 전체 목록 조회 테스트
    @Test
    public void testSelectBoardList() {
        List<BoardListViewDTO> boardListDTO = boardMapper.selectBoardList();

        if (boardListDTO == null || boardListDTO.isEmpty()) {
            log.info("조회된 게시글이 없습니다.");
        } else {
            for (BoardListViewDTO dto : boardListDTO) {
                log.info("게시글: {}", dto);
            }
        }
    }

    // HOT 게시글 목록 조회 테스트
    @Test
    public void testSelectHotBoardList() {
        List<BoardListViewDTO> hotBoardList = boardMapper.selectHotBoardList();

        if (hotBoardList == null || hotBoardList.isEmpty()) {
            log.info("HOT 게시글이 없습니다.");
        } else {
            log.info("HOT 게시글 목록:");
            for (BoardListViewDTO dto : hotBoardList) {
                log.info("제목: {}, 좋아요 수: {}", dto.getBoardTitle(), dto.getBoardLikeCount());
            }
        }
    }

    //  게시글 검색 + 정렬 + 해시태그 필터링
    @Test
    public void testSelectBoardListBySearch() {
        Map<String, Object> params = new HashMap<>();
        params.put("keywords", List.of("테스트"));
        params.put("hashtags", List.of("#테스트"));
        params.put("sort", "recent");
        List<BoardListViewDTO> result = boardMapper.selectBoardListBySearch(params);
        if (result.isEmpty()) {
            log.info("검색 결과 없음");
        } else {
            for (BoardListViewDTO dto : result) {
                log.info("검색 결과 게시글 제목: {}", dto.getBoardTitle());
            }
        }
    }

    // 게시글 1개 조회 (상세보기) 테스트
    @Test
    public void testSelectPostById() {
        Long boardId = 999L;
        BoardViewDTO post = boardMapper.selectBoardById(boardId).orElse(null);

        if (post != null) {
            log.info("제목: " + post.getBoardTitle());
        } else {
            log.info("게시글이 없습니다.");
        }
    }

    // 게시글 작성 테스트
    @Test
    public void 게시글_작성() {
        BoardVO post = new BoardVO();
        post.setBoardTitle("테스트 제목");
        post.setBoardContent("테스트 내용.");
        post.setBoardHashtag("#테스트");
        post.setMemberId(999L);
        boardMapper.insertBoard(post);
        log.info("게시글 작성 완료");
    }

    // 게시글 수정 테스트
    @Test
    public void testUpdatePost() {
        BoardVO post = new BoardVO();
        post.setId(999L);
        post.setBoardTitle("수정된 제목");
        post.setBoardContent("수정된 내용");
        post.setBoardHashtag("#수정");
        boardMapper.updateBoard(post);
        log.info("게시글 수정 완료");
    }

    // 게시글 삭제 테스트
//    @Test
//    public void testDeletePost() {
//        boardMapper.deleteBoard(999L);
//        log.info("게시글 삭제 완료");
//    }

    // 게시글 이미지 등록 테스트
    @Test
    public void testInsertPostImage() {
        BoardImgVO image = new BoardImgVO();
        image.setBoardImgName("test.png");
        image.setBoardImgPath("/images");
        image.setBoardId(999L);

        boardMapper.insertBoardImage(image);
        log.info("이미지 등록 완료");
    }

    // 게시글 이미지 전체 삭제 테스트
    @Test
    public void testDeleteAllPostImages() {
        boardMapper.deleteBoardImages(1L);
        log.info("이미지 전체 삭제 완료");
    }

    // 게시글 이미지 단일 삭제 테스트
    @Test
    public void testDeletePostImageById() {
        boardMapper.deleteBoardImageById(1L);
        log.info("이미지 1개 삭제 완료");
    }

    // 게시글 조회수 증가 테스트
    @Test
    public void testIncreasePostViews() {
        boardMapper.updateBoardViews(1L);
        log.info("조회수 1 증가 완료");
    }

    // 게시글 좋아요 추가 테스트
    @Test
    public void testInsertPostLike() {
        BoardLikeVO like = new BoardLikeVO();
        like.setBoardId(999L);
        like.setMemberId(999L);

        boardMapper.insertBoardLike(like);
        log.info("좋아요 추가 완료");
    }

    // 게시글 좋아요 삭제 테스트
    @Test
    public void testDeletePostLike() {
        BoardLikeVO like = new BoardLikeVO();
        like.setBoardId(1L);
        like.setMemberId(1L);

        boardMapper.deleteBoardLike(like);
        log.info("좋아요 삭제 완료");
    }

    // 게시글 좋아요 여부 확인 테스트
    @Test
    public void testCheckPostLike() {
        BoardLikeVO like = new BoardLikeVO();
        like.setBoardId(1L);
        like.setMemberId(1L);
        int result = boardMapper.checkBoardLike(like);
        if (result > 0) {
            log.info("좋아요 누른 상태");
        } else {
            log.info("좋아요 안 누름");
        }
    }

    // 댓글 목록 조회 테스트
    @Test
    public void testSelectCommentList() {
        Map<String, Object> params = new HashMap<>();
        params.put("boardId", 1L);
        params.put("sort", "like");
        List<BoardCommentViewDTO> comments = boardMapper.selectBoardCommentsByBoardId(params);
        if (comments == null || comments.isEmpty()) {
            log.info("댓글이 없습니다.");
            return;
        }
        for (BoardCommentViewDTO comment : comments) {
            log.info("댓글 내용: " + comment.getBoardCommentContent());
        }
    }

    // 댓글 작성 테스트
//    @Test
//    public void testInsertComment() {
//        BoardCommentVO comment = new BoardCommentVO();
//        comment.setBoardId(999L);
//        comment.setMemberId(999L);
//        comment.setBoardCommentContent("댓글 내용");
//
//        boardMapper.insertBoardComment(comment);
//        log.info("댓글 등록 완료");
//    }

    // 댓글 수정 테스트
    @Test
    public void testUpdateComment() {
        BoardCommentVO comment = new BoardCommentVO();
        comment.setId(1L);
        comment.setBoardCommentContent("수정된 댓글");
        boardMapper.updateBoardComment(comment);
        log.info("댓글 수정 완료");
    }

    // 댓글 삭제 테스트
    @Test
    public void testDeleteComment() {
        boardMapper.deleteBoardComment(1L);
        log.info("댓글 삭제 완료");
    }

    // 댓글 좋아요 추가 테스트
//    @Test
//    public void testInsertCommentLike() {
//        BoardCommentLikeVO like = new BoardCommentLikeVO();
//        like.setBoardCommentId(999L);
//        like.setMemberId(999L);
//        boardMapper.insertBoardCommentLike(like);
//        log.info("댓글 좋아요 완료");
//    }

    // 댓글 좋아요 삭제 테스트
    @Test
    public void testDeleteCommentLike() {
        BoardCommentLikeVO like = new BoardCommentLikeVO();
        like.setBoardCommentId(1L);
        like.setMemberId(1L);
        boardMapper.deleteBoardCommentLike(like);
        log.info("댓글 좋아요 취소 완료");
    }

    // 댓글 좋아요 여부 확인 테스트
    @Test
    public void testCheckCommentLike() {
        BoardCommentLikeVO like = new BoardCommentLikeVO();
        like.setBoardCommentId(1L);
        like.setMemberId(1L);
        int result = boardMapper.checkBoardCommentLike(like);
        if (result > 0) {
            log.info("댓글 좋아요 누름");
        } else {
            log.info("댓글 좋아요 안 누름");
        }
    }
}
