package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.RandomTargetLotteryVO;
import com.app.personalbuddyback.domain.TargetStandardVO;
import com.app.personalbuddyback.domain.TargetVO;
import com.app.personalbuddyback.domain.TargetViewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface TargetMapper {

    //  목표테이블에 insert 되면, 기간에 따라 완료 횟수를 체크한다.
    //  따라서, 일정이 추가되면, target 테이블에 함께 insert 알맞는 정보를 insert 하여 정보를 기입
    public void insertTarget(TargetVO targetVO);

    // 목표 기준 테이블에 데이터 추가
    public void insertTargetStandard(TargetStandardVO targetStandardVO);

    // 기간 별 시작 날에 추가될 랜덤 목표 선정용 테이블 insert
    public void insertRandomTargetLottery(RandomTargetLotteryVO randomTargetLotteryVO);

    // 일간 / 주간 / 월간 목표 유형에 대한 검색(ex 일정 종류를 param 으로 받아 검색)
    // findData { memberId, type(default : daily / weekly / monthly }
    public List<TargetViewDTO> selectTargetsView(Map<String, Objects> findData);

    // 목표 삭제 (회원탈퇴 시)
    public void deleteTargetByMemberId(Long memberId);

    // 랜덤 선정된 목표 삭제 (목표 삭제와 함께 트랜잭션 관리)
    public void deleteRandomTargetLotteryByMemberId(Long memberId);
}
