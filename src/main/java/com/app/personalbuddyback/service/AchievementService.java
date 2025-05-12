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

    public List<AchievementViewDTO> getAllAchievements();


}
