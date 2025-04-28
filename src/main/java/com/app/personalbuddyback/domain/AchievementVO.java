package com.app.personalbuddyback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@Schema(description = "업적 정보")
public class AchievementVO {
    @Schema(description = "업적 번호", required = true, example = "1")
    private Long id;
    @Schema(description = "업적 이름", required = true, example = "퍼스널 버디")
    private String achievementName;
    @Schema(description = "업적을 달성하기 위한 일정의 카테고리", required = true, example = "운동 (ScheduleVO 내의 scheduleCategory 와 형태가 동일)")
    private String achievementScheduleCategory;
    @Schema(description = "업적 달성을 위한 총 미션 횟수", required = true, example = "10")
    private int achievementMissionCount;
    @Schema(description = "업적 이미지 경로", required = true, example = "C:/project/achievements/img")
    private String achievementImgPath;
    @Schema(description = "업적 이미지 이름", required = true, example = "abc.png")
    private String achievementImgName;
    @Schema(description = "업적 획득 시 획득할 포인트", required = true, example = "100")
    private int achievementGetPoint;
}
