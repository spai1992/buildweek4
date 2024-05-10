package Application.DAOS;

import Application.Data.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class Parte2DAO {
    private EntityManagerFactory emf = createEntityManagerFactory("postgres");
    private EntityManager em = emf.createEntityManager();
    private static final Logger logger = LoggerFactory.getLogger(Parte2DAO.class);

    public Parte2DAO() {
    }

    public void creaBus(boolean inServizio, LocalDate dataInizioServizio, LocalDate data){
        Bus bus = new Bus(inServizio, dataInizioServizio, data);
        logger.info("Creo nuovo bus");
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(bus);
        trans.commit();
    }

    public void creaTram(boolean inServizio, LocalDate dataInizioServizio, LocalDate data){
        Tram tram = new Tram(inServizio, dataInizioServizio, data);
        logger.info("Creo nuovo tram");
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(tram);
        trans.commit();
    }


    public void gestisciServizio(long mezzoId){
        Mezzo mezzo = em.find(Mezzo.class, mezzoId);
        if(mezzo == null){
            logger.error("Il mezzo non esiste");
        } else if (mezzo.isInServizio()) {
            long giorni = ChronoUnit.DAYS.between(mezzo.getDataInizioServizio(), mezzo.getTempoServizio());
            logger.info("E' in servizio da " + giorni + " giorni");
        } else {
            long giorni = ChronoUnit.DAYS.between(mezzo.getDataInizioServizio(), mezzo.getTempoManutenzione());
            logger.info("E' in manutenzione da " + giorni + " giorni");
        }
    }

    public void vidimaBiglietto(long idBiglietto, long idMezzo, LocalDate dataTimbratura){
        Biglietto biglietto = em.find(Biglietto.class, idBiglietto);
        if(biglietto == null){
            logger.error("Il biglietto non esiste");
        } else if (biglietto.isTimbrato()) {
            logger.error("Il biglietto è già stato vidimato");
        } else {
            Mezzo mezzo = em.find(Mezzo.class, idMezzo);
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.createQuery("UPDATE Biglietto b SET b.timbrato = true, b.timbratura = :dataTimbratura, b.mezzo = :mezzo WHERE b.id = :idBiglietto")
                    .setParameter("idBiglietto", idBiglietto)
                    .setParameter("dataTimbratura", dataTimbratura)
                    .setParameter("mezzo", mezzo)
                    .executeUpdate();

            transaction.commit();
            em.clear();
            Biglietto biglietto1 = em.find(Biglietto.class, idBiglietto);
            logger.info("verifica: " + biglietto1.getId());
        }
    }

    public void contaTimbri(long idMezzo){
        Mezzo mezzo = em.find(Mezzo.class, idMezzo);
        if(mezzo == null){
            logger.error("Il mezzo non esiste");
        }else{
            var query = em.createNamedQuery("contaBigliettiTimbrati", Long.class)
                    .setParameter("mezzoId", mezzo);
            Long numeroBigliettiTimbrati = (Long) query.getSingleResult();

            logger.info( numeroBigliettiTimbrati +" biglietto/i timbrati sul mezzo ID: " +idMezzo);
        }
    }
    public void contaTimbriPeriodo(long idMezzo,LocalDate dataInizio, LocalDate dataFine){
        Mezzo mezzo = em.find(Mezzo.class, idMezzo);
        if(mezzo == null){
            logger.error("Il mezzo non esiste");
        }else{
            var query = em.createNamedQuery("contaBigliettiTimbratiPeriodo", Long.class)
                    .setParameter("mezzoId", mezzo)
                    .setParameter("dataInizio", dataInizio)
                    .setParameter("dataFine", dataFine);
            Long numeroTitoli = (Long) query.getSingleResult();

            logger.info("Biglietti timbrati da mezzo ID " + idMezzo + ": " + numeroTitoli);
        }

    }
}
