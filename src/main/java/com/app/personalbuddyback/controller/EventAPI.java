package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.service.EventCommentService;
import com.app.personalbuddyback.service.EventParticipationService;
import com.app.personalbuddyback.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/events/api/*")
public class EventAPI {

    private final EventService eventService;
    private final EventCommentService eventCommentService;
    private final EventParticipationService eventParticipationService;


    // --------------------이벤트 리스트--------------------
    // 진행 중 이벤트 3개 (슬라이드배너)
    @Operation(summary = "진행 중 이벤트 3개 (슬라이드배너)", description = "진행 중 이벤트 3개 (슬라이드배너) API")
    @GetMapping("/current")
    public List<EventListViewDTO> getCurrentEvents() {
        return eventService.getCurrentEvents();
    }

    // 커밍순 이벤트 리스트
    @Operation(summary = "커밍순 이벤트 리스트", description = "커밍순 이벤트 리스트 API")
    @GetMapping("/coming")
    public List<EventListViewDTO> getComingEvents() {
        return eventService.getComingEvents();
    }

//    // 참여 가능한 이벤트 리스트
//    @Operation(summary = "참여 가능한 이벤트 리스트", description = "참여 가능한 이벤트 리스트 API")
//    @GetMapping("/available")
//    public List<EventListViewDTO> getAvailableEvents() {
//        return eventService.getAvailableEvents();
//    }



    // 이벤트 상세 조회(조회수 자동 증가)
    @Operation(summary = "이벤트 상세 조회(조회수 자동 증가)", description = "이벤트 상세 조회(조회수 자동 증가) API")
    @GetMapping("/detail/{id}")
    public EventViewDTO getEventDetail(@PathVariable Long id) {
        eventService.increaseEventViews(id);
        return eventService.getEventDetail(id).orElse(null);
    }

    // 좋아요 추가
    @Operation(summary = "좋아요 추가", description = "좋아요 추가 API")
    @PostMapping("/like")
    public void likeEvent(@RequestBody EventLikeVO likeVO) {
        eventService.likeEvent(likeVO);
    }

    // 좋아요 취소
    @Operation(summary = "좋아요 취소", description = "좋아요 취소 API")
    @DeleteMapping("/un-like")
    public void unlikeEvent(@RequestBody EventLikeVO likeVO) {
        eventService.deleteLikeEvent(likeVO);
    }

    // 좋아요 여부
    @Operation(summary = "좋아요 여부", description = "좋아요 여부 API")
    @PostMapping("/like-check")
    public boolean isEventLiked(@RequestBody EventLikeVO likeVO) {
        return eventService.isEventLiked(likeVO);
    }

    // 해당 이벤트의 좋아요 수 조회
    @Operation(summary = "해당 이벤트의 좋아요 수 조회", description = "해당 이벤트의 좋아요 수 조회 API")
    @GetMapping("/like-count/{eventId}")
    public int getEventLikeCount(@PathVariable Long eventId) {
        return eventService.getEventLikeCount(eventId);
    }

    // --------------------이벤트 댓글--------------------
    // 댓글 목록
    @Operation(summary = "댓글 목록", description = "댓글 목록 API")
    @GetMapping("/comment/list")
    public List<EventCommentViewDTO> getEventComments(@RequestParam Map<String, Object> params) {
        return eventCommentService.getEventComments(params);
    }

    // 댓글 작성
    @Operation(summary = "댓글 작성", description = "댓글 작성 API")
    @PostMapping("/comment/write")
    public void writeComment(@RequestBody EventCommentVO commentVO) {
        eventCommentService.writeEventComment(commentVO);
    }

    // 댓글 중복 확인
    @Operation(summary = "댓글 중복 확인", description = "댓글 중복 확인 API")
    @PostMapping("/comment/check")
    public boolean isCommented(@RequestBody EventCommentVO commentVO) {
        return eventCommentService.isEventCommented(commentVO);
    }

    // 댓글 좋아요
    @Operation(summary = "댓글 좋아요", description = "댓글 좋아요 API")
    @PostMapping("/comment/like")
    public void likeComment(@RequestBody EventCommentLikeVO likeVO) {
        eventCommentService.likeEventComment(likeVO);
    }

    // 댓글 좋아요 취소
    @Operation(summary = "댓글 좋아요 취소", description = "댓글 좋아요 취소 API")
    @DeleteMapping("/comment/un-like")
    public void unlikeComment(@RequestBody EventCommentLikeVO likeVO) {
        eventCommentService.deleteLikeEventComment(likeVO);
    }

    // 댓글 좋아요 여부
    @Operation(summary = "댓글 좋아요 여부", description = "댓글 좋아요 여부 API")
    @PostMapping("/comment/like-check")
    public boolean isCommentLiked(@RequestBody EventCommentLikeVO likeVO) {
        return eventCommentService.isEventCommentLiked(likeVO);
    }

    // 힐링데이 베스트 댓글
    @Operation(summary = "힐링데이 베스트 댓글", description = "힐링데이 베스트 댓글 API")
    @GetMapping("/comment/best/{eventId}")
    public List<EventCommentViewDTO> getBestComments(@PathVariable Long eventId) {
        return eventCommentService.getBestComments(eventId);
    }

    // 루틴 공유 조건 댓글
    @Operation(summary = "루틴 공유 조건 댓글", description = "루틴 공유 조건 댓글 API")
    @GetMapping("/comment/routine/{eventId}")
    public List<EventCommentViewDTO> getRoutineComments(@PathVariable Long eventId) {
        return eventCommentService.getRoutineComments(eventId);
    }

    // --------------------이벤트 참여--------------------
    // 이벤트 참여 등록
    @Operation(summary = "이벤트 참여 등록", description = "이벤트 참여 등록 API")
    @PostMapping("/join")
    public void joinEvent(@RequestBody EventJoinVO joinVO) {
        eventParticipationService.joinEvent(joinVO);
    }

    // 이벤트 참여 여부 확인
    @Operation(summary = "이벤트 참여 여부 확인", description = "이벤트 참여 여부 확인 API")
    @GetMapping("/join-check")
    public boolean isJoined(@RequestParam Long eventId, @RequestParam Long memberId) {
        return eventParticipationService.isJoined(eventId, memberId);
    }

    // 현재 시간이 기상 챌린지 조건(05:00~06:00)인지 확인
    @Operation(summary = "기상 챌린지 조건 확인", description = "기상 챌린지 조건 확인 API")
    @GetMapping("/wake-up-time")
    public boolean isWakeUpTime() {
        return eventParticipationService.isWakeUpTime();
    }

    // --------------------이벤트 관리자--------------------
    // 이벤트 등록
    @Operation(summary = "이벤트 등록", description = "이벤트를 등록 API")
    @PostMapping("/register")
    public void registerEvent(@RequestBody EventVO eventVO) {
        eventService.registerEvent(eventVO);
    }

    // 이벤트 수정
    @Operation(summary = "이벤트 수정", description = "이벤트 정보를 수정 API")
    @PutMapping("/update")
    public void updateEvent(@RequestBody EventVO eventVO) {
        eventService.updateEvent(eventVO);
    }

    // 이벤트 삭제
    @Operation(summary = "이벤트 삭제", description = "ID를 기준으로 이벤트 삭제 API")
    @DeleteMapping("/delete/{id}")
    public void deleteEvent(@PathVariable Long id) {
        eventService.deleteEvent(id);
    }

}
