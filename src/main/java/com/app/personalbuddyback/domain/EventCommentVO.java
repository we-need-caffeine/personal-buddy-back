package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class EventCommentVO {
    private Long id;
    private String eventCommentDescription;
    private Date eventCommentCreateDate;
    private Long eventId;
    private Long memberId;
}
