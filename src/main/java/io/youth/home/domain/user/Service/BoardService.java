package io.youth.home.domain.user.Service;

import io.youth.home.domain.user.dto.BoardRequest;
import io.youth.home.domain.user.dto.BoardResponse;
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
        return new BoardResponse(
                saved.getId(),
                saved.getTitle(),
                saved.getContent(),
                saved.getAuthor().getId()
        );
    }
}
