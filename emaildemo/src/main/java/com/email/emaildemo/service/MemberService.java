package com.email.emaildemo.service;

import com.email.emaildemo.entity.Member;
import com.email.emaildemo.sender.MemberRegisterSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRegisterSender memberRegisterSender;

    public Long memberRegister(Member member) throws Exception {
        //会员注册
            memberRegisterSender.sendMessage(member);

        return member.getId();
    }
}
