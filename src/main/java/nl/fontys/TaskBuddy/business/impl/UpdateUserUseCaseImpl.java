package nl.fontys.TaskBuddy.business.impl;

import nl.fontys.TaskBuddy.business.exception.*;
import nl.fontys.TaskBuddy.business.interf.UpdateUserUseCase;
import nl.fontys.TaskBuddy.configuration.security.token.impl.AccessToken;
import nl.fontys.TaskBuddy.domain.requests.UpdateUserRequest;
import nl.fontys.TaskBuddy.domain.responses.UpdateUserResponse;
import nl.fontys.TaskBuddy.persistence.entity.UserEntity;
import nl.fontys.TaskBuddy.persistence.interf.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateUserUseCaseImpl implements UpdateUserUseCase {
    private final UserRepository userRepository;
    private AccessToken accessToken;

    @Override
    @Transactional
    public UpdateUserResponse updateUser(UpdateUserRequest request) {
        if(accessToken.getUserId().equals(request.getId())) {
            Optional<UserEntity> userOptional = userRepository.findById(request.getId());
            if (userOptional.isEmpty()) {
                throw new InvalidUserException("USER_ID_INVALID");
            }

            UserEntity user = userOptional.get();
            updateFields(request, user);
            updateXp(request, user);
            return UpdateUserResponse.builder()
                    .newXp(user.getExp())
                    .newLevel(user.getLevel())
                    .build();
        } else {
            throw new UnauthorizedAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
        }
    }

    private void updateXp(UpdateUserRequest request, UserEntity user) {
        if(user.getExp() + request.getXpDelta() >= 25){ //hardcoded, should change
            user.setLevel(user.getLevel() + 1);
            user.setExp(user.getExp() + request.getXpDelta() - 25);
        }
        else {
            user.setExp(user.getExp() + request.getXpDelta());
        }
    }

    private void updateFields(UpdateUserRequest request, UserEntity user){

        if(request.getUsername() != null){
            if(!userRepository.existsByUsername(request.getUsername())) {
                user.setUsername(request.getUsername());
            }
            else {
                throw new UsernameAlreadyExistsException();
            }
        }
        if(request.getEmail() != null) {
            if(!userRepository.existsByEmail(request.getEmail())) {
                user.setEmail(request.getEmail());
            }
            else {
                throw new EmailAlreadyExistsException();
            }
        }
        if(request.getFirstName() != null){
            user.setFirstName(request.getFirstName());
        }
        if(request.getLastName() != null){
            user.setLastName(request.getLastName());
        }
        if(request.getAddress() != null){
            user.setAddress(request.getAddress());
        }

        userRepository.save(user);

    }
}
