package com.app.personalbuddyback.JungHoon;

import com.app.personalbuddyback.domain.CalendarViewDTO;
import com.app.personalbuddyback.domain.ScheduleVO;
import com.app.personalbuddyback.mapper.CalendarMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class Tests {

    @Autowired
    private CalendarMapper calendarMapper;

    //  일정 등록 단위 테스트
    @Test
    public void scheduleInsertTest() {
        ScheduleVO scheduleVO = new ScheduleVO();
        scheduleVO.setCalendarId(1);
        scheduleVO.setScheduleTitle("밥먹기");
        scheduleVO.setScheduleContent("오랜 친구 길동이와 오랜만에 보기로함 !");
        scheduleVO.setScheduleCategory("한식");
        Date date = new Date();
        scheduleVO.setScheduleStartDate(date);
        scheduleVO.setScheduleEndDate(date);
        scheduleVO.setScheduleCreatedDate(date);
        calendarMapper.insert(scheduleVO);
    }

    //  일정 수정 단위 테스트
    @Test
    public void scheduleUpdateTest() {
        ScheduleVO scheduleVO = new ScheduleVO();
        Optional<ScheduleVO> foundSchedule = calendarMapper.selectSchedule(1L);
        if(foundSchedule.isPresent()) {
            scheduleVO = foundSchedule.get();
        }
        scheduleVO.setScheduleContent("화이팅");
        calendarMapper.update(scheduleVO);
    }

    //  일정 단일 조회 단위 테스트
    @Test
    public void scheduleSelectTest() {
        ScheduleVO scheduleVO = new ScheduleVO();
        Optional<ScheduleVO> foundSchedule = calendarMapper.selectSchedule(1L);
        if(foundSchedule.isPresent()) {
            scheduleVO = foundSchedule.get();
        }
        log.info("{}", scheduleVO);
    }

    // 일정 전체 조회 단위 테스트
    @Test
    public void scheduleSelectAllTest() {
        List<CalendarViewDTO> calenderViewDTOList = new ArrayList<>();
        calenderViewDTOList = calendarMapper.selectScheduleAll(1L);
        log.info("{}", calenderViewDTOList);
    }

    // 일정 삭제 단위 테스트
    @Test
    public void deleteTest() {
        calendarMapper.delete( 3L);
    }
}
