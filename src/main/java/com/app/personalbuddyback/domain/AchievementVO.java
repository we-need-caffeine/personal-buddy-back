package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AchievementVO {
    private Long id;
    private String achievementName;
    private String achievementScheduleCategory;
    private int achievementMissionCount;
    private String achievementImgPath;
    private String achievementImgName;
    private int achievementGetPoint;
}
