package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class InterestDTO {

    private String interestType;
    private Long memberId;

    private String interestDetailSection;
    private String interestDetailValue;
    private Long interestId;
}
