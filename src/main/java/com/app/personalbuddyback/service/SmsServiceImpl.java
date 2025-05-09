package com.app.personalbuddyback.service;

import com.app.personalbuddyback.util.SmsUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

    private final JavaMailSender mailSender;
    private final SmsUtil smsUtil;

    private final Map<String, String> codeMap = new ConcurrentHashMap<>();


    //    문자 전송
    @Override
    public ResponseEntity<Map<String, Object>> transferMessage(String memberPhone) {

        Map<String, Object> response = new HashMap<>();
        String verificationCode = String.format("%06d", (int)(Math.random() * 900000) + 100000);

        response.put("message", "메세지 전송 성공");
        response.put("verificationCode", verificationCode);
        codeMap.put("verificationCode", verificationCode);

        smsUtil.sendOne(memberPhone, verificationCode);

//        인증코드 시간 코드 설정
        return ResponseEntity.ok(response);
    }

    //    이메일 전송
    @Override
    public ResponseEntity<Map<String, Object>> sendEmailVerification(String memberEmail) {
        Map<String, Object> response = new HashMap<>();

        String verificationCode = String.format("%06d", (int)(Math.random() * 900000) + 100000);

        String emailSubject = "[퍼스널 버디] 이메일 인증 코드";
        String emailContent = "퍼스널 버디 인증 확인 메일입니다.\n아래 보이는 숫자 6자리를 입력해주세요.\n인증 코드: "+ verificationCode;
        codeMap.put("verificationCode", verificationCode);

        try {
            smsUtil.sendEmail(memberEmail, emailSubject, emailContent);
            response.put("message", "인증 코드가 전송되었습니다");
            response.put("verificationCode", verificationCode);
        } catch (MessagingException e) {
            response.put("message", "인증 코드 전송에 실패했습니다");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }

    //    인증코드 검증
    @Override
    public boolean verifyAuthCode(String authCode) {
        String code = authCode != null ? authCode.replaceAll("\"", "") : null;
        return codeMap.get("verificationCode").equals(code);
    }
}
