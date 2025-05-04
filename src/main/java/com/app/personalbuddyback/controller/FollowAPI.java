package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.domain.FollowVO;
import com.app.personalbuddyback.service.FollowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follow/*")
public class FollowAPI {

    public final FollowService followService;

    @Operation(summary = "팔로우", description = "유저의 ID와 해당 유저가 팔로우할 사람의 ID를 받아 등록하는 API")
    @Parameter(
            name = "followerMemberId",
            description = "내가 팔로우 할 사람의 ID",
            in = ParameterIn.PATH,
            schema = @Schema(type = "number"),
            required = true
    )
    @ApiResponse(responseCode = "200", description = "팔로우 성공")
    @PostMapping("save/{followerMemberId}")
    public void saveFollow(@PathVariable Long followerMemberId, @RequestBody Long memberId) {
        FollowVO followVO = new FollowVO();

        followVO.setFollowerMemberId(followerMemberId);
        followVO.setFollowingMemberId(memberId);
        followService.followMember(followVO);
    }


}
