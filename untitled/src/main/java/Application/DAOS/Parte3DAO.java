package Application.DAOS;

import Application.Data.Enum.Tratte;
import Application.Data.Mezzo;
import Application.Data.Tratta;
import Application.Data.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class    Parte3DAO {
    private EntityManagerFactory emf = createEntityManagerFactory("postgres");
    private EntityManager em = emf.createEntityManager();
    private static final Logger logger = LoggerFactory.getLogger(Parte3DAO.class);



    public Parte3DAO() {
    }

    public void creaTratta(Tratte partenza, Tratte arrivo, int tempoPercorrenza, long Idmezzo){
        Mezzo mezzo = em.find(Mezzo.class,Idmezzo);
        Tratta tratta = new Tratta(partenza, arrivo, tempoPercorrenza, mezzo);
        logger.info("Creo nuova tratta");
        EntityTransaction trans = em.getTransaction();
        trans.begin();
        em.persist(tratta);
        trans.commit();
    }

    public void contaNumeroVolteTratta(long idMezzo,Tratte partenza,Tratte arrivo){
        Mezzo mezzo = em.find(Mezzo.class,idMezzo);
        if(mezzo == null) {
            logger.error("Mezzo non trovato con ID: " + idMezzo);
        } else {
            var query = em.createNamedQuery("numeroVolteTratte", Long.class)
                    .setParameter("partenza", partenza)
                    .setParameter("arrivo", arrivo);
            Long numeroTitoli = (Long) query.getSingleResult();

            logger.info("Numero di volte: " + numeroTitoli + " in cui hai percorso la tratta con partenza da: " + partenza + " arrivo a/in: " + arrivo);
        }

    }
    public void tempoMedio(long idMezzo, Tratte partenza, Tratte arrivo){
        Mezzo mezzo = em.find(Mezzo.class,idMezzo);
        if(mezzo == null) {
            logger.error("Mezzo non trovato con ID: " + idMezzo);
        } else {
            var query = em.createNamedQuery("mediaTempoPercorrenzaTratte", Double.class)
                    .setParameter("partenza", partenza)
                    .setParameter("arrivo", arrivo);

            Double min = (Double) query.getSingleResult();

            var query2  = em.createNamedQuery("cercaTratte", Tratta.class)
                            .setParameter("partenza", partenza)
                            .setParameter("arrivo", arrivo);
            Tratta ris = query2.setMaxResults(1).getSingleResult();


            logger.info("Tempo medio percorrenza : "+ min + "paretnza da: " + partenza + " arrivo a/in: " + arrivo);
            EntityTransaction transaction = em.getTransaction();
            transaction.begin();
            em.createQuery("UPDATE Tratta u SET u.tempoMedio = :tempoMedio WHERE u.id = :trattaId")
                    .setParameter("tempoMedio", min)
                    .setParameter("trattaId", ris.getId())
                    .executeUpdate();

            transaction.commit();
            em.clear();
            Tratta ris1  = em.find(Tratta.class, ris.getId());
            logger.info("Tessera aggiornata, nuova scadenza: " + ris1.getTempoMedio());
        }

    }


}
