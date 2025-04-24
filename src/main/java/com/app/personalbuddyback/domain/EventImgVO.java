package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class EventImgVO {
    private Long id;
    private String eventImgPath;
    private String eventImgName;
    private Long eventId;
}
