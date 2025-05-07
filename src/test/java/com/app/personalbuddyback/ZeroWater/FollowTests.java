package com.app.personalbuddyback.ZeroWater;

import com.app.personalbuddyback.domain.FollowVO;
import com.app.personalbuddyback.mapper.FollowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class FollowTests {

    @Autowired
    private FollowMapper followMapper;

    //    팔로워 추가
    @Test
    public void insertFollowTest() {
        FollowVO followVO = new FollowVO();
        followVO.setFollowerMemberId(3L);
        followVO.setFollowingMemberId(4L);

        followMapper.insert(followVO);
    }
    //    팔로워 리스트 포기
    @Test
    public void selectFollowerTest() {
        List<FollowVO> followerList = followMapper.selectFollower(1L);
        log.info(followerList.toString());
    }
    //    팔로우 리스트 보기
    @Test
    public void selectFollowTest() {
        List<FollowVO> followList = followMapper.selectFollowing(2L);
        log.info(followList.toString());
    }
    //    즐겨찾기 토글
    @Test
    public void updateFollowTest() {
        FollowVO followVO = new FollowVO();
        followVO.setFollowerMemberId(1L);
        followVO.setFollowingMemberId(3L);
        followVO.setFollowFavorite(1);

        followMapper.update(followVO);
    }
    //    팔로우 취소
    @Test
    public void deleteFollowTest() {
        FollowVO followVO = new FollowVO();
        followVO.setFollowerMemberId(1L);
        followVO.setFollowingMemberId(2L);

        followMapper.delete(followVO);
    }
}
