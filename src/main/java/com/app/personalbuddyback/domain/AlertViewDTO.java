package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class AlertViewDTO {
    private Long id;
    private String alertType;
    private String alertMessage;
    private Date alertCreateTime;
    private Boolean alertRead;
    private String alertParam;
    private Long receiverMemberId;
    private Long senderMemberId;
    private String memberNickname;
    private String memberImgName;
    private String memberImgPath;
}
