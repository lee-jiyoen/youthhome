package io.youth.home.domain.user.Service;

import io.youth.home.domain.user.dto.BoardRequest;
import io.youth.home.domain.user.dto.BoardResponse;
import io.youth.home.domain.user.dto.BoardUpdateRequest;
import io.youth.home.domain.user.entity.Board;
import io.youth.home.domain.user.entity.User;
import io.youth.home.domain.user.repository.BoardRepository;
import io.youth.home.domain.user.repository.UserRepository;
import io.youth.home.global.ErrorCode;
import io.youth.home.global.UserException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardResponse create(BoardRequest req) {
        User author = userRepository.findById(req.authorId())
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        Board board = Board.builder()
                .title(req.title())
                .content(req.content())
                .author(author)
                .build();

        Board saved = boardRepository.save(board);
        return toResponse(saved);
    }

    public BoardResponse update(Long boardId, BoardUpdateRequest req) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new UserException(ErrorCode.BOARD_NOT_FOUND));

        if (!board.getAuthor().getId().equals(req.requesterId())) {
            throw new UserException(ErrorCode.FORBIDDEN);
        }

        board.update(req.title(), req.content());

        return toResponse(board);
    }

    public void delete(Long boardId, Long requesterId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new UserException(ErrorCode.BOARD_NOT_FOUND));

        if (!board.getAuthor().getId().equals(requesterId)) {
            throw new UserException(ErrorCode.FORBIDDEN);
        }

        boardRepository.delete(board);
    }

    private BoardResponse toResponse(Board b) {
        return new BoardResponse(
                b.getId(),
                b.getTitle(),
                b.getContent(),
                b.getAuthor().getId()
        );
    }
}