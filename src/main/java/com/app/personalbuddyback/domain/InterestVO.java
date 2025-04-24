package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class InterestVO {
    private Long id;
    private String interestType;
    private Long memberId;
}
