package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class BuyingItemRequestDTO {
    private int totalPrice;
    private boolean deleteCart;
    private List<BuyingItemDTO> buyingItems;
}
