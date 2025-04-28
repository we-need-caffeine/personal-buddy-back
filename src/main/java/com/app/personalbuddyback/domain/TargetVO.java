package com.app.personalbuddyback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
@Schema()
public class TargetVO {
    private Long id;
    private Date targetCompleteDate;
    private String targetScheduleCategory;
    private Long memberId;
}
