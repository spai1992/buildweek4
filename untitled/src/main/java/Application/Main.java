package Application;

import Application.DAOS.Parte1DAO;
import Application.DAOS.Parte2DAO;
import Application.DAOS.Parte3DAO;
import Application.Data.*;
import Application.Data.Enum.Tratte;
import Application.Data.Enum.Validita;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.Scanner;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class Main {

    public static void main(String[] args) {

        Parte1DAO parte1 = new Parte1DAO();
        Parte2DAO parte2 = new Parte2DAO();
        Parte3DAO parte3 = new Parte3DAO();


//       ------------------- TEST APPLICAZIONE -------------------


//        - Creo diversi utenti da registrare nel sistema, sono necessari per creare degli abbonamenti:

//        - Scelgo il numero tessera, nome, cognome, e la data di creazione della tessera:

//          parte1.createUser(1, "mario", "rossi", LocalDate.now());

//        - Creo utenti con diverse date di creazione tessera per eseguire un test sulla validità di quest'ultima:

//          parte1.createUser(2, "paolo", "bitta", LocalDate.of(2022,1,5));
//          parte1.createUser(3, "luca", "nervi", LocalDate.of(2024,3,15));
//          parte1.createUser(4, "nello", "rizzo", LocalDate.of(2019,5,10));
//          parte1.createUser(5, "salvatore", "mura", LocalDate.now());

//        - Creo diversi punti vendita per poter emettere i titoli di viaggio:

//          parte1.creaRivenditore();

//        - Ci saranno due distributori, uno attivo e uno fuori servizio:

//          parte1.creaDistributore(true);
//          parte1.creaDistributore(false);

//        - Emettiamo diversi biglietti e abbonamenti da diversi distributori:

//        - Per gli abbonamenti scelgo la data di creazione dell'abbonamento,
//          la validità settimanale o mensile, id punto vendita da cui acquisto
//          e id utente a cui associare l'abbonamento:

//          parte1.creaAbbonamento(LocalDate.now(), Validita.MENSILE,,);
//          parte1.creaAbbonamento(LocalDate.of(2023,04,05),Validita.SETTIMANALE,,);
//          parte1.creaAbbonamento(LocalDate.of(2019,02,15),Validita.MENSILE,,);
//          parte1.creaAbbonamento(LocalDate.of(2021,10,05),Validita.MENSILE,,);
//          parte1.creaAbbonamento(LocalDate.now(),Validita.SETTIMANALE,,);

//        - Per i biglietti scelgo data di creazione e id punto vendita da dove acquisto:

//          parte1.createTicket(LocalDate.now(),);
//          parte1.createTicket(LocalDate.of(2022,1,1),);

//        - Verifico le validità di varie tessere utente:
//        - Per la verifica devo passare l'id utente:

//          parte1.isTesseraScaduta();

//        - Se una tessera risulta scaduta, viene rinnovata in automatico
//          con scadenza in un anno partendo dalla data attuale:

//        - Il sistema permette di verificare quanti titoli sono stati emessi
//          per ogni punto vendita, in totale o in un dato periodo
//          per ottenere un conteggio dobbiamo passare id del punto vendita ed
//          eventualmente una data di inizio e di fine ricerca:

//          parte1.contaTitoli();
//          parte1.contaTitoliPeriodo();

//        - Possiamo anche verificare la validità di un abbonamento fornendo il numero tessera utente:

//          parte1.isAbbonamentoValido(1)
//          parte1.isAbbonamentoValido(2)

//        - Se l'abbonamento risulta scaduto, si ha la possibilità di rinnovarlo fornendo il suo id e
//          una nuova validità settimanale o mensile (la scadenza viene calcolata sulla base della data odierna)

//          parte1.aggiornaScadenzaAbbonamento(,Validita.SETTIMANALE);


//        - Creiamo un mezzo di tipo Bus:

//          parte2.creaBus(true, LocalDate.of(2023,1,4), LocalDate.now());
//          parte2.creaBus(false, LocalDate.of(2024,1,4), LocalDate.now());

//        - Lanciamo un metodo che decide se il mezzo selezionato è in servizio o in manutenzione:

//           parte2.gestisciServizio(202);

//        - Creiamo un mezzo di tipo Tram:

//          parte2.creaTram(true,LocalDate.of(2023,3,2),LocalDate.now());

//        - Lanciamo un metodo che timbra il biglietto:

//          parte2.vidimaBiglietto(2302,202,LocalDate.of(2024,5,1));

//        - Lanciamo un metodo che conta i titoli validati dato l'Id di un rivenditore o distributore:

//        - Lanciamo un metodo che dato l'ID di un rivenditore o distributore, conta i titoli timbrati:

//          parte2.contaTimbri(202);

//         - Lanciamo un metodo che dato l'ID di un rivenditore o distributore, conta i titoli timbrati
//           in un dato periodo di tempo

//        parte2.contaTimbriPeriodo(202, LocalDate.of(2024,5,1),LocalDate.now());
//      - Crea una tratta usando una enum per le destinazioni di partenza e arrivo, utilizzo un intero per la durata del viaggio e infine idmezzo che percorre la tratta

//        parte3.creaTratta(Tratte.MILANO,Tratte.AFRICA,50,102);
//        parte3.creaTratta(Tratte.ROMA,Tratte.FIGALAND,30,102);

//      -conto numero volte che un mezzo svolge la stessa tratta

//        parte3.contaNumeroVolteTratta(102, Tratte.MILANO,Tratte.AFRICA);
//      -calcolo tempo medio in base ai diversi tempi di percorrenza per la stessa tratta
      //parte3.tempoMedio(102,Tratte.MILANO,Tratte.AFRICA);


    }

}
