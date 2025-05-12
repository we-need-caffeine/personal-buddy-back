package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.ItemVO;
import com.app.personalbuddyback.domain.TreeCustomizingVO;
import com.app.personalbuddyback.mapper.PointShopMapper;
import com.app.personalbuddyback.repository.PointShopDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PointShopServiceImpl implements PointShopService {
    private final PointShopDAO pointShopDAO;

    @Override
    public void itemAdd(ItemVO itemVO) {
        pointShopDAO.saveItem(itemVO);
    }

    @Override
    public void buyMemberTreeItem(TreeCustomizingVO treeCustomizingVO) {
        pointShopDAO.saveTreeCustomizing(treeCustomizingVO);
    }

    @Override
    public List<ItemVO> searchAllItemsByType(Map<String, Objects> type){
        return pointShopDAO.findAllItemsDivideByType(type);
    }

    @Override
    public void itemEdit(ItemVO itemVO) {
        pointShopDAO.editItem(itemVO);
    }

    @Override
    public void itemDelete(Long id) {
        pointShopDAO.deleteItem(id);
    }
}
