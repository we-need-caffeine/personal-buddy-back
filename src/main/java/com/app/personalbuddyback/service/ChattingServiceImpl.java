package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.ChatRoomVO;
import com.app.personalbuddyback.domain.ChatRoomViewDTO;
import com.app.personalbuddyback.domain.ChatVO;
import com.app.personalbuddyback.repository.ChattingDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChattingServiceImpl implements ChattingService {

    private final ChattingDAO chattingDAO;

    @Override
    public void saveChatAndUpdateChatRoomLastMessage(ChatVO chatVO) {
        ChatRoomVO chatRoomVO = new ChatRoomVO();
        chatRoomVO.setId(chatVO.getId());
        chatRoomVO.setChatRoomLastChat(chatVO.getChatContent());
        chatRoomVO.setChatRoomLastChatTime(chatVO.getChatSendTime());
        // 채팅 기록을 저장
        chattingDAO.saveChat(chatVO);
        // 채팅방의 마지막 메세지와 시간을 기록
        chattingDAO.updateChatRoom(chatRoomVO);
    }

    @Override
    public List<ChatVO> updateChatReadAndGetAllChat(Long memberId, Long chatRoomId) {
        List<ChatVO> chats = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        
        map.put("memberId", memberId);
        map.put("chatRoomId", chatRoomId);
        
        // 상대가 보낸 채팅 기록을 읽음 처리
        chattingDAO.updateChatRead(map);
        // 채팅방의 채팅기록
        chats = chattingDAO.findChat(chatRoomId);
        return chats;
    }

    @Override
    public List<ChatRoomViewDTO> findAllChatRoomByMemberIdAndFilter(Long memberId, String filter) {
        List<ChatRoomViewDTO> chatRooms = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("memberId", memberId);
        map.put("filter", filter);

        chatRooms = chattingDAO.findAllChatRoom(map);

        return chatRooms;
    }

    @Override
    public String saveChatRoomOrChangeViewChatRoom(Long memberId, Long secondMemberId) {
        Map<String, Object> membersMap = new HashMap<>();
        Map<String, Object> findMemberPositionInfo = new HashMap<>();
        Map<String, Object> changeViewInfo = new HashMap<>();
        Long chatRoomId = null;
        String memberPosition = null;
        String message = null;

        membersMap.put("firstmemberId", memberId);
        membersMap.put("secondMemberId", secondMemberId);

        // 멤버 2명의 아이디를 받아 이미 존재하는 채팅방인지 검증
        chatRoomId = chattingDAO.findChatRoomIsTrue(membersMap);

        findMemberPositionInfo.put("chatRoomId", chatRoomId);
        findMemberPositionInfo.put("memberId", memberId);

        if (chatRoomId != null) {
            //채팅방 아이디가 존재한다면 해당 채팅방에서 멤버의 view 포지션을 문자열값으로 받는다.
            memberPosition = chattingDAO.findChatMemberPosition(findMemberPositionInfo);

            changeViewInfo.put("position", memberPosition);
            changeViewInfo.put("chatRoomId", chatRoomId);

            // 채팅방에서 멤버의 view 포지션을 1로 바꾼 후 메세지를 리턴
            chattingDAO.updateViewChatRoom(changeViewInfo);
            message = "이미 존재하는 채팅방입니다.";
            return message;
        } else {
            // 해당 채팅방이 존재하지 않다면, 받았던 멤버 아이디로 새로운 채팅방을 만든다.
            ChatRoomVO chatRoomVO = new ChatRoomVO();

            chatRoomVO.setFirstMemberId(memberId);
            chatRoomVO.setSecondMemberId(secondMemberId);
            
            // 처음의 받았던 아이디로 새로운 채팅방을 만들고 메세지를 리턴
            chattingDAO.saveChatRoom(chatRoomVO);
            message = "새로운 채팅방을 만들었습니다.";
            return message;
        }
    }

    @Override
    public void updateChangeHideByChatRoomIdAndMemberId(Long chatRoomId, Long memberId) {
        Map<String, Object> findMemberPositionInfo = new HashMap<>();
        Map<String, Object> hideChattinginfo = new HashMap<>();
        String memberPosition = null;

        findMemberPositionInfo.put("chatRoomId", chatRoomId);
        findMemberPositionInfo.put("memberId", memberId);
        
        // 멤버의 포지션
        memberPosition = chattingDAO.findChatMemberPosition(findMemberPositionInfo);

        hideChattinginfo.put("position", memberPosition);
        hideChattinginfo.put("chatRoomId", chatRoomId);

        // 해당 채팅방의 멤버 포지션에 해당하는 view값을 숨긴다.
        chattingDAO.updateHideChatRoom(hideChattinginfo);
    }

    @Override
    public void updateHideChatByChatIdAndMemberId(Long chatId, Long memberId) {
        Map<String, Object> changeHideChatInfo = new HashMap<>();
        changeHideChatInfo.put("chatId", chatId);
        changeHideChatInfo.put("memberId", memberId);
        chattingDAO.updateHideChat(changeHideChatInfo);
    }
}
