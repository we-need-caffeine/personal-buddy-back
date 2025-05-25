package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@Component
public class ScheduleSaveDTO {
    private Long id;
    private String scheduleTitle;
    private String scheduleContent;
    private Date scheduleStartDate;
    private Date scheduleEndDate;
    private Date scheduleCreatedDate;
    private String scheduleColor;
    private String scheduleLocation;
    private Integer scheduleRepeat;
    private Long calendarId;

    private Long scheduleCategoryId;
    private Long scheduleSubCategoryId;
    private List<Long> memberIds;
}
