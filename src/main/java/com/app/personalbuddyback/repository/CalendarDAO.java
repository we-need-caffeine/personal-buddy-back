package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.mapper.CalendarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CalendarDAO {

    private final CalendarMapper calendarMapper;

    // 투두리스트 할일 등록
    public void saveTodoList(ToDoListVO toDoListVO) {
        calendarMapper.insertTodoList(toDoListVO);
    }

    // 캘린더 등록
    public void saveCalendar(CalendarVO calendarVO) {
        calendarMapper.insertCalendar(calendarVO);
    }

    // 스케줄 멤버 그룹 등록
    public void saveScheduleMemberGroup(ScheduleMemberGroupVO scheduleMemberGroupVO) {
        calendarMapper.insertScheduleMemberGroup(scheduleMemberGroupVO);
    }

    // 스케줄 그룹 멤버 등록
    public void saveScheduleGroupMember(ScheduleGroupMemberVO scheduleGroupMemberVO) {
        calendarMapper.insertScheduleGroupMember(scheduleGroupMemberVO);
    }

    // 일정 등록
    public void saveSchedule(ScheduleVO scheduleVO) {
        calendarMapper.insertSchedule(scheduleVO);
    }

    // 캘린더 초대 그룹 등록
    public void saveCalendarInvite(CalendarInviteVO calendarInviteVO) {
        calendarMapper.insertCalendarInvite(calendarInviteVO);
    }

    // 캘린더 멤버 그룹 등록
    public void saveCalendarMember(CalendarMemberVO calendarMemberVO) {
        calendarMapper.insertCalendarMember(calendarMemberVO);
    }

    // 일정 전체 조회
    public List<CalendarViewDTO> findAllSchedulesByMemberId(Long memberId) {
        return calendarMapper.selectAllSchedulesByMemberId(memberId);
    }

    // 일정 단일 조회
    public Optional<ScheduleVO> findSchedule(Long scheduleId) {
        return calendarMapper.selectSchedule(scheduleId);
    }

    // 일정 그룹 멤버 전체 조회
    public List<MemberVO> findAllScheduleGroupMembersByScheduleMemberGroupId(Long scheduleMemberGroupId) {
        return calendarMapper.selectAllScheduleGroupMembersByScheduleMemberGroupId(scheduleMemberGroupId);
    };

    // 팔로잉 전체 조회
    public List<MemberVO> findMutualFollowingsByMemberId(Long memberId) {
        return calendarMapper.selectAllMutualFollowingsByMemberId(memberId);
    }

    // 캘린더 단일 조회(알림)
    public Optional<CalendarViewDTO> findCalendarDetailByMemberId(Long memberId) {
        return calendarMapper.selectCalendarDetailByMemberId(memberId);
    }

    // 캘린더 단일 조회
    public Optional<CalendarVO> findCalendar(Long calendarId) {
        return calendarMapper.selectCalendar(calendarId);
    }

    // 투두리스트 전체 조회
    public List<ToDoListVO> findAllToDoListByCalendarId(Long calendarId) {
        return calendarMapper.selectAllToDoListsByCalendarId(calendarId);
    }

    // 캘린더 전체 조회
    public List<CalendarVO> findAllCalendarsByMemberId(Long memberId) {
        return calendarMapper.selectAllCalendarsByMemberId(memberId);
    }

    // 캘린더 멤버 그룹 조회
    public List<MemberVO> findAllCalendarMembersByCalendarId(Long calendarId) {
        return calendarMapper.selectAllCalendarMembersByCalendarId(calendarId);
    }

    // 초대 승인 여부 업데이트
    public void updateCalendarInvite(CalendarInviteVO calendarInviteVO) {
        calendarMapper.updateCalendarInvite(calendarInviteVO);
    }

    // 캘린더 수정
    public void updateCalendar(CalendarVO calendarVO) {
        calendarMapper.updateCalendar(calendarVO);
    }

    // 일정 수정
    public void updateSchedule(ScheduleVO scheduleVO) {
        calendarMapper.updateSchedule(scheduleVO);
    }

    // 투두리스트 수정
    public void updateTodoList(ToDoListVO toDoListVO) {
        calendarMapper.updateTodoList(toDoListVO);
    }

    // 투두리스트 삭제
    public void deleteTodoList(Long todoListId)
    {
        calendarMapper.deleteTodoList(todoListId);
    }

    // 캘린더 초대 그룹 멤버 삭제
    public void deleteCalendarInvite(Long calendarInviteId) {
        calendarMapper.deleteCalendarInvite(calendarInviteId);
    }

    // 캘린더 그룹 멤버 삭제
    public void deleteCalendarMember(Long calendarGroupMemberId) {
        calendarMapper.deleteCalendarMember(calendarGroupMemberId);
    }

    // 일정 그룹 멤버 삭제
    public void deleteScheduleGroupMember(Long scheduleGroupMemberId) {
        calendarMapper.deleteScheduleGroupMember(scheduleGroupMemberId);
    }

    // 일정 수정 그룹 멤버 삭제
    public void deleteAllScheduleGroupMembersByScheduleMemberGroupId(Long scheduleGroupMemberGroupId) {
        calendarMapper.deleteAllScheduleGroupMembersByScheduleMemberGroupId(scheduleGroupMemberGroupId);
    }

    // 일정 그룹 멤버 전체 삭제
    public void deleteAllScheduleGroupMembersByCalendarId(Long calendarId) {
        calendarMapper.deleteAllScheduleGroupMembersByCalendarId(calendarId);
    }

    // 일정 멤버 그룹 삭제
    public void deleteScheduleMemberGroupByCalendarId(Long calendarId) {
        calendarMapper.deleteScheduleMemberGroupByCalendarId(calendarId);
    }

    // 일정 전체 삭제
    public void deleteAllSchedulesByCalendarId(Long calendarId) {
        calendarMapper.deleteAllSchedulesByCalendarId(calendarId);
    }

    // 투두리스트 전체 삭제
    public void deleteAllTodoListsByCalendarId(Long calendarId) {
        calendarMapper.deleteAllTodoListsByCalendarId(calendarId);
    }

    // 유저 그룹 삭제
    public void deleteAllCalendarMembersByCalendarId(Long calendarId) {
        calendarMapper.deleteAllCalendarMembersByCalendarId(calendarId);
    }

    // 초대 그룹 삭제
    public void deleteAllCalendarInvitesByCalendarId(Long calendarId) {
        calendarMapper.deleteAllCalendarInvitesByCalendarId(calendarId);
    }

    // 캘린더 삭제
    public void deleteCalendarById(Long calendarId) {
        calendarMapper.deleteCalendarById(calendarId);
    }

    // 일정 그룹 멤버 삭제 ByScheduleId
    public void deleteAllScheduleGroupMembersByScheduleId(Long scheduleId) {
        calendarMapper.deleteAllScheduleGroupMembersByScheduleId(scheduleId);
    }

    // 일정 멤버 그룹 삭제 ByScheduleId
    public void deleteScheduleMemberGroupByScheduleId(Long scheduleId) {
        calendarMapper.deleteScheduleMemberGroupByScheduleId(scheduleId);
    }

    // 일정 삭제 ByScheduleId
    public void deleteScheduleByScheduleId(Long scheduleId) {
        calendarMapper.deleteScheduleByScheduleId(scheduleId);
    }

}
