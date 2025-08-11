package io.youth.home.domain.user.Service;

import io.youth.home.domain.user.dto.UserRequest;
import io.youth.home.domain.user.entity.Role;
import io.youth.home.domain.user.entity.User;
import io.youth.home.domain.user.repository.UserRepository;
import io.youth.home.global.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(UserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new UserException.UserEmailAlreadyExistsException();
        }
        User user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .nickname(request.nickname())
                .address(request.address())
                .role(Role.USER)
                .active(true)
                .build();

        userRepository.save(user);
    }
}
