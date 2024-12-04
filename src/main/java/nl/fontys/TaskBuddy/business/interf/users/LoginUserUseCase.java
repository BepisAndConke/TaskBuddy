package nl.fontys.TaskBuddy.business.interf.users;

import nl.fontys.TaskBuddy.domain.requests.users.LoginUserRequest;
import nl.fontys.TaskBuddy.domain.responses.users.LoginUserResponse;

public interface LoginUserUseCase {
    LoginUserResponse loginUser(LoginUserRequest request);
}
