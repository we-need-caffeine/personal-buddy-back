package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class MemberServiceImpl implements MemberService {

    private final MemberDAO memberDAO;

    public void join(MemberVO memberVO) {
        memberDAO.insert(memberVO);
    }
}
