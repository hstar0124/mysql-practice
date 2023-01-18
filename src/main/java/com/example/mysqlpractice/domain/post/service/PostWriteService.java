package com.example.mysqlpractice.domain.post.service;


import com.example.mysqlpractice.domain.post.dto.PostCommand;
import com.example.mysqlpractice.domain.post.entity.Post;
import com.example.mysqlpractice.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostWriteService {

    final private PostRepository postRepository;

    public Long create(PostCommand command) {
        var post = Post.builder()
                .memberId(command.memberId())
                .contents(command.contents())
                .build();

        return postRepository.save(post).getId();
    }

}
