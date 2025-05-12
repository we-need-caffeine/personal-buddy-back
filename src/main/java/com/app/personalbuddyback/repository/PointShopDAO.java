package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.BuyItemVO;
import com.app.personalbuddyback.domain.CartVO;
import com.app.personalbuddyback.domain.ItemVO;
import com.app.personalbuddyback.domain.TreeCustomizingVO;
import com.app.personalbuddyback.mapper.PointShopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class PointShopDAO {
    private final PointShopMapper pointShopMapper;

    public void saveItem(ItemVO itemVO) {
        pointShopMapper.insertItem(itemVO);
    }

    public void saveBuyItem(BuyItemVO buyItemVO) {
        pointShopMapper.insertBuyItem(buyItemVO);
    }

    public void saveCart(CartVO cartVO) {
        pointShopMapper.insertCart(cartVO);
    }

    public void saveTreeCustomizing(TreeCustomizingVO treeCustomizingVO){
        pointShopMapper.insertTreeCustomizing(treeCustomizingVO);
    }

    public List<ItemVO> findAllItemsDivideByType(Map<String, Objects> type){
        return pointShopMapper.selectAllItemByType(type);
    }

    public void editItem(ItemVO itemVO) {
        pointShopMapper.updateItem(itemVO);
    }

    public void deleteItem(Long id) {
        pointShopMapper.deleteItem(id);
    }

    public void deleteCart(Long memberId) {
        pointShopMapper.deleteCart(memberId);
    }
}
