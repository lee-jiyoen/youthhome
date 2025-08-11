package io.youth.home.domain.user.repository;

import io.youth.home.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    // 로그인용
    Optional<User> findByEmail(String email);

}
