package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.CalendarInviteVO;
import com.app.personalbuddyback.domain.CalendarMemberVO;
import com.app.personalbuddyback.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CalendarMemberMapper {

    // 캘린더 초대 등록
    public void insertCalendarInvite(CalendarInviteVO calendarInviteVO);

    // 캘린더 멤버 등록
    public void insertCalendarMember(CalendarMemberVO calendarMemberVO);

    // 팔로잉 전체 조회 By MemberId
    public List<MemberVO> selectAllMutualFollowingsByMemberId(Long memberId);

    // 캘린더 멤버 전체 조회
    public List<MemberVO> selectAllCalendarMembersByCalendarId(Long calendarId);

    // 캘린더 초대 승인 여부 수정
    public void updateCalendarInvite(CalendarInviteVO calendarInviteVO);

    // 캘린더 멤버 전체 삭제 By CalendarId
    public void deleteAllCalendarMembersByCalendarId(Long calendarId);

    // 캘린더 초대 전체 삭제 By CalendarId
    public void deleteAllCalendarInvitesByCalendarId(Long calendarId);

    // 캘린더 멤버 삭제
    public void deleteCalendarMember(Long calendarMemberId);
}
