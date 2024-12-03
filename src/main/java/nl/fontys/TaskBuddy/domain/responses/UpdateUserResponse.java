package nl.fontys.TaskBuddy.domain.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateUserResponse {
    private Integer newXp;
    private Integer newLevel;
}
