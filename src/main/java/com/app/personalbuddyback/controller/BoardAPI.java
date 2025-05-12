package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.service.BoardCommentService;
import com.app.personalbuddyback.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;


@RestController
@RequiredArgsConstructor
@RequestMapping("/boards/api/*")
public class BoardAPI {

    private final BoardService boardService;
    private final BoardCommentService boardCommentService;

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
    @ApiResponse(responseCode = "200", description = "게시글 작성 성공")
    @PostMapping("/write")
    public ResponseEntity writeBoard(@RequestBody BoardVO boardVO) {
        try {
            boardService.writeBoard(boardVO);
            return ResponseEntity.ok("게시글 작성 성공");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("게시글 작성 실패: " + e.getMessage());
        }
    }

    // 게시글 이미지 등록
    @Operation(summary = "게시글 이미지 등록", description = "게시글 이미지 등록 API")
    @ApiResponse(responseCode = "200", description = "게시글 이미지 등록 성공")
    @PostMapping("/image")
    public void addBoardImage(@RequestBody BoardImgVO boardImgVO) {
        boardService.addBoardImage(boardImgVO);
    }

    //  게시글과 이미지들을 함께 등록 (트랜잭션 처리)
    @Operation(summary = "게시글+이미지 등록", description = "게시글+이미지 등록 API")
    @ApiResponse(responseCode = "200", description = "게시글+이미지 등록 성공")
    @PostMapping("/image-with-write")
    public void writeBoardImageWithWrite(@RequestBody List<BoardImgVO> boardImgVO, @RequestBody BoardVO boardVO) {
        boardService.writeBoardWithImages(boardVO, boardImgVO);
    }

    // 게시글 수정
    @Operation(summary = "게시글 수정", description = "게시글 수정 API")
    @ApiResponse(responseCode = "200", description = "게시글 수정 성공")
    @PutMapping("/post/edit")
    public void updateBoard(@RequestBody BoardVO boardVO) {
        boardService.updateBoard(boardVO);
    }

    // 게시글 이미지 삭제(전체)
    @Operation(summary = "게시글 이미지 전체 삭제", description = "게시글 이미지 전체 삭제 API")
    @ApiResponse(responseCode = "200", description = "게시글 이미지 전체 삭제 성공")
    @DeleteMapping("/images/{boardId}")
    public void deleteAllImages(@PathVariable("boardId") Long boardId) {
        boardService.removeAllBoardImages(boardId);
    }

    // 특정 이미지 1개 삭제
    @Operation(summary = "게시글 이미지 1개 삭제", description = "게시글 이미지 1개 삭제 API")
    @ApiResponse(responseCode = "200", description = "게시글 이미지 1개 삭제 성공")
    @DeleteMapping("/image/delete/{id}")
    public void deleteImageById(@PathVariable("id") Long id) {
        boardService.removeBoardImageById(id);
    }

    // 게시글 삭제
    @Operation(summary = "게시글 삭제", description = "게시글 삭제 API")
    @ApiResponse(responseCode = "200", description = "게시글 삭제 성공")
    @DeleteMapping("/post/delete/{id}")
    public void deleteBoard(@PathVariable("id") Long id) {
        boardService.removeBoard(id);
    }

    // 게시글 조회수 1 증가
    @Operation(summary = "게시글 조회수 증가", description = "게시글 조회수 증가 API")
    @PatchMapping("/post/increase/{id}")
    public void increaseView(@PathVariable("id") Long id) {
        boardService.increaseBoardViews(id);
    }

    // 게시글 좋아요 추가
    @Operation(summary = "게시글 좋아요 추가", description = "게시글 좋아요 추가 API")
    @ApiResponse(responseCode = "200", description = "게시글 좋아요 추가 성공")
    @PostMapping("/post/like")
    public void likeBoard(@RequestBody BoardLikeVO boardLikeVO) {
        boardService.increaseLikeBoard(boardLikeVO);
    }

    // 게시글 좋아요 취소
    @Operation(summary = "게시글 좋아요 취소", description = "게시글 좋아요 취소 API")
    @ApiResponse(responseCode = "200", description = "게시글 좋아요 취소 성공")
    @DeleteMapping("/post/unLike")
    public void cancelLikeBoard(@RequestBody BoardLikeVO boardLikeVO) {
        boardService.deleteLikeBoard(boardLikeVO);
    }

