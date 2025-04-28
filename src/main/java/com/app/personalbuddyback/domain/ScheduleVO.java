package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class ScheduleVO {
    private Long id;
    private String scheduleTitle;
    private String scheduleContent;
    private Date scheduleStartDate;
    private Date scheduleEndDate;
    private Date scheduleCreatedDate;
    private String scheduleColor;
    private String scheduleCategory;
    private Integer scheduleRepeat;
    private Integer calendarId;
    private Long scheduleMemberGroupId;
}
