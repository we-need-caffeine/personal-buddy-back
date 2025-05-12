package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.GuestBookVO;
import com.app.personalbuddyback.mapper.GuestBookMapper;
import com.app.personalbuddyback.repository.GuestBookDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void deleteGuestBookById(Long id) {
        guestBookDAO.delete(id);
    }
}
