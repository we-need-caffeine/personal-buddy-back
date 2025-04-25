package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.CalendarViewDTO;
import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.domain.ScheduleVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CalendarMapper {

    // 일정 등록
    public void insert(ScheduleVO scheduleVO);

    // 일정 수정
    public void update(ScheduleVO scheduleVO);

    // 일정 삭제
    public void delete(Long id);

    // 일정 전체 불러오기
    public List<CalendarViewDTO> selectScheduleAll(Long memberId);

    // 일정 단일 조회
    public Optional<ScheduleVO> selectSchedule(Long scheduleId);

    // 상호 팔로잉 멤버 조회
    public List<MemberVO> selectAllFollowingMember(Long memberId);
}
