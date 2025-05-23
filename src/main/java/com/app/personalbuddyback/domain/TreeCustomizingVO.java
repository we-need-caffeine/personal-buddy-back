package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TreeCustomizingVO {
    private Long id;
    private Long itemId;
    private int treeCustomizingPositionX;
    private int treeCustomizingPositionY;
    private int treeCustomizingApply;
    private Long treeId;
}
