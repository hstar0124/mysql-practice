package com.example.mysqlpractice.domain.post.dto;

public record PostCommand(
        Long memberId,
        String contents
) {
}
