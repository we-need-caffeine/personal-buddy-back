package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class ChatRoomViewDTO {
    private Long chatRoomId;
    private String chatRoomLastChat;
    private Date chatRoomLastChatTime;
    private Long memberId;
    private String memberNickName;
    private String memberImgName;
    private String memberImgPath;
    private Integer isFollowing;
    private Integer isFavourite;
    private Integer unReadCount;
}
