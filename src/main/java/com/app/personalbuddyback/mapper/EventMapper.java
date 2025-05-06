package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EventMapper {

    // 진행중 이벤트 3개(슬라이드배너)
    public List<EventListViewDTO> selectCurrentEvents();

    // 커밍순 이벤트
    public List<EventListViewDTO> selectComingEvents();

    // 참여 가능한 이벤트
    public List<EventListViewDTO> selectAvailableEvents();

    // 이벤트 참여 등록
    public void insertEventJoin(EventJoinVO eventJoinVO);

    // 이벤트 참여 여부 조회
    public int isJoined(@Param("eventId") Long eventId, @Param("memberId") Long memberId);

    // 이벤트 상세 조회
    public EventViewDTO selectEventDetail(Long id);

    // 조회수 1 증가
    public void updateEventView(Long id);

    // 게시글 좋아요 추가
    public void insertEventLike(EventLikeVO eventLikeVO);

    // 게시글 좋아요 취소
    public void deleteEventLike(EventLikeVO eventLikeVO);

    // 게시글 좋아요 여부 조회
    public int isEventLiked(EventLikeVO eventLikeVO);

    // 게시글 좋아요 수 조회
    public int countEventLike(Long eventId);

    // 게시글 댓글 전체 목록
    public List<EventCommentViewDTO> selectEventCommentsByEventId(@Param("eventId") Long eventId, @Param("sort") String sort);

    // 댓글 작성
    public void insertEventComment(EventCommentVO eventCommentVO);

    // 유저 댓글 작성 여부 조회(중복 방지용)
    public int userCommented(EventCommentVO eventCommentVO);

    // 댓글 좋아요
    public void insertEventCommentLike(EventCommentLikeVO eventCommentLikeVO);

    // 댓글 좋아요 취소
    public void deleteEventCommentLike(EventCommentLikeVO eventCommentLikeVO);

    // 댓글 좋아요 여부
    public int isEventCommentLiked(EventCommentLikeVO eventCommentLikeVO);

    // 댓글 좋아요 수 조회
    public int countEventCommentLike(Long eventCommentId);

    // 기상 챌린지 05~06시
    public int wakeUpChallengeTime();

    // 힐링데이 베스트 댓글
    public List<EventCommentViewDTO> selectBestComments(Long eventId);

    // 루틴 공유 이벤트 조건
    public List<EventCommentVO> selectRoutineComments(Long eventId);
}
