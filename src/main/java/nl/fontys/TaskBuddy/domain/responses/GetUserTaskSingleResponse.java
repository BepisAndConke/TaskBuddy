package nl.fontys.TaskBuddy.domain.responses;

import lombok.Builder;
import lombok.Data;
import nl.fontys.TaskBuddy.domain.Task;

@Data
@Builder
public class GetUserTaskSingleResponse {
    private Task task;
}