package nl.fontys.TaskBuddy.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.TaskBuddy.business.interf.CreateTaskUseCase;
import nl.fontys.TaskBuddy.domain.requests.CreateTaskRequest;
import nl.fontys.TaskBuddy.domain.responses.CreateTaskResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/{id}/tasks")
@AllArgsConstructor
//@CrossOrigin(origins = {"http://127.0.0.1:5173/"})
public class TasksController {
    private final CreateTaskUseCase createTaskUseCase;

    @PostMapping("/create")
    public ResponseEntity<CreateTaskResponse> createTask(@RequestBody @Valid CreateTaskRequest request,
                                                         @PathVariable(value = "id") final Long id) {
        CreateTaskResponse response = createTaskUseCase.createTask(request, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
