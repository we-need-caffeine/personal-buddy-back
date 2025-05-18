package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.MemberPointLogVO;
import com.app.personalbuddyback.mapper.PointMapper;
import com.app.personalbuddyback.repository.PointDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
    private final PointDAO pointDAO;

    @Override
    public void savePointLog(MemberPointLogVO memberPointLogVO) {
        pointDAO.savePointLog(memberPointLogVO);
    }

    @Override
    public List<MemberPointLogVO> getPointLogs(Long memberId) {
        return pointDAO.getPointLogs(memberId);
    }

    @Override
    public void deleteAll(Long memberId) {
        pointDAO.deleteAll(memberId);
    }
}
