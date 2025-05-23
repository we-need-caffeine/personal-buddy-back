package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TreeItemCountDTO {
    private Long memberId;
    private Long itemId;
    private int totalCount;
    private int appliedCount;
    private int notAppliedCount;
}
