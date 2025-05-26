package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class ChatViewDTO {
    private Long id;
    private String chatContent;
    private Integer chatReading;
    private Date chatSendTime;
    private Long chatWriterMemberId;
    private String memberNickname;
    private String memberImgName;
    private String memberImgPath;
}
