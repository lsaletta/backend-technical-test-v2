package com.tui.proof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class MainApplication {

    /**
     * MainApplication class.
     *
     * @param args
     */
    public static void main(final String[] args) {

        SpringApplication.run(MainApplication.class, args);

    }

}
