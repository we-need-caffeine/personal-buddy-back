package com.app.personalbuddyback.controller;

import com.app.personalbuddyback.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member/*")
public class MemberAPI {

    private final MemberService memberService;


}
