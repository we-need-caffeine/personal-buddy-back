package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
public class CalendarDTO {

//    캘린더 내용
    private Long id;
    private String calendarTitle;
    private Integer calendarIndex;
    private Long memberId;


//    할일 리스트
    private List<ToDoListVO> todoLists;

//    캘린더 공유 리스트
    private List<MemberVO> sharedMemberLists;

//    일정 리스트
    private List<ScheduleDTO> scheduleLists;

//    초대 가능 팔로잉 리스트
    private List<MemberVO> canInviteMemberLists;
}
