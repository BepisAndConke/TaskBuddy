package nl.fontys.TaskBuddy.controller;

import nl.fontys.TaskBuddy.domain.requests.CreateUserRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nl.fontys.TaskBuddy.business.interf.CreateUserUseCase;
import nl.fontys.TaskBuddy.domain.responses.CreateUserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
//@CrossOrigin(origins = {"http://127.0.0.1:5173/"})
public class UsersController {
    private final CreateUserUseCase createUserUseCase;

    @PostMapping("/signup")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse response = createUserUseCase.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
