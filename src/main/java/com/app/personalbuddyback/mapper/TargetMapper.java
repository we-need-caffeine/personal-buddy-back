package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Mapper
public interface TargetMapper {
    // 목표 달성 시 추가 테이블
    public void insertTarget(TargetVO targetVO);

    // 목표 달성 기준 테이블 (일정 / 카테고리 별 항목 -> 관리자 최초 추가)  추가
    public void insertTargetStandard(TargetStandardVO targetStandardVO);

    // 멤버 / 기간 별 기준 테이블 선정 (매 주 시작일 , 매 월 시작일에 해당하는 기준 테이블 선정)
    public void insertRandomTargetLottery(RandomTargetLotteryVO randomTargetLotteryVO);

    // 목표 달성 포인트 획득 로그 기록 테이블 추가 (중복 지급 방지용)
    public void insertTargetPointRewardLog(TargetPointRewardLogVO targetPointRewardLogVO);

    // 같은 목표 달성 목록이 있는지 조회용 쿼리 (1이면 추가하지 않는다)
    // 이중 체크로 unique 제약 조건을 걸어놓음
    public int selectTargetCount(TargetVO targetVO);

    // 일간 목표 완성 View 조회
    public List<TargetViewDTO> selectDailyTargetCompleteList(Long memberId);

    // 주간 목표 완성 View 조회
    public List<TargetViewDTO> selectWeeklyTargetCompleteList(Long memberId);

    // 월간 목표 완성 View 조회
    public List<TargetViewDTO> selectMonthlyTargetCompleteList(Long memberId);

    // 선정된 일간 Random Target 조회 (없으면 추가)
    public List<RandomTargetLotteryVO> selectDailyRandomTargetList(Long memberId);

    // 선정된 주간 Random Target 조회 (없으면 추가)
    public List<RandomTargetLotteryVO> selectWeeklyRandomTargetList(Long memberId);

    // 선정된 월간 Random Target 조회 (없으면 추가)
    public List<RandomTargetLotteryVO> selectMonthlyRandomTargetList(Long memberId);

    // 목표 달성 포인트 지급 전, 중복 지급 방지용 포인트 지급 이력 확인 포인트 추가 전 체크할 것
    public int selectCountTargetPointRewardLog(TargetPointRewardLogVO targetPointRewardLogVO);

    // 목표 달성 기준에 대한 수정 (관리자)
    public void updateTargetStandard(TargetStandardVO targetStandardVO);

    // 목표 완성 내용 삭제 (회원 탈퇴 시)
    public void deleteAllTarget(Long memberId);

    // 선정된 RandomTarget 내용 삭제 (회원 탈퇴 시)
    public void deleteAllRandomTarget(Long memberId);
}
