package io.github.emfsilva.restfull.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException {

    private static final long serialVersionUID = 5595339996800749257L;

    public UnsupportedMathOperationException(String exception) {
        super(exception);
    }
}
