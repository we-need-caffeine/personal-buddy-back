package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.repository.CalendarDAO;

import com.app.personalbuddyback.repository.ScheduleDAO;
import com.app.personalbuddyback.repository.TodoListDAO;
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
    private final ScheduleDAO scheduleDAO;
    private final TodoListDAO todoListDAO;

    // 캘린더 등록
    @Override
    public void registerCalendar(CalendarVO calendarVO) {
        calendarDAO.saveCalendar(calendarVO);
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

    // 캘린더 초대 승인
    @Override
    public void approveCalendarInvite(Long calendarInviteId) {
        CalendarInviteVO calendarInviteVO = new CalendarInviteVO();
        calendarInviteVO.setId(calendarInviteId);
        calendarInviteVO.setCalendarInviteIsApproved(1);
        calendarDAO.updateCalendarInvite(calendarInviteVO);
    }

    // 캘린더 초대 거부
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

    // 캘린더 삭제
    @Override
    @Transactional
    public void deleteCalendar(Long calendarId) {
        scheduleDAO.deleteAllScheduleGroupMembersByCalendarId(calendarId);
        scheduleDAO.deleteScheduleMemberGroupByCalendarId(calendarId);
        scheduleDAO.deleteAllSchedulesByCalendarId(calendarId);
        todoListDAO.deleteAllTodoListsByCalendarId(calendarId);
        calendarDAO.deleteAllCalendarMembersByCalendarId(calendarId);
        calendarDAO.deleteAllCalendarInvitesByCalendarId(calendarId);
        calendarDAO.deleteCalendar(calendarId);
    }

}
