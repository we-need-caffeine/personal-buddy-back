package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.AlertVO;
import com.app.personalbuddyback.domain.AlertViewDTO;
import com.app.personalbuddyback.repository.AlertDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final AlertDAO alertDAO;

    @Override
    public void registerAlert(AlertVO alertVO) {
        alertDAO.save(alertVO);
    }

    @Override
    public List<AlertViewDTO> getAlerts(Map<String, Object> map) {
        return alertDAO.findAll(map);
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
