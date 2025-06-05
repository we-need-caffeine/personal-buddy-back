package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface CalendarService {

    // 캘린더 서비스 전체 조회
    public List<CalendarDTO> getCalendarsAll(Long memberId);

    // 캘린더 등록
    public Long registerCalendar(CalendarVO calendarVO);

    // 캘린더 초대
    public void inviteCalendar(List<CalendarInviteVO> invites);

    public void inviteCalendarMember(CalendarInviteVO inviteMember);
    // 캘린더 멤버 등록
    public void addCalendarMember(CalendarMemberVO calendarMemberVO);

    // 캘린더 멤버 조회
    public List<MemberVO> getCalendarMembers(Long calendarId);

    // 캘린더 멤버 전체 조회
    public List<InviteMemberDTO> getMembers(Long calendarId);

    // 캘린더 멤버 추가 초대 조회
    public List<InviteMemberDTO> getInvitableCalendarMembers(Long memberId, Long calendarId);

    // 캘린더 멤버 초대 조회
    public List<MemberVO> getMutualFollowings(Long memberId);

    // 캘린더 초대 이력 조회
    public List<InviteMemberDTO> getMyInvites(Long memberId);

    // 캘린더 단일 초대 조회
    public Optional<InviteMemberDTO> getInviteInfo(Long calendarId, Long hostId);
    // 캘린더 전체 조회
    public List<CalendarVO> getCalendars(Long memberId);

    // 캘린더 단일 조회
    public Optional<CalendarVO> getCalendar(Long calendarId);

    // 캘린더 초대 승인
    public void approveCalendarInvite(Long calendarId, Long memberId);


    // 캘린더 수정
    public void modifyCalendar(CalendarVO calendarVO);

    // 캘린더 초대 취소
    public void cancelCalendarInvite(Long memberId, Long calendarId);

    // 캘린더 멤버 추방
    public void expelCalendarMember(Long memberId, Long calendarId);

    // 캘린더 삭제
    public void deleteCalendar(Long calendarId);



}
