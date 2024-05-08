package Application.DAOS;

import Application.Data.Abbonamento;
import Application.Data.Biglietto;
import Application.Data.Enum.Validita;
import Application.Data.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;

import java.time.LocalDate;
import java.util.Scanner;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class Parte1DAO implements Parte1 {
    private EntityManagerFactory emf = createEntityManagerFactory("postgres");
    EntityManager em = emf.createEntityManager();
    Scanner scanner = new Scanner(System.in);
    public Parte1DAO(){
    }

    @Override
    public void createTitle() {

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
                    EntityTransaction trans = em.getTransaction();
                    createAbbonamento(validita, utente, date, trans);
            }else{
                System.out.println("Devi creare l'utente: ");
                unRegistered();
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
        try{
            var query = em.createNamedQuery("getTessera", Utente.class).setParameter("numero", numeroTessera);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // Nessun utente trovato con il numero di tessera specificato
        } catch (Exception e) {
            e.printStackTrace(); // Gestione dell'errore
            return null;
        }

    }

    public void unRegistered(){
        System.out.println("Inserisci il nome: ");
        String nome = scanner.nextLine();
        System.out.println("Inserisci il cognome:");
        String cognome = scanner.nextLine();
        System.out.println("Inserisci numero di tessera: ");
        String tessera = scanner.nextLine();
        int numero = Integer.parseInt(tessera.trim());
        System.out.println("Data creazione tessera: ");
        String dataTessera = scanner.nextLine();
        LocalDate date = LocalDate.parse(dataTessera);
        System.out.println("Ora puoi creare abbonamento: ");

        System.out.print("\nData di creazione: ");
        String dataCreazione = scanner.nextLine();

        LocalDate dateA = LocalDate.parse(dataCreazione);
        System.out.print("Validità: ");
        String validita = scanner.nextLine();
        Utente utente = new Utente(numero, nome, cognome, date);
        EntityTransaction trans = em.getTransaction();
        createAbbonamento( validita, utente, dateA, trans);
    }

    public void createAbbonamento(String validita, Utente utente, LocalDate dateA, EntityTransaction trans){
        switch (validita) {
            case "settimanale":
                Abbonamento abbonamento = new Abbonamento(dateA, Validita.SETTIMANALE, utente);
                trans.begin();
                em.persist(abbonamento);
                em.persist(utente);
                trans.commit();
                break;
            case "mensile":
                Abbonamento abbonamento1 = new Abbonamento(dateA, Validita.MENSILE, utente);
                trans.begin();
                em.persist(abbonamento1);
                em.persist(utente);
                trans.commit();
                break;
        }
    }
}
