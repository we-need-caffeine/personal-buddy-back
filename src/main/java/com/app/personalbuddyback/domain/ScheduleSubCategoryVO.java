package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ScheduleSubCategoryVO {
        private Long id;
        private Long scheduleCategoryId;
        private String scheduleSubCategoryTitle;
}
