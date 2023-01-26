package com.example.mysqlpractice.application.controller;

import com.example.mysqlpractice.application.usecase.CreatePostLikeUsecase;
import com.example.mysqlpractice.application.usecase.CreatePostUsecase;
import com.example.mysqlpractice.application.usecase.GetTimelinePostsUsecase;
import com.example.mysqlpractice.domain.post.dto.DailyPostCount;
import com.example.mysqlpractice.domain.post.dto.DailyPostCountRequest;
import com.example.mysqlpractice.domain.post.dto.PostCommand;
import com.example.mysqlpractice.domain.post.dto.PostDto;
import com.example.mysqlpractice.domain.post.entity.Post;
import com.example.mysqlpractice.domain.post.service.PostReadService;
import com.example.mysqlpractice.domain.post.service.PostWriteService;
import com.example.mysqlpractice.util.CursorRequest;
import com.example.mysqlpractice.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {

    final private PostWriteService postWriteService;

    final private PostReadService postReadService;

    final private GetTimelinePostsUsecase getTimelinePostsUsecase;

    final private CreatePostUsecase createPostUsecase;

    final private CreatePostLikeUsecase createPostLikeUsecase;

//    @PostMapping("")
//    public Long create(PostCommand command) {
//        return postWriteService.create(command);
//    }

    @PostMapping("")
    public Long create(PostCommand command) {

      return createPostUsecase.execute(command);
    }

    @GetMapping("/daily-post-counts")
    public List<DailyPostCount> getDailyPostCounts(DailyPostCountRequest request) {

        return postReadService.getDailyPostCount(request);
    }

    @GetMapping("/members/{memberId}")
    public Page<PostDto> getPosts(
            @PathVariable Long memberId,
            Pageable pageable
    ) {

        return postReadService.getPosts(memberId, pageable);
    }

    @GetMapping("/members/{memberId}/by-cursor")
    public PageCursor<Post> getPostsByCursor(
            @PathVariable Long memberId,
            CursorRequest cursorRequest
    ) {

        return postReadService.getPosts(memberId, cursorRequest);
    }

    @GetMapping("/members/{memberId}/timeline")
    public PageCursor<Post> getTimeline(
            @PathVariable Long memberId,
            CursorRequest cursorRequest
    ) {

        //return getTimelinePostsUsecase.execute(memberId, cursorRequest);
        return getTimelinePostsUsecase.executeByTimeline(memberId, cursorRequest);
    }

    @PostMapping("/{postId}/like/v1")
    public void likePost(@PathVariable Long postId) {
        //postWriteService.likePost(postId);
        postWriteService.likePostByOptimisticLock(postId);
    }

    @PostMapping("/{postId}/like/v2")
    public void likePostV2(@PathVariable Long postId, @RequestParam Long memberId) {

        createPostLikeUsecase.execute(postId, memberId);
    }

}
