package nl.fontys.TaskBuddy.business.interf;

import nl.fontys.TaskBuddy.domain.requests.CreateUserRequest;
import nl.fontys.TaskBuddy.domain.responses.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}
