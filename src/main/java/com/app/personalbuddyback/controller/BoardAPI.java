package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards/api/*")
public class BoardAPI {

    private final BoardService boardService;

    // 게시글 전체 목록
    @Operation(summary = "게시글 전체 목록", description = "게시글 전체 목록 API")
    @GetMapping("/list")
    public List<BoardListViewDTO> getAllBoards() {
        return boardService.getAllBoards();
    }

    // HOT 게시글 목록
    @Operation(summary = "HOT 게시글 목록", description = "HOT 게시글 목록 API")
    @GetMapping("/hot")
    public List<BoardListViewDTO> getHotBoards() {
        return boardService.getHotBoards();
    }

    // 게시글 검색 + 정렬 + 해시태그 필터링
    @Operation(summary = "게시글 검색 + 정렬 + 해시태그 필터링", description = "게시글 검색 + 정렬 + 해시태그 필터링 API")
    @GetMapping("/search")
    public List<BoardListViewDTO> getBoardsBySearch(@RequestParam Map<String, Object> params) {
        return boardService.getBoardsBySearch(params);
    }

    // 게시글 상세 조회
    @Operation(summary = "게시글 상세 조회", description = "게시글 상세 조회 API")
    @GetMapping("/post/{id}")
    public BoardViewDTO getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id).orElseThrow();
    }

    // 게시글 작성
    @Operation(summary = "게시글 작성", description = "게시글 작성 API")
    @PostMapping("/write")
    public void writeBoard(@RequestBody BoardVO boardVO) {
        boardService.writeBoard(boardVO);
    }

    // 게시글 이미지 등록
    @PostMapping("/image")
    public void addBoardImage(@RequestBody BoardImgVO boardImgVO) {
        boardService.addBoardImage(boardImgVO);
    }

    //  게시글과 이미지들을 함께 등록 (트랜잭션 처리)
    @Operation(summary = "게시글+이미지 등록", description = "게시글+이미지 등록 API")
    @PostMapping("/image-with-write")
    public void writeBoardImageWithWrite(@RequestBody List<BoardImgVO> boardImgVO ,@RequestBody BoardVO boardVO) {
        boardService.writeBoardWithImages(boardVO, boardImgVO);
    }

    // 게시글 수정
    @PutMapping("/post/edit")
    public void updateBoard(@RequestBody BoardVO boardVO) {
        boardService.updateBoard(boardVO);
    }

    // 게시글 이미지 삭제(전체)
    @DeleteMapping("/images/{boardId}")
    public void deleteAllImages(@PathVariable("boardId") Long boardId) {
        boardService.removeAllBoardImages(boardId);
    }

    // 특정 이미지 1개 삭제
    @DeleteMapping("/image/delete/{id}")
    public void deleteImageById(@PathVariable("id") Long id) {
        boardService.removeBoardImageById(id);
    }

    // 게시글 삭제
    @DeleteMapping("/post/delete/{id}")
    public void deleteBoard(@PathVariable("id") Long id) {
        boardService.removeBoard(id);
    }

    // 게시글 조회수 1 증가
    @PatchMapping("/post/increase/{id}")
    public void increaseView(@PathVariable("id") Long id) {
        boardService.increaseBoardViews(id);
    }

    // 게시글 좋아요 추가
    @PostMapping("/post/like")
    public void likeBoard(@RequestBody BoardLikeVO boardLikeVO) {
        boardService.increaseLikeBoard(boardLikeVO);
    }

    // 게시글 좋아요 취소
    @DeleteMapping("/post/unLike")
    public void cancelLikeBoard(@RequestBody BoardLikeVO boardLikeVO) {
        boardService.deleteLikeBoard(boardLikeVO);
    }

    // 게시글 좋아요 여부 조회
    @PostMapping("/post/like-check")
    public int isLiked(@RequestBody BoardLikeVO boardLikeVO) {
        return boardService.isBoardLiked(boardLikeVO);
    }



    // 게시글 댓글 전체 목록
    @GetMapping("/post/comment/list")
    public List<BoardCommentViewDTO> getComments(@RequestParam Map<String, Object> params) {
        return boardService.getBoardComments(params);
    }

    // 댓글 작성
    @PostMapping("/post/comment/write")
    public void writeComment(@RequestBody BoardCommentVO commentVO) {
        boardService.writeComment(commentVO);
    }

    // 댓글 수정
    @PutMapping("/post/comment/edit")
    public void updateComment(@RequestBody BoardCommentVO commentVO) {
        boardService.updateComment(commentVO);
    }

    // 댓글 삭제
    @DeleteMapping("/post/comment/delete/{id}")
    public void deleteComment(@PathVariable("id") Long id) {
        boardService.deleteComment(id);
    }

    // 댓글 좋아요
    @PostMapping("/post/comment/like")
    public void likeComment(@RequestBody BoardCommentLikeVO likeVO) {
        boardService.likeComment(likeVO);
    }

    // 댓글 좋아요 취소
    @DeleteMapping("/post/comment/unlike")
    public void cancelLikeComment(@RequestBody BoardCommentLikeVO likeVO) {
        boardService.deleteLikeComment(likeVO);
    }

    // 댓글 좋아요 여부
    @PostMapping("/post/comment/like-check")
    public int isCommentLiked(@RequestBody BoardCommentLikeVO likeVO) {
        return boardService.isBoardCommentLiked(likeVO);
    }
}