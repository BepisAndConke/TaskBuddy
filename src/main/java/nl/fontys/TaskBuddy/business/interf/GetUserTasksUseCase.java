package nl.fontys.TaskBuddy.business.interf;


import nl.fontys.TaskBuddy.domain.requests.GetUserTasksRequest;
import nl.fontys.TaskBuddy.domain.responses.GetUserTasksResponse;

public interface GetUserTasksUseCase {
    GetUserTasksResponse getUserTasks(GetUserTasksRequest request);
}
