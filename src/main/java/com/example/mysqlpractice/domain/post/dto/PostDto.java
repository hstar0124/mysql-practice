package com.example.mysqlpractice.domain.post.dto;

import java.time.LocalDateTime;

public record PostDto(
        Long id,
        String contents,
        LocalDateTime createdAt,
        Long likeCount
) {
}
