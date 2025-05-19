package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.RandomTargetLotteryVO;
import com.app.personalbuddyback.domain.TargetStandardVO;
import com.app.personalbuddyback.domain.TargetVO;
import com.app.personalbuddyback.domain.TargetViewDTO;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface TargetService {
    public void completeTarget(TargetVO targetVO);

    public void addTargetStandard(TargetStandardVO targetStandardVO);

    public void createRandomTargetLotto(RandomTargetLotteryVO randomTargetLotteryVO);

    public int getTargetCount(TargetVO targetVO);

    public List<TargetViewDTO> getDailyTargets(Long memberId);

    public List<TargetViewDTO> getWeeklyTargets(Long memberId);

    public List<TargetViewDTO> getMonthlyTargets(Long memberId);

    public List<RandomTargetLotteryVO> getDailyRandomTargets(Long memberId);

    public List<RandomTargetLotteryVO> getWeeklyRandomTargets(Long memberId);

    public List<RandomTargetLotteryVO> getMonthlyRandomTargets(Long memberId);

    public void editTargetStandard(TargetStandardVO targetStandardVO);
}
