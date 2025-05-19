package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.AchievementCompleteVO;
import com.app.personalbuddyback.domain.AchievementVO;
import com.app.personalbuddyback.domain.AchievementViewDTO;
import com.app.personalbuddyback.domain.MemberAchievementVO;
import com.app.personalbuddyback.repository.AchievementDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AchievementServiceImpl implements AchievementService {

    private final AchievementDAO achievementDAO;

    @Override
    public void createAchievement(AchievementVO achievementVO) {
        achievementDAO.saveAchievement(achievementVO);
    }

    // 최초 회원 가입 시, 진행도 초기 데이터를 넣어놓고, update 로 미션 완료 상태를 관리한다.
    @Override
    public void createAchievementComplete(Long memberId) {
        List<AchievementVO> achievements = achievementDAO.findAllAchievements();

        for (AchievementVO achievement : achievements) {
            AchievementCompleteVO achievementCompleteVO = new AchievementCompleteVO();

            achievementCompleteVO.setMemberId(memberId);
            Long achievementId = achievement.getId();
            achievementCompleteVO.setAchievementId(achievementId);
            achievementDAO.saveAchievementComplete(achievementCompleteVO);
        }
    }

    @Override
    public void addMemberAchievement(MemberAchievementVO memberAchievementVO) {
        achievementDAO.saveMemberAchievement(memberAchievementVO);
    }

    @Override
    public Optional<AchievementVO> getAchievementById(Long id) {
        return achievementDAO.findAchievementById(id);
    }

    @Override
    public List<AchievementVO> getAllAchievements() {
        return achievementDAO.findAllAchievements();
    }

    @Override
    public List<AchievementViewDTO> getAllAchievementsByMemberId(Long memberId) {
        return achievementDAO.findAllAchievementsById(memberId);
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
    public List<Long> getAchievementsIdByScheduleCategory(String achievementScheduleCategory) {
        return achievementDAO.findAchievementsIdByScheduleCategory(achievementScheduleCategory);
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
    public void deleteAchievement(Long id) {
        achievementDAO.deleteAchievement(id);
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
