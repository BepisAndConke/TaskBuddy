package nl.fontys.TaskBuddy.business.interf;

import nl.fontys.TaskBuddy.domain.requests.CreateTaskRequest;
import nl.fontys.TaskBuddy.domain.responses.CreateTaskResponse;

public interface CreateTaskUseCase {
    CreateTaskResponse createTask(CreateTaskRequest request, Long userId);
}
