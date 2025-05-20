package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.AlertVO;
import com.app.personalbuddyback.domain.AlertViewDTO;

import java.util.List;
import java.util.Map;

public interface AlertService {
//    알림 보내기
    public void registerAlert(AlertVO alertVO);
    
//    해당 유저의 알림 전체 읽기
//    받는 멤버 아이디와 동적 쿼리로 알림 타입에 따른 조회결과 변경
    public List<AlertViewDTO> getAlerts(Long memberId, String alertType);

    //    읽지않은 알림 조회
    public Integer getNotReadAlerts(Long receiverMemberId);

    //    알림 읽음 처리
//    유저가 특정 알람을 클릭했을 때, 읽음 여부처리
    public void updateAlertReadById(Long id);

//    해당 알림 삭제
//    알림의 id값으로 삭제
    public void deleteAlertById(Long id);

//    해당 유저의 모든 알림 삭제
//    해당 유저가 받은 알림을 전체 삭제
    public void deleteAllAlertByReceiverMemberId(Long receiverMemberId);
}
