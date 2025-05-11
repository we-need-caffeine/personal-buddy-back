package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.mapper.ScheduleMapper;
import com.app.personalbuddyback.mapper.ScheduleMemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ScheduleDAO {

    private final ScheduleMapper scheduleMapper;
    private final ScheduleMemberMapper scheduleMemberMapper;

    // 일정 등록
    public void saveSchedule(ScheduleSaveDTO scheduleSaveDTO) {
        scheduleMapper.insertSchedule(scheduleSaveDTO);
    }

    // 일정 멤버 그룹 등록
    public void saveScheduleMemberGroup(ScheduleMemberGroupVO scheduleMemberGroupVO) {
        scheduleMemberMapper.insertScheduleMemberGroup(scheduleMemberGroupVO);
    }

    // 일정 그룹 멤버 등록
    public void saveScheduleGroupMember(ScheduleGroupMemberVO scheduleGroupMemberVO) {
        scheduleMemberMapper.insertScheduleGroupMember(scheduleGroupMemberVO);
    }

    // 일정 전체 조회 By MemberId
    public List<ScheduleVO> findAllSchedulesByMemberId(Long memberId) {
        return scheduleMapper.selectAllSchedulesByMemberId(memberId);
    }

    // 일정 단일 조회
    public Optional<ScheduleViewDTO> findSchedule(Long scheduleId) {
        return scheduleMapper.selectSchedule(scheduleId);
    }

    // 일정 그룹 멤버 전체 조회 By ScheduleMemberGroupId
    public List<MemberVO> findAllScheduleGroupMembersByScheduleMemberGroupId(Long scheduleMemberGroupId) {
        return scheduleMemberMapper.selectAllScheduleGroupMembersByScheduleMemberGroupId(scheduleMemberGroupId);
    };

    // 일정 대분류 전체 조회
    public List<ScheduleCategoryVO> findAllCategories() {
        return scheduleMapper.selectAllScheduleCategories();
    }

    // 일정 소분류 전체 조회 By CategoryId
    public List<ScheduleSubCategoryVO> findAllSubCategories(Long categoryId) {
        return scheduleMapper.selectAllScheduleSubCategories(categoryId);
    }
    // 일정 수정
    public void updateSchedule(ScheduleSaveDTO scheduleSaveDTO) {
        scheduleMapper.updateSchedule(scheduleSaveDTO);
    }

    // 일정 그룹 멤버 삭제
    public void deleteScheduleGroupMember(Long scheduleGroupMemberId) {
        scheduleMemberMapper.deleteScheduleGroupMember(scheduleGroupMemberId);
    }

    // 일정 그룹 멤버 전체 삭제 By ScheduleMemberGroupId
    public void deleteAllScheduleGroupMembersByScheduleMemberGroupId(Long scheduleGroupMemberGroupId) {
        scheduleMemberMapper.deleteAllScheduleGroupMembersByScheduleMemberGroupId(scheduleGroupMemberGroupId);
    }

    // 일정 그룹 멤버 전체 삭제 By CalendarId
    public void deleteAllScheduleGroupMembersByCalendarId(Long calendarId) {
        scheduleMemberMapper.deleteAllScheduleGroupMembersByCalendarId(calendarId);
    }

    // 일정 멤버 그룹 삭제 By CalendarId
    public void deleteScheduleMemberGroupByCalendarId(Long calendarId) {
        scheduleMemberMapper.deleteScheduleMemberGroupByCalendarId(calendarId);
    }

    // 일정 전체 삭제 By CalendarId
    public void deleteAllSchedulesByCalendarId(Long calendarId) {
        scheduleMapper.deleteAllSchedulesByCalendarId(calendarId);
    }

    // 일정 그룹 멤버 삭제 By ScheduleId
    public void deleteAllScheduleGroupMembersByScheduleId(Long scheduleId) {
        scheduleMemberMapper.deleteAllScheduleGroupMembersByScheduleId(scheduleId);
    }

    // 일정 멤버 그룹 삭제 By ScheduleId
    public void deleteScheduleMemberGroupByScheduleId(Long scheduleId) {
        scheduleMemberMapper.deleteScheduleMemberGroupByScheduleId(scheduleId);
    }

    // 일정 삭제
    public void deleteSchedule(Long scheduleId) {
        scheduleMapper.deleteSchedule(scheduleId);
    }

}
