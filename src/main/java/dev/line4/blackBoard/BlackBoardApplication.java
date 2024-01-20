package dev.line4.blackBoard;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@Import(AppConfig.class)
public class BlackBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlackBoardApplication.class, args);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("blackboard");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 트랜잭션 시작

        try {

            tx.commit();
            System.out.println("try!");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("catch!");
            tx.rollback();
        } finally {
            System.out.println("finally!");
            em.close();
        }
        emf.close();

    }

}
