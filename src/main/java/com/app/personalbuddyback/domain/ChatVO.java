package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class ChatVO {
    private Long id;
    private String chatContent;
    private Date chatSendTime;
    private Integer chatReading;
    private Integer chatVisible;
    private Long chatWriterMemberId;
    private Long chatRoomId;
}

