package io.github.emfsilva.restfull.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class AccountCredentialsDTO implements Serializable {
    private static final long serialVersionUID = 7612145804351471829L;

    private String username;
    private String password;
}
