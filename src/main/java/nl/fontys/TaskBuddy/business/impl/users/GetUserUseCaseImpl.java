package nl.fontys.TaskBuddy.business.impl.users;

import lombok.AllArgsConstructor;
import nl.fontys.TaskBuddy.business.UserConverter;
import nl.fontys.TaskBuddy.business.exception.InvalidTaskException;
import nl.fontys.TaskBuddy.business.exception.InvalidUserException;
import nl.fontys.TaskBuddy.business.interf.users.GetUserUseCase;
import nl.fontys.TaskBuddy.configuration.security.token.impl.AccessToken;
import nl.fontys.TaskBuddy.domain.User;
import nl.fontys.TaskBuddy.domain.requests.users.GetUserRequest;
import nl.fontys.TaskBuddy.domain.responses.users.GetUserResponse;
import nl.fontys.TaskBuddy.persistence.interf.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserUseCaseImpl implements GetUserUseCase {
    private UserRepository userRepository;

    private AccessToken accessToken;

    @Override
    @Transactional
    public GetUserResponse getUser(GetUserRequest request) {
        if(!userRepository.existsById(request.getUserId())) {
            throw new InvalidUserException("INVALID_USER_ID");
        }

        Optional<User> userOptional = userRepository.findById(request.getUserId())
                .map(UserConverter::convert);

        if(userOptional.isEmpty()){
            throw new InvalidTaskException("INVALID_TASK_ID");
        } else {
            return GetUserResponse.builder()
                .user(userOptional.get())
                .build();
        }
    }
}
