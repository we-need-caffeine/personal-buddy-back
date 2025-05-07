package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.AchievementCompleteVO;
import com.app.personalbuddyback.domain.AchievementVO;
import com.app.personalbuddyback.domain.AchievementViewDTO;
import com.app.personalbuddyback.domain.MemberAchievementVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AchievementMapper {
    // 업적 내용 추가
    public void insertAchievement(AchievementVO achievementVO);

    // 업적 완료 테이블 추가
    public void insertAchievementComplete(AchievementCompleteVO achievementCompleteVO);

    // 업적 - 멤버 연결 테이블 추가
    public void insertMemberAchievement(MemberAchievementVO memberAchievementVO);

    // 회원 번호를 통한 업적 리스트 조회
    public List<AchievementViewDTO> selectAchievementsByMemberId(Long memberId);

    // 업적 완료 사항 수정 (완료 횟수 수정)
    public void updateAchievementComplete(AchievementCompleteVO achievementCompleteVO);
}
