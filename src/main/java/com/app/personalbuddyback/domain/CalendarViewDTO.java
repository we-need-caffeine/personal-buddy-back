package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

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
    private Long calendarInviteGroupInvitedMemberId;
    private Long calendarInviteGroupHostId;
    private Integer calendarInviteGroupIsApproved;

//  캘린더 맴버 그룹 VO
    private Long calendarMemberGroupInvitedId;
    private Integer calendarMemberGroupIsHost;

//  스케줄 VO
    private String scheduleTitle;
    private String scheduleContent;
    private Date scheduleStartDate;
    private Date scheduleEndDate;
    private Date scheduleCreatedDate;
    private String scheduleColor;
    private String scheduleCategory;
    private Integer scheduleRepeat;
}
