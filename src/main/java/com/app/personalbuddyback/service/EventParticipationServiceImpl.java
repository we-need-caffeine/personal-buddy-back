package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.EventJoinVO;
import com.app.personalbuddyback.mapper.EventParticipationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class EventParticipationServiceImpl implements EventParticipationService {

    private final EventParticipationMapper eventParticipationMapper;

    // 이벤트 참여 등록
    @Override
    public void joinEvent(EventJoinVO eventJoinVO) {
        eventParticipationMapper.insertEventJoin(eventJoinVO);
    }

    // 이벤트 참여 여부 확인 (0이면 참여 안 함, 1 이상이면 참여함)
    @Override
    public boolean isJoined(Long eventId, Long memberId) {
        return eventParticipationMapper.isJoined(eventId, memberId) > 0;
    }

    // 현재 시간이 05:00 ~ 06:00 사이인지 확인 (1이면 조건 만족)
    @Override
    public boolean isWakeUpTime() {
        return eventParticipationMapper.wakeUpChallengeTime() == 1;
    }
}
