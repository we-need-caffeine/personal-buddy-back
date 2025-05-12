package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.ItemVO;

public interface PointShopService {
    public void itemAdd(ItemVO itemVO);

    public void itemEdit(ItemVO itemVO);

    public void itemDelete(Long id);
}
