package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class InterestDetailVO {
    private Long id;
    private String interestDetailInformation;
    private String interestDetailPlace;
    private String interestDetailShopping;
    private Long interestId;
}
