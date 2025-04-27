package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class AlertVO {
    private Long id;
    private String alertType;
    private String alertMessage;
    private Date alertCreateTime;
    private int alertRead;
    private Long alertParam;
    private Long receiverMemberId;
    private Long senderMemberId;
}

