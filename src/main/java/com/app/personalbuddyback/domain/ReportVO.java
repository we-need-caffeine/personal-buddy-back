package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class ReportVO {
    private Long id;
    private String reportReason;
    private Date reportDate;
    private Long reportMemberId;
    private Long reportedMemberId;
}
