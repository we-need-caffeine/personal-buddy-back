package com.app.personalbuddyback.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@Component
public class ScheduleDTO {
    private Long id;
    private String scheduleTitle;
    private String scheduleContent;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX", timezone = "Asia/Seoul")
    private Date scheduleStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX", timezone = "Asia/Seoul")
    private Date scheduleEndDate;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX", timezone = "Asia/Seoul")
    private Date scheduleCreatedDate;

    private String scheduleColor;
    private String scheduleCategory;
    private Integer scheduleRepeat;
    private Long calendarId;
    private List<MemberVO> scheduleMembers;

}
