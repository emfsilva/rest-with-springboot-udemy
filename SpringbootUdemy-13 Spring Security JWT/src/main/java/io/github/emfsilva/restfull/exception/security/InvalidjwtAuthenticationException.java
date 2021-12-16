package io.github.emfsilva.restfull.exception.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidjwtAuthenticationException extends AuthenticationException {
    private static final long serialVersionUID = -6313081738851106671L;

    public InvalidjwtAuthenticationException(String exception) {
        super(exception);
    }
}
