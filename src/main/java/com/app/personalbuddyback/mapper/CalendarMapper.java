package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CalendarMapper {

    // 투두리스트 할일 등록
    public void insertTodoList(ToDoListVO toDoListVO);

    // 캘린더 등록
    public void insertCalendar(CalendarVO calendarVO);

    // 일정 멤버 그룹 등록
    public void insertScheduleMemberGroup(ScheduleMemberGroupVO scheduleMemberGroupVO);

    // 일정 그룹 멤버 등록
    public void insertScheduleGroupMember(ScheduleGroupMemberVO scheduleGroupMemberVO);

    // 일정 등록
    public void insertSchedule(ScheduleVO scheduleVO);

    // 캘린더 초대 등록
    public void insertCalendarInvite(CalendarInviteVO calendarInviteVO);

    // 캘린더 멤버 등록
    public void insertCalendarMember(CalendarMemberVO calendarMemberVO);

    // 일정 전체 조회
    public List<CalendarViewDTO> selectAllSchedulesByMemberId(Long memberId);

    // 일정 단일 조회
    public Optional<ScheduleVO> selectSchedule(Long scheduleId);

    // 일정 그룹 멤버 전체 조회
    public List<MemberVO> selectAllScheduleGroupMembersByScheduleMemberGroupId(Long scheduleMemberGroupId);

    // 팔로잉 전체 조회
    public List<MemberVO> selectAllMutualFollowingsByMemberId(Long memberId);

    // 캘린더 단일 조회(알람)
    public Optional<CalendarViewDTO> selectCalendarDetailByMemberId(Long memberId);

    // 캘린더 전체 조회
    public List<CalendarVO> selectAllCalendarsByMemberId(Long memberId);

    // 캘린더 단일 조회
    public Optional<CalendarVO> selectCalendar(Long calendarId);

    // 투두리스트 전체 조회
    public List<ToDoListVO> selectAllToDoListsByCalendarId(Long calendarId);

    // 캘린더 멤버 전체 조회
    public List<MemberVO> selectAllCalendarMembersByCalendarId(Long calendarId);

    // 초대 승인 여부 수정
    public void updateCalendarInvite(CalendarInviteVO calendarInviteVO);

    //  캘린더 수정
    public void updateCalendar(CalendarVO calendarVO);

    // 일정 수정
    public void updateSchedule(ScheduleVO scheduleVO);

    // 투두리스트 수정
    public void updateTodoList(ToDoListVO toDoListVO);

    // 투두리스트 삭제
    public void deleteTodoList(Long todoListId);

    // 일정 삭제
    public void deleteSchedule(Long scheduleId);

    // 캘린더 초대 삭제
    public void deleteCalendarInvite(Long calendarInviteId);

    // 캘린더 멤버 삭제
    public void deleteCalendarMember(Long calendarMemberId);

    // 일정 그룹 멤버 삭제
    public void deleteScheduleGroupMember(Long scheduleGroupMemberId);

    // 일정 수정 그룹 멤버 삭제
    public void deleteAllScheduleGroupMembersByScheduleMemberGroupId(Long scheduleGroupMemberGroupId);

    // 캘린더 삭제
    // 일정 그룹 멤버 전체 삭제
    public void deleteAllScheduleGroupMembersByCalendarId(Long calendarId);

    // 일정 멤버 그룹 삭제
    public void deleteScheduleMemberGroupByCalendarId(Long calendarId);

    // 일정 전체 삭제
    public void deleteAllSchedulesByCalendarId(Long calendarId);

    // 투두리스트 전체 삭제
    public void deleteAllTodoListsByCalendarId(Long calendarId);

    // 유저 그룹 삭제
    public void deleteAllCalendarMembersByCalendarId(Long calendarId);

    // 초대 그룹 전체 삭제
    public void deleteAllCalendarInvitesByCalendarId(Long calendarId);

    // 캘린더 삭제
    public void deleteCalendarById(Long calendarId);

    // 일정 그룹 멤버 삭제 ByScheduleId
    public void deleteAllScheduleGroupMembersByScheduleId(Long scheduleId);

    // 일정 멤버 그룹 삭제 ByScheduleId
    public void deleteScheduleMemberGroupByScheduleId(Long scheduleId);

    // 일정 삭제 ByScheduleId
    public void deleteScheduleByScheduleId(Long scheduleId);


}
