package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TreeVO {
    private Long id;
    private Long memberId;
}
