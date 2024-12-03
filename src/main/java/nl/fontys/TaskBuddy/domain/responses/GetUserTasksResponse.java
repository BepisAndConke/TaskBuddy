package nl.fontys.TaskBuddy.domain.responses;

import lombok.Builder;
import lombok.Data;
import nl.fontys.TaskBuddy.domain.Task;

import java.util.List;

@Data
@Builder
public class GetUserTasksResponse {
    private List<Task> tasks;
}
