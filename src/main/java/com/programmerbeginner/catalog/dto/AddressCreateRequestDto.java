package com.programmerbeginner.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressCreateRequestDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -6564261699175295211L;

    private String street;

    private String city;

    private String zipCode;
}
