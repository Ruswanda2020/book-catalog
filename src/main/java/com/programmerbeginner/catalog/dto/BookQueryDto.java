package com.programmerbeginner.catalog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
@Data
@AllArgsConstructor
public class BookQueryDto implements Serializable {
    @Serial
    private static final long serialVersionUID = -2087549496224107105L;

    private Long id;

    private String bookId;

    private String bookTitle;

    private String description;

    private String publisherName;
}
