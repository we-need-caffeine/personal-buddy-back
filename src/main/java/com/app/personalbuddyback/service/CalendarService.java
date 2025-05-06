package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;

import java.util.List;
import java.util.Optional;

public interface CalendarService {

    // 캘린더 등록
    public void registerCalendar(CalendarVO calendarVO);

    // 일정 등록
    public void registerSchedule(ScheduleVO scheduleVO, List<Long> memberIds);

    // 투두리스트 할일 등록
    public void registerTodoList(ToDoListVO toDoListVO);

    // 공유 캘린더 초대
    public void inviteCalendar(CalendarInviteVO calendarInviteVO);

    // 공유 캘린더 멤버 등록
    public void addCalendarMember(CalendarMemberVO calendarMemberVO);

    // 공유 일정 멤버 등록
    public void addScheduleMember(ScheduleGroupMemberVO scheduleGroupMemberVO);

    // 캘린더 멤버 조회
    public List<MemberVO> getCalendarMembers(Long calendarId);

    // 일정 멤버 조회
    public List<MemberVO> getScheduleMembers(Long scheduleMemberGroupId);

    // 추가 가능한 일정 멤버 조회
    public List<MemberVO> getAvailableScheduleMembers(Long calendarId, Long scheduleMemberGroupId);

    // 팔로잉 전체 조회
    public List<MemberVO> getMutualFollowings(Long memberId);

    // 캘린더 전체 조회
    public List<CalendarVO> getCalendars(Long memberId);

    // 캘린더 단일 조회
    public Optional<CalendarVO> getCalendar(Long calendarId);

    // 일정 전체 조회
    public List<CalendarViewDTO> getSchedules(Long memberId);

    // 일정 단일 조회
    public Optional<ScheduleVO> getSchedule(Long scheduleId);

    // 캘린더 단일 조회(알람)
    public Optional<CalendarViewDTO> getCalendarForAlarm(Long calendarId);

    // 투두리스트 전체 조회
    public List<ToDoListVO> getTodoLists(Long calendarId);

    // 초대 승인
    public void approveCalendarInvite(Long calendarInviteId);

    // 초대 거부
    public void rejectCalendarInvite(Long calendarInviteId);

    // 캘린더 수정
    public void modifyCalendar(CalendarVO calendarVO);

    // 일정 수정
    public void modifySchedule(ScheduleVO scheduleVO, List<Long> memberIds);

    // 투두리스트 수정
    public void modifyTodoList(ToDoListVO toDoListVO);

    // 공유 캘린더 초대 취소
    public void cancelCalendarInvite(Long calendarInviteId);

    // 공유 캘린더 추방
    public void expelCalendarMember(Long calendarGroupMemberId);

    // 일정 추방
    public void expelScheduleMember(Long ScheduleGroupMemberId);

    // 투두리스트 삭제
    public void deleteTodoList(Long todoListId);

    // 캘린더 삭제
    public void deleteCalendar(Long calendarId);

    // 일정 삭제
    public void deleteSchedule(Long scheduleId);
}
