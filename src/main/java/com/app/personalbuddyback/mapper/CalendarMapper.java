package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CalendarMapper {

    // 캘린더 등록
    public Long insertCalendar(CalendarVO calendarVO);

    // 캘린더 전체 조회 ByMemberId
    public List<CalendarVO> selectAllCalendarsByMemberId(Long memberId);

    // 캘린더 단일 조회
    public Optional<CalendarVO> selectCalendar(Long calendarId);

    //  캘린더 수정
    public void updateCalendar(CalendarVO calendarVO);


    // 캘린더 삭제
    public void deleteCalendar(Long calendarId);

}
