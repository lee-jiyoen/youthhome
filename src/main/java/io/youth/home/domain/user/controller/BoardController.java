package io.youth.home.domain.user.controller;

import io.youth.home.domain.user.Service.BoardService;
import io.youth.home.domain.user.dto.BoardRequest;
import io.youth.home.domain.user.dto.BoardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boards") // 기본 경로
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponse> create(@RequestBody BoardRequest req) {
        return ResponseEntity.ok(boardService.create(req));
    }
}
