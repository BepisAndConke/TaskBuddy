package nl.fontys.TaskBuddy.business.interf;

import nl.fontys.TaskBuddy.domain.requests.UpdateTaskRequest;
import nl.fontys.TaskBuddy.domain.responses.UpdateTaskResponse;

public interface UpdateTaskUseCase {
    UpdateTaskResponse updateTask(UpdateTaskRequest request);
}
