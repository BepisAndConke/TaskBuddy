package nl.fontys.TaskBuddy.business.impl.users;

import nl.fontys.TaskBuddy.business.exception.EmailAlreadyExistsException;
import nl.fontys.TaskBuddy.business.exception.InvalidPasswordException;
import nl.fontys.TaskBuddy.business.exception.UsernameAlreadyExistsException;
import nl.fontys.TaskBuddy.business.interf.users.CreateUserUseCase;
import nl.fontys.TaskBuddy.configuration.security.token.AccessTokenEncoder;
import nl.fontys.TaskBuddy.configuration.security.token.impl.AccessToken;
import nl.fontys.TaskBuddy.domain.requests.users.CreateUserRequest;
import nl.fontys.TaskBuddy.domain.responses.users.CreateUserResponse;
import nl.fontys.TaskBuddy.persistence.entity.UserRoles;
import nl.fontys.TaskBuddy.persistence.entity.UserEntity;
import nl.fontys.TaskBuddy.persistence.interf.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final AccessTokenEncoder accessTokenEncoder;

    private final PasswordValidator passwordValidator;

    @Override
    @Transactional
    public CreateUserResponse createUser(CreateUserRequest request) {
        if(userRepository.existsByUsername(request.getUsername())) {
            throw new UsernameAlreadyExistsException();
        }

        if(userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        String errors = passwordValidator.validatePassword(request.getPassword());
        if(errors.isEmpty()) {
            throw new InvalidPasswordException(errors);
        }

        UserEntity savedUser = saveNewUser(request);
        String accessToken = generateAccessToken(savedUser);
        return CreateUserResponse.builder()
                .accessToken(accessToken)
                .build();
    }

    private UserEntity saveNewUser(CreateUserRequest request) {
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        UserEntity newUser = UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encodedPassword)
                .firstName(request.getFirstName().trim())
                .lastName(request.getLastName().trim())
                .address(request.getAddress())
                .role(UserRoles.USER_NORMAL)
                .exp(0)
                .level(1)
                .build();
        return userRepository.save(newUser);
    }

    private String generateAccessToken(UserEntity user) {
        Long userId = user.getId() != null ? user.getId() : null;
        return accessTokenEncoder.encode(
                new AccessToken(user.getUsername(), userId, user.getRole()));
    }
}
