package com.example.mysqlpractice.application.usecase;

import com.example.mysqlpractice.domain.follow.entity.Follow;
import com.example.mysqlpractice.domain.follow.service.FollowReadService;
import com.example.mysqlpractice.domain.post.dto.PostCommand;
import com.example.mysqlpractice.domain.post.service.PostWriteService;
import com.example.mysqlpractice.domain.post.service.TimelineWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePostUsecase {

    final private PostWriteService postWriteService;
    final private FollowReadService followReadService;
    final private TimelineWriteService timelineWriteService;

    //@Transactional
    public Long execute(PostCommand postCommand) {
        var postId = postWriteService.create(postCommand);

        var followerMemberIds = followReadService
                .getFollowers(postCommand.memberId())
                .stream()
                .map(Follow::getFromMemberId)
                .toList();

        timelineWriteService.deliveryToTimeline(postId, followerMemberIds);

        return postId;
    }
}
