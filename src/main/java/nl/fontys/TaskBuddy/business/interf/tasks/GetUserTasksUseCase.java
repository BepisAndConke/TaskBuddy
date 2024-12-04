package nl.fontys.TaskBuddy.business.interf.tasks;


import nl.fontys.TaskBuddy.domain.requests.tasks.GetUserTasksRequest;
import nl.fontys.TaskBuddy.domain.responses.tasks.GetUserTasksResponse;

public interface GetUserTasksUseCase {
    GetUserTasksResponse getUserTasks(GetUserTasksRequest request);
}
