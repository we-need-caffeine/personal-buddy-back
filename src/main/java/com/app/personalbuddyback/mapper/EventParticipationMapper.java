package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.EventJoinVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EventParticipationMapper {

    // 이벤트 참여 등록
    void insertEventJoin(EventJoinVO eventJoinVO);

    // 이벤트 참여 여부 확인
    int isJoined(@Param("eventId") Long eventId, @Param("memberId") Long memberId);

    // 기상 챌린지 시간 조건 체크
    int wakeUpChallengeTime();
}
