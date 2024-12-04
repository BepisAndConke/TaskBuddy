package nl.fontys.TaskBuddy.business.impl.tasks;

import nl.fontys.TaskBuddy.business.exception.InvalidUserException;
import nl.fontys.TaskBuddy.business.exception.UnauthorizedAccessException;
import nl.fontys.TaskBuddy.business.TaskConverter;
import nl.fontys.TaskBuddy.configuration.security.token.impl.AccessToken;
import nl.fontys.TaskBuddy.domain.Task;
import nl.fontys.TaskBuddy.domain.requests.tasks.GetUserTasksRequest;
import nl.fontys.TaskBuddy.domain.responses.tasks.GetUserTasksResponse;
import nl.fontys.TaskBuddy.persistence.interf.TaskRepository;
import nl.fontys.TaskBuddy.persistence.interf.UserRepository;
import lombok.AllArgsConstructor;
import nl.fontys.TaskBuddy.business.interf.tasks.GetUserTasksUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUserTasksUseCaseImpl implements GetUserTasksUseCase {
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    private AccessToken accessToken;

    @Override
    @Transactional
    public GetUserTasksResponse getUserTasks(GetUserTasksRequest request) {
        if(!accessToken.getUserId().equals(request.getUserId())){
            throw new UnauthorizedAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
        }

        if(!userRepository.existsById(request.getUserId())) {
            throw new InvalidUserException("INVALID_USER_ID");
        }

        List<Task> tasks = taskRepository.getTaskEntitiesByUserId(request.getUserId())
                .stream().map(TaskConverter::convert).toList();

        return GetUserTasksResponse.builder()
                .tasks(tasks)
                .build();
    }
}
