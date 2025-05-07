package com.app.personalbuddyback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@Schema(description = "업적 완료 정보")
public class AchievementCompleteVO {
        @Schema(description = "업적 완료 번호", required = true, example = "1")
        private Long id;
        @Schema(description = "업적 완료 진행도 횟수", required = true, example = "2")
        private int achievementCurrentMissionCount;
        @Schema(description = "업적 번호", required = true, example = "1")
        private Long achievementId;
}
