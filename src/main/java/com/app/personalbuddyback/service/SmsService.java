package com.app.personalbuddyback.service;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface SmsService {

//    문자 전송
    public ResponseEntity<Map<String, Object>> transferMessage(String memberPhone);

//    이메일 전송
    public ResponseEntity<Map<String, Object>> sendEmailVerification(String memberEmail);

//    인증코드 생성
    public boolean verifyAuthCode(String authCode);
}
