// 보드 상세페이지 댓글

package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class BoardCommentViewDTO {
    private Long id; // 댓글 ID
    private String boardCommentContent; // 댓글 본문
    private Date boardCommentCreateDate; // 댓글 작성일+시간
    private Long memberId; // 댓글 작성자 ID
    private String memberNickName; // 멤버 닉네임
    private String memberImgPath; // 댓글 작성자 프로필 이미지 경로
    private String memberImgName; // 댓글 작성자 프로필 이미지 이름

    // 베스트 댓글
    private int boardCommentLikeCount; // 댓글 좋아요 수
    private Long boardId;                // 게시글 ID
    private String boardTitle;           // 게시글 제목
    private Date boardContentCreateDate;
}
