package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.*;
import com.app.personalbuddyback.repository.MemberDAO;
import com.app.personalbuddyback.repository.MyTreeDAO;
import com.app.personalbuddyback.repository.PointDAO;
import com.app.personalbuddyback.repository.PointShopDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class PointShopServiceImpl implements PointShopService {
    private final MemberDAO memberDAO;
    private final PointDAO pointDAO;
    private final PointShopDAO pointShopDAO;
    private final MyTreeDAO myTreeDAO;

    @Override
    public void addItem(ItemVO itemVO) {
        pointShopDAO.saveItem(itemVO);
    }

    @Override
    public void buyItem(List<BuyingItemDTO> buyingItemDTOList, boolean deleteCart , int totalPrice) {
        Long memberId = buyingItemDTOList.get(0).getMemberId();
        Long treeId = myTreeDAO.findTreeIdByMemberId(memberId);
        for (BuyingItemDTO buyingItemDTO : buyingItemDTOList) {
            Long itemId = buyingItemDTO.getItemId();
            int buyItemCount = buyingItemDTO.getBuyItemCount();

            BuyItemVO buyItemVO = new BuyItemVO();
            buyItemVO.setItemId(itemId);
            buyItemVO.setMemberId(memberId);
            buyItemVO.setBuyItemCount(buyItemCount);

            for(int i = 0; i < buyItemCount; i++){
                TreeCustomizingVO treeCustomizingVO = new TreeCustomizingVO();
                treeCustomizingVO.setItemId(itemId);
                treeCustomizingVO.setTreeId(treeId);
                myTreeDAO.saveTreeCustomizing(treeCustomizingVO);
            }
            pointShopDAO.saveBuyItem(buyItemVO);

            if(deleteCart){
                pointShopDAO.deleteCartItem(buyingItemDTO.getId());
            }
        }
        MemberPointLogVO memberPointLogVO = new MemberPointLogVO();
        memberPointLogVO.setMemberId(memberId);
        memberPointLogVO.setMemberPointChangeAmount(-totalPrice);
        memberPointLogVO.setMemberPointChangeDate(new Date());
        memberPointLogVO.setMemberPointReason("아이템 구매");

        pointDAO.savePointLog(memberPointLogVO);

        MemberVO member = memberDAO.selectMemberById(memberId).orElseThrow(() -> {
            return new RuntimeException("멤버 정보가 없습니다.");
        });

        member.setMemberPoint(member.getMemberPoint() - totalPrice);
        memberDAO.update(member);

    }

    @Override
    public void addCart(List<BuyingItemDTO> buyingItemDTOList) {
        Long memberId = buyingItemDTOList.get(0).getMemberId();

        // 기존 장바구니 목록 조회
        List<BuyingItemDTO> cartItems = pointShopDAO.findAllCartItems(memberId);

        for (BuyingItemDTO buyingItemDTO : buyingItemDTOList) {
            Long itemId = buyingItemDTO.getItemId();
            int buyItemCount = buyingItemDTO.getBuyItemCount();

            // 기존 장바구니에 같은 아이템이 있는지 확인
            Optional<BuyingItemDTO> existingCartItem = cartItems.stream()
                    .filter(item -> item.getItemId().equals(itemId))
                    .findFirst();

            if (existingCartItem.isPresent()) {
                // 이미 있으면 → 기존 개수 + 현재 개수로 update
                int newCount = existingCartItem.get().getBuyItemCount() + buyItemCount;

                CartVO cartVO = new CartVO();
                cartVO.setMemberId(memberId);
                cartVO.setItemId(itemId);
                cartVO.setBuyItemCount(newCount);

                pointShopDAO.updateCartItem(cartVO);
            } else {
                // 없으면 → 신규 insert
                CartVO cartVO = new CartVO();
                cartVO.setMemberId(memberId);
                cartVO.setItemId(itemId);
                cartVO.setBuyItemCount(buyItemCount);

                pointShopDAO.saveCart(cartVO);
            }
        }
    }

    @Override
    public List<ItemVO> getAllItems(String itemType) {
        return pointShopDAO.findAllItems(itemType);
    }

    @Override
    public List<PointShopViewDTO> getPointShopItems(Map<String, Object> params) {
        return pointShopDAO.findPointShopItems(params);
    }

    @Override
    public Optional<CartVO> getCartItem(Long id) {
        return pointShopDAO.findCartItemById(id);
    }

    @Override
    public List<BuyingItemDTO> getAllCartItems(Long memberId) {
        return pointShopDAO.findAllCartItems(memberId);
    }

    @Override
    public void editItem(ItemVO itemVO) {
        pointShopDAO.updateItem(itemVO);
    }

    @Override
    public void editCartItem(CartVO cartVO) {
        pointShopDAO.updateCartItem(cartVO);
    }

    @Override
    public void deleteItem(Long id) {
        pointShopDAO.deleteItem(id);
    }

    @Override
    public void deleteCartItem(Long id) {
        pointShopDAO.deleteCartItem(id);
    }
}
