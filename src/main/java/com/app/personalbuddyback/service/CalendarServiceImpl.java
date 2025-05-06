package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.repository.CalendarDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class CalendarServiceImpl implements CalendarService {

    private final CalendarDAO calendarDAO;

    // 캘린더 등록
    @Override
    public void registerCalendar(CalendarVO calendarVO) {
        calendarDAO.saveCalendar(calendarVO);
    }

    // 일정 등록
    @Override
    @Transactional
    public void registerSchedule(ScheduleVO scheduleVO, List<Long> memberIds) {
        ScheduleMemberGroupVO scheduleMemberGroupVO = new ScheduleMemberGroupVO();
        calendarDAO.saveScheduleMemberGroup(scheduleMemberGroupVO);
        Long groupId = scheduleMemberGroupVO.getId();
        scheduleVO.setScheduleMemberGroupId(groupId);

        if (memberIds != null && !memberIds.isEmpty()) {
            for(Long memberId : memberIds) {
                ScheduleGroupMemberVO scheduleGroupMemberVO = new ScheduleGroupMemberVO();
                scheduleGroupMemberVO.setMemberId(memberId);
                scheduleGroupMemberVO.setScheduleMemberGroupId(groupId);
                calendarDAO.saveScheduleGroupMember(scheduleGroupMemberVO);
            }
        }
        calendarDAO.saveSchedule(scheduleVO);
    }

    // 투두리스트 할일 등록
    @Override
    public void registerTodoList(ToDoListVO toDoListVO) {
        calendarDAO.saveTodoList(toDoListVO);
    }

    // 공유 캘린더 초대
    @Override
    public void inviteCalendar(CalendarInviteVO calendarInviteVO) {
        calendarDAO.saveCalendarInvite(calendarInviteVO);
    }

    // 공유 캘린더 멤버 등록
    @Override
    public void addCalendarMember(CalendarMemberVO calendarMemberVO) {
        calendarDAO.saveCalendarMember(calendarMemberVO);
    }

    // 공유 일정 멤버 등록
    @Override
    public void addScheduleMember(ScheduleGroupMemberVO scheduleGroupMemberVO) {
        calendarDAO.saveScheduleGroupMember(scheduleGroupMemberVO);
    }

    // 공유 일정 멤버 조회
    public List<MemberVO> getScheduleMembers(Long scheduleMemberGroupId) {
        return calendarDAO.findAllScheduleGroupMembersByScheduleMemberGroupId(scheduleMemberGroupId);
    }

    // 추가 가능한 공유 일정 멤버 조회
    @Override
    @Transactional
    public List<MemberVO> getAvailableScheduleMembers(Long calendarId, Long scheduleMemberGroupId) {
        List<MemberVO> calendarMembers = calendarDAO.findAllCalendarMembersByCalendarId(calendarId);
        List<MemberVO> scheduleMembers = calendarDAO.findAllScheduleGroupMembersByScheduleMemberGroupId(scheduleMemberGroupId);

        return calendarMembers.stream()
                .filter(calendarMember -> {
                    boolean exists = false;
                    for (MemberVO scheduleMember : scheduleMembers) {
                        if (scheduleMember.getId().equals(calendarMember.getId())) {
                            exists = true;
                            break;
                        }
                    }
                    return !exists;
                })
                .toList();
    }


    // 캘린더 멤버 전체 조회
    @Override
    public List<MemberVO> getCalendarMembers(Long calendarId) {
        return calendarDAO.findAllCalendarMembersByCalendarId(calendarId);
    }

    // 캘린더 추가 가능 멤버 조회
    @Override
    public List<MemberVO> getMutualFollowings(Long memberId) {
        return calendarDAO.findMutualFollowingsByMemberId(memberId);
    }

    // 캘린더 전체 조회
    @Override
    public List<CalendarVO> getCalendars(Long memberId) {
        return calendarDAO.findAllCalendarsByMemberId(memberId);
    }

    // 캘린더 단일 조회
    @Override
    public Optional<CalendarVO> getCalendar(Long calendarId) {
        return calendarDAO.findCalendar(calendarId);
    }

    // 일정 전체 조회
    @Override
    public List<CalendarViewDTO> getSchedules(Long memberId) {
        return calendarDAO.findAllSchedulesByMemberId(memberId);
    }

    // 일정 단일 조회
    @Override
    public Optional<ScheduleVO> getSchedule(Long scheduleId) {
        return calendarDAO.findSchedule(scheduleId);
    }

    // 캘린더 단일 조회(알람)
    @Override
    public Optional<CalendarViewDTO> getCalendarForAlarm(Long calendarId) {
        return calendarDAO.findCalendarDetailByMemberId(calendarId);
    }

    // 투두리스트 전체 조회
    @Override
    public List<ToDoListVO> getTodoLists(Long calendarId) {
        return calendarDAO.findAllToDoListByCalendarId(calendarId);
    }

    // 초대 승인
    @Override
    public void approveCalendarInvite(Long calendarInviteId) {
        CalendarInviteVO calendarInviteVO = new CalendarInviteVO();
        calendarInviteVO.setCalendarId(calendarInviteId);
        calendarInviteVO.setCalendarInviteIsApproved(1);
        calendarDAO.updateCalendarInvite(calendarInviteVO);
    }

    // 초대 거부
    @Override
    public void rejectCalendarInvite(Long calendarInviteId) {
        CalendarInviteVO calendarInviteVO = new CalendarInviteVO();
        calendarInviteVO.setCalendarId(calendarInviteId);
        calendarInviteVO.setCalendarInviteIsApproved(0);
        calendarDAO.updateCalendarInvite(calendarInviteVO);
    }

    // 캘린더 수정
    @Override
    public void modifyCalendar(CalendarVO calendarVO) {
        calendarDAO.updateCalendar(calendarVO);
    }

    // 일정 수정
    @Override
    @Transactional
    public void modifySchedule(ScheduleVO scheduleVO, List<Long> memberIds) {
        // 일정 정보 수정
        calendarDAO.updateSchedule(scheduleVO);

        Long groupId = scheduleVO.getScheduleMemberGroupId();

        if (groupId != null) {
            // 기존 공유 멤버 제거
            calendarDAO.deleteAllScheduleGroupMembersByScheduleMemberGroupId(groupId);

            // 새 공유 멤버 추가
            if (memberIds != null && !memberIds.isEmpty()) {
                for (Long memberId : memberIds) {
                    ScheduleGroupMemberVO memberVO = new ScheduleGroupMemberVO();
                    memberVO.setMemberId(memberId);
                    memberVO.setScheduleMemberGroupId(groupId);
                    calendarDAO.saveScheduleGroupMember(memberVO);
                }
            }
        }
    }

    // 투두리스트 수정
    @Override
    public void modifyTodoList(ToDoListVO toDoListVO) {
        calendarDAO.updateTodoList(toDoListVO);
    }

    // 공유 캘린더 초대 취소
    @Override
    public void cancelCalendarInvite(Long calendarInviteId) {
        calendarDAO.deleteCalendarInvite(calendarInviteId);
    }

    // 공유 캘린더 추방
    @Override
    public void expelCalendarMember(Long calendarGroupMemberId) {
        calendarDAO.deleteCalendarMember(calendarGroupMemberId);
    }

    // 일정 추방
    @Override
    public void expelScheduleMember(Long ScheduleGroupMemberId) {
        calendarDAO.deleteScheduleGroupMember(ScheduleGroupMemberId);
    }

    // 투두리스트 삭제
    @Override
    public void deleteTodoList(Long todoListId) {
        calendarDAO.deleteTodoList(todoListId);
    }

    // 캘린더 삭제
    @Override
    @Transactional
    public void deleteCalendar(Long calendarId) {
        calendarDAO.deleteAllScheduleGroupMembersByCalendarId(calendarId);
        calendarDAO.deleteScheduleMemberGroupByCalendarId(calendarId);
        calendarDAO.deleteAllSchedulesByCalendarId(calendarId);
        calendarDAO.deleteAllTodoListsByCalendarId(calendarId);
        calendarDAO.deleteAllCalendarMembersByCalendarId(calendarId);
        calendarDAO.deleteAllCalendarInvitesByCalendarId(calendarId);
        calendarDAO.deleteCalendarById(calendarId);
    }

    // 일정 삭제
    @Override
    @Transactional
    public void deleteSchedule(Long scheduleId) {
        calendarDAO.deleteAllScheduleGroupMembersByScheduleId(scheduleId);
        calendarDAO.deleteScheduleMemberGroupByScheduleId(scheduleId);
        calendarDAO.deleteScheduleByScheduleId(scheduleId);
    }
}
