package io.github.emfsilva.data.vo.v1;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@JsonPropertyOrder({ "id", "firstName", "lastName", "address", "gender", "enabled"})
public class PersonDTO extends RepresentationModel<PersonDTO> implements Serializable{

	private static final long serialVersionUID = 1L;

	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	private String firstName;
	private String lastName;
	private String address;
	private String gender;
	private Boolean enabled;
}