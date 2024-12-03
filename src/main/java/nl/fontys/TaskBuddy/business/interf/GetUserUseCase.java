package nl.fontys.TaskBuddy.business.interf;


import nl.fontys.TaskBuddy.domain.requests.GetUserRequest;
import nl.fontys.TaskBuddy.domain.responses.GetUserResponse;

public interface GetUserUseCase {
    GetUserResponse getUser(GetUserRequest request);
}
