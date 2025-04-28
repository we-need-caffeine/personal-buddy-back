package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AchievementViewDTO {
    private Long id; //업적 id
    private Long achievementCompleteId; // 업적완료 정보 id
    private Long memberAchievementId; // 개인 업적완료 정보 id
    private String achievementName; // 업적 이름
    private String achievementScheduleCategory; // 업적 달성 종류 (일정 카테고리와 연결)
    private String achievementImgPath; // 업적 이미지 경로
    private String achievementImgName; // 업적 이미지 파일 이름
    private int achievementGetPoint; // 업적 달성 시 획득 포인트
    private int achievementMissionCount; // 업적 달성에 필요한 미션 횟수
    private int achievementCurrentMissionCount; // 현재까지 달성한 업적 미션 횟수
}
