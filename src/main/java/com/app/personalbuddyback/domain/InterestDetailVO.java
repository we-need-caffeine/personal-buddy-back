package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class InterestDetailVO {
    private Long id;
    private String interestDetailSection;
    private String interestDetailValue;
    private Long interestId;
}
