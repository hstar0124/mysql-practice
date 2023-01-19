package com.example.mysqlpractice.domain.post.service;

import com.example.mysqlpractice.domain.post.dto.DailyPostCount;
import com.example.mysqlpractice.domain.post.dto.DailyPostCountRequest;
import com.example.mysqlpractice.domain.post.entity.Post;
import com.example.mysqlpractice.domain.post.repository.PostRepository;
import com.example.mysqlpractice.util.CursorRequest;
import com.example.mysqlpractice.util.PageCursor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostReadService {

    final private PostRepository postRepository;

    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request) {

        return postRepository.groupByCreatedDate(request);
    }

    public Page<Post> getPosts(Long memberId, Pageable pageRequest) {
        return postRepository.findAllByMemberId(memberId, pageRequest);
    }

    public PageCursor<Post> getPosts(Long memberId, CursorRequest cursorRequest) {
        var posts = findAllBy(memberId, cursorRequest);
        var nextKey = posts.stream()
                .mapToLong(Post::getId)
                .min()
                .orElse(CursorRequest.NONE_KEY);

        return new PageCursor<>(cursorRequest.next(nextKey), posts);
    }

    private List<Post> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if(cursorRequest.hasKey()){
            return postRepository.findALlByLessThanIdAndMemberIdAndOrderByIdDesc(cursorRequest.key(), memberId, cursorRequest.size());
        } else {
            return postRepository.findALlByMemberIdAndOrderByIdDesc(memberId, cursorRequest.size());
        }
    }
}
