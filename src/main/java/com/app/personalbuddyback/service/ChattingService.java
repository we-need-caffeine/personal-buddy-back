package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.ChatRoomViewDTO;
import com.app.personalbuddyback.domain.ChatVO;
import com.app.personalbuddyback.domain.ChatViewDTO;

import java.util.List;
import java.util.Optional;

public interface ChattingService {
//    채팅 기록을 저장하고 채팅방에 마지막 메세지와 시간을 기록하는 서비스
    public void saveChatAndUpdateChatRoomLastMessage(ChatVO chatVO);
//    해당 채팅방에서 상대가 보낸 메세지를 읽음처리하고 채팅 기록을 불러오는 서비스
    public List<ChatViewDTO> updateChatReadAndGetAllChat(Long memberId, Long chatRoomId);
//    채팅방 리스트를 불러오는 서비스
    public List<ChatRoomViewDTO> findAllChatRoomByMemberIdAndFilter(Long memberId, String filterType, String searchNickname);
//    채팅방의 존재 여부를 검증하고, 원래 존재하던 채팅방의 숨김 여부를 제거하거나 새로운 채팅방을 만드는 서비스
    public Optional<Long> saveChatRoomOrChangeViewChatRoom(Long memberId, Long secondMemberId);
//    해당하는 채팅방에서 멤버의 포지션을 찾고, 해당하는 view 포지션을 hide 상태로 바꾸는 서비스
    public void updateChangeHideByChatRoomIdAndMemberId(Long chatRoomId, Long memberId);
//    내가 보낸 메세지를 모든 유저에게서 숨김처리하는 서비스
    public void updateHideChatByChatIdAndMemberId(Long chatId, Long memberId);
}
