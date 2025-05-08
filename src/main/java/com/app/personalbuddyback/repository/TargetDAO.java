package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.TargetVO;
import com.app.personalbuddyback.mapper.TargetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TargetDAO {
    private final TargetMapper targetMapper;

    public void saveTarget(TargetVO targetVO) {
        targetMapper.insert(targetVO);
    }

    public int findDailyTarget(TargetVO targetVO) {
        return targetMapper.selectDailyTargetCountByCategoryAndMemberId(targetVO);
    }

    public int findWeeklyMonthlyTarget(TargetVO targetVO) {
        return targetMapper.selectWeeklyMonthlyTargetsCountByCategoryAndMemberId(targetVO);
    }
}
