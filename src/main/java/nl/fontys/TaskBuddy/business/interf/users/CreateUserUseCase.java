package nl.fontys.TaskBuddy.business.interf.users;

import nl.fontys.TaskBuddy.domain.requests.users.CreateUserRequest;
import nl.fontys.TaskBuddy.domain.responses.users.CreateUserResponse;

public interface CreateUserUseCase {
    CreateUserResponse createUser(CreateUserRequest request);
}
