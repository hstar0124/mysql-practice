package com.example.mysqlpractice.application.controller;

import com.example.mysqlpractice.domain.member.dto.MemberDto;
import com.example.mysqlpractice.domain.member.dto.MemberNicknameHistoryDto;
import com.example.mysqlpractice.domain.member.dto.RegisterMemberCommand;
import com.example.mysqlpractice.domain.member.service.MemberReadService;
import com.example.mysqlpractice.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/members")
public class MemberController {

    final private MemberWriteService memberWriteService;
    final private MemberReadService memberReadService;

    @PostMapping
    public MemberDto register(@RequestBody RegisterMemberCommand commond) {
        var member = memberWriteService.create(commond);
        return memberReadService.toDto(member);
    }

    @GetMapping("/{id}")
    public MemberDto getMember(@PathVariable Long id) {
        return memberReadService.getMember(id);
    }

    @PostMapping("/{id}/name")
    public MemberDto changeNickname(@PathVariable Long id, @RequestBody String nickname) {
        memberWriteService.changeNickname(id, nickname);
        return memberReadService.getMember(id);
    }

    @GetMapping("/{memberId}/nickname-histories")
    public List<MemberNicknameHistoryDto> getNicknameHistories(@PathVariable Long memberId) {
        return memberReadService.getNicknameHistories(memberId);

    }
}
