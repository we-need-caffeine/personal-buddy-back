package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;

import java.util.List;
import java.util.Optional;

public interface PointShopService {

    // 포인트샵 아이템 추가 (관리자)
    public void addItem(ItemVO itemVO);

    // 아이템 구매 추가
    public void buyItem(List<BuyingItemDTO> buyingItemDTOList, boolean deleteCart, int totalPrice);

    // 장바구니 담기(추가)
    public void addCart(List<BuyingItemDTO> buyingItemDTOList);

    // 아이템 목록 SELECT
    public List<ItemVO> getAllItems(String itemType);
    
    // 소유하고 있는 아이템 구분할 수 있는 데이터 조회 (포인트 샵 화면)
    public List<PointShopViewDTO> getPointShopItems(Long memberId);

    // 장바구니 정보 조회(장바구니 화면에 담을 정보) 아이템 개별 총합 가격
    public List<BuyingItemDTO> getAllCartItems(Long memberId);

    // 장바구니 아이템 단일 조회
    public Optional<CartVO> getCartItem(Long id);

    // 아이템 수정 (관리자)
    public void editItem(ItemVO itemVO);

    // 장바구니 아이템 변경 (수량만, 장바구니 제거는 삭제를 통해 제거)
    public void editCartItem(CartVO cartVO);

    // 아이템 삭제(관리자)
    public void deleteItem(Long id);

    // 장바구니 아이템 개별 삭제 (장바구니 편집 시)
    public void deleteCartItem(Long id);
}
