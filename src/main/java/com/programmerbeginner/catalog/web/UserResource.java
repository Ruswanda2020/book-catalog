package com.programmerbeginner.catalog.web;

import com.programmerbeginner.catalog.dto.UserResponseDetailDto;
import com.programmerbeginner.catalog.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserResource {

    private UserService userService;

    @GetMapping("/v1/user")
    public ResponseEntity<UserResponseDetailDto> findByUsername(){
        return ResponseEntity.ok().body(userService.findUserDetail());
    }

}
