package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.AchievementCompleteVO;
import com.app.personalbuddyback.domain.AchievementVO;
import com.app.personalbuddyback.domain.AchievementViewDTO;
import com.app.personalbuddyback.domain.MemberAchievementVO;
import com.app.personalbuddyback.mapper.AchievementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AchievementDAO {
    private final AchievementMapper achievementMapper;

    // 업적 등록
    public void saveAchievement(AchievementVO achievementVO) {
        achievementMapper.insertAchievement(achievementVO);
    }

    // 업적 완료 추가
    public void saveAchievementComplete(AchievementCompleteVO achievementCompleteVO) {
        achievementMapper.insertAchievementComplete(achievementCompleteVO);
    }

    // 업적 - 멤버 연결 테이블 추가
    public void saveMemberAchievement(MemberAchievementVO memberAchievementVO) {
        achievementMapper.insertMemberAchievement(memberAchievementVO);
    }

    // 업적 리스트 조회 (회원 번호를 통해서)
    public List<AchievementViewDTO> findAchievementsByMemberId(Long memberId) {
        return achievementMapper.selectAchievementsByMemberId(memberId);
    }

    // 업적 완료 정보 수정 (업적 완료 카운트 증가 시)
    public void editAchievementComplete(AchievementCompleteVO achievementCompleteVO) {
        achievementMapper.updateAchievementComplete(achievementCompleteVO);
    }
}
