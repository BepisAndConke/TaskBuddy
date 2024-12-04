package nl.fontys.TaskBuddy.domain.responses.users;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserResponse {
    private String accessToken;
}
