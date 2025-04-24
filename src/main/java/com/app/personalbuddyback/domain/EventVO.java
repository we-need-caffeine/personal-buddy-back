package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class EventVO {
    private Long id;
    private String eventTitle;
    private String eventDescription;
    private Date eventCreateDate;
    private int eventViews;
    private int eventGetPoint;
    private Date eventStartDate;
    private Date eventEndDate;
    private Long memberId;
}
