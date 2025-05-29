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
    public List<MemberVO> findInvitableCalendarMembers(Long memberId, Long calendarId) {
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

    // 캘린더 멤버 그룹 조회
    public List<MemberVO> findAllCalendarMembersByCalendarId(Long calendarId) {
        return calendarMemberMapper.selectAllCalendarMembersByCalendarId(calendarId);
    }

    // 캘린더 초대 승인 여부 업데이트
    public void updateCalendarInvite(CalendarInviteVO calendarInviteVO) {
        calendarMemberMapper.updateCalendarInvite(calendarInviteVO);
    }

    // 캘린더 수정
    public void updateCalendar(CalendarVO calendarVO) {
        calendarMapper.updateCalendar(calendarVO);
    }

    // 캘린더 초대 그룹 멤버 삭제
    public void deleteCalendarInvite(Long calendarInviteId) {
        calendarMapper.deleteCalendarInvite(calendarInviteId);
    }

    // 캘린더 그룹 멤버 삭제
    public void deleteCalendarMember(Long calendarGroupMemberId) {
        calendarMemberMapper.deleteCalendarMember(calendarGroupMemberId);
    }

    // 유저 그룹 삭제
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
