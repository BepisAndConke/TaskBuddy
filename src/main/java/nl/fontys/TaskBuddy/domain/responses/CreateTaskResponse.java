package nl.fontys.TaskBuddy.domain.responses;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateTaskResponse {
    private String taskId;
}
