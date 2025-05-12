package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.ChatRoomVO;
import com.app.personalbuddyback.domain.ChatRoomViewDTO;
import com.app.personalbuddyback.domain.ChatVO;
import com.app.personalbuddyback.repository.ChattingDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChattingServiceImpl implements ChattingService {

    private final ChattingDAO chattingDAO;

    @Override
    public Long findChatRoomIsTrueByFirstMemberAndSecondMember(Map<String, Object> map) {
        return chattingDAO.findChatRoomIsTrue(map);
    }

    @Override
    public List<ChatRoomViewDTO> findAllChatRoomByMemberIdAndFilter(Map<String, Object> map) {
        return chattingDAO.findAllChatRoom(map);
    }

    @Override
    public String findChatMemberPositionByMemberIdAndChatRoomId(Map<String, Object> map) {
        return chattingDAO.findChatMemberPosition(map);
    }

    @Override
    public List<ChatVO> findChatByChatRoomId(Long chatRoomId) {
        return chattingDAO.findChat(chatRoomId);
    }

    @Override
    public void createChatRoom(ChatRoomVO chatRoomVO) {
        chattingDAO.saveChatRoom(chatRoomVO);
    }

    @Override
    public void sendChatMessage(ChatVO chatVO) {
        chattingDAO.saveChat(chatVO);
    }

    @Override
    public void updateChatRoomLastMessage(ChatRoomVO chatRoomVO) {
        chattingDAO.updateChatRoom(chatRoomVO);
    }

    @Override
    public void updateHideChatRoomByPositionAndChatRoomId(Map<String, Object> map) {
        chattingDAO.updateHideChatRoom(map);
    }

    @Override
    public void updateViewChatRoomByPositionAndChatRoomId(Map<String, Object> map) {
        chattingDAO.updateViewChatRoom(map);
    }

    @Override
    public void updateChatReadByChatRoomIdAndMemberId(Map<String, Object> map) {
        chattingDAO.updateChatRead(map);
    }

    @Override
    public void updateHideChatByIdAndMemberId(Map<String, Object> map) {
        chattingDAO.updateHideChat(map);
    }
}
