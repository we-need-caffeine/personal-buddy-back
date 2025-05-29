package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CalendarMemberVO {
    private Long id;
    private Integer calendarMemberIsHost;
    private Long calendarId;
    private Long memberId;
}
