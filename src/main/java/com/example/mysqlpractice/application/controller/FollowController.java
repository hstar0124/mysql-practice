package com.example.mysqlpractice.application.controller;


import com.example.mysqlpractice.application.usecase.CreateFollowMemberUsecase;
import com.example.mysqlpractice.application.usecase.GetFollowingMembersUsecase;
import com.example.mysqlpractice.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/follow")
public class FollowController {

    final private CreateFollowMemberUsecase createFollowMemberUsecase;

    final private GetFollowingMembersUsecase getFollowingMembersUsecase;

    @PostMapping("/{fromId}/{toId}")
    public void create(@PathVariable Long fromId, @PathVariable Long toId) {
        createFollowMemberUsecase.execute(fromId, toId);
    }

    @GetMapping("/members/{fromId}")
    public List<MemberDto> create(@PathVariable Long fromId) {
        return getFollowingMembersUsecase.execute(fromId);
    }
}
