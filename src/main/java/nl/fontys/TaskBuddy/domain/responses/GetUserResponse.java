package nl.fontys.TaskBuddy.domain.responses;

import lombok.Builder;
import lombok.Data;
import nl.fontys.TaskBuddy.domain.User;

@Data
@Builder
public class GetUserResponse {
    private User user;
}
