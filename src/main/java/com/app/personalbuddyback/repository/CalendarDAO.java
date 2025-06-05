package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.mapper.CalendarMapper;
import com.app.personalbuddyback.mapper.CalendarMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CalendarDAO {

    private final CalendarMapper calendarMapper;
    private final CalendarMemberMapper calendarMemberMapper;

    // 캘린더 등록
    public Long saveCalendar(CalendarVO calendarVO) {
        calendarMapper.insertCalendar(calendarVO);
        return calendarVO.getId();
    }

    // 캘린더 초대 그룹 등록
    public void saveCalendarInvite(CalendarInviteVO calendarInviteVO) {
        calendarMemberMapper.insertCalendarInvite(calendarInviteVO);
    }

    // 캘린더 멤버 그룹 등록
    public void saveCalendarMember(CalendarMemberVO calendarMemberVO) {
        calendarMemberMapper.insertCalendarMember(calendarMemberVO);
    }

    // 켈린더 멤버 추가 초대 조회
    public List<InviteMemberDTO> findInvitableCalendarMembers(Long memberId, Long calendarId) {
        return calendarMemberMapper.selectInvitableCalendarMembers(memberId, calendarId);
    }

    // 켈린더 멤버 초대 조회
    public List<MemberVO> findAllMutualFollowingsByMemberId(Long memberId) {
        return calendarMemberMapper.selectAllMutualFollowingsByMemberId(memberId);
    }
    // 캘린더 단일 조회
    public Optional<CalendarVO> findCalendar(Long calendarId) {
        return calendarMapper.selectCalendar(calendarId);
    }

    // 캘린더 전체 조회
    public List<CalendarVO> findAllCalendarsByMemberId(Long memberId) {
        return calendarMapper.selectAllCalendarsByMemberId(memberId);
    }

    // 캘린더 멤버 전체 조회
    public List<MemberVO> findAllCalendarMembersByCalendarId(Long calendarId) {
        return calendarMemberMapper.selectAllCalendarMembersByCalendarId(calendarId);
    }

    // 캘린더 멤버 전체 조회
    public List<InviteMemberDTO> findAllMembersByCalendarId(Long calendarId) {
        return calendarMemberMapper.selectAllMembersByCalendarId(calendarId);
    }

    // 캘린더 초대 이력 조회
    public List<InviteMemberDTO> selectInviteMembersByMemberId(Long memberId) {
        return calendarMemberMapper.selectInvitedMembersByMemberId(memberId);
    }

    // 캘린더 단일 초대 조회
    public Optional<InviteMemberDTO> selectInviteInfoByCalendarIdAndHostId(Long calendarId, Long hostId) {
        return calendarMemberMapper.selectInviteInfoByByCalendarIdAndHostId(calendarId, hostId);
    }

    // 캘린더 초대 승인 여부 업데이트
    public void updateCalendarInvite(CalendarInviteVO calendarInviteVO) {
        calendarMemberMapper.updateCalendarInvite(calendarInviteVO);
    }

    // 캘린더 수정
    public void updateCalendar(CalendarVO calendarVO) {
        calendarMapper.updateCalendar(calendarVO);
    }

    // 캘린더 초대 멤버 삭제
    public void deleteCalendarInviteMemberByCalendarIdAndMemberId(Long memberId, Long calendarId) {
        calendarMemberMapper.deleteCalendarInviteMemberByCalendarIdAndMemberId(memberId, calendarId);
    }

    // 캘린더 그룹 멤버 삭제
    public void deleteCalendarMemberByCalendarIdAndMemberId(Long memberId, Long calendarId) {
        calendarMemberMapper.deleteCalendarMemberByCalendarIdAndMemberId(memberId, calendarId);
    }

    // 캘린더 멤버 전체 삭제
    public void deleteAllCalendarMembersByCalendarId(Long calendarId) {
        calendarMemberMapper.deleteAllCalendarMembersByCalendarId(calendarId);
    }

    // 초대 그룹 삭제
    public void deleteAllCalendarInvitesByCalendarId(Long calendarId) {
        calendarMemberMapper.deleteAllCalendarInvitesByCalendarId(calendarId);
    }

    // 캘린더 삭제
    public void deleteCalendar(Long calendarId) {
        calendarMapper.deleteCalendar(calendarId);
    }

}
