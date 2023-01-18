package com.example.mysqlpractice.domain.post.service;

import com.example.mysqlpractice.domain.post.dto.DailyPostCount;
import com.example.mysqlpractice.domain.post.dto.DailyPostCountRequest;
import com.example.mysqlpractice.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostReadService {

    final private PostRepository postRepository;

    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request) {

        return postRepository.groupByCreatedDate(request);
    }
}
