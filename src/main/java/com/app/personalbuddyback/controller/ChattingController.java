package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.ChatVO;
import com.app.personalbuddyback.service.ChattingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("chats/api/*")
public class ChattingController {
    private final SimpMessageSendingOperations template;
    private final ChattingService chattingService;

    // 채팅 리스트 반환
    @GetMapping("/{id}")
    public ResponseEntity<List<ChatVO>> getChatMessages(@PathVariable Long id){
        ChatVO chatVO = new ChatVO();
        return ResponseEntity.ok().body(List.of(chatVO));
    }

    //메시지 송신 및 수신, /pub가 생략된 모습. 클라이언트 단에선 /pub/message로 요청
    @MessageMapping("/message")
    public ResponseEntity<Void> receiveMessage(@RequestBody ChatVO chatVO) {
        template.convertAndSend("/sub/chatroom/1", chatVO);
        return ResponseEntity.ok().build();
    }

}
