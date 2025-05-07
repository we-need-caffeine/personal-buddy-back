package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.GuestBookVO;
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
@RequestMapping("guest-book")
public class GuestBookAPI {
    private final GuestBookService guestBookService;

//    방명록 작성
    @Operation(summary = "방명록 작성", description = "방명록 작성을 위한 API")
    @ApiResponse(responseCode = "200", description = "방명록 작성 성공")
    @PostMapping("write")
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
    @GetMapping("list/{ownerMemberId}")
    public List<GuestBookVO> list(@PathVariable Long ownerMemberId) {
        return guestBookService.getGuestBooksByMemberId(ownerMemberId);
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
    @DeleteMapping("delete/{id}")
    public void delete(Long id) {
        guestBookService.deleteGuestBook(id);
    }
}
