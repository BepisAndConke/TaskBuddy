package nl.fontys.TaskBuddy.business.interf.tasks;

import nl.fontys.TaskBuddy.domain.requests.tasks.UpdateTaskRequest;
import nl.fontys.TaskBuddy.domain.responses.tasks.UpdateTaskResponse;

public interface UpdateTaskUseCase {
    UpdateTaskResponse updateTask(UpdateTaskRequest request);
}
