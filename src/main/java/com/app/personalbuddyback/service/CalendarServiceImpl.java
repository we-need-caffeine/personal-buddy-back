package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.repository.CalendarDAO;

import com.app.personalbuddyback.repository.ScheduleDAO;
import com.app.personalbuddyback.repository.TodoListDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class CalendarServiceImpl implements CalendarService {

    private final CalendarDAO calendarDAO;
    private final ScheduleDAO scheduleDAO;
    private final TodoListDAO todoListDAO;
    private final ScheduleService scheduleService;

    @Override
    public List<CalendarDTO> getCalendarsAll(Long memberId) {
        List<CalendarDTO> calendars = new ArrayList<>();
        calendarDAO.findAllCalendarsByMemberId(memberId).forEach(calendar -> {
            List<ScheduleDTO> schedules = new ArrayList<>();
            CalendarDTO calendarDTO = new CalendarDTO();

//            캘린더
            calendarDTO.setId(calendar.getId());
            calendarDTO.setCalendarTitle(calendar.getCalendarTitle());
            calendarDTO.setCalendarIndex(calendar.getCalendarIndex());
            calendarDTO.setMemberId(memberId);
//            할일 리스트
            calendarDTO.setTodoLists(todoListDAO.findAllTodoListByCalendarId(calendar.getId()));

//            캘린더를 공유받는 멤버
            calendarDTO.setSharedMemberLists(calendarDAO.findAllCalendarMembersByCalendarId(calendar.getId()));

//            캘린더 초대 가능 멤버

//            List 생성, 찾은 유저에 맞는 유저의 정보를 List<SchedulesDTO>로 담아서 한번에 보낸다.
//            일정 리스트
            scheduleDAO.findAllSchedulesByCalendarId(calendar.getId()).forEach((member) -> {
                ScheduleDTO scheduleDTO = new ScheduleDTO();
                scheduleDTO.setId(member.getId());
                scheduleDTO.setScheduleTitle(member.getScheduleTitle());
                scheduleDTO.setScheduleContent(member.getScheduleContent());
                scheduleDTO.setScheduleStartDate(member.getScheduleStartDate());
                scheduleDTO.setScheduleEndDate(member.getScheduleEndDate());
                scheduleDTO.setScheduleCreatedDate(member.getScheduleCreatedDate());
                scheduleDTO.setScheduleColor(member.getScheduleColor());
                scheduleDTO.setScheduleCategory(member.getScheduleCategory());
                scheduleDTO.setScheduleRepeat(member.getScheduleRepeat());
                scheduleDTO.setCalendarId(member.getCalendarId());
                schedules.add(scheduleDTO);
            });

            calendarDTO.setScheduleLists(schedules);
            calendars.add(calendarDTO);
        });

        return calendars;
    }

    // 캘린더 등록
    @Override
    public Long registerCalendar(CalendarVO calendarVO) {
        Long calendarId = calendarDAO.saveCalendar(calendarVO);
        Long memberId = calendarVO.getMemberId();
        CalendarMemberVO calendarMemberVO = new CalendarMemberVO();
        calendarMemberVO.setMemberId(memberId);
        calendarMemberVO.setCalendarId(calendarId);
        calendarMemberVO.setCalendarMemberIsHost(1);
        log.info("캘린더 멤버 VO: {}", calendarMemberVO);
        calendarDAO.saveCalendarMember(calendarMemberVO);

        return calendarId;
    }

    // 캘린더 등록 멤버 초대
    @Override
    public void inviteCalendar(List<CalendarInviteVO> invites) {
        for (CalendarInviteVO invite : invites) {
            calendarDAO.saveCalendarInvite(invite);  // 단건 mapper 반복 호출
        }
    }

    // 캘린더 멤더 단일 초대
    @Override
    public void inviteCalendarMember(CalendarInviteVO inviteMember) {
            calendarDAO.saveCalendarInvite(inviteMember);

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

    @Override
    public List<InviteMemberDTO> getInvitableCalendarMembers(Long memberId, Long calendarId) {
        return calendarDAO.findInvitableCalendarMembers(memberId, calendarId);
    }
    // 캘린더 멤버 초대 조회
    @Override
    public List<MemberVO> getMutualFollowings(Long memberId) {
        return calendarDAO.findAllMutualFollowingsByMemberId(memberId);
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

    // 캘린더 초대 이력 조회
    @Override
    public List<InviteMemberDTO> getMyInvites(Long memberId)  {
        return calendarDAO.selectInviteMembersByMemberId(memberId);
    }

    // 캘린더 단일 초대 조회
    @Override
    public Optional<InviteMemberDTO> getInviteInfo(Long calendarId, Long hostId) {
        return calendarDAO.selectInviteInfoByCalendarIdAndHostId(calendarId, hostId);
    }

    // 캘린더 초대 승인
    @Override
    public void approveCalendarInvite(Long calendarId, Long memberId) {
        CalendarInviteVO calendarInviteVO = new CalendarInviteVO();
        calendarInviteVO.setCalendarInviteIsApproved(1);
        calendarInviteVO.setCalendarId(calendarId);
        calendarInviteVO.setCalendarInviteInvitedMemberId(memberId);
        calendarDAO.updateCalendarInvite(calendarInviteVO);

        CalendarMemberVO calendarMemberVO = new CalendarMemberVO();
        calendarMemberVO.setCalendarMemberIsHost(0);
        calendarMemberVO.setCalendarId(calendarId);
        calendarMemberVO.setMemberId(memberId);
        calendarDAO.saveCalendarMember(calendarMemberVO);
    }


    // 캘린더 수정
    @Override
    public void modifyCalendar(CalendarVO calendarVO) {
        calendarDAO.updateCalendar(calendarVO);
    }

    // 공유 캘린더 초대 취소
    @Override
    public void cancelCalendarInvite(Long memberId, Long calendarId) {
        calendarDAO.deleteCalendarInviteMemberByCalendarIdAndMemberId(memberId, calendarId);
    }

    // 공유 캘린더 추방
    @Override
    public void expelCalendarMember(Long memberId, Long calendarId) {
        calendarDAO.deleteCalendarMemberByCalendarIdAndMemberId(memberId, calendarId);
        calendarDAO.deleteCalendarInviteMemberByCalendarIdAndMemberId(memberId, calendarId);
    }

    // 캘린더 삭제
    @Override
    @Transactional
    public void deleteCalendar(Long calendarId) {
        scheduleDAO.deleteAllScheduleMembersByCalendarId(calendarId);
        scheduleDAO.deleteAllSchedulesByCalendarId(calendarId);
        todoListDAO.deleteAllTodoListsByCalendarId(calendarId);
        calendarDAO.deleteAllCalendarMembersByCalendarId(calendarId);
        calendarDAO.deleteAllCalendarInvitesByCalendarId(calendarId);
        calendarDAO.deleteCalendar(calendarId);
    }
}
