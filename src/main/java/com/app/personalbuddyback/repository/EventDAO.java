package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.EventListViewDTO;
import com.app.personalbuddyback.mapper.EventMapper;
import com.app.personalbuddyback.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class EventDAO {

    private final EventMapper eventMapper;

    // 진행중 이벤트 3개(슬라이드 배너)
    public List<EventListViewDTO> findSurrentEventList() {
        return eventMapper.selectCurrentEvents();
    }

    // 커밍순 이벤트 리스트
    public List<EventListViewDTO> findComingEvent() {
        return eventMapper.selectComingEvents();
    }

    // 참여 가능한 이벤트 리스트
    public List<EventListViewDTO> findAvailableEvent() {
        return eventMapper.selectAvailableEvents();
    }

    // 이벤트 참여 등록

    // 이벤트 참여 여부 조회

    // 이벤트 상세 조회

    // 조회수 1 증가

    // 게시글 좋아요 추가

    // 게시글 좋아요 취소

    // 게시글 좋아요 여부 조회

    // 게시글 좋아요 수 조회

    // 게시글 댓글 전체 목록

    // 댓글 작성

    // 유저 댓글 작성 여부 조회(중복 방지용)

    // 댓글 좋아요

    // 댓글 좋아요 취소

    // 댓글 좋아요 여부

    // 댓글 좋아요 수 조회

    // 기상 이벤트 시간 조건

    // 힐링데이 이벤트 베스트 댓글 TOP3

    // 루틴 공유 이벤트 조건

}
