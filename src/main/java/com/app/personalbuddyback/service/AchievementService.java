package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.AchievementCompleteVO;
import com.app.personalbuddyback.domain.AchievementVO;
import com.app.personalbuddyback.domain.AchievementViewDTO;
import com.app.personalbuddyback.domain.MemberAchievementVO;

import java.util.List;

public interface AchievementService {
    public void createAchievement(AchievementVO achievementVO);

    public void createAchievementComplete(AchievementCompleteVO achievementCompleteVO);

    public void addMemberAchievement(MemberAchievementVO memberAchievementVO);

    public List<AchievementViewDTO> getAllAchievements(Long memberId);

    public List<AchievementViewDTO> getAchievementComplements(Long memberId);

    public List<AchievementViewDTO> getCompletedAchievements(Long memberId);

    public List<AchievementViewDTO> getDisplayedAchievements(Long memberId);

    public void editAchievement(AchievementVO achievementVO);

    public void editAchievementComplement(AchievementCompleteVO achievementCompleteVO);

    public void changeAchievementDisplay(MemberAchievementVO memberAchievementVO);

    public void deleteAchievement(AchievementVO achievementVO);

    public void deleteAchievementComplete(AchievementCompleteVO achievementCompleteVO);

    public void deleteMemberAchievement(Long memberId);
}
