package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.TargetVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TargetMapper {

//    목표테이블에 insert 되면, 기간에 따라 완료 횟수를 체크한다.
//    따라서, 일정이 추가되면, target 테이블에 함께 insert 알맞는 정보를 insert 하여 정보를 기입
    public void insert(TargetVO targetVO);

//    일간 목표 확인
    public int selectDailyTargetCountByCategoryAndMemberId(TargetVO targetVO);

//    주간/월간 목표 확인
    public int selectWeeklyMonthlyTargetCountByCategoryAndMemberId(TargetVO targetVO);
}
