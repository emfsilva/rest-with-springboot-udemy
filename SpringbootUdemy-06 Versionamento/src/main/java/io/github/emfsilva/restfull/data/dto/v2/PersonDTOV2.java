package io.github.emfsilva.restfull.data.dto.v2;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class PersonDTOV2 implements Serializable {
    private static final long serialVersionUID = -1030028469294741072L;

    private Long id;

    private String firstName;

    private String lastName;

    private String address;

    private String gender;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date birthDay;

}
