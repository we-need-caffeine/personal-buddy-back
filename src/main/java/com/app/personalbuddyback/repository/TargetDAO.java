package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.RandomTargetLotteryVO;
import com.app.personalbuddyback.domain.TargetStandardVO;
import com.app.personalbuddyback.domain.TargetVO;
import com.app.personalbuddyback.domain.TargetViewDTO;
import com.app.personalbuddyback.mapper.TargetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class TargetDAO {
    private final TargetMapper targetMapper;

    //  목표테이블에 insert 되면, 기간에 따라 완료 횟수를 체크한다.
    //  따라서, 일정이 추가되면, target 테이블에 함께 insert 알맞는 정보를 insert 하여 정보를 기입
    public void saveTarget(TargetVO targetVO) {
        targetMapper.insertTarget(targetVO);
    }

    // 목표 기준 테이블에 데이터 추가
    public void saveTargetStandard(TargetStandardVO targetStandardVO) {
        targetMapper.insertTargetStandard(targetStandardVO);
    }

    // 기간 별 시작 날에 추가될 랜덤 목표 선정용 테이블 insert
    public void saveRandomTargetLottery(RandomTargetLotteryVO randomTargetLotteryVO) {
        targetMapper.insertRandomTargetLottery(randomTargetLotteryVO);
    }

    // 일간 / 주간 / 월간 목표 유형에 대한 검색(ex 일정 종류를 param 으로 받아 검색)
    // findData { memberId, type(default : daily / weekly / monthly }
    public List<TargetViewDTO> findTargetsView(Map<String, Objects> findData) {
        return targetMapper.selectTargetsView(findData);
    }

    // 목표 삭제 (회원탈퇴 시)
    public void deleteTarget(Long memberId) {
        targetMapper.deleteTargetByMemberId(memberId);
    }

    // 랜덤 선정된 목표 삭제 (목표 삭제와 함께 트랜잭션 관리)
    public void deleteRandomTargetLottery(Long memberId) {
        targetMapper.deleteRandomTargetLotteryByMemberId(memberId);
    }
}
