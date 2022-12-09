package edu.ucmo.studentenrollment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class CannotAssignException extends RuntimeException {
    public CannotAssignException() {
    }

    public CannotAssignException(String message) {
        super(message);
    }
}
