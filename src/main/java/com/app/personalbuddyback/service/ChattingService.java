package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.ChatRoomVO;
import com.app.personalbuddyback.domain.ChatRoomViewDTO;
import com.app.personalbuddyback.domain.ChatVO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ChattingService {
//    채팅방 존재 여부
    public Long findChatRoomIsTrueByFirstMemberAndSecondMember(Map<String, Object> map);
//    채팅방 리스트 불러오기
    public List<ChatRoomViewDTO> findAllChatRoomByMemberIdAndFilter(Map<String, Object> map);
//    멤버가 채팅방의 퍼스트멤버인지, 세컨드 멤버인지 구별
    public String findChatMemberPositionByMemberIdAndChatRoomId(Map<String, Object> map);
//    채팅 내역 불러오기
    public List<ChatVO> findChatByChatRoomId(Long chatRoomId);
//    채팅방 생성
    public void createChatRoom(ChatRoomVO chatRoomVO);
//    채팅 작성
    public void sendChatMessage(ChatVO chatVO);
//    채팅방 마지막 채팅 업데이트
    public void updateChatRoomLastMessage(ChatRoomVO chatRoomVO);
//    채팅방 나가기(숨기기)
    public void updateHideChatRoomByPositionAndChatRoomId(Map<String, Object> map);
//    채팅방 보이기
    public void updateViewChatRoomByPositionAndChatRoomId(Map<String, Object> map);
//    채팅 읽음 처리
    public void updateChatReadByChatRoomIdAndMemberId(Map<String, Object> map);
//    채팅 삭제(숨기기)
    public void updateHideChatByIdAndMemberId(Map<String, Object> map);
}
