package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CartVO {
    private Long id;
    private Long itemId;
    private int buyItemCount;
    private Long memberId;
}
