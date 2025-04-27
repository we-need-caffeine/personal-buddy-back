package com.app.personalbuddyback.ZeroWater;

import com.app.personalbuddyback.mapper.FollowMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class FollowTests {

    @Autowired
    private FollowMapper followMapper;

    @Test
    public void test() {}

}
