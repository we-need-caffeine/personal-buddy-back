package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class MemberPointLogVO {
    private Long id;
    private int memberPointVariance;
    private String memberPointReason;
    private Date memberPointDate;
    private Long memberId;
}
