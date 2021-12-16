package io.github.emfsilva.restfull.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 5595339996800749257L;

    public ResourceNotFoundException(String exception) {
        super(exception);
    }
}
