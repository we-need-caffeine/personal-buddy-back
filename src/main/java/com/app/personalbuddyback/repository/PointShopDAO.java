package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.mapper.PointShopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PointShopDAO {
    private final PointShopMapper pointShopMapper;

    // 포인트샵 아이템 추가 (관리자)
    public void saveItem(ItemVO itemVO) {
        pointShopMapper.insertItem(itemVO);
    }

    // 아이템 구매 추가
    public void saveBuyItem(BuyItemVO buyItemVO) {
        pointShopMapper.insertBuyItem(buyItemVO);
    }

    // 장바구니 담기(추가)
    public void saveCart(CartVO cartVO) {
        pointShopMapper.insertCart(cartVO);
    }

    // 아이템 목록 SELECT
    public List<ItemVO> findAllItems(String itemType) {
        return pointShopMapper.selectAllItems(itemType);
    }
    
    // 아이템 한개 정보 조회
    public Optional<ItemVO> findItemById(Long id){
        return pointShopMapper.selectItemById(id);
    }

    // 소유하고 있는 아이템 구분할 수 있는 데이터 조회 (포인트 샵 화면)
    public List<PointShopViewDTO> findPointShopItems(Map<String, Object> params) {
        return pointShopMapper.selectPointShopItems(params);
    }

    // 장바구니 정보 조회(장바구니 화면에 담을 정보) 아이템 개별 총합 가격
    public List<BuyingItemDTO> findAllCartItems(Long memberId) {
        return pointShopMapper.selectAllCartItems(memberId);
    }

    // 장바구니 아이템 단일 조회
    public Optional<CartVO> findCartItemById(Long id) {
        return pointShopMapper.selectCartItemById(id);
    }

    // 아이템 수정 (관리자)
    public void updateItem(ItemVO itemVO) {
        pointShopMapper.updateItem(itemVO);
    }

    // 장바구니 아이템 변경 (수량만, 장바구니 제거는 삭제를 통해 제거)
    public void updateCartItem(CartVO cartVO) {
        pointShopMapper.updateCartItem(cartVO);
    }

    // 아이템 삭제(관리자)
    public void deleteItem(Long id) {
        pointShopMapper.deleteItem(id);
    }

    // 장바구니 전체 아이템 삭제 (장바구니에서 구매 시 / 회원 탈퇴 시)
    public void deleteAllCartItems(Long memberId) {
        pointShopMapper.deleteAllCartItems(memberId);
    }

    // 장바구니 아이템 개별 삭제 (장바구니 편집 시)
    public void deleteCartItem(Long id) {
        pointShopMapper.deleteCartItem(id);
    }
}
