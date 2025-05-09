package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.service.SmsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("sms/api")
public class SmsAPI {
    private final SmsService smsService;



    @PostMapping("sendSms")
    public ResponseEntity<Map<String, Object>> sendSms(@RequestBody String memberPhone) {

        return smsService.transferMessage(memberPhone);
    }

    @PostMapping("sendEmail")
    public ResponseEntity<Map<String, Object>> sendEmail(@RequestBody String memberEmail) {
        return smsService.sendEmailVerification(memberEmail);
    }

    @PostMapping("verifyCode")
    public ResponseEntity<Map<String, Object>> verifyCode(@RequestBody String code){
        Map<String,Object> response = new HashMap<>();

        boolean isFlag = smsService.verifyAuthCode(code);
        if(isFlag){
            response.put("message", "인증이 완료되었습니다");
            response.put("isFlag", isFlag);
            return ResponseEntity.ok(response);
        }else{
            response.put("message", "인증 번호를 확인해주세요😅");
            response.put("isFlag", isFlag);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}
