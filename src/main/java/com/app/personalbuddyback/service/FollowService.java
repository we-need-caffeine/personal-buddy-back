package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.FollowVO;
import com.app.personalbuddyback.domain.MemberVO;
import com.app.personalbuddyback.domain.ProfileCardDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface FollowService {

    //    팔로워 추가
    public void followMember(FollowVO followVO);
    //    팔로워 리스트 포기
    public List<FollowVO> getAllMyFollower(Long followerMemberId);
    //    팔로우 리스트 보기
    public List<FollowVO> getAllMyFollowing(Long followingMemberId);
    //    내가 즐겨찾기 한 유저의 리스트
    public List<FollowVO> getAllMyFavorite(Long favoriteMemberId);
    //    맞팔된 유저의 리스트
    public List<MemberVO> getMutualFollows(Long memberId);
    //    해당멤버의 프로필 카드와 함께 팔로우 여부와 즐겨찾기 여부를 가져온다
    public Optional<ProfileCardDTO> getMemberProfileCard(Long memberId, Long profileCardMemberId);
    //    즐겨찾기 토글
    void updateToggleFollowing(FollowVO followVO);
    //    팔로우 취소
    void deleteUnfollowMember(Long followerMemberId, Long followingMemberId);
}
