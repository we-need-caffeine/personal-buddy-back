package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.ItemVO;
import com.app.personalbuddyback.mapper.PointShopMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PointShopDAO {
    private final PointShopMapper pointShopMapper;

    public void save(ItemVO itemVO) {
        pointShopMapper.insert(itemVO);
    }

    public void edit(ItemVO itemVO) {
        pointShopMapper.update(itemVO);
    }

    public void delete(Long id) {
        pointShopMapper.delete(id);
    }
}
