package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;

import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    // 일정 등록
    public void registerSchedule(ScheduleSaveDTO scheduleSaveDTO);

    // 일정 멤버 등록
    public void addScheduleMember(ScheduleGroupMemberVO scheduleGroupMemberVO);

    // 일정 멤버 조회
    public List<MemberVO> getScheduleMembers(Long scheduleMemberGroupId);

    // 추가 가능한 일정 멤버 조회
    public List<MemberVO> getAvailableScheduleMembers(Long calendarId, Long scheduleMemberGroupId);

    // 일정 전체 조회
    public List<ScheduleVO> getSchedules(Long memberId);

    // 일정 단일 조회
    public Optional<ScheduleViewDTO> getSchedule(Long scheduleId);

    // 일정 대분류 전체 조회
    public List<ScheduleCategoryVO> getScheduleCategories();

    // 일정 소분류 전체 조회
    public List<ScheduleSubCategoryVO> getScheduleSubCategories(Long scheduleCategoryId);

    // 일정 수정
    public void modifySchedule(ScheduleSaveDTO scheduleSaveDTO);

    // 일정 추방
    public void expelScheduleMember(Long ScheduleGroupMemberId);

    // 일정 삭제
    public void deleteSchedule(Long scheduleId);

}
