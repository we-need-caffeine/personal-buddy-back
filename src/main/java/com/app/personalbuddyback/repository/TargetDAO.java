package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.TargetVO;
import com.app.personalbuddyback.mapper.TargetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TargetDAO {
    private final TargetMapper targetMapper;

    public void save(TargetVO targetVO) {
        targetMapper.insert(targetVO);
    }

    public int checkDailyTarget(TargetVO targetVO) {
        return targetMapper.selectDailyTargetCountByCategoryAndMemberId(targetVO);
    }

    public int checkWeeklyMonthlyTarget(TargetVO targetVO) {
        return targetMapper.selectWeeklyMonthlyTargetCountByCategoryAndMemberId(targetVO);
    }
}
