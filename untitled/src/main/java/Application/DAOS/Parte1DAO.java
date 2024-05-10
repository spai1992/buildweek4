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


import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class Parte1DAO {
    private EntityManagerFactory emf = createEntityManagerFactory("postgres");
    private EntityManager em = emf.createEntityManager();
    private static final Logger logger = LoggerFactory.getLogger(Parte1DAO.class);

    public Parte1DAO() {
    }


    public void createTicket(LocalDate dataCreazione, long idPuntoVendita) {
        if(tipoDiPuntoVendita(idPuntoVendita)){
            PuntoVendita puntoVendita = em.find(PuntoVendita.class, idPuntoVendita);
            Biglietto ticket = new Biglietto(dataCreazione, Validita.GIORNALIERO, puntoVendita);
            logger.info("Creo il biglietto " + ticket);
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            em.persist(ticket);
            trans.commit();

        }
    }

        public void creaAbbonamento(LocalDate dataCreazione, Validita validita,long idPuntoVendita, long idUtente) {
        if(tipoDiPuntoVendita(idPuntoVendita)){
            Utente utente = em.find(Utente.class, idUtente);
            PuntoVendita puntoVendita = em.find(PuntoVendita.class, idPuntoVendita);
            Abbonamento abbonamento = new Abbonamento(dataCreazione, validita, puntoVendita, utente);
            logger.info("Creo l'abbonamento");
            EntityTransaction trans = em.getTransaction();
            trans.begin();
            em.persist(abbonamento);
            trans.commit();
        }

    }

    public void aggiornaScadenzaAbbonamento(long abbonamentoId, Validita valido) {
        Abbonamento abbonamento = em.find(Abbonamento.class, abbonamentoId);
        if (abbonamento == null) {
            logger.error("Abbonamento non trovato con ID: " + abbonamento);
        }
        var data = abbonamento.getDataScadenza();
        try {
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            if (LocalDate.now().isAfter(data)) {
                logger.info("scaduto" + data);
                em.createQuery("UPDATE Abbonamento a SET a.validita = :valido WHERE a.id = :idAbbonamento")
                        .setParameter("valido", valido)
                        .setParameter("idAbbonamento", abbonamento.getId())
                        .executeUpdate();

                switch (valido) {

                    case Validita.SETTIMANALE:
                        data = LocalDate.now().plusDays(7);
                        break;
                    case Validita.MENSILE:
                        data = LocalDate.now().plusDays(30);
                        break;

                }

                em.createQuery("UPDATE Abbonamento a SET a.dataScadenza = :scadenza WHERE a.id = :idAbbonamento")
                        .setParameter("scadenza", data)
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

    public void createUser(int numeroTessera, String nome, String cognome, LocalDate creazioneTessera) {
        Utente utente = new Utente(numeroTessera, nome, cognome, creazioneTessera);
        logger.info("Creo nuovo utente");
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(utente);
        trans.commit();
    }

    public Rivenditore creaRivenditore() {
        Rivenditore rivenditore = new Rivenditore();
        logger.info("Creo il rivenditore");
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(rivenditore);
        trans.commit();
        return rivenditore;
    }

    public Distributore creaDistributore(boolean attivo) {
        Distributore distributore = new Distributore(attivo);
        logger.info("Creo il distributore, stato attività: " + attivo);
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(distributore);
        trans.commit();
        return distributore;
    }

    public void isTesseraScaduta(long utenteId){
        Utente utente  = em.find(Utente.class, utenteId);
        if (utente == null) {
            logger.error("Tessera non trovata con ID: " + utenteId);
        }
        var scadenza = utente.getScadenzaTessera();
        try{
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            if(LocalDate.now().isAfter(scadenza)){
                logger.info("Tessera scaduta " + scadenza);
                em.createQuery("UPDATE Utente u SET u.scadenzaTessera = :nuovaScadenza WHERE u.id = :utenteId")
                        .setParameter("nuovaScadenza", LocalDate.now().plusDays(365))
                        .setParameter("utenteId", utenteId)
                        .executeUpdate();

                transaction.commit();
                em.clear();
                Utente utente1  = em.find(Utente.class, utenteId);
                logger.info("Tessera aggiornata, nuova scadenza: " + utente1.getScadenzaTessera());
            } else {
                logger.info("Tessera valida con scadenza " + scadenza);
            }
        } catch (NoResultException e) {
            logger.error("errore: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Errore durante l'aggiornamento della scadenza della tessera", e);
        }
    }

    public void contaTitoliPeriodo(long idPuntoVendita, LocalDate dataInizio, LocalDate dataFine){
        PuntoVendita puntoVendita = em.find(PuntoVendita.class, idPuntoVendita);
        if (puntoVendita == null) {
            logger.error("Punto vendita non trovato con ID: " + idPuntoVendita);
        } else {
            var query = em.createNamedQuery("getTitoliByDate", Long.class)
                    .setParameter("dataInizio", dataInizio)
                    .setParameter("dataFine", dataFine);
            Long numeroTitoli1 = (Long) query.getSingleResult();

            logger.info("Titoli generati dal punto vendita con ID " + idPuntoVendita + ": " + numeroTitoli1);
        }
    }


    public boolean tipoDiPuntoVendita(long IdPuntoVendita){
        PuntoVendita puntoVendita = em.find(PuntoVendita.class, IdPuntoVendita);
        if(puntoVendita != null){
            if(puntoVendita instanceof Distributore && ((Distributore) puntoVendita).isActive() == true ){
                logger.info("Punto vendita Distributore, stato: attivo");
                return true;
            }else if (puntoVendita instanceof Rivenditore){
                logger.info("Punto vendita Rivenditore");
                return true;
            }else{
                logger.error("Impossibile creare titolo, distributore in manutenzione");
                return false;
            }
        }else{
            logger.error("Punto vendita non esistente");
            return false;
        }

    }

    public void validaAbbonamento(int numeroTessera){
        var utente = em.createNamedQuery("getTessera", Utente.class)
                .setParameter("numero", numeroTessera)
                .getSingleResult();
        var abbonamento = em.createNamedQuery("isUserAbbonato", Abbonamento.class)
                .setParameter("utente", utente)
                .getSingleResult();
        if(LocalDate.now().isAfter(abbonamento.getDataScadenza())){
            logger.info("Abbonamento scaduto il " + abbonamento.getDataScadenza());
        }else{
            logger.info("Abbonamento valido fino al " + abbonamento.getDataScadenza());
        }
    }
}






