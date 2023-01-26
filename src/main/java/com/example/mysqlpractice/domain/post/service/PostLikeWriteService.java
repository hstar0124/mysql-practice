package com.example.mysqlpractice.domain.post.service;


import com.example.mysqlpractice.domain.member.dto.MemberDto;
import com.example.mysqlpractice.domain.post.dto.PostCommand;
import com.example.mysqlpractice.domain.post.entity.Post;
import com.example.mysqlpractice.domain.post.entity.PostLike;
import com.example.mysqlpractice.domain.post.repository.PostLikeRepository;
import com.example.mysqlpractice.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostLikeWriteService {

    final private PostLikeRepository postLikeRepository;

    public Long create(Post post, MemberDto memberDto) {
        var postLike = PostLike.builder()
                .postId(post.getId())
                .memberId(memberDto.id())
                .build();

        return postLikeRepository.save(postLike).getPostId();
    }


}
