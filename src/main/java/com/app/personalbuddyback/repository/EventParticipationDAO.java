package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.EventJoinVO;
import com.app.personalbuddyback.mapper.EventParticipationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class EventParticipationDAO {

    private final EventParticipationMapper participationMapper;

    // 이벤트 참여 등록
    public void saveParticipation(EventJoinVO joinVO) {
        participationMapper.insertEventJoin(joinVO);
    }

    // 이벤트 참여 여부 확인
    public boolean hasJoined(Long eventId, Long memberId) {
        return participationMapper.isJoined(eventId, memberId) > 0;
    }

    // 기상 챌린지 시간 조건 확인
    public boolean isWakeUpTime() {
        return participationMapper.wakeUpChallengeTime() == 1;
    }
}
