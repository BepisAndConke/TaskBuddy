package nl.fontys.TaskBuddy.business.impl.users;

import nl.fontys.TaskBuddy.business.exception.LoginFailedException;
import nl.fontys.TaskBuddy.configuration.security.token.AccessTokenEncoder;
import nl.fontys.TaskBuddy.configuration.security.token.impl.AccessToken;
import nl.fontys.TaskBuddy.domain.requests.users.LoginUserRequest;
import nl.fontys.TaskBuddy.domain.responses.users.LoginUserResponse;
import nl.fontys.TaskBuddy.persistence.entity.UserEntity;
import nl.fontys.TaskBuddy.persistence.interf.UserRepository;
import lombok.AllArgsConstructor;
import nl.fontys.TaskBuddy.business.interf.users.LoginUserUseCase;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginUserUseCaseImpl implements LoginUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    @Override
    @Transactional
    public LoginUserResponse loginUser(LoginUserRequest request){
        Optional<UserEntity> loginUser = userRepository.findByUsernameOrEmail(request.getUsernameOrEmail(), request.getUsernameOrEmail());
        if(loginUser.isEmpty()){
            throw new LoginFailedException();
        }
        if(!matchesPassword(request.getPassword(), loginUser.get().getPassword())) {
            throw new LoginFailedException();
        }

        String accessToken = generateAccessToken(loginUser.get());
        return LoginUserResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    private boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    private String generateAccessToken(UserEntity user) {
        Long userId = user.getId() != null ? user.getId() : null;
        return accessTokenEncoder.encode(
                new AccessToken(user.getUsername(), userId, user.getRole()));
    }
}
