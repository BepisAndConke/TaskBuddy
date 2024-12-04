package nl.fontys.TaskBuddy.business.interf.users;

import nl.fontys.TaskBuddy.domain.requests.users.UpdateUserRequest;
import nl.fontys.TaskBuddy.domain.responses.users.UpdateUserResponse;

public interface UpdateUserUseCase {
    UpdateUserResponse updateUser(UpdateUserRequest request);
}
