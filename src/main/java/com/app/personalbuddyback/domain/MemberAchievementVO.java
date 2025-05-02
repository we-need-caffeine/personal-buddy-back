package com.app.personalbuddyback.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@Schema(description = "회원-업적완료 연결 정보")
public class MemberAchievementVO {
    @Schema(description = "회원-업적완료 연결정보 번호", required = true, example = "1")
    private Long id;
    @Schema(description = "대표 업적 표시 정보", required = true, example = "1 : 대표 업적 표시 O / 0 : 대표업적 표시 X")
    private int memberAchievementDisplay;
    @Schema(description = "회원 번호", required = true, example = "1")
    private Long memberId;
    @Schema(description = "업적 완료 정보 번호", required = true, example = "1")
    private Long achievementCompleteId;
}
