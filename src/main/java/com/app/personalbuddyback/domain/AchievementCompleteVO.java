package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AchievementCompleteVO {
    private Long id;
    private int achievementCurrentMissionCount;
    private Long achievementId;
}
