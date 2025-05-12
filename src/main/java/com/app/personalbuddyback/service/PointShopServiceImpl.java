package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.ItemVO;
import com.app.personalbuddyback.mapper.PointShopMapper;
import com.app.personalbuddyback.repository.PointShopDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class PointShopServiceImpl implements PointShopService {
    private final PointShopDAO pointShopDAO;

    @Override
    public void itemAdd(ItemVO itemVO) {
        pointShopDAO.save(itemVO);
    }

    @Override
    public void itemEdit(ItemVO itemVO) {
        pointShopDAO.edit(itemVO);
    }

    @Override
    public void itemDelete(Long id) {
        pointShopDAO.delete(id);
    }
}
