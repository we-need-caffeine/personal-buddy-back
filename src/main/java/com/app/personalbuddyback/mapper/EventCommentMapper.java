package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.EventCommentLikeVO;
import com.app.personalbuddyback.domain.EventCommentVO;
import com.app.personalbuddyback.domain.EventCommentViewDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EventCommentMapper {

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

    // 힐링데이 베스트 댓글
    public List<EventCommentViewDTO> selectBestComments(Long eventId);

    // 루틴 공유 조건 만족 댓글
    public List<EventCommentViewDTO> selectRoutineComments(Long eventId);

//    <!-- 힐링데이: 좋아요 기준 Top 3 memberId 추출 -->
    public List<Long> selectTop3MemberIdsByLikes(Long eventId);

}
