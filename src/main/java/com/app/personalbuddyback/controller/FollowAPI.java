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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/follows/api/*")
public class FollowAPI {

    public final FollowService followService;

//    해당 유저를 팔로우하는 API
    @Operation(summary = "팔로우", description = "body(followingMemberId)와 해당 유저가 팔로우할 사람의 followerMemberId를 받아 등록하는 API")
    @Parameter(
            name = "followerMemberId",
            description = "내가 팔로우 할 사람의 ID",
            in = ParameterIn.PATH,
            schema = @Schema(type = "number"),
            required = true
    )
    @ApiResponse(responseCode = "200", description = "팔로우 성공")
    @PostMapping("following/{followerMemberId}")
    public void saveFollow(@PathVariable Long followerMemberId, @RequestBody Long followingMemberId) {
        FollowVO followVO = new FollowVO();

        followVO.setFollowerMemberId(followerMemberId);
        followVO.setFollowingMemberId(followingMemberId);
        followService.followMember(followVO);
    }

//    n번 유저를 팔로우한 모든 유저를 찾는 API
    @Operation(summary = "팔로워 리스트", description = "n번 유저를 구독한 유저들의 리스트를 가져오는 API")
    @Parameter(
            name = "followerMemberId",
            description = "구독된 유저의 아이디",
            in = ParameterIn.PATH,
            schema = @Schema(type = "number"),
            required = true
    )
    @ApiResponse(responseCode = "200", description = "n번 유저의 구독자 리스트 가져오기 성공")
    @GetMapping("get-follower/{followerMemberId}")
    public List<FollowVO> getMyFollower(@PathVariable Long followerMemberId) {
        return followService.getMyFollower(followerMemberId);
    }

//    n번 유저가 팔로우한 모든 유저를 찾는 API
    @Operation(summary = "팔로잉 리스트", description = "n번 유저가 구독한 유저들의 리스트를 가져오는 API")
    @Parameter(
            name = "followingMemberId",
            description = "구독한 유저의 아이디",
            in = ParameterIn.PATH,
            schema = @Schema(type = "number"),
            required = true
    )
    @ApiResponse(responseCode = "200", description = "n번 유저가 구독한 유저의 리스트 가져오기 성공")
    @GetMapping("get-following/{followingMemberId}")
    public List<FollowVO> getMyFollowing(@PathVariable Long followingMemberId) {
        return followService.getMyFollowing(followingMemberId);
    }

    @Operation(summary = "즐겨찾기 리스트", description = "팔로잉 되어있는 유저 중, 즐겨찾기 여부가 1인 유저를 가져오는 API")
    @ApiResponse(responseCode = "200", description = "즐겨찾기 리스트 가져오기 성공")
    @GetMapping("get-favorite/{followingMemberId}")
    public List<FollowVO> getMyFavorite(@PathVariable Long followingMemberId) {
        return followService.getMyFavorite(followingMemberId);
    }

//    n번 유저가 팔로우한 상태의 유저의 즐겨찾기를 토글하는 API
    @Operation(summary = "즐겨찾기 토글", description = "팔로잉 되어있는 유저의 즐겨찾기 여부를 토글하는 API")
    @ApiResponse(responseCode = "200", description = "즐겨찾기 토글 성공")
    @PutMapping("toggle-favorite")
    public void toggleFavorite(@RequestBody FollowVO followVO) {
        followService.toggleFollowing(followVO);
    }

//    해당 팔로우를 삭제하는 API
    @Operation(summary = "팔로우 삭제", description = "팔로잉ID와 팔로워ID를 받아 삭제하는 API")
    @ApiResponse(responseCode = "200", description = "팔로우 삭제 성공")
    @DeleteMapping("unfollow")
    public void delete(@RequestBody FollowVO followVO) {
        followService.unfollowMember(followVO);
    }

}
