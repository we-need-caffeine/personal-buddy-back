package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.ChatRoomVO;
import com.app.personalbuddyback.domain.ChatRoomViewDTO;
import com.app.personalbuddyback.domain.ChatVO;
import com.app.personalbuddyback.domain.ChatViewDTO;
import com.app.personalbuddyback.mapper.ChattingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ChattingDAO {
    private final ChattingMapper chattingMapper;

//    채팅방 존재 여부
    public Optional<Long> findChatRoomIsTrue(Map<String, Object> map) {
        return chattingMapper.selectChatRoomIsTrue(map);
    }
//    채팅방 정보 단일 조회
    public Optional<ChatRoomViewDTO> findChatRoom(Map<String, Object> map) {
        return chattingMapper.selectChatRoom(map);
    }
//    채팅방 리스트 불러오기
    public List<ChatRoomViewDTO> findAllChatRoom(Map<String, Object> map) {
        return chattingMapper.selectAllChatRoom(map);
    }
//    멤버가 채팅방의 퍼스트멤버인지, 세컨드 멤버인지 찾기
    public Optional<String> findChatMemberPosition(Map<String, Object> map) {
        return chattingMapper.selectChatMemberPosition(map);
    }
//    채팅 내역 불러오기
    public List<ChatViewDTO> findChat(Long chatRoomId) {
        return chattingMapper.selectChat(chatRoomId);
    }
//    읽지 않은 채팅의 수 조회
    public Optional<Integer> findAllNotReadChat(Long memberId) {
        return chattingMapper.selectAllNotReadChat(memberId);
    }
//    채팅방 생성
    public void saveChatRoom(ChatRoomVO chatRoomVO) {
        chattingMapper.insertChatRoom(chatRoomVO);
    }
//    채팅 작성
    public void saveChat(ChatVO chatVO) {
        chattingMapper.insertChat(chatVO);
    }
//    채팅방 마지막 채팅내역 업데이트
    public void updateChatRoom(ChatVO ChatVO) {
        chattingMapper.updateChatRoom(ChatVO);
    }
//    채팅방 숨기기
    public void updateHideChatRoom (Map<String, Object> map) {
        chattingMapper.updateHideChatRoom(map);
    }
//    채팅방 보이기
    public void updateViewChatRoom (Map<String, Object> map) {
        chattingMapper.updateViewChatRoom(map);
    }
//    채팅 읽음 처리
    public void updateChatRead(Map<String, Object> map) {
        chattingMapper.updateChatRead(map);
    }
//    특정 채팅을 모든 유저에게 숨기기
    public void updateHideChat(Map<String, Object> map) {
        chattingMapper.updateHideChat(map);
    }
//    회원탈퇴용 채팅방 전체 삭제
    public void deleteAllChatRoom(Long memberId) {
        chattingMapper.deleteAllChatRoom(memberId);
    }
//    회원탈퇴용 채팅 전체 삭제
    public void deleteAllChat(Long chatRoomId) {
        chattingMapper.deleteAllChat(chatRoomId);
    }
}
