package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TreeViewDTO {
    private Long id; // Tree id (나무 정보를 가지고 올 수 있는 id)
    private Long memberId; // member id (해당 나무정보를 들고있는 멤버를 확인하는 id)
    private Long itemId; // item id (아이템의 이름, 타입, 이미지 정보를 확인할 item의 id)
    private Long treeCustomizingId; // treeCustomizing id (나무 정보를 가져올 id)
    private double treeCustomizingPositionX; // treeCustomizing item 의 X좌표 정보
    private double treeCustomizingPositionY; // treeCustomizing item 의 Y좌표 정보
    private int treeCustomizingApply; // treeCustomizing 화면 적용 여부
    private String itemName; // item 의 이름
    private String itemType; // item 의 타입
    private String itemImgPath; // item 의 이미지 경로
    private String itemImgName; // item 의 이미지 파일 이름
}
