package com.programmerbeginner.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AddressUpdateRequestDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -3269263100106073547L;

    private Long addressId;

    private String street;

    private String city;

    private String zipCode;
}
