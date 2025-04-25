package com.app.personalbuddyback.ZeroWater;

import com.app.personalbuddyback.domain.AlertVO;
import com.app.personalbuddyback.mapper.AlertMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class Tests {

    @Autowired
    AlertMapper alertMapper;

    @Test
    public void alertInsertTest() {
    }

}
