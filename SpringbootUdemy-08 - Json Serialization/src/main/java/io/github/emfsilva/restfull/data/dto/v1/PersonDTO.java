package io.github.emfsilva.restfull.data.dto.v1;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@JsonPropertyOrder({"id", "address", "first_name", "last_name", "gender"})
public class PersonDTO implements Serializable {
    private static final long serialVersionUID = -2157592387257060508L;

    @JsonIgnore
    private Long id;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String address;

    private String gender;
}
