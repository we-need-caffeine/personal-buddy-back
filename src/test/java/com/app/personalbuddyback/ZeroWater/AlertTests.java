package com.app.personalbuddyback.ZeroWater;

import com.app.personalbuddyback.domain.AlertVO;
import com.app.personalbuddyback.mapper.AlertMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@Slf4j
public class AlertTests {

    @Autowired
    AlertMapper alertMapper;

    //    알람 입력 테스트
    @Test
    public void alertInsertTest() {
        AlertVO alertVO = new AlertVO();
        alertVO.setAlertType("event");
        alertVO.setAlertMessage("이벤트 획득! +500P");
        alertVO.setAlertParam(1L);
        alertVO.setReceiverMemberId(2L);
        alertVO.setSenderMemberId(1L);

        alertMapper.insert(alertVO);
    }

    //    2번 유저의 알람 리스트
    @Test
    public void alertViewTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("receiverMemberId", 2L);
//        map.put("alertType", "event");
        List<AlertVO> alerts = alertMapper.selectAll(map);

        alerts.forEach(alert -> {
            log.info("{}", alert);
        });
    }

    //    알람 읽음 표시
    @Test
    public void alertReadTest() {
        alertMapper.update(1L);
    }
    //    알람 삭제
    @Test
    public void alertDeleteTest() {
        alertMapper.delete(1L);
    }
    //    알림 전체 삭제
    @Test
    public void alertDeleteAllTest() {
        alertMapper.deleteAll(2L);
    }
}
