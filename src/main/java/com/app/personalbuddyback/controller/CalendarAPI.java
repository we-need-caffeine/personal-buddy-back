package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.service.CalendarService;
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
@RequestMapping("/calendars/api/*")
@Slf4j
public class CalendarAPI {

    private final CalendarService calendarService;

    // ---------------------------- [캘린더] ----------------------------

    @Operation(summary = "캘린더 등록", description = "새 캘린더를 등록할 수 있는 API")
    @PostMapping("/register")
    public void registerCalendar(@RequestBody CalendarVO calendarVO) {
        log.info("calendarVO = {}", calendarVO);
        calendarService.registerCalendar(calendarVO);
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
    @PutMapping("/invites/{calendarInviteId}/approve")
    public void approveCalendarInvite(@PathVariable Long calendarInviteId) {
        calendarService.approveCalendarInvite(calendarInviteId);
    }

    @Operation(summary = "캘린더 초대 거부", description = "캘린더 초대를 거부하는 API")
    @PutMapping("/invites/{calendarInviteId}/reject")
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

}
