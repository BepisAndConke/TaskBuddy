package nl.fontys.TaskBuddy.business.interf.users;


import nl.fontys.TaskBuddy.domain.requests.users.GetUserRequest;
import nl.fontys.TaskBuddy.domain.responses.users.GetUserResponse;

public interface GetUserUseCase {
    GetUserResponse getUser(GetUserRequest request);
}
