package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@Component
public class CalendarViewDTO {

//  캘린더 VO
    private Long calendarId;
    private String calendarTitle;
    private Integer calendarIsGroup;
    private Integer calendarIndex;
    private Long memberId;
    private Long todoListId;

//  캘린더 초대 그룹 VO
    private Long calendarInviteInvitedMemberId;
    private Long calendarInviteHostId;
    private Integer calendarInviteIsApproved;

//  캘린더 맴버 그룹 VO
    private Integer calendarMemberIsHost;

//  스케줄 VO
    private ScheduleVO scheduleVO;
    private String scheduleTitle;
    private String scheduleContent;
    private Date scheduleStartDate;
    private Date scheduleEndDate;
    private Date scheduleCreatedDate;
    private String scheduleColor;
    private String scheduleCategory;
    private Integer scheduleRepeat;
    private Long scheduleMemberGroupId;

    private List<Long> memberIds;
}
