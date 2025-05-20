package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.AlertVO;
import com.app.personalbuddyback.domain.AlertViewDTO;
import com.app.personalbuddyback.repository.AlertDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AlertServiceImpl implements AlertService {

    private final AlertDAO alertDAO;

    @Override
    public void registerAlert(AlertVO alertVO) {
        alertDAO.save(alertVO);
    }

    @Override
    public List<AlertViewDTO> getAlerts(Long memberId, String alertType) {
        Map<String, Object> map = new HashMap<>();
        List<AlertViewDTO> alerts = new ArrayList<>();

        map.put("receiverMemberId", memberId);
        map.put("alertType", alertType);
        return alertDAO.findAll(map);
    }

    @Override
    public Integer getNotReadAlerts(Long receiverMemberId) {
        return alertDAO.getCount(receiverMemberId);
    }

    @Override
    public void updateAlertReadById(Long id) {
        alertDAO.update(id);
    }

    @Override
    public void deleteAlertById(Long id) {
        alertDAO.delete(id);
    }

    @Override
    public void deleteAllAlertByReceiverMemberId(Long receiverMemberId) {
        alertDAO.deleteAll(receiverMemberId);
    }
}
