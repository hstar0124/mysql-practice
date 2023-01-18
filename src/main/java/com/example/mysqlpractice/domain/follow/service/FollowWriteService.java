package com.example.mysqlpractice.domain.follow.service;

import com.example.mysqlpractice.domain.follow.entity.Follow;
import com.example.mysqlpractice.domain.follow.repository.FollowRepository;
import com.example.mysqlpractice.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@RequiredArgsConstructor
@Service
public class FollowWriteService {

    final private FollowRepository followRepository;

    public void create(MemberDto fromMember, MemberDto toMember) {

        Assert.isTrue(!fromMember.id().equals(toMember.id()), "From, To 회원이 동일합니다.");

        var follow = Follow.builder()
                .fromMemberId(fromMember.id())
                .toMemberId(toMember.id())
                .build();

        followRepository.save(follow);
    }
}
