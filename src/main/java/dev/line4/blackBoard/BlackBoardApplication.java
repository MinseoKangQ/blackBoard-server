package dev.line4.blackBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class BlackBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlackBoardApplication.class, args);
    }

}
