package nl.fontys.TaskBuddy.business.interf.tasks;

import nl.fontys.TaskBuddy.domain.requests.tasks.CreateTaskRequest;
import nl.fontys.TaskBuddy.domain.responses.tasks.CreateTaskResponse;

public interface CreateTaskUseCase {
    CreateTaskResponse createTask(CreateTaskRequest request, Long userId);
}
