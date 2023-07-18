package com.subrutin.catalog.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@SpringBootTest(classes = PasswordEncoder.class)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("default")
public class PasswordEncryptedTest {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void encryptedPassword() {
        log.info("password: {}", passwordEncoder.encode("test123"));
    }
}
