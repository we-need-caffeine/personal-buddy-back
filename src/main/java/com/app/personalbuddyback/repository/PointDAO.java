package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.MemberPointLogVO;
import com.app.personalbuddyback.mapper.PointMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PointDAO {
    private final PointMapper pointMapper;

    public void savePointLog(MemberPointLogVO memberPointLogVO) {
        pointMapper.insert(memberPointLogVO);
    }

    public List<MemberPointLogVO> getPointLogs(Long memberId) {
        return pointMapper.selectAllByMemberId(memberId);
    }

    public void deleteAll(Long memberId) {
        pointMapper.deleteAllByMemberId(memberId);
    }
}
