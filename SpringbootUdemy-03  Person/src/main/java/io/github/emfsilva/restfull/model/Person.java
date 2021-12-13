package io.github.emfsilva.restfull.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Person implements Serializable {
    private static final long serialVersionUID = -2157592387257060508L;

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
}
