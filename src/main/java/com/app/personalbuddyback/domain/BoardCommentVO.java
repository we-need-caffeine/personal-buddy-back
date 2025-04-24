package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class BoardCommentVO {
    private Long id;
    private String boardCommentContent;
    private Date boardCommentCreateDate;
    private Long memberId;
    private Long boardId;
}
