package nl.fontys.TaskBuddy.business.impl;

import lombok.AllArgsConstructor;
import nl.fontys.TaskBuddy.business.exception.*;
import nl.fontys.TaskBuddy.business.interf.UpdateTaskUseCase;
import nl.fontys.TaskBuddy.configuration.security.token.impl.AccessToken;
import nl.fontys.TaskBuddy.domain.requests.UpdateTaskRequest;
import nl.fontys.TaskBuddy.domain.responses.UpdateTaskResponse;
import nl.fontys.TaskBuddy.persistence.entity.TaskEntity;
import nl.fontys.TaskBuddy.persistence.entity.TaskStatus;
import nl.fontys.TaskBuddy.persistence.entity.UserEntity;
import nl.fontys.TaskBuddy.persistence.interf.TaskRepository;
import nl.fontys.TaskBuddy.persistence.interf.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UpdateTaskUseCaseImpl implements UpdateTaskUseCase {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private AccessToken accessToken;

    @Override
    @Transactional
    public UpdateTaskResponse updateTask(UpdateTaskRequest request) {
        if(accessToken.getUserId().equals(request.getId())) {
            Optional<TaskEntity> taskOptional = taskRepository.findById(request.getId());
            if (taskOptional.isEmpty()) {
                throw new InvalidTaskException("TASK_ID_INVALID");
            }

            TaskStatus newStatus = TaskStatus.TODO;

            if(request.getStatus() != null)
            {
                if(!ObjectUtils.containsConstant(TaskStatus.values(),request.getStatus())){
                    throw new InvalidStatusException("INVALID_STATUS");
                } else {
                    newStatus = Enum.valueOf(TaskStatus.class, request.getStatus());
                }
            }
            UserEntity user = taskOptional.get().getUser();
            updateFields(request, taskOptional.get());

            if(newStatus.equals(TaskStatus.DONE)) {
                updateXp(taskRepository.findById(request.getId()).get(), user);
            }

            return UpdateTaskResponse.builder()
                    .newXp(user.getExp())
                    .newLevel(user.getLevel())
                    .build();
        } else {
            throw new UnauthorizedAccessException("USER_ID_NOT_FROM_LOGGED_IN_USER");
        }
    }

    private void updateXp(TaskEntity task, UserEntity user) {
        task.setStatus(TaskStatus.DONE);
        Integer xpDelta = task.getDifficulty() * task.getLength();
        if(user.getExp() + xpDelta >= 25){ //hardcoded to 25, should change
            user.setLevel(user.getLevel() + 1);
            user.setExp(user.getExp() + xpDelta - 25);
        }
        else {
            user.setExp(user.getExp() + xpDelta);
        }
        userRepository.save(user);
        taskRepository.save(task);
    }

    private void updateFields(UpdateTaskRequest request, TaskEntity task) {

        if(request.getTitle() != null){
            task.setTitle(request.getTitle());
        }
        if(request.getDescription() != null){
            task.setDescription(request.getDescription());
        }
        if(request.getDueDate() != null){
            task.setDueDate(Date.from(Instant.from(LocalDateTime.parse(request.getDueDate()).toLocalDate())));
        }

        Integer newLength = -1;
        Integer newDifficulty = -1;
        if(request.getLength() != null){
            task.setLength(request.getLength());
            newLength = request.getLength();
        }
        if(request.getDifficulty() != null){
            task.setDifficulty(request.getDifficulty());
            newDifficulty = request.getDifficulty();
        }

        if(newLength > 0 && newDifficulty > 0){
            task.setRewardXp(newDifficulty * newLength);
        }

        taskRepository.save(task);

    }
}
