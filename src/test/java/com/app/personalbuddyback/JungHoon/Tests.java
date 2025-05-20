package com.app.personalbuddyback.JungHoon;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.mapper.CalendarMapper;
import com.app.personalbuddyback.repository.CalendarDAO;
import com.app.personalbuddyback.repository.ScheduleDAO;
import com.app.personalbuddyback.repository.TodoListDAO;
import com.app.personalbuddyback.service.CalendarService;
import com.app.personalbuddyback.service.ScheduleService;
import com.app.personalbuddyback.service.TodoListService;
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
    private CalendarService calendarService;

    @Autowired
    CalendarDAO calendarDAO;

    @Autowired
    private TodoListDAO todoListDAO;

    @Autowired
    private ScheduleDAO scheduleDAO;

    @Test
    public void test() {

        List<CalendarDTO> calendars = new ArrayList<>();
        calendarDAO.findAllCalendarsByMemberId(1L).forEach(calendar -> {
            List<ScheduleDTO> schedules = new ArrayList<>();
            CalendarDTO calendarDTO = new CalendarDTO();

//            캘린더
            calendarDTO.setId(calendar.getId());
            calendarDTO.setCalendarIndex(calendar.getCalendarIndex());
            calendarDTO.setMemberId(1L);

//            할일 리스트
            calendarDTO.setTodoLists(todoListDAO.findAllTodoListByCalendarId(calendar.getId()));

//            캘린더를 공유받는 멤버
            calendarDTO.setSharedMemberLists(calendarDAO.findAllCalendarMembersByCalendarId(calendar.getId()));

//            List 생성, 찾은 유저에 맞는 유저의 정보를 List<SchedulesDTO>로 담아서 한번에 보낸다.
//            일정 리스트
            scheduleDAO.findAllSchedulesByMemberId(1L).forEach((member) -> {
                ScheduleDTO scheduleDTO = new ScheduleDTO();
                scheduleDTO.setId(member.getId());
                scheduleDTO.setScheduleTitle(member.getScheduleTitle());
                scheduleDTO.setScheduleContent(member.getScheduleContent());
                scheduleDTO.setScheduleStartDate(member.getScheduleStartDate());
                scheduleDTO.setScheduleEndDate(member.getScheduleEndDate());
                scheduleDTO.setScheduleCreatedDate(member.getScheduleCreatedDate());
                scheduleDTO.setScheduleColor(member.getScheduleColor());
                scheduleDTO.setScheduleCategory(member.getScheduleCategory());
                scheduleDTO.setScheduleRepeat(member.getScheduleRepeat());
                scheduleDTO.setCalendarId(member.getCalendarId());
                scheduleDTO.setScheduleMembers(scheduleDAO.findAllScheduleGroupMembersByScheduleMemberGroupId(calendar.getId()));
                schedules.add(scheduleDTO);
            });

            calendarDTO.setScheduleLists(schedules);

            calendars.add(calendarDTO);
        });

        log.info("캘린더 정보 : {}", calendars.toString());

    }
}
