package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.ChatRoomVO;
import com.app.personalbuddyback.domain.ChatRoomViewDTO;
import com.app.personalbuddyback.domain.ChatVO;
import com.app.personalbuddyback.service.ChattingService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("chats/api/*")
public class ChattingAPI {

    private final SimpMessageSendingOperations template;
    private final ChattingService chattingService;

    // 채팅방 리스트를 불러오는 API
    @GetMapping("chat-room/list/{memberId}")
    public ResponseEntity<List<ChatRoomViewDTO>> getAllChatRoomByMemberId(@PathVariable("memberId") String memberId, @Parameter(required = false) String filter) {
        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("filter", filter);

        // 멤버의 아이디와 필터 조건에 따라 채팅방을 검색한다.
        List<ChatRoomViewDTO> chatRoomViewDTO = chattingService.findAllChatRoomByMemberIdAndFilter(map);

        return ResponseEntity.ok().body(chatRoomViewDTO);
    }

    // 해당 채팅방의 아이디를 받아 채팅기록을 불러오는 API
    @GetMapping("chatting-log/{memberId}")
    public ResponseEntity<List<ChatVO>> getChattingLog(@PathVariable Long memberId, @Parameter Long chatRoomId) {
        List<ChatVO> chatList = chattingService.findChatByChatRoomId(chatRoomId);
        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("chatRoomId", chatRoomId);

        chattingService.updateChatReadByChatRoomIdAndMemberId(map);

        return ResponseEntity.ok().body(chatList);
    }

    // 메시지 송신 및 수신, 프론트에선 /pub/chatroom로 요청
    // 메시지 송신 및 수신하는 API
    @MessageMapping("chatting")
    public void receiveChatting(@Payload ChatVO chatVO) {
        ChatRoomVO chatRoomVO = new ChatRoomVO();
        chatRoomVO.setId(chatVO.getId());
        chatRoomVO.setChatRoomLastChat(chatVO.getChatContent());
        chatRoomVO.setChatRoomLastChatTime(chatVO.getChatSendTime());
        
        // 채팅 기록을 저장
        chattingService.sendChatMessage(chatVO);
        // 채팅방의 마지막 메세지와 시간을 기록
        chattingService.updateChatRoomLastMessage(chatRoomVO);
        // 해당 채팅방 구독자에게 메시지 전송
        template.convertAndSend("/sub/chatroom/" + chatVO.getChatRoomId(), chatVO);
    }

    // 채팅방을 생성하는 API
    @PostMapping("room")
    public void createRoom(@RequestBody Map<String, Object> map) {
        // 멤버 2명의 아이디를 받아 이미 존재하는 채팅방인지 검증
        Long chatRoomId = chattingService.findChatRoomIsTrueByFirstMemberAndSecondMember(map);
        Map<String, Object> findMemberPosition = new HashMap<>();
        Map<String, Object> updateChatView = new HashMap<>();
        String memberPosition = "";

        findMemberPosition.put("chatRoomId", chatRoomId);
        findMemberPosition.put("memberId", map.get("firstMemberId"));

        memberPosition = chattingService.findChatMemberPositionByMemberIdAndChatRoomId(findMemberPosition);

        updateChatView.put("position", memberPosition);
        updateChatView.put("chatRoomId", chatRoomId);

        // 해당 채팅방이 존재하면, 현재 유저의 포지션과 채팅방의 아이디로 채팅방을 view 상태로 만든다.
        if (chatRoomId != null) {
            chattingService.updateViewChatRoomByPositionAndChatRoomId(updateChatView);
        } else {
            // 해당 채팅방이 존재하지 않다면, 받았던 멤버 아이디로 새로운 채팅방을 만든다.
            ChatRoomVO chatRoomVO = new ChatRoomVO();
            chatRoomVO.setFirstMemberId((Long) map.get("firstMemberId"));
            chatRoomVO.setSecondMemberId((Long) map.get("secondMemberId"));
            chattingService.createChatRoom(chatRoomVO);
        }
    }

    // 채팅방을 숨기는 API
    @PutMapping("hide")
    public void hideChatRoom(@RequestBody Map<String, Object> map) {
        Long id = (Long) map.get("chatRoomId");
        Map<String, Object> findMemberPosition = new HashMap<>();
        Map<String, Object> hideChatting = new HashMap<>();
        String memberPosition = "";

        findMemberPosition.put("chatRoomId", id);
        findMemberPosition.put("memberId", map.get("memberId"));

        memberPosition = chattingService.findChatMemberPositionByMemberIdAndChatRoomId(findMemberPosition);

        hideChatting.put("position", memberPosition);
        hideChatting.put("chatRoomId", id);

        chattingService.updateHideChatRoomByPositionAndChatRoomId(hideChatting);
    }

    // 해당 채팅을 숨기는 API
    @PutMapping("hide-chat")
    public void hideChat(@RequestBody Map<String, Object> map) {
        chattingService.updateHideChatByIdAndMemberId(map);
    }

}
