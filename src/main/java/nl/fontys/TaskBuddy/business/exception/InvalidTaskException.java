package nl.fontys.TaskBuddy.business.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidTaskException extends ResponseStatusException {
    public InvalidTaskException(String errorCode) {
        super(HttpStatus.NOT_FOUND, errorCode);
    }
}