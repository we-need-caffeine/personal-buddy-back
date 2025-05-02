package com.app.personalbuddyback.DyunDyun.mapper.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class EventMapperTests {

}
