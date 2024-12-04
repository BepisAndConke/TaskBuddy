package nl.fontys.TaskBuddy.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.TaskBuddy.business.interf.tasks.CreateTaskUseCase;
import nl.fontys.TaskBuddy.business.interf.tasks.GetUserTaskSingleUseCase;
import nl.fontys.TaskBuddy.business.interf.tasks.GetUserTasksUseCase;
import nl.fontys.TaskBuddy.business.interf.tasks.UpdateTaskUseCase;
import nl.fontys.TaskBuddy.domain.requests.tasks.CreateTaskRequest;
import nl.fontys.TaskBuddy.domain.requests.tasks.GetUserTaskSingleRequest;
import nl.fontys.TaskBuddy.domain.requests.tasks.GetUserTasksRequest;
import nl.fontys.TaskBuddy.domain.requests.tasks.UpdateTaskRequest;
import nl.fontys.TaskBuddy.domain.responses.tasks.CreateTaskResponse;
import nl.fontys.TaskBuddy.domain.responses.tasks.GetUserTaskSingleResponse;
import nl.fontys.TaskBuddy.domain.responses.tasks.GetUserTasksResponse;
import nl.fontys.TaskBuddy.domain.responses.tasks.UpdateTaskResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{id}/tasks")
@AllArgsConstructor
//@CrossOrigin(origins = {"http://127.0.0.1:5173/"})
public class TasksController {
    private final CreateTaskUseCase createTaskUseCase;
    private final GetUserTasksUseCase getUserTasksUseCase;
    private final GetUserTaskSingleUseCase getUserTaskSingleUseCase;
    private final UpdateTaskUseCase updateTaskUseCase;

    @PostMapping("/create")
    public ResponseEntity<CreateTaskResponse> createTask(@RequestBody @Valid CreateTaskRequest request,
                                                         @PathVariable(value = "id") final Long id) {
        CreateTaskResponse response = createTaskUseCase.createTask(request, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping()
    public ResponseEntity<GetUserTasksResponse> getUserTasks(@PathVariable(value = "id") final long id) {
        GetUserTasksResponse response = getUserTasksUseCase.getUserTasks(new GetUserTasksRequest(id));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("{taskId}")
    public ResponseEntity<GetUserTaskSingleResponse> getUserTask(@PathVariable(value = "id") final long userId,
                                                                 @PathVariable(value = "taskId") final String taskId) {
        GetUserTaskSingleResponse response = getUserTaskSingleUseCase.getUserTask(new GetUserTaskSingleRequest(
                userId, Long.parseLong(taskId)));
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("{taskId}")
    public ResponseEntity<UpdateTaskResponse> updateUser(@PathVariable(value = "id") Long id,
                                                         @PathVariable(value = "taskId") Long taskId,
                                                         @RequestBody @Valid UpdateTaskRequest request){
        request.setId(id);
        request.setTaskId(taskId);
        UpdateTaskResponse response = updateTaskUseCase.updateTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
