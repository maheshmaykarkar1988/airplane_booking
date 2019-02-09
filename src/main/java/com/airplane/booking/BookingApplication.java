package com.airplane.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main actor responsible for end to end running of the application
 * Created by Mahesh Maykarkar on 09/02/19.
 */
//(exclude = {SecurityAutoConfiguration.class})
@SpringBootApplication
@EnableSwagger2
public class BookingApplication {
    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
    }
}

