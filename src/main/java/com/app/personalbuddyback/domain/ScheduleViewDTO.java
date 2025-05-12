package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@Component
public class ScheduleViewDTO {
    private Long id;
    private String scheduleTitle;
    private String scheduleContent;
    private Date scheduleStartDate;
    private Date scheduleEndDate;
    private Date scheduleCreatedDate;
    private String scheduleColor;
    private String scheduleCategory;
    private Integer scheduleRepeat;
    private Long calendarId;
    private Long scheduleMemberGroupId;

    private String scheduleCategoryTitle;
    private String scheduleSubCategoryTitle;
    private List<Long> memberIds;
}
