package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.MemberPointLogVO;

import java.util.List;

public interface PointService {
    public void savePointLog(MemberPointLogVO memberPointLogVO);

    public List<MemberPointLogVO> getPointLogs(Long memberId);

    public void deleteAll(Long memberId);
}
