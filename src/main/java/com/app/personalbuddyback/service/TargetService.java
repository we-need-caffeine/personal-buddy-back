package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.RandomTargetLotteryVO;
import com.app.personalbuddyback.domain.TargetStandardVO;
import com.app.personalbuddyback.domain.TargetVO;
import com.app.personalbuddyback.domain.TargetViewDTO;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public interface TargetService {
    public void addTarget(TargetVO targetVO);

    public void createTargetStandard(TargetStandardVO targetStandardVO);

    public void pickRandomTargetLottery(RandomTargetLotteryVO randomTargetLotteryVO);

    public List<TargetViewDTO> getTargetsView(Map<String, Objects> params);

    public void deleteTarget(Long memberId);

    public void deleteRandomTargetLottery(Long memberId);
}
