package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class BoardCommentLikeVO {
    private Long id;
    private Date boardCommentLikeCreateDate;
    private Long memberId;
    private Long boardCommentId;
}
