package io.youth.home.domain.user.dto;

public record BoardResponse(
        Long boardId,
        String title,
        String content,
        Long authorId
) {}