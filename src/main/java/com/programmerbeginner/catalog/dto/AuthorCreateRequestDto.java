 package com.programmerbeginner.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import com.programmerbeginner.catalog.validator.annotsi.ValidAuthorName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AuthorCreateRequestDto implements Serializable {

	 @Serial
	 private static final long serialVersionUID = 6046489608477214951L;

	 @ValidAuthorName
	@NotBlank(message = "author name must not be blank")
	private String authorName;
	
	@NotNull(message = "birth date must not be null")
	private Long birthDate;

	@NotEmpty
	private List<AddressCreateRequestDto> addresses;
	

}
