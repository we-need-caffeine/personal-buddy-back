package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.BuyItemVO;
import com.app.personalbuddyback.domain.CartVO;
import com.app.personalbuddyback.domain.ItemVO;
import com.app.personalbuddyback.domain.TreeCustomizingVO;
import org.apache.ibatis.annotations.Mapper;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface PointShopMapper {

    // 성장 나무 아이템 추가 (관리자 용)
    public void insertItem(ItemVO itemVO);

    public void insertBuyItem(BuyItemVO buyItemVO);

    public void insertCart(CartVO cartVO);

    // 성장나무 추가 (아이템 구매 시)
    public void insertTreeCustomizing(TreeCustomizingVO treeCustomizingVO);

    public List<ItemVO> selectAllItemByType(Map<String, Objects> type);

    // 성장 나무 아이템 수정 (관리자 용)
    public void updateItem(ItemVO itemVO);

    // 성장 나무 아이템 삭제 (관리자 용)
    public void deleteItem(Long id);

    public void deleteCart(Long memberId);
}
