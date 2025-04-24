package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class TargetVO {
    private Long id;
    private Date targetCompleteDate;
    private String targetScheduleCategory;
    private Long memberId;
}
