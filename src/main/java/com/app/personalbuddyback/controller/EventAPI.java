package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event/*")
public class EventAPI {

    private final EventService eventService;

    // 진행 중 이벤트 3개 (슬라이드배너)

}
