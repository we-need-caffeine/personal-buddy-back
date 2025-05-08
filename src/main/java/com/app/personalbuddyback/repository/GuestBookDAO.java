package com.app.personalbuddyback.repository;

import com.app.personalbuddyback.domain.GuestBookVO;
import com.app.personalbuddyback.mapper.GuestBookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GuestBookDAO {
    public final GuestBookMapper guestBookMapper;

    //    방명록 작성
    public void save(GuestBookVO guestBookVO) {
        guestBookMapper.insert(guestBookVO);
    }
    //    방명록 리스트
    public List<GuestBookVO> findAll(Long ownerMemberId) {
        return guestBookMapper.selectAll(ownerMemberId);
    }
    //    방명록 삭제
    public void delete(Long id) {
        guestBookMapper.delete(id);
    }
    //    회원탈퇴용 방명록 전체 삭제
    public void deleteAllByWithdraw(Long memberId) {
        guestBookMapper.deleteAllByWithdraw(memberId);
    }
}