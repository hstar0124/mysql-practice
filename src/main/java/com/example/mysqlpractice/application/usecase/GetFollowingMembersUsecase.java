package com.example.mysqlpractice.application.usecase;

import com.example.mysqlpractice.domain.follow.service.FollowReadService;
import com.example.mysqlpractice.domain.member.dto.MemberDto;
import com.example.mysqlpractice.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetFollowingMembersUsecase {

    final private MemberReadService memberReadService;

    final private FollowReadService followReadService;

    public List<MemberDto> execute(Long memberId) {
        var followings = followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream().map(f -> f.getToMemberId()).toList();
        return memberReadService.getMembers(followingMemberIds);

    }
}
