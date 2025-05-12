package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AchievementMapper {

    // 업적 추가 (관리자 용)
    public void insertAchievement(AchievementVO achievementVO);

    // 업적 완료 테이블 추가 최초 회원 가입시
    public void insertAchievementComplete(AchievementCompleteVO achievementCompleteVO);

    // 회원 번호 - 업적완료 연결 테이블 추가 미션 진행도와 업적완료 미션 횟수를 비교하여 추가
    public void insertMemberAchievement(MemberAchievementVO memberAchievementVO);

    // 회원 번호를 통한 전체 업적 리스트 조회
    public List<AchievementViewDTO> selectAllAchievementsByMemberId(Long memberId);

    // 회원의 완료하지 못한 업적 중 완료 여부 체크용 데이터 조회
    public List<AchievementViewDTO> selectAchievementComplementsByMemberId(Long memberId);

    // 회원의 완료된 업적 리스트 조회
    public List<AchievementViewDTO> selectCompletedAchievementsByMemberId(Long memberId);

    // 회원의 대표 업적 조회
    public List<AchievementViewDTO> selectDisplayedAchievementsByMemberId(Long memberId);

    // 업적 수정 (관리자 용)
    public void updateAchievement(AchievementVO achievementVO);

    // 업적 완료 미션 횟수 수정 (수정 후 완료 여부도 함께 체크 해야함)
    public void updateAchievementComplete(AchievementCompleteVO achievementCompleteVO);

    // 대표 업적 설정 수정
    public void updateMemberAchievementDisplay (MemberAchievementVO memberAchievementVO);

    // 업적 삭제 (관리자 용)
    public void deleteAchievement(Long id);

    // 회원의 업적 완료 진행 정보 삭제 (회원 탈퇴 시)
    public void deleteAchievementComplete(Long memberId);

    // 회원의 업적 삭제 (회원 탈퇴 시)
    public void deleteMemberAchievement(Long memberId);
}
