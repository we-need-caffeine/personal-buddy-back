package com.app.personalbuddyback.service;

import com.app.personalbuddyback.domain.FollowVO;
import com.app.personalbuddyback.repository.FollowDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final FollowDAO followDAO;

    @Override
    public void followMember(FollowVO followVO) {
        followDAO.save(followVO);
    }

    @Override
    public void getFollowers(Long followerMemberId) {
        followDAO.findFollower(followerMemberId);
    }

    @Override
    public void getFollowing(Long followingMemberId) {
        followDAO.findFollowing(followingMemberId);
    }

    @Override
    public void toggleFollowing(FollowVO followVO) {
        followDAO.update(followVO);
    }

    @Override
    public void unfollowMember(FollowVO followVO) {
        followDAO.delete(followVO);
    }
}
