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
    public void saveScheduleMember(ScheduleMemberVO scheduleMemberVO) {
        scheduleMemberMapper.insertScheduleMember(scheduleMemberVO);
    }

    // 일정 전체 조회 By CalendarId
    public List<ScheduleVO> findAllSchedulesByCalendarId(Long calendarId) {
        return scheduleMapper.selectAllSchedulesByCalendarId(calendarId);
    }


    // 일정 단일 조회
    public Optional<ScheduleViewDTO> findSchedule(Long scheduleId) {
        return scheduleMapper.selectSchedule(scheduleId);
    }

    // 일정 그룹 멤버 전체 조회 By ScheduleMemberGroupId
    public List<MemberVO> findAllScheduleMembersByScheduleId(Long scheduleMemberId) {
        return scheduleMemberMapper.selectAllScheduleMembersByScheduleId(scheduleMemberId);
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
    public void deleteScheduleMember(Long scheduleMemberId) {
        scheduleMemberMapper.deleteScheduleGroupMember(scheduleMemberId);
    }

    // 일정 그룹 멤버 전체 삭제 By CalendarId
    public void deleteAllScheduleMembersByCalendarId(Long calendarId) {
        scheduleMemberMapper.deleteAllScheduleMembersByCalendarId(calendarId);
    }

    // 일정 전체 삭제 By CalendarId
    public void deleteAllSchedulesByCalendarId(Long calendarId) {
        scheduleMapper.deleteAllSchedulesByCalendarId(calendarId);
    }

    // 일정 그룹 멤버 삭제 By ScheduleId
    public void deleteAllScheduleMembersByScheduleId(Long scheduleId) {
        scheduleMemberMapper.deleteAllScheduleMembersByScheduleId(scheduleId);
    }

    // 일정 삭제
    public void deleteSchedule(Long scheduleId) {
        scheduleMapper.deleteSchedule(scheduleId);
    }

}
