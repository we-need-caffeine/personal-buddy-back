package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class MemberAchievementVO {
    private Long id;
    private int memberAchievementDisplay;
    private Long memberId;
    private Long achievementCompleteId;
}
