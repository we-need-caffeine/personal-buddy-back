package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.service.SmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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


    @Operation(summary = "핸드폰 문자 인증", description = "핸드폰 문자 인증 API")
    @ApiResponse(responseCode = "200", description = "핸드폰 문자 인증번호 전송 성공")
    @PostMapping("sendSms")
    public ResponseEntity<Map<String, Object>> sendSms(@RequestBody String memberPhone) {

        return smsService.transferMessage(memberPhone);
    }

    @Operation(summary = "이메일 인증", description = "이메일 인증 API")
    @ApiResponse(responseCode = "200", description = "이메일 인증번호 전송 성공")
    @PostMapping("sendEmail")
    public ResponseEntity<Map<String, Object>> sendEmail(@RequestBody String memberEmail) {
        return smsService.sendEmailVerification(memberEmail);
    }

    @Operation(summary = "인증번호 확인", description = "인증번호 확인 API")
    @ApiResponse(responseCode = "200", description = "인증 성공")
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
