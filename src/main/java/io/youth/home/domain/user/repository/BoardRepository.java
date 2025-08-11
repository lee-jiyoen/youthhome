package io.youth.home.domain.user.repository;

import io.youth.home.domain.user.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
