package com.programmerbeginner.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data
@AllArgsConstructor
public class AuthorQueryDto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1282022957495606149L;

    private Long idBook;

    private String authorName;
}
