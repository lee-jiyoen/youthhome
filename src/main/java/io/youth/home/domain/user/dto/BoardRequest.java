package io.youth.home.domain.user.dto;

public record BoardRequest(
        String title,
        String content,
        Long authorId
){}
