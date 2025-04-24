package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class EventCommentLikeVO {
    private Long id;
    private Date eventCommentLikeCreateDate;
    private Long eventCommentId;
    private Long memberId;
}
