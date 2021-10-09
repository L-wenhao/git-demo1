package com.csii.meter.console;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * start.
 */
@SpringBootApplication
@EnableAsync
@MapperScan("com.csii.meter.console.mapper")
public class MeterConsoleApplication {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(final String[] args) {
        SpringApplication.run(MeterConsoleApplication.class, args);
    }
}

