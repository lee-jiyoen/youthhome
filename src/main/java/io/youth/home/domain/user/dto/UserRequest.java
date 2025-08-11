package io.youth.home.domain.user.dto;


public record UserRequest(
        String email,
        String password,
        String nickname,
        String address
) {}
