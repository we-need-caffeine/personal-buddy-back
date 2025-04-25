package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.CalendarViewDTO;
import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.domain.ScheduleVO;
import com.app.personalbuddyback.mapper.CalendarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CalendarDAO {

    private final CalendarMapper calendarMapper;

    // 일정 등록
    public void insert(ScheduleVO scheduleVO) {
        calendarMapper.insert(scheduleVO);
    }

    // 일정 수정
    public void update(ScheduleVO scheduleVO) {
        calendarMapper.update(scheduleVO);
    }

    // 일정 삭제
    public void delete(Long id) {
        calendarMapper.delete(id);
    }

    // 일정 전체 불러오기
    public List<CalendarViewDTO> selectAllSchedule(Long memberId) {
        return calendarMapper.selectScheduleAll(memberId);
    }

    // 일정 단일 조회
    public Optional<ScheduleVO> selectScheduleById(Long scheduleId) {
        return calendarMapper.selectSchedule(scheduleId);
    }

    // 팔로잉 검색
    public List<MemberVO> selectAllFollowingMember(Long memberId) {
        return calendarMapper.selectAllFollowingMember(memberId);
    }
}
