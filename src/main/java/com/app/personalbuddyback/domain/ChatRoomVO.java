package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class ChatRoomVO {
    private Long id;
    private Date chatRoomCreateTime;
    private String chatRoomLastChat;
    private Date chatRoomLastChatTime;
    private Integer firstMemberHide;
    private Integer secondMemberHide;
    private Long firstMemberId;
    private Long secondMemberId;
}
