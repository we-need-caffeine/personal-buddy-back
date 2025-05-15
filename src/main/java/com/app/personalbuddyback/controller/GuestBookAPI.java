package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.GuestBookVO;
import com.app.personalbuddyback.domain.GuestBookViewDTO;
import com.app.personalbuddyback.service.GuestBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("guestbooks/api/*")
public class GuestBookAPI {
    private final GuestBookService guestBookService;

//    방명록 작성
    @Operation(summary = "방명록 작성", description = "방명록 작성을 위한 API")
    @ApiResponse(responseCode = "200", description = "방명록 작성 성공")
    @PostMapping("/guestbook/write")
    public void write(@RequestBody GuestBookVO guestBookVO) {
        guestBookService.writeGuestBook(guestBookVO);
    }

//    방명록 조회
    @Operation(summary = "방명록 조회", description = "해당 유저에게 달린 방명록들을 조회하는 API")
    @Parameter(
            name = "ownerMemberId",
            description = "방명록 주인의 아이디",    
            in = ParameterIn.PATH,
            schema = @Schema(type = "number"),
            required = true
    )
    @ApiResponse(responseCode = "200", description = "방명록 조회 성공")
    @GetMapping("/guestbook/list/{ownerMemberId}")
    public List<GuestBookVO> getList(@PathVariable Long ownerMemberId) {
        return guestBookService.getAllGuestBooksByMemberId(ownerMemberId);
    }

//    방명록 페이지네이션
    @Operation(summary = "방명록 페이지 조회", description = "해당 유저에게 달린 방명록들을 한 페이지씩 조회하는 API")
    @Parameter(
            name = "ownerMemberId",
            description = "방명록 주인의 아이디",
            in = ParameterIn.PATH,
            schema = @Schema(type = "number"),
            required = true
    )
    @Parameter(
            name = "page",
            description = "현재 페이지",
            in = ParameterIn.PATH,
            schema = @Schema(type = "number"),
            required = true
    )
    @ApiResponse(responseCode = "200", description = "방명록 페이지 조회 성공")
    @GetMapping("/guestbook/list/page/{ownerMemberId}/{page}")
    public List<GuestBookViewDTO> getOnePage(@PathVariable Long ownerMemberId, @PathVariable Integer page) {
        return  guestBookService.getGuestBooksOnePageByMemberIdAndPage(ownerMemberId, page);
    }

//    방명록 삭제
    @Operation(summary = "방명록 삭제", description = "해당 방명록의 아이디를 받아 삭제하는 API")
    @Parameter(
            name = "id",
            description = "방명록의 아이디",
            in = ParameterIn.PATH,
            schema = @Schema(type = "number"),
            required = true
    )
    @ApiResponse(responseCode = "200", description = "방명록 삭제 성공")
    @DeleteMapping("/guestbook/delete/{id}")
    public void delete(@PathVariable Long id) {
        guestBookService.deleteGuestBookById(id);
    }
}
