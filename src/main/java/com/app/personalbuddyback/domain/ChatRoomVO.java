package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data
@Component
public class ChatRoomVO {
    private Long id;
    private Date chatRoomCreateTime;
    private int chatRoomFix;
    private int chatRequest;
    private Long firstMemberId;
    private Long secondMemberId;
}
