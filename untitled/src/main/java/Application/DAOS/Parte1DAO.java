package Application.DAOS;

import Application.Data.Abbonamento;
import Application.Data.Biglietto;
import Application.Data.Enum.Validita;
import Application.Data.Utente;
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

        if(titolo.equals("biglietto")){
            System.out.print("\nData di creazione: ");
            String dataString = scanner.nextLine();

            LocalDate date = LocalDate.parse(dataString);
            Biglietto biglietto = new Biglietto(date, Validita.GIORNALIERO);
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            em.persist(biglietto);
            trans.commit();
        }else{
            System.out.print("\nInserisci numero tessera: ");

            String line = scanner.nextLine();

                int numero = Integer.parseInt(line.trim());


            


            if (isUserRegistered(numero, em) != null){
                    Utente utente = isUserRegistered(numero, em);
                    System.out.print("\nData di creazione: ");
                    String dataStrin = scanner.nextLine();

                    LocalDate date = LocalDate.parse(dataStrin);
                    System.out.print("Validità: ");
                    String validita = scanner.nextLine();
                    Abbonamento abbonamento;
                    EntityTransaction trans = em.getTransaction();
                    switch (validita){
                        case "settimanale":
                            abbonamento = new Abbonamento(date,Validita.SETTIMANALE);
                            utente.setAbbonamento(abbonamento);
                            trans.begin();
                            em.persist(abbonamento);
                            em.persist(utente);
                            trans.commit();
                            break;
                        case "mensile":
                            abbonamento = new Abbonamento(date, Validita.MENSILE);
                            utente.setAbbonamento(abbonamento);
                            trans.begin();
                            em.persist(abbonamento);
                            em.persist(utente);
                            trans.commit();
                            break;
                    }
                }
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

    public Utente isUserRegistered(int numeroTessera, EntityManager em){
        var query = em.createNamedQuery("getTessera", Utente.class).setParameter("numero", numeroTessera);
        return query.getSingleResult();
    }
}
