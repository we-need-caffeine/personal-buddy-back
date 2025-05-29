package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.EventJoinVO;

public interface EventParticipationService {

        // 이벤트 참여 등록
        void joinEvent(EventJoinVO eventJoinVO);

        // 이벤트 참여 여부 확인
        boolean isJoined(Long eventId, Long memberId);

        // 기상 챌린지 시간 조건 확인
        boolean isWakeUpTime();

        // 루틴 공유 이벤트 포인트 지급
        void givePointForRoutineEvent(Long memberId, Long eventId);
}
