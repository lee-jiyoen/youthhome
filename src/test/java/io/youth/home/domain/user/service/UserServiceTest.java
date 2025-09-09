package io.youth.home.domain.user.service;

import io.youth.home.domain.user.Service.UserService;
import io.youth.home.domain.user.dto.UserRequest;
import io.youth.home.domain.user.entity.Role;
import io.youth.home.domain.user.entity.User;
import io.youth.home.domain.user.repository.UserRepository;
import io.youth.home.global.UserException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void 회원가입_성공() {
        // given
        UserRequest request = new UserRequest("test22@naver.com", "1234", "myhome", "daegu");

        given(userRepository.existsByEmail(request.email())).willReturn(false);
        given(passwordEncoder.encode(request.password())).willReturn("encodedPassword");

        // when
        userService.register(request);

        // then
        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());

        User savedUser = captor.getValue();
        assertThat(savedUser.getEmail()).isEqualTo("test@naver.com");
        assertThat(savedUser.getPassword()).isEqualTo("encodedPassword");
        assertThat(savedUser.getNickname()).isEqualTo("myhome");
        assertThat(savedUser.getAddress()).isEqualTo("daegu");
        assertThat(savedUser.getRole()).isEqualTo(Role.USER);
        assertThat(savedUser.getActive()).isTrue();
    }

    @Test
    void 이메일중복_회원가입_실패() {
        // given
        UserRequest request = new UserRequest("test@naver.com", "1234", "myhome", "daegu");

        given(userRepository.existsByEmail(request.email())).willReturn(true);

        // when & then
        assertThatThrownBy(() -> userService.register(request))
                .isInstanceOf(UserException.UserEmailAlreadyExistsException.class);
    }
}
