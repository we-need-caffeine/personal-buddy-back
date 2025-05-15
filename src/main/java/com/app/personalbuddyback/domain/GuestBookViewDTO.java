package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class GuestBookViewDTO {
    private Long id;
    private String guestbookContent;
    private Date guestbookCreateTime;
    private Long ownerMemberId;
    private Long writerMemberId;
    private String writerName;
    private String memberImgName;
    private String memberImgPath;
}
