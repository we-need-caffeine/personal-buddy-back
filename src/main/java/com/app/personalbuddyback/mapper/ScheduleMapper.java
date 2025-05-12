package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ScheduleMapper {

    // 일정 등록
    public void insertSchedule(ScheduleSaveDTO scheduleSaveDTO);

    // 일정 전체 조회
    public List<ScheduleVO> selectAllSchedulesByMemberId(Long memberId);

    // 일정 단일 조회
    public Optional<ScheduleViewDTO> selectSchedule(Long scheduleId);

    // 일정 대분류 조회
    public List<ScheduleCategoryVO> selectAllScheduleCategories();

    // 일정 소분류 전체 조회 By CategoryId
    public List<ScheduleSubCategoryVO> selectAllScheduleSubCategories(Long categoryId);

    // 일정 수정
    public void updateSchedule(ScheduleSaveDTO scheduleSaveDTO);

    // 일정 전체 삭제 By CalendarId
    public void deleteAllSchedulesByCalendarId(Long calendarId);

    // 일정 삭제
    public void deleteSchedule(Long scheduleId);
}
