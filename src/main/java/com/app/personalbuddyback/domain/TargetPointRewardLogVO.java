package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class TargetPointRewardLogVO {
    private Long id;
    private Long memberId;
    private String targetPointRewardLogCategory;
    private String targetPointRewardLogPeriodType;
    private Date targetPointRewardLogStartDate;
}
