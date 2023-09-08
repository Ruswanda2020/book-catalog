package com.programmerbeginner.catalog.web;

import com.programmerbeginner.catalog.dto.LoginRequestDto;
import com.programmerbeginner.catalog.dto.UserResponseDetailDto;
import com.programmerbeginner.catalog.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@AllArgsConstructor
public class UserResource {

    private UserService userService;

    @GetMapping("/v1/login")
    public ResponseEntity<UserResponseDetailDto> findByUsername(){
        return ResponseEntity.ok().body(userService.findUserDetail());
    }

    @PostMapping("/v1/login")
    public ResponseEntity<Void> addUser(@RequestBody @Valid LoginRequestDto dto){
        userService.registerUser(dto);
        return ResponseEntity.created(URI.create("/v1/login")).build();
    }

}
