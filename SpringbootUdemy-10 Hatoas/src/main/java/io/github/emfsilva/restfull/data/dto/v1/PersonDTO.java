package io.github.emfsilva.restfull.data.dto.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.*;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@JsonPropertyOrder({"id", "firstName", "lastName","address", "gender"})
public class PersonDTO extends ResourceSupport implements Serializable {
    private static final long serialVersionUID = -2157592387257060508L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;
}
