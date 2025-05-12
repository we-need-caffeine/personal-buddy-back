package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.AchievementCompleteVO;
import com.app.personalbuddyback.domain.AchievementVO;
import com.app.personalbuddyback.domain.AchievementViewDTO;
import com.app.personalbuddyback.domain.MemberAchievementVO;
import com.app.personalbuddyback.repository.AchievementDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AchievementServiceImpl implements AchievementService {

    private final AchievementDAO achievementDAO;

    @Override
    public void createAchievement(AchievementVO achievementVO) {
        achievementDAO.saveAchievement(achievementVO);
    }

    @Override
    public void createAchievementComplete(AchievementCompleteVO achievementCompleteVO) {
        achievementDAO.saveAchievementComplete(achievementCompleteVO);
    }

    @Override
    public void addMemberAchievement(MemberAchievementVO memberAchievementVO) {
        achievementDAO.saveMemberAchievement(memberAchievementVO);
    }

    @Override
    public List<AchievementViewDTO> getAllAchievements(Long memberId) {
        return achievementDAO.findAllAchievements(memberId);
    }

    @Override
    public List<AchievementViewDTO> getAchievementComplements(Long memberId){
        return achievementDAO.findComplements(memberId);
    }

    @Override
    public List<AchievementViewDTO> getCompletedAchievements(Long memberId){
        return achievementDAO.findCompletedAchievements(memberId);
    }

    @Override
    public List<AchievementViewDTO> getDisplayedAchievements(Long memberId){
        return achievementDAO.findDisplayedAchievements(memberId);
    }

    @Override
    public void editAchievement(AchievementVO achievementVO) {
        achievementDAO.editAchievement(achievementVO);
    }

    @Override
    public void editAchievementComplement(AchievementCompleteVO achievementCompleteVO){
        achievementDAO.editAchievementComplete(achievementCompleteVO);
    }

    @Override
    public void changeAchievementDisplay(MemberAchievementVO memberAchievementVO) {
        achievementDAO.changeAchievementDisplay(memberAchievementVO);
    }

    @Override
    public void deleteAchievement(AchievementVO achievementVO) {
        achievementDAO.deleteAchievement(achievementVO.getId());
    }

    @Override
    public void deleteAchievementComplete(AchievementCompleteVO achievementCompleteVO) {
        achievementDAO.deleteAchievementComplete(achievementCompleteVO.getId());
    }

    @Override
    public void deleteMemberAchievement(Long memberId) {
        achievementDAO.deleteMemberAchievement(memberId);
    }
}
