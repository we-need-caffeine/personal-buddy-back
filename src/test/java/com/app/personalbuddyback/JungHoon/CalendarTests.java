package com.app.personalbuddyback.JungHoon;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.mapper.CalendarMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Slf4j
public class CalendarTests {
    @Autowired
    private CalendarMapper calendarMapper;

    // 투두리스트 등록 테스트
    @Test
    public void insertTodoListTest() {
        ToDoListVO toDoListVO = new ToDoListVO();
        calendarMapper.insertTodoList(toDoListVO);
        log.info("Inserted TodoList ID: {}", toDoListVO.getId());
    }

    // 캘린더 등록 테스트
    @Test
    public void insertCalendarTest() {
        CalendarVO calendarVO = new CalendarVO();
        calendarVO.setCalendarTitle("Test Calendar3");
        calendarVO.setCalendarIndex(1);
        calendarVO.setCalendarIsGroup(0);
        calendarVO.setCalendarMemberId(2L);

        calendarMapper.insertCalendar(calendarVO);

        log.info("Inserted Calendar: {}", calendarVO);
    }

    // 캘린더 초대 그룹 등록 테스트
    @Test
    public void insertCalendarInviteTest() {
        CalendarInviteVO inviteVO = new CalendarInviteVO();
        inviteVO.setCalendarInviteInvitedMemberId(2L);
        inviteVO.setCalendarInviteHostId(1L);
        inviteVO.setCalendarInviteIsApproved(0);
        inviteVO.setCalendarId(1L);
        calendarMapper.insertCalendarInvite(inviteVO);
        log.info("Inserted CalendarInviteGroup: {}", inviteVO);
    }

    // 캘린더 멤버 그룹 등록 테스트
    @Test
    public void insertCalendarMemberGroupTest() {
        CalendarMemberVO memberGroupVO = new CalendarMemberVO();
        memberGroupVO.setCalendarMemberGroupIsHost(0);
        memberGroupVO.setCalendarId(1L);
        memberGroupVO.setMemberId(4L);
        calendarMapper.insertCalendarMember(memberGroupVO);
        log.info("Inserted CalendarMemberGroup: {}", memberGroupVO);
    }

    // 일정 등록 테스트
    @Test
    public void insertScheduleTest() {
        // 1. ScheduleMemberGroup 먼저 생성
        ScheduleMemberGroupVO scheduleMemberGroupVO = new ScheduleMemberGroupVO();
        calendarMapper.insertScheduleMemberGroup(scheduleMemberGroupVO);

        // 2. ScheduleVo 세팅
        ScheduleVO scheduleVO = new ScheduleVO();
        scheduleVO.setScheduleTitle("밥먹기");
        scheduleVO.setScheduleContent("오랜 친구 길동이와 오랜만에 보기로함 !");
        scheduleVO.setScheduleCategory("한식");
        Date date = new Date();
        scheduleVO.setScheduleStartDate(date);
        scheduleVO.setScheduleEndDate(date);
        scheduleVO.setScheduleCreatedDate(date);
        scheduleVO.setCalendarId(1L);
        scheduleVO.setScheduleMemberGroupId(scheduleMemberGroupVO.getId());

        // 3. insert 실행
        calendarMapper.insertSchedule(scheduleVO);

        // 4. 로그 확인
        log.info("Inserted Schedule: {}", scheduleVO);
    }

    // 일정 전체 불러오기 테스트
    @Test
    public void selectSchedulesTest() {
        Long memberId = 1L;
        List<CalendarViewDTO> scheduleList = calendarMapper.selectAllSchedulesByMemberId(memberId);
        log.info("Schedule lists: {}", scheduleList);
    }

    // 일정 단일 조회
    @Test
    public void selectScheduleTest() {
        Long scheduleId = 22L;
        Optional<ScheduleVO> scheduleVO = calendarMapper.selectSchedule(scheduleId);
        log.info("Schedule Details: {}", scheduleVO);
    }

    // 팔로잉 리스트 불러오기 테스트
    @Test
    public void selectMutualFollowingsTest() {
        List<MemberVO> mutualFollowings = calendarMapper.selectAllMutualFollowingsByMemberId(1L);
        log.info("Mutual Followings: {}", mutualFollowings);
    }

