package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.RandomTargetLotteryVO;
import com.app.personalbuddyback.domain.TargetStandardVO;
import com.app.personalbuddyback.domain.TargetVO;
import com.app.personalbuddyback.domain.TargetViewDTO;
import com.app.personalbuddyback.repository.TargetDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class TargetServiceImpl implements TargetService {
    private final TargetDAO targetDAO;

    @Override
    public void addTarget(TargetVO targetVO) {
        targetDAO.saveTarget(targetVO);
    }

    @Override
    public void createTargetStandard(TargetStandardVO targetStandardVO) {
        targetDAO.saveTargetStandard(targetStandardVO);
    }

    @Override
    public void pickRandomTargetLottery(RandomTargetLotteryVO randomTargetLotteryVO) {
        targetDAO.saveRandomTargetLottery(randomTargetLotteryVO);
    }

    @Override
    public List<TargetViewDTO> getTargetsView(Map<String, Objects> params) {
        return targetDAO.findTargetsView(params);
    }

    @Override
    public void deleteTarget(Long memberId) {
        targetDAO.deleteTarget(memberId);
    }

    @Override
    public void deleteRandomTargetLottery(Long memberId) {
        targetDAO.deleteRandomTargetLottery(memberId);
    }
}
