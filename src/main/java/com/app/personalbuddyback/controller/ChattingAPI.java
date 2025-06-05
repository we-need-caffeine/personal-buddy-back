package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.ChatRoomViewDTO;
import com.app.personalbuddyback.domain.ChatVO;
import com.app.personalbuddyback.domain.ChatViewDTO;
import com.app.personalbuddyback.service.ChattingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    // 메시지 송신 및 수신 (웹소켓)
    @Operation(summary = "채팅 메시지 송수신", description = "/pub/chatroomId로 메시지 송수신 (웹소켓 연결)")
    @MessageMapping("message")
    public void receiveChatting(@Payload ChatVO chatVO) {
        chattingService.saveChatAndUpdateChatRoomLastMessage(chatVO);
        template.convertAndSend("/sub/chatroom/" + chatVO.getChatRoomId(), chatVO);
    }

    // 읽지 않은 채팅의 수를 조회하는 API
    @Operation(summary = "읽지 않은 채팅의 수 조회", description = "멤버의 아이디로 읽지 않은 채팅의 수를 불러오는 API")
    @Parameter(
            name = "memberId",
            description = "멤버 ID",
            in = ParameterIn.PATH,
            required = true,
            schema = @Schema(type = "number", example = "1")
    )
    @ApiResponse(responseCode = "200", description = "읽지 않은 채팅 수 반환")
    @GetMapping("chat/not-read/{memberId}")
    public Integer getAllNotReadChat(@PathVariable Long memberId) {
        return chattingService.findAllNotReadChat(memberId).get();
    }

    // 채팅방 리스트를 불러오는 API
    @Operation(summary = "채팅방 리스트 조회", description = "멤버의 아이디와 필터값으로 채팅방의 리스트를 불러오는 API")
    @Parameter(
            name = "memberId",
            description = "멤버 ID",
            in = ParameterIn.QUERY,
            required = true,
            schema = @Schema(type = "number", example = "1")
    )
    @Parameter(
            name = "filterType",
            description = "필터 타입 (예: favorite)",
            in = ParameterIn.QUERY,
            required = false,
            schema = @Schema(type = "string", example = "favorite")
    )
    @Parameter(
            name = "searchNickname",
            description = "상대 닉네임으로 검색",
            in = ParameterIn.QUERY,
            required = false,
            schema = @Schema(type = "string", example = "홍길동")
    )
    @ApiResponse(responseCode = "200", description = "채팅방 리스트 반환")
    @GetMapping("chat-room/list")
    public List<ChatRoomViewDTO> getAllChatRoomByMemberId(
            @RequestParam Long memberId,
            @RequestParam(required = false) String filterType,
            @RequestParam(required = false) String searchNickname
    ) {
        return chattingService.findAllChatRoomByMemberIdAndFilter(memberId, filterType, searchNickname);
    }

    // 채팅 기록 리스트
    @Operation(summary = "채팅 기록 리스트", description = "상대가 보낸 채팅을 읽음 처리하고 채팅기록을 불러오는 API")
    @Parameter(
            name = "memberId",
            description = "멤버 ID",
            in = ParameterIn.QUERY,
            required = true,
            schema = @Schema(type = "number", example = "1")
    )
    @Parameter(
            name = "chatRoomId",
            description = "채팅방 ID",
            in = ParameterIn.QUERY,
            required = true,
            schema = @Schema(type = "number", example = "5")
    )
    @ApiResponse(responseCode = "200", description = "채팅 기록 리스트 반환")
    @GetMapping("chat/list")
    public List<ChatViewDTO> getChattingLog(
            @RequestParam Long memberId,
            @RequestParam Long chatRoomId
    ) {
        return chattingService.updateChatReadAndGetAllChat(memberId, chatRoomId);
    }

    // 채팅방 생성/숨김 해제
    @Operation(summary = "채팅방 생성/숨김 해제", description = "채팅방이 존재하면 숨김처리를 해제하고, 없으면 생성")
    @Parameter(
            name = "memberId",
            description = "내 아이디",
            in = ParameterIn.QUERY,
            required = true,
            schema = @Schema(type = "number", example = "1")
    )
    @Parameter(
            name = "secondMemberId",
            description = "상대방 아이디",
            in = ParameterIn.QUERY,
            required = true,
            schema = @Schema(type = "number", example = "2")
    )
    @ApiResponse(responseCode = "200", description = "채팅방 정보 반환")
    @PostMapping("chat-room/register")
    public Optional<ChatRoomViewDTO> createRoom(
            @RequestParam Long memberId,
            @RequestParam Long secondMemberId
    ) {
        return chattingService.saveChatRoomOrChangeViewChatRoom(memberId, secondMemberId);
    }

    // 채팅방 숨김
    @Operation(summary = "채팅방 숨김", description = "멤버의 view 포지션을 찾고 숨김처리")
    @Parameter(
            name = "chatRoomId",
            description = "채팅방 ID",
            in = ParameterIn.QUERY,
            required = true,
            schema = @Schema(type = "number", example = "5")
    )
    @Parameter(
            name = "memberId",
            description = "멤버 ID",
            in = ParameterIn.QUERY,
            required = true,
            schema = @Schema(type = "number", example = "1")
    )
    @ApiResponse(responseCode = "200", description = "숨김 처리 성공")
    @PutMapping("chat-room/hide")
    public void hideChatRoom(
            @RequestParam Long chatRoomId,
            @RequestParam Long memberId
    ) {
        chattingService.updateChangeHideByChatRoomIdAndMemberId(chatRoomId, memberId);
    }

    // 채팅 숨김
    @Operation(summary = "채팅 숨김", description = "자신이 보낸 메시지를 모든 유저에게서 숨김 처리")
    @Parameter(
            name = "chatId",
            description = "채팅 메시지 ID",
            in = ParameterIn.QUERY,
            required = true,
            schema = @Schema(type = "number", example = "10")
    )
    @Parameter(
            name = "memberId",
            description = "멤버 ID",
            in = ParameterIn.QUERY,
            required = true,
            schema = @Schema(type = "number", example = "1")
    )
    @ApiResponse(responseCode = "200", description = "숨김 처리 성공")
    @PutMapping("chat/hide")
    public void hideChat(
            @RequestParam Long chatId,
            @RequestParam Long memberId
    ) {
        chattingService.updateHideChatByChatIdAndMemberId(chatId, memberId);
    }
}
