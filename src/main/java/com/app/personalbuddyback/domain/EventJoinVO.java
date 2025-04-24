package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class EventJoinVO {
    private Long id;
    private Long eventId;
    private Long memberId;
    private Date eventJoinDate;
}
