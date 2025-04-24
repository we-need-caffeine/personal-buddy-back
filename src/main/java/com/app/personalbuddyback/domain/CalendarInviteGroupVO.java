package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CalendarInviteGroupVO {
    private Long id;
    private Long calendarInviteGroupInvitedId;
    private Long calendarInviteGroupHostId;
    private Integer calendarInviteGroupIsApproved;
}
