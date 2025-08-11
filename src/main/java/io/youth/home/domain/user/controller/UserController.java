package io.youth.home.domain.user.controller;

import io.youth.home.domain.user.Service.UserService;
import io.youth.home.domain.user.dto.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")//기본경로
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody UserRequest request) {
        userService.register(request);
        return ResponseEntity.ok("회원가입 성공!");
    }
}
