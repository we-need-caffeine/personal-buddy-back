package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.EventListViewDTO;
import com.app.personalbuddyback.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event/*")
public class EventAPI {

    private final EventService eventService;

    // 진행 중 이벤트 3개 (슬라이드배너)
    @GetMapping("current")
    public List<EventListViewDTO> getCurrentEvents() {
        return eventService.getCurrentEvents();
    }

    // 커밍순 이벤트 리스트

    // 참여 가능한 이벤트 리스트

    // 이벤트 상세 조회

    // 조회수 증가

    // 이벤트 참여

    // 이벤트 참여 여부 확인

    // 좋아요 추가

    // 좋아요 취소

    // 좋아요 여부

    // 댓글 목록

    // 댓글 작성

    // 댓글 중복 확인

    // 댓글 좋아요

    // 댓글 좋아요 취소

    // 댓글 좋아요 여부

    // 기상 챌린지 시간

    // 힐링데이 베스트 댓글

    // 루틴 공유 조건 댓글

}
