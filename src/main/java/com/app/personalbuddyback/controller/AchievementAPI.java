package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.service.AchievementService;
import com.app.personalbuddyback.service.AchievementServiceImpl;
import com.app.personalbuddyback.service.MemberService;
import com.app.personalbuddyback.service.PointService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/achievements/api")
public class AchievementAPI {
    private final AchievementService achievementService;
    private final PointService pointService;
    private final MemberService memberService;

    @Operation(summary = "업적 생성", description = "관리자 용 페이지에서 업적 데이터를 추가하는 API")
    @PostMapping("/achievement/create")
    public void createAchievement(@RequestBody AchievementVO achievementVO, @RequestBody Map<String, Object> imageFileData) {
        // 이미지 서비스 추가하여 업로드 후, 경로를 받아서 추가해야한다.
        achievementService.createAchievement(achievementVO);
    }

    @Operation(summary = "회원의 업적 완료 진행도 추가", description = "회원 가입 시 최초로 한번 실행")
    @PostMapping("/achievement/achievement-complete/create/{memberId}")
    public void createAchievementComplements(@PathVariable Long memberId){
        achievementService.createAchievementComplete(memberId);
    }

    @Operation(summary = "업적 전체 목록 조회", description = "전체 업적 목록")
    @GetMapping("/achievement/list")
    public List<AchievementVO> getAllAchievements(){
        return achievementService.getAllAchievements();
    }

    @Operation(summary = "회원의 업적 전체 목록 조회", description = "회원의 전체 업적 목록 (진행도 포함 데이터) 조회")
    @GetMapping("/achievement/{memberId}")
    public List<AchievementViewDTO> getAchievementsByMemberId(@PathVariable Long memberId) {
        return achievementService.getAllAchievementsByMemberId(memberId);
    }

    @Operation(summary = "회원의 완료된 업적 목록 조회", description = "회원의 완료된 전체 업적 목록")
    @GetMapping("/achievement/completed/{memberId}")
    public List<AchievementViewDTO> getCompletedAchievementsByMemberId(@PathVariable Long memberId) {
        return achievementService.getCompletedAchievements(memberId);
    }

    @Operation(summary = "회원의 전시된 업적 목록 조회", description = "회원의 완료된 업적 목록 중 Display 설정된 업적 조회")
    @GetMapping("/achievement/displayed/{memberId}")
    public List<AchievementViewDTO> getDisplayedAchievementsByMemberId(@PathVariable Long memberId) {
        return achievementService.getDisplayedAchievements(memberId);
    }

    @Operation(summary = "회원의 업적 완료 카운트 증가 및 완료 테이블에 추가", description = "")
    @PutMapping("/achievement/achievement-complete/edit")
    public ResponseEntity<Map<String, Object>> achievementCompleteEdit(@RequestBody Map<String, Objects> editData) {
        Map<String, Object> response = new HashMap<>();
        try {
            Long memberId = Long.valueOf(editData.get("memberId").toString());
            String achievementScheduleCategory = editData.get("scheduleCategory").toString();

            List<Long> achievementIds = achievementService.getAchievementsIdByScheduleCategory(achievementScheduleCategory);

            List<Long> memberCompletedAchievementIds = achievementService.getCompletedAchievements(memberId).stream()
                    .map(completedAchievement -> completedAchievement.getId()).collect(Collectors.toList());

            for(Long achievementId : achievementIds){
                List<AchievementViewDTO> memberAchievements = achievementService.getAllAchievementsByMemberId(memberId);

                Optional<AchievementViewDTO> findAchievement = memberAchievements.stream()
                        .filter(achievement -> achievement.getId().equals(achievementId))
                        .findFirst();

                if(memberCompletedAchievementIds.contains(achievementId)){
                    continue;
                }

                AchievementCompleteVO editCompleteData = new AchievementCompleteVO();

                editCompleteData.setAchievementCurrentMissionCount(findAchievement.get().getAchievementCurrentMissionCount() + 1);
                editCompleteData.setId(findAchievement.get().getAchievementCompleteId());

                achievementService.editAchievementComplement(editCompleteData);

                List<AchievementViewDTO> achievementComplements = achievementService.getAchievementComplements(memberId);

                Optional<Long> checkAchievementComplementId = achievementComplements.stream()
                        .map(achievementComplement -> achievementComplement.getId()).collect(Collectors.toList()).stream()
                        .filter(achievementComplementId -> achievementComplementId.equals(achievementId)).findFirst();

                Optional<AchievementVO> getAchievement = achievementService.getAchievementById(achievementId);

                if(checkAchievementComplementId.isPresent()){
                    MemberPointLogVO memberPointLogVO = new MemberPointLogVO();
                    memberPointLogVO.setMemberId(memberId);
                    memberPointLogVO.setMemberPointReason("업적 (" + getAchievement.get().getAchievementName() + ") 달성");
                    memberPointLogVO.setMemberPointChangeAmount(getAchievement.get().getAchievementGetPoint());
                    pointService.savePointLog(memberPointLogVO);

                    MemberVO memberVO = memberService.getMemberInfoById(memberId).orElseThrow(RuntimeException::new);
                    memberVO.setMemberPoint(memberVO.getMemberPoint() + getAchievement.get().getAchievementGetPoint());
                    memberService.edit(memberVO);
                }
            }
            response.put("result", true);
            response.put("message", "업적 완료 추가 처리 완료");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("result", false);
            response.put("message", "업적 완료 처리 실패");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Operation(summary = "업적 삭제", description = "관리자용 업적 삭제")
    @DeleteMapping("/achievement/{id}")
    public void deleteAchievementsByMemberId(@PathVariable Long id) {
        achievementService.deleteAchievement(id);
    }
}
