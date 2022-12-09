package edu.ucmo.studentenrollment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ClassFullException extends RuntimeException {
    public ClassFullException() {
    }

    public ClassFullException(String message) {
        super(message);
    }
}
