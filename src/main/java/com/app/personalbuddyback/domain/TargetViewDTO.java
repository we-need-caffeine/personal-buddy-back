package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TargetViewDTO {
    private Long memberId;
    private String targetType;
    private int completedCount;
    private int standardCount;
}
