package nl.fontys.TaskBuddy.business;


import nl.fontys.TaskBuddy.domain.Task;
import nl.fontys.TaskBuddy.persistence.entity.TaskEntity;

public final class TaskConverter {
    private TaskConverter(){
    }

    public static Task convert(TaskEntity task){
        return Task.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .rewardXp(task.getRewardXp())
                .difficulty(task.getDifficulty())
                .length(task.getLength())
                .createdAt(task.getCreatedAt())
                .dueDate(task.getDueDate())
                .userId(task.getUser().getId())
                .build();
    }

}
