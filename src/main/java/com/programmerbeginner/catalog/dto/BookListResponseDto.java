package com.programmerbeginner.catalog.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class BookListResponseDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1105187910144424344L;

    private String id;

    private String title;

    private String description;

    private String publisherName;

    private List<String> categoryCodes;

    private List<String> authorNames;

}
