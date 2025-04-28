package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.GuestBookVO;

import java.util.List;

public interface GuestBookService {
    //    방명록 작성
    public void writeGuestBook(GuestBookVO guestBook);
    //    방명록 리스트
    public List<GuestBookVO> getGuestBooksByMemberId(Long ownerMemberId);
    //    방명록 삭제
    public void deleteGuestBook(Long id);
}
