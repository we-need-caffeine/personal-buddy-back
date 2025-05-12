package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class RandomTargetLotteryVO {
    private Long id;
    private Long memberId;
    private String randomTargetLotteryPeriodType;
    private Date randomTargetLotteryStartDate;
    private String randomTargetLotteryCategory;
}
