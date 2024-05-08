package Application.DAOS;

import Application.Data.*;
import Application.Data.Enum.Validita;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Scanner;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class Parte1DAO implements Parte1 {
    private EntityManagerFactory emf = createEntityManagerFactory("postgres");
    private EntityManager em = emf.createEntityManager();
    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(Parte1DAO.class);

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

    public Biglietto createTicket(LocalDate dataCreazione, PuntoVendita puntoVendita){
        Biglietto ticket = new Biglietto(dataCreazione, Validita.GIORNALIERO, puntoVendita);
        logger.info("Creo il biglietto " + ticket);
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(ticket);
        trans.commit();
        return ticket;
    }

    public void createSubscription(LocalDate dataCreazione, Validita validita, PuntoVendita puntoVendita, Utente utente, LocalDate dataConfronto){
        LocalDate data = utente.getScadenzaTessera();
        var query1 = em.createNamedQuery("isUserAbbonato", Abbonamento.class).setParameter("utente", utente);
        if(data.isBefore(dataConfronto) && query1.getResultList().isEmpty()){
            Abbonamento abbonamento = new Abbonamento(dataCreazione, validita, puntoVendita, utente);
            logger.info("Creo l'abbonamento " + abbonamento);
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            em.persist(abbonamento);
            trans.commit();
        } else if (data.isBefore(dataConfronto) && !query1.getResultList().isEmpty()) {
            logger.info("Sei già abbonata bravo");
        } else if(data.isAfter(dataConfronto)){
            logger.info("La tessera è scaduta, la rinnovo per un altro anno.");
            var query2 = em.createNamedQuery("updateScadenzaTessera", Utente.class).setParameter("scadenza", dataConfronto.plusDays(365)).setParameter("numero", utente.getNumeroTessera());
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            query2.executeUpdate();
            trans.commit();
            logger.info("nuova data di scadenza: " + utente.getScadenzaTessera());
        } else{
            logger.info("Scaduto abbonamento, aggiorno il tuo abbonamento");
            LocalDate dataScadenza = null;
            switch (validita){
                case Validita.GIORNALIERO:
                    dataScadenza = dataConfronto.plusDays(1);
                    break;
                case Validita.SETTIMANALE:
                    dataScadenza = dataConfronto.plusDays(7);
                    break;
                case Validita.MENSILE:
                    dataScadenza = dataConfronto.plusDays(30);
                    break;
                case Validita.ANNUALE:
                    dataScadenza = dataConfronto.plusDays(365);
                    break;
            }
            var query3 = em.createNamedQuery("updateScadenzaAbbonamento", TitoloDiViaggio.class).setParameter("scadenza", dataScadenza).setParameter("abbonamentoId",query1.getSingleResult().getId());
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            query3.executeUpdate();
            trans.commit();
        }
    }

    public Utente createUser(int numeroTessera, String nome, String cognome, LocalDate creazioneTessera){
        Utente utente = new Utente(numeroTessera, nome, cognome, creazioneTessera);
        logger.info("Creo nuovo utente");
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(utente);
        trans.commit();
        return utente;
    }

    public Rivenditore createReseller(){
        Rivenditore rivenditore = new Rivenditore();
        logger.info("Creo il rivenditore");
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(rivenditore);
        trans.commit();
        return rivenditore;
    }

    public Distributore createDistributor(){
        Distributore distributore = new Distributore();
        logger.info("Creo il distributore");
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(distributore);
        trans.commit();
        return distributore;
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

    public Abbonamento creaAbbonamento(LocalDate dataCreazione,  Validita validita,PuntoVendita puntoVendita, Utente utente){
        Abbonamento abbonamento = new Abbonamento(dataCreazione, validita, puntoVendita,utente);
        logger.info("Creo l'abbonamento");
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(abbonamento);
        trans.commit();
        return abbonamento;
    }



    public void aggiornaScadenza(long abbonamentoId, Validita valido) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Abbonamento abbonamento = em.find(Abbonamento.class, abbonamentoId);
        if (abbonamento == null) {
            logger.error("Abbonamento non trovato con ID: " + abbonamento);
        }
            var data = abbonamento.getDataScadenza();
            try {

                if (LocalDate.now().isAfter(data)) {
                    logger.info("scaduto" + data);
                    em.createQuery("UPDATE Abbonamento a SET a.validita = :valido WHERE a.id = :idAbbonamento")
                            .setParameter("valido", valido)
                            .setParameter("idAbbonamento", abbonamento.getId())
                            .executeUpdate();

                    switch (valido){

                        case Validita.SETTIMANALE:
                            data = LocalDate.now().plusDays(7);
                            break;
                        case Validita.MENSILE:
                            data = LocalDate.now().plusDays(30);
                            break;

                    }

                    em.createQuery("UPDATE Abbonamento a SET a.dataScadenza = :scadenza WHERE a.id = :idAbbonamento")
                            .setParameter("scadenza",data)
                            .setParameter("idAbbonamento", abbonamento.getId())
                            .executeUpdate();

                    transaction.commit();
                    em.clear();
                    Abbonamento abbonamento1 = em.find(Abbonamento.class, abbonamentoId);
                    logger.info("nuova scadenza: " + abbonamento1.getDataScadenza());
                } else {
                    logger.info("ancora valido");
                }
            } catch (NoResultException e) {
                // Gestisci l'eccezione
            } catch (Exception e) {
                logger.error("Errore durante l'aggiornamento della scadenza dell'abbonamento.", e);
            }
        }
    }


