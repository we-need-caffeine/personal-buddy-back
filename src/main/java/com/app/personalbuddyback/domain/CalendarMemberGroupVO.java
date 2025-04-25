package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CalendarMemberGroupVO {
    private Long id;
    private Long calendarMemberGroupInvitedId;
    private Integer calendarMemberGroupIsHost;
    private Long calendarId;
    private Long memberId;
}
