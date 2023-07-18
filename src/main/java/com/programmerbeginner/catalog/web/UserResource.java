package com.programmerbeginner.catalog.web;

import com.programmerbeginner.catalog.dto.UserDetailResponseDto;
import com.programmerbeginner.catalog.service.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserResource {

    private AppUserService appUserService;

    @GetMapping("v1/user")
    public ResponseEntity<UserDetailResponseDto> findUserDetail(){
        return ResponseEntity.ok().body(appUserService.findUserDetail());
    }
}
