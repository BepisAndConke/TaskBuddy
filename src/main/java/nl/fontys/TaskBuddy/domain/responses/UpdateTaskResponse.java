package nl.fontys.TaskBuddy.domain.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateTaskResponse {
    private Integer newXp;
    private Integer newLevel;
}
