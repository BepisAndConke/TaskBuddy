package nl.fontys.TaskBuddy.business.interf;

import nl.fontys.TaskBuddy.domain.requests.UpdateUserRequest;
import nl.fontys.TaskBuddy.domain.responses.UpdateUserResponse;

public interface UpdateUserUseCase {
    UpdateUserResponse updateUser(UpdateUserRequest request);
}
