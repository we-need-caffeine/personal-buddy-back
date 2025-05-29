package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedules/api/*")
@Slf4j
public class ScheduleAPI {

    private final ScheduleService scheduleService;

    // ---------------------------- [일정] ----------------------------

    @Operation(summary = "일정 등록", description = "새 일정을 등록할 수 있는 API")
    @PostMapping("register")
    public void registerSchedule(@RequestBody ScheduleSaveDTO scheduleSaveDTO) {
        scheduleService.registerSchedule(scheduleSaveDTO);
    }

    @Operation(summary = "일정 전체 조회", description = "일정 전체를 조회할 수 있는 API")
    @Parameter(
            name = "memberId",
            description = "멤버 ID",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("members/{memberId}/schedules")
    public List<ScheduleVO> getSchedules(@PathVariable Long memberId) {
        return scheduleService.getSchedules(memberId);
    }

    @Operation(summary = "일정 단일 조회", description = "일정 하나를 조회할 수 있는 API")
    @Parameter(
            name = "scheduleId",
            description = "일정 ID",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/{scheduleId}")
    public Optional<ScheduleViewDTO> getSchedule(@PathVariable Long scheduleId) {
        return scheduleService.getSchedule(scheduleId);
    }

    @Operation(summary = "일정 색 조회", description = "일정에 쓰이는 색목록을 조회할 수 있는 API")
    @GetMapping("colors")
    public List<String> getColors() {
        return scheduleService.getColors();
    }

    @Operation(summary = "일정 대분류 조회", description = "일정 대분류를 조회할 수 있는 API")
    @GetMapping("categories")
    public List<ScheduleCategoryVO> getCategories() {
        return scheduleService.getScheduleCategories();
    }

    @Operation(summary = "일정 소분류 조회", description = "일정 소분류를 조회할 수 있는 API")
    @Parameter(
            name = "categoryId",
            description = "일정 ID",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("categories/{categoryId}")
    public List<ScheduleSubCategoryVO> getSubCategories(@PathVariable Long categoryId) {
        return scheduleService.getScheduleSubCategories(categoryId);
    }

    @Operation(summary = "일정 수정", description = "일정과 해당 공유 멤버를 수정하는 API")
    @PutMapping("schedules")
    public void modifySchedule(@RequestBody ScheduleSaveDTO scheduleSaveDTO) {
        scheduleService.modifySchedule(scheduleSaveDTO);
    }

    @Operation(summary = "일정 삭제", description = "일정과 관련된 모든 멤버 및 그룹 정보를 포함하여 삭제하는 API")
    @DeleteMapping("delete/{scheduleId}")
    public void deleteSchedule(@PathVariable Long scheduleId)
    {
        scheduleService.deleteSchedule(scheduleId);
    }

    // ---------------------------- [일정 멤버] ----------------------------

    @Operation(summary = "공유 일정 멤버 등록", description = "공유 일정 멤버에 등록할 수 있는 API")
    @PostMapping("members")
    public void addScheduleMember(@RequestBody ScheduleMemberVO scheduleMemberVO) {
        scheduleService.addScheduleMember(scheduleMemberVO);
    }

    @Operation(summary = "공유 일정 멤버 조회", description = "공유 일정 멤버 목록을 조회할 수 있는 API")
    @Parameter(
            name = "scheduleId",
            description = "일정 Id",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("members/{scheduleId}")
    public List<MemberVO> getScheduleMembers(@PathVariable Long scheduleId) {
        return scheduleService.getScheduleMembers(scheduleId);
    }

    @Operation(summary = "추가 가능한 공유 일정 멤버 조회", description = "공유 일정에 추가 가능한 멤버 목록을 조회할 수 있는 API")
    @Parameter(
            name = "calendarId",
            description = "캘린더 ID",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @Parameter(
            name = "groupId",
            description = "일정 공유 멤버 그룹 ID",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/calendar/{calendarId}/schedules/{groupId}/available-members")
    public List<MemberVO> getAvailableScheduleMembers(@PathVariable Long calendarId, @PathVariable Long groupId) {
        return scheduleService.getAvailableScheduleMembers(calendarId, groupId);
    }

    @Operation(summary = "일정 멤버 추방", description = "일정에서 특정 멤버를 추방하는 API")
    @DeleteMapping("members/{scheduleGroupMemberId}")
    public void expelScheduleMember(@PathVariable Long scheduleGroupMemberId) {
        scheduleService.expelScheduleMember(scheduleGroupMemberId);
    }
}
