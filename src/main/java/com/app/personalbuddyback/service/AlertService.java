package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.AlertVO;

import java.util.Map;

public interface AlertService {
//    알림 보내기
//    alertType :
//        캘린더 초대장 = calendar,
//        게시판 관련 알림 = borad,
//        일정 알림 = schedule,
//        포인트 획득 = point,
//        팔로우 알림 = follow
//        방명록 알림 = guestBook
//    alertParam :
//        해당 알림의 타입에 따른 id값 없으면 null
    public void sendAlert(AlertVO alertVO);
    
//    해당 유저의 알림 전체 읽기
//    받는 멤버 아이디와 동적 쿼리로 알림 타입에 따른 조회결과 변경
//    타입은 입력하지 않으면 전체조회
    public void getAlerts(Map<String, Object> map);

//    알림 읽음 처리
//    유저가 특정 알람을 클릭했을 때, 읽음 여부처리
    public void readAlert(Long id);

//    해당 알림 삭제
//    알림의 id값으로 삭제
    public void deleteAlert(Long id);

//    해당 유저의 모든 알림 삭제
//    해당 유저가 받은 알림을 전체 삭제
    public void deleteAllAlerts(Long receiverMemberId);
}
