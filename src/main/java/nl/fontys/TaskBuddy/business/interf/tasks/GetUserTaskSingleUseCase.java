package nl.fontys.TaskBuddy.business.interf.tasks;


import nl.fontys.TaskBuddy.domain.requests.tasks.GetUserTaskSingleRequest;
import nl.fontys.TaskBuddy.domain.responses.tasks.GetUserTaskSingleResponse;

public interface GetUserTaskSingleUseCase {
    GetUserTaskSingleResponse getUserTask(GetUserTaskSingleRequest request);
}
