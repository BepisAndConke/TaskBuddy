package nl.fontys.TaskBuddy.controller;

import nl.fontys.TaskBuddy.business.interf.GetUserUseCase;
import nl.fontys.TaskBuddy.business.interf.UpdateUserUseCase;
import nl.fontys.TaskBuddy.domain.requests.CreateUserRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.TaskBuddy.business.interf.CreateUserUseCase;
import nl.fontys.TaskBuddy.domain.requests.GetUserRequest;
import nl.fontys.TaskBuddy.domain.requests.UpdateUserRequest;
import nl.fontys.TaskBuddy.domain.responses.CreateUserResponse;
import nl.fontys.TaskBuddy.domain.responses.GetUserResponse;
import nl.fontys.TaskBuddy.domain.responses.UpdateUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
//@CrossOrigin(origins = {"http://127.0.0.1:5173/"})
public class UsersController {
    private final CreateUserUseCase createUserUseCase;
    private final GetUserUseCase getUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;

    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<GetUserResponse> getUserTask(@PathVariable(value = "id") final long userId){
        GetUserResponse response = getUserUseCase.getUser(new GetUserRequest(userId));
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable(value = "id") Long id,
                                           @RequestBody @Valid UpdateUserRequest request){
        request.setId(id);
        UpdateUserResponse response = updateUserUseCase.updateUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
