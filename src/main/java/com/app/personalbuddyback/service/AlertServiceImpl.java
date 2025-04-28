package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.AlertVO;
import com.app.personalbuddyback.repository.AlertDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final AlertDAO alertDAO;

    @Override
    public void sendAlert(AlertVO alertVO) {
        alertDAO.save(alertVO);
    }

    @Override
    public void getAlerts(Map<String, Object> map) {
        alertDAO.findAll(map);
    }

    @Override
    public void readAlert(Long id) {
        alertDAO.update(id);
    }

    @Override
    public void deleteAlert(Long id) {
        alertDAO.delete(id);
    }

    @Override
    public void deleteAllAlerts(Long receiverMemberId) {
        alertDAO.deleteAll(receiverMemberId);
    }
}
