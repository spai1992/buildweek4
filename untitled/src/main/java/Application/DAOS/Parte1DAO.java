package Application.DAOS;

import Application.Data.Biglietto;
import Application.Data.Enum.Validita;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;

import java.time.LocalDate;
import java.util.Scanner;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class Parte1DAO implements Parte1 {
    private EntityManagerFactory emf = createEntityManagerFactory("postgres");

    public Parte1DAO(){
    }

    @Override
    public void createTitle() {
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Vuoi acquistare un biglietto o un abbonamento? ");
        String titolo = scanner.nextLine();
        scanner.reset();
        if(titolo.equals("biglietto")){
            System.out.print("\nData di creazione: ");
            String dataString = scanner.nextLine();
            LocalDate date = LocalDate.parse(dataString);
            Biglietto biglietto = new Biglietto(date, Validita.GIORNALIERO);
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            em.persist(biglietto);
            trans.commit();
        }

    }

    @Override
    public void getSubByID() {

    }

    @Override
    public void getTitleByDate() {

    }

    @Override
    public void getTitleBySellpoint() {

    }
}
