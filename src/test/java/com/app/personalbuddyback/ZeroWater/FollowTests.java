package com.app.personalbuddyback.ZeroWater;

import com.app.personalbuddyback.domain.FollowVO;
import com.app.personalbuddyback.domain.ProfileCardDTO;
import com.app.personalbuddyback.mapper.FollowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void followerListTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("myId", 2L);
        map.put("searchNickname", "홍길동");
//        map.put("filterType", "follow");
        map.put("filterType", "favorite");

        List<ProfileCardDTO> profileCardDTO = followMapper.selectFollowerList(map);

        profileCardDTO.forEach(profileCardDTO1 -> {
            log.info("추출한 멤버 : {}", profileCardDTO1);
        });

    }

    @Test
    public void followListTest() {
        Map<String, Object> map = new HashMap<>();
        map.put("myId", 2L);
        map.put("searchNickname", "홍길동");
//        map.put("filterType", "favorite");

        List<ProfileCardDTO> profileCardDTO = followMapper.selectFollowList(map);

        profileCardDTO.forEach(profileCardDTO1 -> {
            log.info("추출한 멤버 : {}", profileCardDTO1);
        });
    }
}
