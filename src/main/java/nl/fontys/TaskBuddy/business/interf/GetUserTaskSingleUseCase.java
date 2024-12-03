package nl.fontys.TaskBuddy.business.interf;


import nl.fontys.TaskBuddy.domain.requests.GetUserTaskSingleRequest;
import nl.fontys.TaskBuddy.domain.responses.GetUserTaskSingleResponse;

public interface GetUserTaskSingleUseCase {
    GetUserTaskSingleResponse getUserTask(GetUserTaskSingleRequest request);
}
