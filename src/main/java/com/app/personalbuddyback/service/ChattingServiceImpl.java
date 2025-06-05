package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.ChatRoomVO;
import com.app.personalbuddyback.domain.ChatRoomViewDTO;
import com.app.personalbuddyback.domain.ChatVO;
import com.app.personalbuddyback.domain.ChatViewDTO;
import com.app.personalbuddyback.repository.ChattingDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ChattingServiceImpl implements ChattingService {

    private final ChattingDAO chattingDAO;

    @Override
    public void saveChatAndUpdateChatRoomLastMessage(ChatVO chatVO) {
        // 채팅 기록을 저장
        chattingDAO.saveChat(chatVO);
        // 채팅방의 마지막 메세지와 시간을 기록
        chattingDAO.updateChatRoom(chatVO);
    }

    @Override
    public List<ChatViewDTO> updateChatReadAndGetAllChat(Long memberId, Long chatRoomId) {
        Map<String, Object> param = new HashMap<>();

        param.put("memberId", memberId);
        param.put("chatRoomId", chatRoomId);
        
        // 상대가 보낸 채팅 기록을 읽음 처리
        chattingDAO.updateChatRead(param);
        // 채팅방의 채팅기록
        List<ChatViewDTO> chats = chattingDAO.findChat(chatRoomId);
        return chats;
    }

    @Override
    public List<ChatRoomViewDTO> findAllChatRoomByMemberIdAndFilter(Long memberId, String filterType, String searchNickname) {
        Map<String, Object> param = new HashMap<>();
        param.put("memberId", memberId);
        param.put("searchNickname", searchNickname);
        param.put("filterType", filterType);

        List<ChatRoomViewDTO> chatRooms = chattingDAO.findAllChatRoom(param);
        return chatRooms;
    }

    @Override
    public Optional<ChatRoomViewDTO> saveChatRoomOrChangeViewChatRoom(Long memberId, Long secondMemberId) {
        Map<String, Object> memberParam = new HashMap<>();
        Map<String, Object> findMemberPositionParam = new HashMap<>();
        Map<String, Object> viewChatRoomParam = new HashMap<>();

        memberParam.put("firstMemberId", memberId);
        memberParam.put("secondMemberId", secondMemberId);

        // 이미 존재하는 채팅방이면 view로 변환후 정보 반환
        Optional<Long> chatRoomId = chattingDAO.findChatRoomIsTrue(memberParam);

        if (chatRoomId.isPresent()) {
            Long roomId = chatRoomId.get();
            findMemberPositionParam.put("chatRoomId", roomId);
            findMemberPositionParam.put("memberId", memberId);

            Optional<String> memberPosition = chattingDAO.findChatMemberPosition(findMemberPositionParam);
            viewChatRoomParam.put("position", memberPosition.get());
            viewChatRoomParam.put("chatRoomId", roomId);

            chattingDAO.updateViewChatRoom(viewChatRoomParam);

            return chattingDAO.findChatRoom(findMemberPositionParam);
        } else {
            // 새로운 채팅방 생성 후 반환
            ChatRoomVO chatRoomVO = new ChatRoomVO();
            chatRoomVO.setFirstMemberId(memberId);
            chatRoomVO.setSecondMemberId(secondMemberId);

            chattingDAO.saveChatRoom(chatRoomVO);

            findMemberPositionParam.put("memberId", memberId);
            findMemberPositionParam.put("chatRoomId", chatRoomVO.getId());
            return chattingDAO.findChatRoom(findMemberPositionParam);
        }
    }

    @Override
    public Optional<Integer> findAllNotReadChat(Long memberId) {
        return chattingDAO.findAllNotReadChat(memberId);
    }

    @Override
    public void updateChangeHideByChatRoomIdAndMemberId(Long chatRoomId, Long memberId) {
        Map<String, Object> findMemberPositionParam = new HashMap<>();
        Map<String, Object> hideChatRoomParam = new HashMap<>();

        findMemberPositionParam.put("chatRoomId", chatRoomId);
        findMemberPositionParam.put("memberId", memberId);
        
        // 멤버의 포지션
        Optional<String> memberPosition = chattingDAO.findChatMemberPosition(findMemberPositionParam);
        hideChatRoomParam.put("position", memberPosition.get());
        hideChatRoomParam.put("chatRoomId", chatRoomId);

        // 해당 채팅방의 멤버 포지션에 해당하는 view값을 숨긴다.
        chattingDAO.updateHideChatRoom(hideChatRoomParam);
    }

    @Override
    public void updateHideChatByChatIdAndMemberId(Long chatId, Long memberId) {
        Map<String, Object> hideChatParam = new HashMap<>();
        hideChatParam.put("chatId", chatId);
        hideChatParam.put("memberId", memberId);
        chattingDAO.updateHideChat(hideChatParam);
    }
}
