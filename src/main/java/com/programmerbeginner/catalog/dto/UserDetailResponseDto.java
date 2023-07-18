package com.programmerbeginner.catalog.dto;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserDetailResponseDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -6992537403932158850L;

    private String username;
}