    // 캘린더 정보 불러오기 테스트
    @Test
    public void selectCalendarDetailTest() {
        Optional<CalendarViewDTO> calendarDetails = calendarMapper.selectCalendarDetailByMemberId(2L);
        log.info("Calendar Details: {}", calendarDetails);
    }

    // 캘린더 리스트 불러오기 테스트
    @Test
    public void selectCalendersTest() {
        List<CalendarVO> calendarList = calendarMapper.selectAllCalendarsByMemberId(2L);
        log.info("Calendar List: {}", calendarList);
    }

    // 캘린더 멤버 그룹 불러오기 테스트
    @Test
    public void selectCalendarMembersTest() {
        List<MemberVO> calendarMembers = calendarMapper.selectAllCalendarMembersByCalendarId(1L);
        log.info("Calendar Members: {}", calendarMembers);
    }


    // 초대 승인 여부 업데이트 테스트
    @Test
    public void updateCalendarInviteTest() {
        CalendarInviteVO inviteVO = new CalendarInviteVO();
        inviteVO.setId(1L);
        inviteVO.setCalendarInviteIsApproved(1); // 승인
        calendarMapper.updateCalendarInvite(inviteVO);
        log.info("Updated CalendarInvite: {}", inviteVO);
    }

    // 일정 삭제 테스트
    @Test
    public void deleteScheduleTest() {
        calendarMapper.deleteSchedule(21L);
        log.info("Deleted Schedule with ID: 21");
    }

    // 캘린더 초대 삭제 테스트
    @Test
    public void deleteCalendarInviteTest() {
        calendarMapper.deleteCalendarInvite(1L);
        log.info("Deleted CalendarInviteGroupMember with ID: 1");
    }

    // 캘린더 그룹 멤버 삭제 테스트
    @Test
    public void deleteCalendarMemberTest() {
        calendarMapper.deleteCalendarMember(1L);
        log.info("Deleted CalendarGroupMember with ID: 1");
    }

    // 일정 그룹 멤버 삭제 테스트
    @Test
    public void deleteScheduleGroupMemberTest() {
        calendarMapper.deleteScheduleGroupMember(1L);
        log.info("Deleted ScheduleGroupMember with ID: 1");
    }

    // 일정 그룹 멤버 전체 삭제 테스트
    @Test
    public void deleteAllScheduleGroupMembersTest() {
        calendarMapper.deleteAllScheduleGroupMembersByCalendarId(1L);
        log.info("Deleted all ScheduleGroupMembers for Calendar ID: 1");
    }

    // 일정 멤버 그룹 삭제 테스트
    @Test
    public void deleteScheduleMemberGroupTest() {
        calendarMapper.deleteScheduleMemberGroupByCalendarId(1L);
        log.info("Deleted all ScheduleMemberGroups for Calendar ID: 1");
    }

    // 스케줄 삭제 테스트
    @Test
    public void deleteAllSchedulesTest() {
        calendarMapper.deleteAllSchedulesByCalendarId(1L);
        log.info("Deleted all Schedules for Calendar ID: 1");
    }

    // 투두리스트 삭제 테스트
    @Test
    public void deleteAllTodoListsTest() {
        calendarMapper.deleteAllTodoListsByCalendarId(1L);
        log.info("Deleted TodoList for Calendar ID: 1");
    }

    // 유저 그룹 삭제 테스트
    @Test
    public void deleteAllCalendarMembersTest() {
        calendarMapper.deleteAllCalendarMembersByCalendarId(1L);
        log.info("Deleted CalendarMemberGroups for Calendar ID: 1");
    }

    // 초대 삭제 테스트
    @Test
    public void deleteCalendarInviteGroupTest() {
        calendarMapper.deleteAllCalendarInvitesByCalendarId(1L);
        log.info("Deleted CalendarInviteGroups for Calendar ID: 1");
    }

    // 캘린더 삭제 테스트
    @Test
    public void deleteCalendarTest() {
        calendarMapper.deleteCalendarById(1L);
        log.info("Deleted Calendar with ID: 1");
    }
}
