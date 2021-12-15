package io.github.emfsilva.restfull.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ExceptionResponse implements Serializable {
    private static final long serialVersionUID = -4776977170378399944L;

    private Date timestamp;
    private String message;
    private String details;
}
