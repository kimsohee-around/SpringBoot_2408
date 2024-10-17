package org.iclass.board.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class PasswordEncoderTest {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void encode() {
        String encodedPassword =
                passwordEncoder.encode("12345");
        log.info("encodedPassword: {}", encodedPassword);
        assertEquals(60,encodedPassword.length());
    }

}