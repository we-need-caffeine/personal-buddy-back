package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.FollowVO;

import java.util.List;

public interface FollowService {

    //    팔로워 추가
    public void followMember(FollowVO followVO);
    //    팔로워 리스트 포기
    public void getFollowers(Long followerMemberId);
    //    팔로우 리스트 보기
    public void getFollowing(Long followingMemberId);
    //    즐겨찾기 토글
    public void toggleFollowing(FollowVO followVO);
    //    팔로우 취소
    public void unfollowMember(FollowVO followVO);

}
