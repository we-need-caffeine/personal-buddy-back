package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class GuestBookVO {
    private Long id;
    private String guestBookContent;
    private Date guestBookCreateTime;
    private Long ownerMemberId;
    private Long writerMemberId;
}
