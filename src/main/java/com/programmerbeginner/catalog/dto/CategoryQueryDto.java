package com.programmerbeginner.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@AllArgsConstructor
@Data
public class CategoryQueryDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 2476512974163190603L;

    private Long idBook;

    private String categoryCode;
}
