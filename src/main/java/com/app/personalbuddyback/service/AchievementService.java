package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.AchievementCompleteVO;
import com.app.personalbuddyback.domain.AchievementVO;
import com.app.personalbuddyback.domain.AchievementViewDTO;
import com.app.personalbuddyback.domain.MemberAchievementVO;

import java.util.List;
import java.util.Optional;

public interface AchievementService {
    public void createAchievement(AchievementVO achievementVO);

    public void createAchievementComplete(Long memberId);

    public void addMemberAchievement(MemberAchievementVO memberAchievementVO);

    public Optional<AchievementVO> getAchievementById(Long id);

    public List<AchievementVO> getAllAchievements();

    public List<AchievementViewDTO> getAllAchievementsByMemberId(Long memberId);

    public List<AchievementViewDTO> getAchievementComplements(Long memberId);

    public List<AchievementViewDTO> getCompletedAchievements(Long memberId);

    public List<AchievementViewDTO> getDisplayedAchievements(Long memberId);

    public List<Long> getAchievementsIdByScheduleCategory(String achievementScheduleCategory);

    public void editAchievement(AchievementVO achievementVO);

    public void editAchievementComplement(AchievementCompleteVO achievementCompleteVO);

    public void changeAchievementDisplay(MemberAchievementVO memberAchievementVO);

    public void deleteAchievement(Long id);

    public void deleteAchievementComplete(AchievementCompleteVO achievementCompleteVO);

    public void deleteMemberAchievement(Long memberId);
}
