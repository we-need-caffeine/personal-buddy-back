package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.CalendarInviteVO;
import com.app.personalbuddyback.domain.CalendarMemberVO;
import com.app.personalbuddyback.domain.InviteMemberDTO;
import com.app.personalbuddyback.domain.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CalendarMemberMapper {

    // 캘린더 초대 등록
    public void insertCalendarInvite(CalendarInviteVO calendarInviteVO);

    // 캘린더 멤버 등록
    public void insertCalendarMember(CalendarMemberVO calendarMemberVO);

    // 캘린더 멤버 추가 초대 조회
    public List<InviteMemberDTO> selectInvitableCalendarMembers( Long memberId, Long calendarId);

    // 캘린더 멤버 초대 조회
    public List<MemberVO> selectAllMutualFollowingsByMemberId(Long memberId);

    // 캘린더 멤버 전체 조회
    public List<MemberVO> selectAllCalendarMembersByCalendarId(Long calendarId);

    // 캘린더 초대 이력 조회
    public List<InviteMemberDTO> selectInvitedMembersByMemberId(Long memberId);

    // 랠린더 단일 초대 조회
    public Optional<InviteMemberDTO> selectInviteInfoByByCalendarIdAndHostId(Long calendarId, Long hostId);

    // 캘린더 초대 승인 여부 수정
    public void updateCalendarInvite(CalendarInviteVO calendarInviteVO);

    // 캘린더 멤버 전체 삭제 By CalendarId
    public void deleteAllCalendarMembersByCalendarId(Long calendarId);

    // 캘린더 초대 전체 삭제 By CalendarId
    public void deleteAllCalendarInvitesByCalendarId(Long calendarId);

    // 캘린더 멤버 삭제
    public void deleteCalendarMemberByCalendarIdAndMemberId(Long memberId, Long calendarId);

    // 캘린더 초대 멤버 삭제
    public void deleteCalendarInviteMemberByCalendarIdAndMemberId(Long memberId, Long calendarId);
}
