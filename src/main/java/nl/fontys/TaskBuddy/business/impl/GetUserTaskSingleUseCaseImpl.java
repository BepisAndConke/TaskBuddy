package nl.fontys.TaskBuddy.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.TaskBuddy.business.TaskConverter;
import nl.fontys.TaskBuddy.business.exception.InvalidTaskException;
import nl.fontys.TaskBuddy.business.exception.InvalidUserException;
import nl.fontys.TaskBuddy.business.exception.UnauthorizedAccessException;
import nl.fontys.TaskBuddy.business.interf.GetUserTaskSingleUseCase;
import nl.fontys.TaskBuddy.configuration.security.token.impl.AccessToken;
import nl.fontys.TaskBuddy.domain.Task;
import nl.fontys.TaskBuddy.domain.requests.GetUserTaskSingleRequest;
import nl.fontys.TaskBuddy.domain.responses.GetUserTaskSingleResponse;
import nl.fontys.TaskBuddy.persistence.interf.TaskRepository;
import nl.fontys.TaskBuddy.persistence.interf.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GetUserTaskSingleUseCaseImpl implements GetUserTaskSingleUseCase {
    private TaskRepository taskRepository;
    private UserRepository userRepository;

    private AccessToken accessToken;

    @Override
    @Transactional
    public GetUserTaskSingleResponse getUserTask(GetUserTaskSingleRequest request) {
        if(!accessToken.getUserId().equals(request.getUserId())){
            throw new UnauthorizedAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
        }

        if(!userRepository.existsById(request.getUserId())) {
            throw new InvalidUserException("INVALID_USER_ID");
        }

        Optional<Task> taskOptional = taskRepository.findById(request.getTaskId())
                .map(TaskConverter::convert);

        if(taskOptional.isEmpty()){
            throw new InvalidTaskException("INVALID_TASK_ID");
        } else {
            return GetUserTaskSingleResponse.builder()
                .task(taskOptional.get())
                .build();
        }
    }
}
