package nl.fontys.TaskBuddy.controller;

import nl.fontys.TaskBuddy.business.interf.users.GetUserUseCase;
import nl.fontys.TaskBuddy.business.interf.users.LoginUserUseCase;
import nl.fontys.TaskBuddy.business.interf.users.UpdateUserUseCase;
import nl.fontys.TaskBuddy.domain.requests.users.CreateUserRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.TaskBuddy.business.interf.users.CreateUserUseCase;
import nl.fontys.TaskBuddy.domain.requests.users.GetUserRequest;
import nl.fontys.TaskBuddy.domain.requests.users.LoginUserRequest;
import nl.fontys.TaskBuddy.domain.requests.users.UpdateUserRequest;
import nl.fontys.TaskBuddy.domain.responses.users.CreateUserResponse;
import nl.fontys.TaskBuddy.domain.responses.users.GetUserResponse;
import nl.fontys.TaskBuddy.domain.responses.users.LoginUserResponse;
import nl.fontys.TaskBuddy.domain.responses.users.UpdateUserResponse;
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
    private final LoginUserUseCase loginUserUseCase;

    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginUserResponse> loginUser(@RequestBody @Valid LoginUserRequest request){
        LoginUserResponse response = loginUserUseCase.loginUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
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
