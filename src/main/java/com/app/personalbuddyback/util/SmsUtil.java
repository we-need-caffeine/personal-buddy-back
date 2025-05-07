package com.app.oauth.util;

import jakarta.annotation.PostConstruct;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.response.SingleMessageSentResponse;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SmsUtil {

    private final JavaMailSender javaMailSender;
    @Value("${coolsms.api.key}")
    private String apiKey;

    @Value("${coolsms.api.secret}")
    private String apiSecret;

    private final JavaMailSender JavaMailSender;
    private DefaultMessageService messageService;

    @PostConstruct
    private void init() {
        this.messageService = NurigoApp.INSTANCE.initialize(apiKey, apiSecret, "https://api.coolsms.co.kr");
    }

    //    메세지 발송
    public SingleMessageSentResponse sendOne(String to, String verificationCode){
        Message message = new Message();
        String toPhoneNumber = to.replace("\"", "");
        log.info("toPhoneNumber: {}" + toPhoneNumber);

        message.setFrom("여러분들 핸드폰 번호 제발 입력하세요!!"); // 보내는 사람
        message.setTo(toPhoneNumber); // 받는 사람
        message.setText("[낮잠자는 고양이] 인증번호 \n[" + verificationCode + "]를 입력하세요");

        return this.messageService.sendOne(new SingleMessageSendingRequest(message));
    };

    //    이메일 발송
    public void sendEmail(String to, String subject, String content) throws MessagingException {
        MimeMessage mimeMessage = JavaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content);

        javaMailSender.send(mimeMessage);
        log.info("이메일 전송 완료 : {}", to);
    }



}
