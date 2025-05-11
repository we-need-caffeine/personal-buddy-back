package com.app.personalbuddyback.DyunDyun.mapper.event;

import com.app.personalbuddyback.domain.EventJoinVO;
import com.app.personalbuddyback.mapper.EventParticipationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class EventParticipationMapperTests {

    @Autowired
    private EventParticipationMapper eventParticipationMapper;

    // 이벤트 참여 등록 테스트
    @Test
    public void testInsertEventJoin() {
        EventJoinVO joinVO = new EventJoinVO();
        joinVO.setEventId(1L);
        joinVO.setMemberId(1L);
        eventParticipationMapper.insertEventJoin(joinVO);
        log.info("이벤트 참여 등록 완료");
    }

    // 이벤트 참여 여부 확인 테스트
    @Test
    public void testIsJoined() {
        int result = eventParticipationMapper.isJoined(1L, 999L);
        log.info("이벤트 참여 여부: {}", result);
    }

    // 기상 챌린지 시간 조건 테스트
    @Test
    public void testWakeUpChallengeTime() {
        int result = eventParticipationMapper.wakeUpChallengeTime();
        log.info("기상 챌린지 시간 조건 (1: 만족): {}", result);
    }
}
