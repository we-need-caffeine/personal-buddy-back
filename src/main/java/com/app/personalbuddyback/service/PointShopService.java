package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.ItemVO;
import com.app.personalbuddyback.domain.TreeCustomizingVO;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface PointShopService {
    public void itemAdd(ItemVO itemVO);

    public void buyMemberTreeItem(TreeCustomizingVO treeCustomizingVO);

    public List<ItemVO> searchAllItemsByType(Map<String, Objects> type);

    public void itemEdit(ItemVO itemVO);

    public void itemDelete(Long id);
}
