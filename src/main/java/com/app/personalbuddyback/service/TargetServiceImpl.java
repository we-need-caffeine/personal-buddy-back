package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.repository.MemberDAO;
import com.app.personalbuddyback.repository.PointDAO;
import com.app.personalbuddyback.repository.TargetDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class TargetServiceImpl implements TargetService {
    private final TargetDAO targetDAO;
    private final PointDAO pointDAO;
    private final MemberDAO memberDAO;

    // 같은 날 동일한 사람이 동일한 종류의 목표는 추가할 수 없다.
    // 컨트롤러 단에서 체크 후, 검증된 데이터만 추가함. (컨트롤러에서 유효성 검사 후, 완료 항목에 추가해야할 때만 추가)
    @Override
    public void completeTarget(TargetVO targetVO) {
        Long memberId = targetVO.getMemberId();
        String targetCategory = targetVO.getTargetScheduleCategory();

        // 완료 목표 추가
        targetDAO.saveTarget(targetVO);

        // 완료 목표 추가 후, 기간 별 달성 여부 확인
        // 이 때, 이미 같은 내용으로 포인트가 지급 된 내용을 확인하여, 있다면 포인트를 지급하지 않도록 한다.
        
        // 비교할 데이터 먼저 받기
        List<TargetViewDTO> dailyTargets = targetDAO.findDailyTargetList(memberId);
        List<TargetViewDTO> weeklyTargets = targetDAO.findWeeklyTargetList(memberId);
        List<TargetViewDTO> monthlyTargets = targetDAO.findMonthlyTargetList(memberId);
        List<RandomTargetLotteryVO> randomDailyTargets = targetDAO.findDailyRandomTargetList(memberId);
        List<RandomTargetLotteryVO> randomWeeklyTargets = targetDAO.findWeeklyRandomTargetList(memberId);
        List<RandomTargetLotteryVO> randomMonthlyTargets = targetDAO.findMonthlyRandomTargetList(memberId);

        // 목표 중, 완료가 되었는 지 완료된 목표 추가 후 체크
        for(TargetViewDTO dailyTarget : dailyTargets){
            if(dailyTarget.getTargetType().equals(targetCategory)
                && dailyTarget.getIsCompleted() == 1) {

                // 완료 목표에 대해 랜덤으로 선정된 목표에 포함이 되어있는지 체크
                for(RandomTargetLotteryVO randomDailyTarget : randomDailyTargets){
                    if(targetCategory.equals(randomDailyTarget.getRandomTargetLotteryCategory())) {
                        
                        // 포인트 지급 내역이 있는 지 체크
                        TargetPointRewardLogVO targetPointRewardLogVO = new TargetPointRewardLogVO();

                        targetPointRewardLogVO.setMemberId(memberId);
                        targetPointRewardLogVO.setTargetPointRewardLogCategory(targetCategory);
                        targetPointRewardLogVO.setTargetPointRewardLogPeriodType("일간");
                        targetPointRewardLogVO.setTargetPointRewardLogStartDate(randomDailyTarget.getRandomTargetLotteryStartDate());

                        boolean isGetPoint = targetDAO.findCountTargetPointRewardLog(targetPointRewardLogVO) == 1;

                        // 업적 포인트 지급내역이 없을 때만, 포인트를 지급
                        if(!isGetPoint) {
                            // 업적 포인트 지급 기록
                            targetDAO.saveTargetPointRewardLog(targetPointRewardLogVO);
                            
                            // 포인트 로그에 기록
                            MemberPointLogVO memberPointLogVO = new MemberPointLogVO();
                            memberPointLogVO.setMemberId(memberId);
                            memberPointLogVO.setMemberPointChangeAmount(dailyTarget.getTargetGetPoint());
                            memberPointLogVO.setMemberPointReason("일간 목표 (" + targetCategory + ") 달성");
                            pointDAO.savePointLog(memberPointLogVO);

                            // 회원의 포인트 정보 변경
                            MemberVO memberVO = new MemberVO();
                            memberVO.setId(memberId);
                            memberVO.setMemberPoint(memberVO.getMemberPoint() + dailyTarget.getTargetGetPoint());

                            memberDAO.update(memberVO);
                        }
                    }
                }
            }
        }

        // 목표 중, 완료가 되었는 지 완료된 목표 추가 후 체크
        for(TargetViewDTO weeklyTarget : weeklyTargets){
            if(weeklyTarget.getTargetType().equals(targetCategory)
                    && weeklyTarget.getIsCompleted() == 1) {

                // 완료 목표에 대해 랜덤으로 선정된 목표에 포함이 되어있는지 체크
                for(RandomTargetLotteryVO randomWeeklyTarget : randomWeeklyTargets){
                    if(targetCategory.equals(randomWeeklyTarget.getRandomTargetLotteryCategory())) {

                        // 포인트 지급 내역이 있는 지 체크
                        TargetPointRewardLogVO targetPointRewardLogVO = new TargetPointRewardLogVO();

                        targetPointRewardLogVO.setMemberId(memberId);
                        targetPointRewardLogVO.setTargetPointRewardLogCategory(targetCategory);
                        targetPointRewardLogVO.setTargetPointRewardLogPeriodType("주간");
                        targetPointRewardLogVO.setTargetPointRewardLogStartDate(randomWeeklyTarget.getRandomTargetLotteryStartDate());

                        boolean isGetPoint = targetDAO.findCountTargetPointRewardLog(targetPointRewardLogVO) == 1;

                        // 업적 포인트 지급내역이 없을 때만, 포인트를 지급
                        if(!isGetPoint) {
                            // 업적 포인트 지급 기록
                            targetDAO.saveTargetPointRewardLog(targetPointRewardLogVO);

                            // 포인트 로그에 기록
                            MemberPointLogVO memberPointLogVO = new MemberPointLogVO();
                            memberPointLogVO.setMemberId(memberId);
                            memberPointLogVO.setMemberPointChangeAmount(weeklyTarget.getTargetGetPoint());
                            memberPointLogVO.setMemberPointReason("주간 목표 (" + targetCategory + ") 달성");
                            pointDAO.savePointLog(memberPointLogVO);

                            // 회원의 포인트 정보 변경
                            MemberVO memberVO = new MemberVO();
                            memberVO.setId(memberId);
                            memberVO.setMemberPoint(memberVO.getMemberPoint() + weeklyTarget.getTargetGetPoint());

                            memberDAO.update(memberVO);
                        }
                    }
                }
            }
        }

        // 목표 중, 완료가 되었는 지 완료된 목표 추가 후 체크
        for(TargetViewDTO monthlyTarget : monthlyTargets){
            if(monthlyTarget.getTargetType().equals(targetCategory)
                    && monthlyTarget.getIsCompleted() == 1) {

                // 완료 목표에 대해 랜덤으로 선정된 목표에 포함이 되어있는지 체크
                for(RandomTargetLotteryVO randomMonthlyTarget : randomMonthlyTargets){
                    if(targetCategory.equals(randomMonthlyTarget.getRandomTargetLotteryCategory())) {

                        // 포인트 지급 내역이 있는 지 체크
                        TargetPointRewardLogVO targetPointRewardLogVO = new TargetPointRewardLogVO();

                        targetPointRewardLogVO.setMemberId(memberId);
                        targetPointRewardLogVO.setTargetPointRewardLogCategory(targetCategory);
                        targetPointRewardLogVO.setTargetPointRewardLogPeriodType("월간");
                        targetPointRewardLogVO.setTargetPointRewardLogStartDate(randomMonthlyTarget.getRandomTargetLotteryStartDate());

                        boolean isGetPoint = targetDAO.findCountTargetPointRewardLog(targetPointRewardLogVO) == 1;

                        // 업적 포인트 지급내역이 없을 때만, 포인트를 지급
                        if(!isGetPoint) {
                            // 업적 포인트 지급 기록
                            targetDAO.saveTargetPointRewardLog(targetPointRewardLogVO);

                            // 포인트 로그에 기록
                            MemberPointLogVO memberPointLogVO = new MemberPointLogVO();
                            memberPointLogVO.setMemberId(memberId);
                            memberPointLogVO.setMemberPointChangeAmount(monthlyTarget.getTargetGetPoint());
                            memberPointLogVO.setMemberPointReason("월간 목표 (" + targetCategory + ") 달성");
                            pointDAO.savePointLog(memberPointLogVO);

                            // 회원의 포인트 정보 변경
                            MemberVO memberVO = new MemberVO();
                            memberVO.setId(memberId);
                            memberVO.setMemberPoint(memberVO.getMemberPoint() + monthlyTarget.getTargetGetPoint());

                            memberDAO.update(memberVO);
                        }
                    }
                }
            }
        }
    }

    //관리자용 타겟 기준 추가
    @Override
    public void addTargetStandard(TargetStandardVO targetStandardVO) {
        targetDAO.saveTargetStandard(targetStandardVO);
    }

    // 같은 날 목표 달성한 내용이 있는지 조회용
    @Override
    public int getTargetCount(TargetVO targetVO) {
        return targetDAO.findTargetCount(targetVO);
    }

    // 매일 / 매 주 첫일 / 매 월 1일 멤버 별 랜덤 목표 생성
    @Override
    public void createRandomTargetLotto(RandomTargetLotteryVO randomTargetLotteryVO) {
         targetDAO.saveRandomTargetLottery(randomTargetLotteryVO);
    }

    @Override
    public List<TargetViewDTO> getDailyTargets(Long memberId) {
        return targetDAO.findDailyTargetList(memberId);
    }

    @Override
    public List<TargetViewDTO> getWeeklyTargets(Long memberId) {
        return targetDAO.findWeeklyTargetList(memberId);
    }

    @Override
    public List<TargetViewDTO> getMonthlyTargets(Long memberId) {
        return targetDAO.findMonthlyTargetList(memberId);
    }

    @Override
    public List<RandomTargetLotteryVO> getDailyRandomTargets(Long memberId) {
        return targetDAO.findDailyRandomTargetList(memberId);
    }

    @Override
    public List<RandomTargetLotteryVO> getWeeklyRandomTargets(Long memberId) {
        return targetDAO.findWeeklyRandomTargetList(memberId);
    }

    @Override
    public List<RandomTargetLotteryVO> getMonthlyRandomTargets(Long memberId) {
        return targetDAO.findMonthlyRandomTargetList(memberId);
    }

    // 관리자용 목표 기준 내용 수정
    @Override
    public void editTargetStandard(TargetStandardVO targetStandardVO) {
        targetDAO.updateTargetStandard(targetStandardVO);
    }
}
