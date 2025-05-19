package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.RandomTargetLotteryVO;
import com.app.personalbuddyback.domain.TargetStandardVO;
import com.app.personalbuddyback.domain.TargetVO;
import com.app.personalbuddyback.service.TargetService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/targets/api")
public class TargetAPI {
    private final TargetService targetService;

    @Operation(summary = "회원의 목표 완료 추가", description = "같은 회원이, 같은 날, 같은 내용의 목표가 완료되었다면 추가하지 않음")
    @PostMapping("/target/complete")
    public void completeTarget(@RequestBody TargetVO targetVO) {
        int targetCount = targetService.getTargetCount(targetVO);

        if(targetCount == 0) {
            targetService.completeTarget(targetVO);
        }
    }

    @Operation(summary = "목표의 기준치 추가 (관리자용)", description = "목표 완료 여부를 판단할 기준 테이블 정보 추가")
    @PostMapping("/target-standard/add")
    public void addTargetStandard(@RequestBody TargetStandardVO targetStandardVO) {
        targetService.addTargetStandard(targetStandardVO);
    }

    @Operation(summary = "매일,매주,매월 시작일에 선정할 목표 조회 및 생성", description = "매일 / 매주 첫 째날 / 매월 1일 기준 데이터 생성 및 없다면 추가")
    @PostMapping("/target/random-target/{memberId}")
    public void randomTargetLottery(@PathVariable Long memberId) {
        String[] categoryList = {"운동", "공부", "업무", "모임", "여가", "식사", "여행", "건강"};

        List<RandomTargetLotteryVO> dailyRandomTargets = targetService.getDailyRandomTargets(memberId);
        List<RandomTargetLotteryVO> weeklyRandomTargets = targetService.getWeeklyRandomTargets(memberId);
        List<RandomTargetLotteryVO> monthlyRandomTargets = targetService.getMonthlyRandomTargets(memberId);

        if(dailyRandomTargets.size() == 0) {
            RandomTargetLotteryVO randomTargetLotteryVO = new RandomTargetLotteryVO();

            randomTargetLotteryVO.setMemberId(memberId);
            randomTargetLotteryVO.setRandomTargetLotteryPeriodType("일간");

            List<Integer> randomCategoryNums = new ArrayList<>();
            for(int i = 0; i < categoryList.length; i++) {
                randomCategoryNums.add(i);
            }
            Collections.shuffle(randomCategoryNums);

            for(int i = 0; i < 3; i++) {
                randomTargetLotteryVO.setRandomTargetLotteryCategory(categoryList[randomCategoryNums.get(i)]);
                targetService.createRandomTargetLotto(randomTargetLotteryVO);
            }
        }

        if(weeklyRandomTargets.size() == 0) {
            RandomTargetLotteryVO randomTargetLotteryVO = new RandomTargetLotteryVO();

            randomTargetLotteryVO.setMemberId(memberId);
            randomTargetLotteryVO.setRandomTargetLotteryPeriodType("주간");

            List<Integer> randomCategoryNums = new ArrayList<>();
            for(int i = 0; i < categoryList.length; i++) {
                randomCategoryNums.add(i);
            }
            Collections.shuffle(randomCategoryNums);

            for(int i = 0; i < 3; i++) {
                randomTargetLotteryVO.setRandomTargetLotteryCategory(categoryList[randomCategoryNums.get(i)]);
                targetService.createRandomTargetLotto(randomTargetLotteryVO);
            }
        }

        if(monthlyRandomTargets.size() == 0) {
            RandomTargetLotteryVO randomTargetLotteryVO = new RandomTargetLotteryVO();

            randomTargetLotteryVO.setMemberId(memberId);
            randomTargetLotteryVO.setRandomTargetLotteryPeriodType("월간");

            List<Integer> randomCategoryNums = new ArrayList<>();
            for(int i = 0; i < categoryList.length; i++) {
                randomCategoryNums.add(i);
            }
            Collections.shuffle(randomCategoryNums);

            for(int i = 0; i < 3; i++) {
                randomTargetLotteryVO.setRandomTargetLotteryCategory(categoryList[randomCategoryNums.get(i)]);
                targetService.createRandomTargetLotto(randomTargetLotteryVO);
            }
        }
    }

    @Operation(summary = "목표 목록 조회", description = "회원 번호를 통해 목표의 목록을 조회")
    @PostMapping("/target/list/{memberId}")
    public ResponseEntity<Map<String, Object>> getTargetList(@PathVariable Long memberId) {
        Map<String, Object> response = new HashMap<>();
        response.put("result", true);
        response.put("dailyTargets", targetService.getDailyTargets(memberId));
        response.put("weeklyTargets", targetService.getWeeklyTargets(memberId));
        response.put("monthlyTargets", targetService.getMonthlyTargets(memberId));

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "목표 기준 수정", description = "관리자용 목표 기준 수정")
    @PostMapping("/target/standard/edit")
    public void editTargetStandard(@RequestBody TargetStandardVO targetStandardVO) {
        targetService.editTargetStandard(targetStandardVO);
    }
}
