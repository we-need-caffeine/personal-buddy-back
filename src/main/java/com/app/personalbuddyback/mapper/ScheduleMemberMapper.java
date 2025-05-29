package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.domain.ScheduleMemberGroupVO;
import com.app.personalbuddyback.domain.ScheduleMemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ScheduleMemberMapper {

    // 일정 멤버 그룹 등록
    public void insertScheduleMemberGroup(ScheduleMemberGroupVO scheduleMemberGroupVO);

    // 일정 초대 가능 멤버 조회
    public List<MemberVO> selectInvitableCalendarMembers(Long memberId,
                                                      Long calendarId);
    // 일정 그룹 멤버 등록
    public void insertScheduleMember(ScheduleMemberVO scheduleMemberVO);

    // 일정 그룹 멤버 전체 조회
    public List<MemberVO> selectAllScheduleMembersByScheduleId(Long scheduleId);

    // 일정 그룹 멤버 삭제
    public void deleteScheduleGroupMember(Long scheduleGroupMemberId);

    // 일정 그룹 멤버 전체 삭제 By ScheduleMemberGroupId
    public void deleteAllScheduleGroupMembersByScheduleMemberGroupId(Long scheduleGroupMemberGroupId);

    // 일정 그룹 멤버 전체 삭제 By CalendarId
    public void deleteAllScheduleMembersByCalendarId(Long calendarId);

    // 일정 그룹 멤버 전체 삭제 By ScheduleId
    public void deleteAllScheduleMembersByScheduleId(Long scheduleId);



}
