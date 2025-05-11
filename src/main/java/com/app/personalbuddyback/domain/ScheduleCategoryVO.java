package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ScheduleCategoryVO {
    private Long id;
    private String scheduleCategoryTitle;
}
