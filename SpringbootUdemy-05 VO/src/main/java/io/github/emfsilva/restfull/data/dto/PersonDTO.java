package io.github.emfsilva.restfull.data.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

public class PersonDTO implements Serializable {
    private static final long serialVersionUID = -2157592387257060508L;

    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;
}
