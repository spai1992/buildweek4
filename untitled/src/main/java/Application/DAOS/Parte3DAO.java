package Application.DAOS;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class    Parte3DAO {
    private EntityManagerFactory emf = createEntityManagerFactory("postgres");
    private EntityManager em = emf.createEntityManager();
    private static final Logger logger = LoggerFactory.getLogger(Parte3DAO.class);



    public Parte3DAO() {
    }




}
