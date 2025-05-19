package com.app.personalbuddyback.DyunDyun.mapper.board;

import com.app.personalbuddyback.mapper.BoardMapper;
import com.app.personalbuddyback.repository.BoardDAO;
import com.app.personalbuddyback.service.BoardService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@Slf4j
public class BoardDTOTests {

    @Autowired
    private BoardMapper boardMapper;
    @Autowired
    private BoardDAO boardDAO;
    @Autowired
    private BoardService boardService;

    @Test
    public void selectBoardsTest() {
        Map<String, Object> params = new HashMap<>();
//        params.put("boardHashtag", "#관심 일정");
        params.put("order", "좋아요");

        log.info("{}", boardMapper.selectHotBoards());

//        log.info("{}", boardMapper.selectBoards(params));
//        log.info("{}", boardDAO.findBoards(params).toString());
//        log.info("{}",boardService.getBoards(params).toString());
    }
}
