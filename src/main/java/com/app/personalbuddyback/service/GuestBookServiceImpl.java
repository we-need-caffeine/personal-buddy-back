package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.GuestBookVO;
import com.app.personalbuddyback.domain.GuestBookViewDTO;
import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.repository.GuestBookDAO;
import com.app.personalbuddyback.repository.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GuestBookServiceImpl implements GuestBookService {

    private final GuestBookDAO guestBookDAO;

    @Override
    public void writeGuestBook(GuestBookVO guestBook) {
        guestBookDAO.save(guestBook);
    }

    @Override
    public List<GuestBookVO> getAllGuestBooksByMemberId(Long ownerMemberId) {
        return guestBookDAO.findAll(ownerMemberId);
    }

    @Override
    public List<GuestBookViewDTO> getGuestBooksOnePageByMemberIdAndPage(Long ownerMemberId, Integer page) {
        Map<String, Object> map = new HashMap<>();
        map.put("ownerMemberId", ownerMemberId);
        map.put("page", page);
        return guestBookDAO.findOnePage(map);
    }

    @Override
    public void deleteGuestBookById(Long id) {
        guestBookDAO.delete(id);
    }
}
