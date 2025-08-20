package io.youth.home.domain.user.controller;

import io.youth.home.domain.user.Service.BoardService;
import io.youth.home.domain.user.dto.BoardRequest;
import io.youth.home.domain.user.dto.BoardResponse;
import io.youth.home.domain.user.dto.BoardUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    public ResponseEntity<BoardResponse> create(@RequestBody BoardRequest req) {
        return ResponseEntity.ok(boardService.create(req));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BoardResponse> update(
            @PathVariable("id") Long id,
            @RequestBody BoardUpdateRequest req
    ) {
        return ResponseEntity.ok(boardService.update(id, req));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable("id") Long id,
            @RequestParam(name = "requesterId") Long requesterId
    ) {
        boardService.delete(id, requesterId);
        return ResponseEntity.noContent().build();
    }
}
