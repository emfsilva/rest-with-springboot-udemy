package io.github.emfsilva.restfull.data.dto.v1;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import lombok.*;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@JsonPropertyOrder({"id", "author", "launchDate", "price", "title"})
public class BookDTO extends ResourceSupport implements Serializable {
    private static final long serialVersionUID = 3219970101374288562L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;

    private String author;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date launchDate;

    private Double price;

    private String title;
}