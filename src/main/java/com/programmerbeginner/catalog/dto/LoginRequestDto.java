package com.programmerbeginner.catalog.dto;

import lombok.Data;

@Data
public class LoginRequestDto {

    private String userName;

    private String password;
}
