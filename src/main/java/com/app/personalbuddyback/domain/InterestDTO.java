package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class InterestDTO {

    private String interestType;
    private Long memberId;

    private String interestDetailInformation;
    private String interestDetailPlace;
    private String interestDetailShopping;
    private Long interestId;
}
