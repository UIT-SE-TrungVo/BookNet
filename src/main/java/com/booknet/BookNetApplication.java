package com.booknet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookNetApplication {
    public static void main(String[] args) {
        init();
        SpringApplication.run(BookNetApplication.class, args);
    }

    private static void init() {
        final String ENCRYPTOR = "memoicunglen";
        System.setProperty("jasypt.encryptor.password", ENCRYPTOR);
    }
}
