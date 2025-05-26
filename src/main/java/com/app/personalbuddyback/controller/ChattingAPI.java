package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.ChatRoomViewDTO;
import com.app.personalbuddyback.domain.ChatVO;
import com.app.personalbuddyback.domain.ChatViewDTO;
import com.app.personalbuddyback.service.ChattingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("chats/api/*")
public class ChattingAPI {

    private final SimpMessageSendingOperations template;
    private final ChattingService chattingService;

    // 메시지 송신 및 수신, 프론트에선 /pub/chatroom로 요청
    // 메시지 송신 및 수신하는 API
    @MessageMapping("message")
    public void receiveChatting(@Payload ChatVO chatVO) {
        // 채팅 기록을 저장하고 채팅방에 마지막 메세지와 시간을 기록하는 서비스
        chattingService.saveChatAndUpdateChatRoomLastMessage(chatVO);
        // 해당 채팅방 구독자에게 메시지 전송
        template.convertAndSend("/sub/chatroom/" + chatVO.getChatRoomId(), chatVO);
    }

    // 채팅방 리스트를 불러오는 API
    @Operation(summary = "채팅방 리스트 조회", description = "멤버의 아이디와 필터값으로 채팅방의 리스트를 불러오는 API")
    @GetMapping("chat-room/list")
    public List<ChatRoomViewDTO> getAllChatRoomByMemberId(
            @RequestParam Long memberId,
            @RequestParam(required = false) String filterType,
            @RequestParam(required = false) String searchNickname
    ) {
        // 멤버의 아이디와 필터 조건에 따라 채팅방을 검색한다.
        List<ChatRoomViewDTO> chatRooms = chattingService.findAllChatRoomByMemberIdAndFilter(memberId, filterType, searchNickname);

        return chatRooms;
    }

    // 해당 채팅방에서 상대가 보낸 메세지를 읽음처리하고 채팅 기록을 불러오는 API
    @Operation(summary = "채팅 기록 리스트", description = "상대가 보낸 채팅을 읽음 처리하고 채팅기록을 불러오는 API")
    @GetMapping("chat/list")
    public List<ChatViewDTO> getChattingLog(@RequestParam Long memberId, @RequestParam Long chatRoomId) {
        // 해당 채팅방에서 상대가 보낸 메세지를 읽음처리하고 채팅 기록을 불러오는 서비스
        List<ChatViewDTO> chats = chattingService.updateChatReadAndGetAllChat(memberId, chatRoomId);

        return chats;
    }

    // 채팅방을 생성하는 API
    @Operation(summary = "채팅방 생성/숨김 해제", description = "채팅방이 존재하면 숨김처리를 해제하고, 존재하지 않으면 새로운 채팅방을 생성하는 API")
    @PostMapping("chat-room/register")
    public Long createRoom(@RequestParam Long memberId, @RequestParam Long secondMemberId) {
        // 멤버 2명의 아이디를 받아 이미 존재하는지 채팅방인지 검증 후,
        // 존재하면 멤버아이디가 해당하는 view컬럼을 1로 바꿔준다
        // 존재하지 않는다면 새로운 채팅방을 만든다
        // 로직이 끝난 후, 채팅방의 아이디를 반환한다
        Optional<Long> chatRoomId = chattingService.saveChatRoomOrChangeViewChatRoom(memberId, secondMemberId);

        return chatRoomId.get();
    }

    // 채팅방을 숨기는 API
    @Operation(summary = "채팅방 숨김", description = "멤버의 view 포지션을 찾고 숨김처리하는 API")
    @PutMapping("chat-room/hide")
    public void hideChatRoom(@RequestParam Long chatRoomId, @RequestParam Long memberId) {
        // 멤버의 포지션 정보를 찾고, 해당 포지션에 해당하는 view 컬럼을 hide 상태로 바꾼다
        chattingService.updateChangeHideByChatRoomIdAndMemberId(chatRoomId, memberId);
    }

    // 해당 채팅을 숨기는 API
    @Operation(summary = "채팅 숨김", description = "자신이 보낸 메세지를 모든 유저에게서 숨김 처리하는 API")
    @PutMapping("chat/hide")
    public void hideChat(@RequestParam Long chatId, @RequestParam Long memberId) {
        chattingService.updateHideChatByChatIdAndMemberId(chatId, memberId);
    }

}
