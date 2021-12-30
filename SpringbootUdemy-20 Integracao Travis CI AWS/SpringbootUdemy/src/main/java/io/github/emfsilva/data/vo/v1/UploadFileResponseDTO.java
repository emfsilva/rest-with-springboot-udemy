package io.github.emfsilva.data.vo.v1;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileResponseDTO implements Serializable{
	private static final long serialVersionUID = 8239373813372350142L;

	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private Long size;
}