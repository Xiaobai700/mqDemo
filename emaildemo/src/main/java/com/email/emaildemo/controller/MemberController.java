package com.email.emaildemo.controller;

import com.email.emaildemo.entity.Member;
import com.email.emaildemo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @RequestMapping("/registerMember")
    @Scheduled(fixedRate = 2000)
    public void registerMember() throws Exception {
        Member member = new Member();
        member.setUsername("zhangsan");
        member.setPassword("123456");
        member.setEmail("2504964152@qq.com");
        memberService.memberRegister(member);
    }
}
