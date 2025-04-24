package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BuyItemVO {
    private Long id;
    private Long itemId;
    private int buyItemCount;
    private Long memberId;
}
