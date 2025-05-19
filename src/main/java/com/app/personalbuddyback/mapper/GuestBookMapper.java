package com.app.personalbuddyback.mapper;

import com.app.personalbuddyback.domain.GuestBookVO;
import com.app.personalbuddyback.domain.GuestBookViewDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GuestBookMapper {
//    방명록 작성
    public void insert(GuestBookVO guestBookVO);
//    방명록 리스트
    public List<GuestBookVO> selectAll(Long ownerMemberId);
//    방명록 페이지네이션
    public List<GuestBookViewDTO> selectOnePage(Map<String,Object> map);
//    방명록 삭제
    public void delete(Long id);
//    회원탈퇴용 방명록 전체 삭제
    public void deleteAllByWithdraw(Long memberId);
}
