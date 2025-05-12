package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TargetStandardVO {
    private Long id;
    private String targetStandardScheduleCategory;
    private String targetStandardPeriodType;
    private int targetStandardMissionCount;
}
