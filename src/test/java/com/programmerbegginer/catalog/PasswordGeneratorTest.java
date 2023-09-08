package com.programmerbegginer.catalog;

import com.programmerbeginner.catalog.BookCatalogV2Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@Slf4j
@ExtendWith(SpringExtension.class)
@ActiveProfiles("default")
@ContextConfiguration(classes = BookCatalogV2Application.class)
class PasswordGeneratorTest {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    void setPasswordEncoder(){
        log.info("password {}",passwordEncoder.encode("test12345"));
    }
}
