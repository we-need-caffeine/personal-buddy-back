// 보드 메인 리스트

package com.app.personalbuddyback.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class BoardListViewDTO {
    private Long id; // 게시글 ID
    private String boardTitle; // 게시글 제목
    private String boardContent; // 게시글 내용
    private String boardImgPath; // 게시글 이미지 경로
    private String boardImgName; // 게시글 이미지 이름
    private String boardHashtag; // 게시글 해시태그
    private String boardContentViews; // 게시글 조회수
    private Long boardLikeCount; // 게시글 좋아요
    private Date boardCreateDate; // 게시글 작성일+시간
    private int boardCommentCount; // 댓글수
    private Long memberId; // 게시글 작성자 ID
    private String memberNickName; // 멤버 닉네임
    private String memberImgPath; // 게시글 작성자 프로필 이미지 경로
    private String memberImgName; // 게시글 작성자 프로필 이미지 이름

}
