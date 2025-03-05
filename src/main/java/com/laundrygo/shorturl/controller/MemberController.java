package com.laundrygo.shorturl.controller;

import com.laundrygo.shorturl.domain.Member;
import com.laundrygo.shorturl.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author laundrygo
 * @version 0.1.0
 * @since 2021/06/22 7:05 오후
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public List<Member> findAll() {
        return memberService.findAll();
    }
}
