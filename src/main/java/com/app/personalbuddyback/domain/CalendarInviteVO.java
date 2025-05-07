package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CalendarInviteVO {
    private Long id;
    private Long calendarInviteInvitedMemberId;
    private Long calendarInviteHostId;
    private Integer calendarInviteIsApproved;
    private Long calendarId;
}
