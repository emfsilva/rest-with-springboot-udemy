package io.github.emfsilva.data.vo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

@JsonPropertyOrder({ "id", "author", "launchDate", "price", "title", "enabled" })
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class BookDTO extends RepresentationModel<BookDTO> implements Serializable{
 
	private static final long serialVersionUID = 1L;

	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	private String author;
	private Date launchDate;
	private Double price;
	private String title;
	private Boolean enabled;

}