package dev.line4.blackBoard;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlackBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlackBoardApplication.class, args);
    }

}
