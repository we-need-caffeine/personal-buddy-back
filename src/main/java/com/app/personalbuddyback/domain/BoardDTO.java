package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class BoardDTO {
    private int id;
    private String boardTitle;
    private String boardContent;
    private Long boardContentViews;
    private Date boardContentCreateDate;
    private Date boardContentUpdateDate;
    private Long boardLikeCount;
    private Long boardCommentCount;
    private String boardHashtag;
    private String boardImgPath;
    private String boardImgName;
    private Long memberId;
    private String memberEmail;
    private String memberNickname;
    private String memberImgPath;
    private String memberImgName;

    private Long boardId;         // 게시글 ID
}
