package Application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World");

    EntityManagerFactory emf = createEntityManagerFactory("postgres");

    EntityManager em = emf.createEntityManager();


    }
}
