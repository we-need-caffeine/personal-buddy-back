package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.service.CalendarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/calendars/api/*")
@Slf4j
public class CalendarAPI {

    private final CalendarService calendarService;

    // ---------------------------- [캘린더] ----------------------------

    @Operation(summary = "캘린더 등록", description = "새 캘린더를 등록할 수 있는 API")
    @PostMapping("/register")
    public void registerCalendar(@RequestBody CalendarVO calendarVO) {
        calendarService.registerCalendar(calendarVO);
        log.info("calendarVO = {}", calendarVO);
    }

    @Operation(summary = "캘린더 전체 조회", description = "캘린더 전체를 조회할 수 있는 API")
    @Parameter(
            name = "memberId",
            description = "멤버 ID",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/members/{memberId}/calendars")
    public List<CalendarVO> getCalendars(@PathVariable Long memberId) {
        return calendarService.getCalendars(memberId);
    }

    @Operation(summary = "캘린더 단일 조회", description = "캘린더 하나를 조회할 수 있는 API")
    @Parameter(
            name = "calendarId",
            description = "캘린더 ID",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/calendars/{calendarId}")
    public Optional<CalendarVO> getCalendar(@PathVariable Long calendarId) {
        return calendarService.getCalendar(calendarId);
    }

    @Operation(summary = "캘린더 단일 조회 (알람)", description = "알람용으로 캘린더 단일 정보를 조회하는 API")
    @Parameter(
            name = "calendarId",
            description = "캘린더 ID",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/calendars/{calendarId}/alarm")
    public Optional<CalendarViewDTO> getCalendarForAlarm(@PathVariable Long calendarId) {
        return calendarService.getCalendarForAlarm(calendarId);
    }

    @Operation(summary = "캘린더 수정", description = "기존 캘린더 정보를 수정하는 API")
    @PutMapping("/calendars")
    public void modifyCalendar(@RequestBody CalendarVO calendarVO) {
        calendarService.modifyCalendar(calendarVO);
    }

    @Operation(summary = "캘린더 삭제", description = "캘린더와 연관된 모든 데이터를 포함하여 삭제하는 API")
    @DeleteMapping("/calendars/{calendarId}")
    public void deleteCalendar(@PathVariable Long calendarId) {
        calendarService.deleteCalendar(calendarId);
    }

    // ---------------------------- [캘린더 멤버] ----------------------------

    @Operation(summary = "캘린더 초대", description = "공유 캘린더에 초대할 수 있는 API")
    @PostMapping("/invites")
    public void inviteCalendar(@RequestBody CalendarInviteVO calendarInviteVO) {
        calendarService.inviteCalendar(calendarInviteVO);
    }

    @Operation(summary = "캘린더 멤버 등록", description = "공유 캘린더 멤버에 등록할 수 있는 API")
    @PostMapping("/members")
    public void addCalendarMember(@RequestBody CalendarMemberVO calendarMemberVO) {
        calendarService.addCalendarMember(calendarMemberVO);
    }

    @Operation(summary = "캘린더 멤버 전체 조회", description = "캘린더 멤버 전체를 조회할 수 있는 API")
    @Parameter(
            name = "calendarId",
            description = "캘린더 ID",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/members/{calendarId}")
    public List<MemberVO> getCalendarMembers(@PathVariable Long calendarId) {
        return calendarService.getCalendarMembers(calendarId);
    }

    @Operation(summary = "캘린더 추가 가능 멤버 전체 조회", description = "캘린더 추가 멤버 전체를 조회할 수 있는 API")
    @Parameter(
            name = "memberId",
            description = "캘린더 ID",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/members/{memberId}/followings")
    public List<MemberVO> getMutualFollowings(@PathVariable Long memberId) {
        return calendarService.getMutualFollowings(memberId);
    }

    @Operation(summary = "캘린더 초대 승인", description = "캘린더 초대를 승인하는 API")
    @PostMapping("/invites/{calendarInviteId}/approve")
    public void approveCalendarInvite(@PathVariable Long calendarInviteId) {
        calendarService.approveCalendarInvite(calendarInviteId);
    }

    @Operation(summary = "캘린더 초대 거부", description = "캘린더 초대를 거부하는 API")
    @PostMapping("/invites/{calendarInviteId}/reject")
    public void rejectCalendarInvite(@PathVariable Long calendarInviteId) {
        calendarService.rejectCalendarInvite(calendarInviteId);
    }

    @Operation(summary = "캘린더 초대 취소", description = "보낸 초대를 취소하는 API")
    @DeleteMapping("/invites/{calendarInviteId}")
    public void cancelCalendarInvite(@PathVariable Long calendarInviteId) {
        calendarService.cancelCalendarInvite(calendarInviteId);
    }

    @Operation(summary = "캘린더 멤버 추방", description = "캘린더에서 특정 멤버를 추방하는 API")
    @DeleteMapping("/members/{calendarGroupMemberId}")
    public void expelCalendarMember(@PathVariable Long calendarGroupMemberId) {
        calendarService.expelCalendarMember(calendarGroupMemberId);
    }

    // ---------------------------- [투두리스트] ----------------------------

    @Operation(summary = "투두리스트 할일 등록", description = "투두리스트에 할일을 등록할 수 있는 API")
    @PostMapping("/todoLists/register")
    public void registerTodoList(@RequestBody ToDoListVO toDoListVO) {
        calendarService.registerTodoList(toDoListVO);
    }

    @Operation(summary = "투두리스트 전체 조회", description = "투두리스트 전체 할일 목록을 조회하는 API")
    @Parameter(
            name = "calendarId",
            description = "일정 ID",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/todoLists/{calendarId}")
    public List<ToDoListVO> getTodoLists(@PathVariable Long calendarId) {
        return calendarService.getTodoLists(calendarId);
    }

    @Operation(summary = "투두리스트 수정", description = "투두리스트 항목을 수정하는 API")
    @PutMapping("/todoLists")
    public void modifyTodoList(@RequestBody ToDoListVO toDoListVO) {
        calendarService.modifyTodoList(toDoListVO);
    }

    @Operation(summary = "투두리스트 삭제", description = "투두리스트 항목을 삭제하는 API")
    @DeleteMapping("/todoLists/{todoListId}")
    public void deleteTodoList(@PathVariable Long todoListId) {
        calendarService.deleteTodoList(todoListId);
    }

    // ---------------------------- [일정] ----------------------------

    @Operation(summary = "일정 등록", description = "새 일정을 등록할 수 있는 API")
    @PostMapping("/schedules/register")
    public void registerSchedule(@RequestBody CalendarViewDTO calendarViewDTO) {
        calendarService.registerSchedule(calendarViewDTO.getScheduleVO(), calendarViewDTO.getMemberIds());
    }

    @Operation(summary = "일정 전체 조회", description = "일정 전체를 조회할 수 있는 API")
    @Parameter(
            name = "memberId",
            description = "멤버 ID",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/members/{memberId}/schedules")
    public List<CalendarViewDTO> getSchedules(@PathVariable Long memberId) {
        return calendarService.getSchedules(memberId);
    }

    @Operation(summary = "일정 단일 조회", description = "일정 하나를 조회할 수 있는 API")
    @Parameter(
            name = "scheduleId",
            description = "일정 ID",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/schedules/{scheduleId}")
    public Optional<ScheduleVO> getSchedule(@PathVariable Long scheduleId) {
        return calendarService.getSchedule(scheduleId);
    }

    @Operation(summary = "일정 수정", description = "일정과 해당 공유 멤버를 수정하는 API")
    @PutMapping("/schedules")
    public void modifySchedule(@RequestBody CalendarViewDTO calendarViewDTO) {
        calendarService.modifySchedule(calendarViewDTO.getScheduleVO(), calendarViewDTO.getMemberIds());
    }

    @Operation(summary = "일정 삭제", description = "일정과 관련된 모든 멤버 및 그룹 정보를 포함하여 삭제하는 API")
    @DeleteMapping("/schedules/{scheduleId}")
    public void deleteSchedule(@PathVariable Long scheduleId) {
        calendarService.deleteSchedule(scheduleId);
    }

    // ---------------------------- [일정 멤버] ----------------------------

    @Operation(summary = "공유 일정 멤버 등록", description = "공유 일정 멤버에 등록할 수 있는 API")
    @PostMapping("/schedules/members")
    public void addScheduleMember(@RequestBody ScheduleGroupMemberVO scheduleGroupMemberVO) {
        calendarService.addScheduleMember(scheduleGroupMemberVO);
    }

    @Operation(summary = "공유 일정 멤버 조회", description = "공유 일정 멤버 목록을 조회할 수 있는 API")
    @Parameter(
            name = "groupId",
            description = "공유 멤버 그룹 ID",
            schema = @Schema(type = "number"),
            in = ParameterIn.PATH,
            required = true
    )
    @GetMapping("/schedules/{groupId}")
    public List<MemberVO> getScheduleMembers(@PathVariable Long groupId) {
        return calendarService.getScheduleMembers(groupId);
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
        return calendarService.getAvailableScheduleMembers(calendarId, groupId);
    }

    @Operation(summary = "일정 멤버 추방", description = "일정에서 특정 멤버를 추방하는 API")
    @DeleteMapping("/schedules/members/{scheduleGroupMemberId}")
    public void expelScheduleMember(@PathVariable Long scheduleGroupMemberId) {
        calendarService.expelScheduleMember(scheduleGroupMemberId);
    }


























}
