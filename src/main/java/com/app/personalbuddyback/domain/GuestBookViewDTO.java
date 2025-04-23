package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Data
@Component
public class GuestBookViewDTO {
    private Long id;
    private String guestbookContent;
    private Date guestbookCreateDate;
    private Long ownerMemberId;
    private Long writerMemberId;
}
