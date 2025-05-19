package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.mapper.TargetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

@Repository
@RequiredArgsConstructor
public class TargetDAO {
    private final TargetMapper targetMapper;

    // 목표 달성 시 추가 테이블
    public void saveTarget(TargetVO targetVO) {
        targetMapper.insertTarget(targetVO);
    }

    // 목표 달성 기준 테이블 (일정 / 카테고리 별 항목 -> 관리자 최초 추가)  추가
    public void saveTargetStandard(TargetStandardVO targetStandardVO) {
        targetMapper.insertTargetStandard(targetStandardVO);
    }

    // 멤버 / 기간 별 기준 테이블 선정 (매 주 시작일 , 매 월 시작일에 해당하는 기준 테이블 선정)
    public void saveRandomTargetLottery(RandomTargetLotteryVO randomTargetLotteryVO) {
        targetMapper.insertRandomTargetLottery(randomTargetLotteryVO);
    }

    // 목표 달성 포인트 획득 로그 기록 테이블 추가 (중복 지급 방지용)
    public void saveTargetPointRewardLog(TargetPointRewardLogVO targetPointRewardLogVO) {
        targetMapper.insertTargetPointRewardLog(targetPointRewardLogVO);
    }

    // 같은 목표 달성 목록이 있는지 조회용 쿼리 (1이면 추가하지 않는다)
    // 이중 체크로 unique 제약 조건을 걸어놓음
    public int findTargetCount(TargetVO targetVO) {
        return targetMapper.selectTargetCount(targetVO);
    }

    // 일간 목표 완성 View 조회
    public List<TargetViewDTO> findDailyTargetList(Long memberId) {
        return targetMapper.selectDailyTargetCompleteList(memberId);
    }

    // 주간 목표 완성 View 조회
    public List<TargetViewDTO> findWeeklyTargetList(Long memberId) {
        return targetMapper.selectWeeklyTargetCompleteList(memberId);
    }

    // 월간 목표 완성 View 조회
    public List<TargetViewDTO> findMonthlyTargetList(Long memberId) {
        return targetMapper.selectMonthlyTargetCompleteList(memberId);
    }

    // 선정된 일간 Random Target 조회 (없으면 추가)
    public List<RandomTargetLotteryVO> findDailyRandomTargetList(Long memberId) {
        return targetMapper.selectDailyRandomTargetList(memberId);
    }

    // 선정된 주간 Random Target 조회 (없으면 추가)
    public List<RandomTargetLotteryVO> findWeeklyRandomTargetList(Long memberId) {
        return targetMapper.selectWeeklyRandomTargetList(memberId);
    }

    // 선정된 월간 Random Target 조회 (없으면 추가)
    public List<RandomTargetLotteryVO> findMonthlyRandomTargetList(Long memberId) {
        return targetMapper.selectMonthlyRandomTargetList(memberId);
    }

    // 목표 달성 포인트 지급 전, 중복 지급 방지용 포인트 지급 이력 확인 포인트 추가 전 체크할 것
    public int findCountTargetPointRewardLog(TargetPointRewardLogVO targetPointRewardLogVO) {
        return targetMapper.selectCountTargetPointRewardLog(targetPointRewardLogVO);
    }

    // 목표 달성 기준에 대한 수정 (관리자)
    public void updateTargetStandard(TargetStandardVO targetStandardVO) {
        targetMapper.updateTargetStandard(targetStandardVO);
    }

    // 목표 완성 내용 삭제 (회원 탈퇴 시)
    public void deleteAllTarget(Long memberId) {
        targetMapper.deleteAllTarget(memberId);
    }

    // 선정된 RandomTarget 내용 삭제 (회원 탈퇴 시)
    public void deleteAllRandomTarget(Long memberId) {
        targetMapper.deleteAllRandomTarget(memberId);
    }
}
