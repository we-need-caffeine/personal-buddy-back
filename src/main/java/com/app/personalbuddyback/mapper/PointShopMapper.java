package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.ItemVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointShopMapper {

    // 성장 나무 아이템 추가 (관리자 용)
    public void insert(ItemVO itemVO);

    // 성장 나무 아이템 수정 (관리자 용)
    public void update(ItemVO itemVO);

    // 성장 나무 아이템 삭제 (관리자 용)
    public void delete(Long id);
}
