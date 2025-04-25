package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CalendarInviteGroupVO {
    private Long id;
    private Long calendarInviteGroupInvitedMemberId;
    private Long calendarInviteGroupHostId;
    private Integer calendarInviteGroupIsApproved;
}
