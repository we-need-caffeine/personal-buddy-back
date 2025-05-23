package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ItemVO {
    private Long id;
    private String itemName;
    private int itemPrice;
    private String itemImgPath;
    private String itemImgName;
    private String itemType;
    private int itemSizeWidth;
    private int itemSizeHeight;
}
