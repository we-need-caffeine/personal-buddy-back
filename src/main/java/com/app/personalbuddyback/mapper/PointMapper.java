package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.MemberPointLogVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PointMapper {
    public void insert(MemberPointLogVO memberPointLogVO);

    public List<MemberPointLogVO> selectAllByMemberId(Long memberId);

    public void deleteAllByMemberId(Long memberId);
}
