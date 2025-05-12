package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.AchievementCompleteVO;
import com.app.personalbuddyback.domain.AchievementVO;
import com.app.personalbuddyback.domain.AchievementViewDTO;
import com.app.personalbuddyback.domain.MemberAchievementVO;
import com.app.personalbuddyback.mapper.AchievementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AchievementDAO {
    private final AchievementMapper achievementMapper;

    // 업적 추가 (관리자 용)
    public void saveAchievement(AchievementVO achievementVO) {
        achievementMapper.insertAchievement(achievementVO);
    }

    // 업적 완료 테이블 추가 최초 회원 가입시
    public void saveAchievementComplete(AchievementCompleteVO achievementCompleteVO) {
        achievementMapper.insertAchievementComplete(achievementCompleteVO);
    }

    // 회원 번호 - 업적완료 연결 테이블 추가 미션 진행도와 업적완료 미션 횟수를 비교하여 추가
    public void saveMemberAchievement(MemberAchievementVO memberAchievementVO) {
        achievementMapper.insertMemberAchievement(memberAchievementVO);
    }

    // 회원 번호를 통한 전체 업적 리스트 조회
    public List<AchievementViewDTO> findAllAchievements(Long memberId) {
        return achievementMapper.selectAllAchievementsByMemberId(memberId);
    }

    // 회원의 완료하지 못한 업적 중 완료 여부 체크용 데이터 조회
    public List<AchievementViewDTO> findComplements(Long memberId) {
        return achievementMapper.selectAchievementComplementsByMemberId(memberId);
    }

    // 회원의 완료된 업적 리스트 조회
    public List<AchievementViewDTO> findCompletedAchievements(Long memberId) {
        return achievementMapper.selectCompletedAchievementsByMemberId(memberId);
    }

    // 회원의 대표 업적 조회
    public List<AchievementViewDTO> findDisplayedAchievements(Long memberId) {
        return achievementMapper.selectDisplayedAchievementsByMemberId(memberId);
    }

    // 업적 수정 (관리자 용)
    public void editAchievement(AchievementVO achievementVO) {
        achievementMapper.updateAchievement(achievementVO);
    }

    // 업적 완료 미션 횟수 수정 (수정 후 완료 여부도 함께 체크 해야함)
    public void editAchievementComplete(AchievementCompleteVO achievementCompleteVO) {
        achievementMapper.updateAchievementComplete(achievementCompleteVO);
    }

    // 대표 업적 설정 수정
    public void changeAchievementDisplay(MemberAchievementVO memberAchievementVO) {
        achievementMapper.updateMemberAchievementDisplay(memberAchievementVO);
    }

    // 업적 삭제 (관리자 용)
    public void deleteAchievement(Long id){
        achievementMapper.deleteAchievement(id);
    }

    // 회원의 업적 완료 진행 정보 삭제 (회원 탈퇴 시)
    public void deleteAchievementComplete(Long memberId){
        achievementMapper.deleteAchievementComplete(memberId);
    }

    // 회원의 업적 삭제 (회원 탈퇴 시)
    public void deleteMemberAchievement(Long memberId){
        achievementMapper.deleteMemberAchievement(memberId);
    }
}
