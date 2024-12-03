package nl.fontys.TaskBuddy.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.TaskBuddy.business.exception.InvalidUserException;
import nl.fontys.TaskBuddy.business.exception.UnauthorizedAccessException;
import nl.fontys.TaskBuddy.business.interf.CreateTaskUseCase;
import nl.fontys.TaskBuddy.configuration.security.token.impl.AccessToken;
import nl.fontys.TaskBuddy.domain.requests.CreateTaskRequest;
import nl.fontys.TaskBuddy.domain.responses.CreateTaskResponse;
import nl.fontys.TaskBuddy.persistence.entity.TaskEntity;
import nl.fontys.TaskBuddy.persistence.entity.TaskStatus;
import nl.fontys.TaskBuddy.persistence.entity.UserEntity;
import nl.fontys.TaskBuddy.persistence.interf.TaskRepository;
import nl.fontys.TaskBuddy.persistence.interf.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CreateTaskUseCaseImpl implements CreateTaskUseCase {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private AccessToken accessToken;


    @Override
    @Transactional
    public CreateTaskResponse createTask(CreateTaskRequest request, Long userId) {
        if(!accessToken.getUserId().equals(userId)) {
            throw new UnauthorizedAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
        }

        Optional<UserEntity> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new InvalidUserException("USER_NOT_FOUND");
        }

        TaskEntity savedTask = saveNewTask(request, userId);
        return CreateTaskResponse.builder()
                .taskId(savedTask.getId().toString())
                .build();
    }

    private TaskEntity saveNewTask(CreateTaskRequest request, Long userId) {
        TaskEntity newTask = TaskEntity.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .createdAt(Date.from(Instant.now()))
                .status(TaskStatus.TODO)
                .dueDate(null) //for now
                .difficulty(request.getDifficulty())
                .length(request.getLength())
                .rewardXp(request.getDifficulty() * request.getLength()) //temp
                .user(userRepository.findById(userId).get())
                .build();
        return taskRepository.save(newTask);
    }

}
