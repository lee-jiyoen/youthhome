package io.youth.home.domain.user.dto;

public record BoardUpdateRequest(
        String title,
        String content,
        Long requesterId
) {}
