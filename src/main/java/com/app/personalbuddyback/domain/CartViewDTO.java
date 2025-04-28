package com.app.personalbuddyback.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CartViewDTO {
    private Long id; // 카트 id
    private Long itemId; // 아이템의 id
    private Long memberId; // 구매하는 이용자의 id
    private String itemImgPath; // 아이템 이미지의 경로
    private String itemImgName; // 아이템 이미지의 이름
    private int buyItemCount; // 구매 아이템의 개수
    private String itemType; // 아이템의 타입
    private int itemPrice; // 구매 아이템의 가격
    private int totalPrice; // 구매 아이템의 총 가격
}