    // 게시글 좋아요 여부 조회
    @Operation(summary = "게시글 좋아요 눌렀는지 여부 조회", description = "게시글 좋아요 눌렀는지 여부 조회 API")
    @PostMapping("/post/like-check")
    public int isLiked(@RequestBody BoardLikeVO boardLikeVO) {
        return boardService.isBoardLiked(boardLikeVO);
    }

    // 댓글 전체 목록
    @Operation(summary = "댓글 전체 목록", description = "댓글 전체 목록 API")
    @GetMapping("/post/comment/list")
    public List<BoardCommentViewDTO> getComments(@RequestParam Map<String, Object> params) {
        return boardCommentService.getBoardComments(params);
    }

    // 댓글 작성
    @Operation(summary = "댓글 작성", description = "댓글 작성 API")
    @ApiResponse(responseCode = "200", description = "댓글 작성 성공")
    @PostMapping("/post/comment/write")
    public void writeComment(@RequestBody BoardCommentVO commentVO) {
        boardCommentService.writeComment(commentVO);
    }

    // 댓글 수정
    @Operation(summary = "댓글 수정", description = "댓글 수정 API")
    @ApiResponse(responseCode = "200", description = "댓글 수정 성공")
    @PutMapping("/post/comment/edit")
    public void updateComment(@RequestBody BoardCommentVO commentVO) {
        boardCommentService.updateComment(commentVO);
    }

    // 댓글 삭제
    @Operation(summary = "댓글 삭제", description = "댓글 삭제 API")
    @ApiResponse(responseCode = "200", description = "게시글 삭제 성공")
    @DeleteMapping("/post/comment/delete/{id}")
    public void deleteComment(@PathVariable("id") Long id) {
        boardCommentService.deleteComment(id);
    }

    // 댓글 좋아요
    @Operation(summary = "댓글 좋아요 추가", description = "댓글 좋아요 추가 API")
    @ApiResponse(responseCode = "200", description = "댓글 좋아요 성공")
    @PostMapping("/post/comment/like")
    public void likeComment(@RequestBody BoardCommentLikeVO likeVO) {
        boardCommentService.likeComment(likeVO);
    }

    // 댓글 좋아요 취소
    @Operation(summary = "댓글 좋아요 취소", description = "댓글 좋아요 취소 API")
    @ApiResponse(responseCode = "200", description = "댓글 좋아요 취소 성공")
    @DeleteMapping("/post/comment/unlike")
    public void cancelLikeComment(@RequestBody BoardCommentLikeVO likeVO) {
        boardCommentService.deleteLikeComment(likeVO);
    }

    // 댓글 좋아요 여부
    @Operation(summary = "댓글 좋아요 눌렀는지 여부", description = "댓글 좋아요 눌렀는지 여부 API")
    @PostMapping("/post/comment/like-check")
    public int isCommentLiked(@RequestBody BoardCommentLikeVO likeVO) {
        return boardCommentService.isBoardCommentLiked(likeVO);
    }

    // 베스트 댓글 3개
    @Operation(summary = "베스트 댓글 3개", description = "베스트 댓글 3개 API")
    @GetMapping("/post/comment/best/{boardId}")
    public List<BoardCommentViewDTO> getBestComments(@PathVariable("boardId") Long boardId) {
        return boardCommentService.getBestCommentsByBoardId(boardId);
    }

    // 마이페이지 - 내가 쓴 게시글 목록
    @Operation(summary = "내가 쓴 게시글 목록(마이페이지)", description = "내가 쓴 게시글 목록(마이페이지) API")
    @GetMapping("/mypage/posts/{memberId}")
    public List<BoardListViewDTO> getMyBoards(@PathVariable("memberId") Long memberId) {
        return boardService.getBoardsByMemberId(memberId);
    }

    // 마이페이지 - 내가 쓴 댓글 목록
    @Operation(summary = "내가 쓴 댓글 목록(마이페이지)", description = "내가 쓴 댓글 목록(마이페이지) API")
    @GetMapping("/mypage/comments/{memberId}")
    public List<BoardCommentViewDTO> getMyComments (@PathVariable("memberId") Long memberId){
        return boardCommentService.getCommentsByMemberId(memberId);
    }
}