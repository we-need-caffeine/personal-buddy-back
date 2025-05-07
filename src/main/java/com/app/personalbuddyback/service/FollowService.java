package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.FollowVO;

import java.util.List;

public interface FollowService {

    //    팔로워 추가
    public void followMember(FollowVO followVO);
    //    팔로워 리스트 포기
    public List<FollowVO> getMyFollower(Long followerMemberId);
    //    팔로우 리스트 보기
    public List<FollowVO> getMyFollowing(Long followingMemberId);
    //    내가 즐겨찾기 한 유저의 리스트
    public List<FollowVO> getMyFavorite(Long favouriteMemberId);
    //    즐겨찾기 토글
    public void toggleFollowing(FollowVO followVO);
    //    팔로우 취소
    public void unfollowMember(FollowVO followVO);

}
