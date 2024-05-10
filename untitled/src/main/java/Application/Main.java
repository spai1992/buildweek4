package Application;

import Application.DAOS.Parte1DAO;
import Application.DAOS.Parte2DAO;
import Application.Data.*;
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
//            parte1.createUser(2, "paolo", "rossi", LocalDate.now());
//         parte1.creaAbbonamento(LocalDate.now(),Validita.MENSILE,1552, 1802);
//        Abbonamento abbonamento1 = parte1.creaAbbonamento(LocalDate.of(2023,04,05),Validita.MENSILE,rivenditore,utente1);

//        parte1.aggiornaScadenzaAbbonamento(1602,Validita.SETTIMANALE);
//        parte1.createTicket(LocalDate.now(), rivenditore);
//        parte1.isTesseraScaduta(352);
//        parte1.createTicket(LocalDate.now(), 2);
//        parte1.creaAbbonamento(LocalDate.now(), Validita.MENSILE, 1552, 352);
        //parte1.creaAbbonamento(LocalDate.of(2023, 05,01), Validita.MENSILE, 1552, 502);
//        parte1.creaAbbonamento(LocalDate.of(2023, 03,01), Validita.MENSILE, 1552, 402);
//        parte1.createTicket(LocalDate.now(), 1552);
//        parte1.createTicket(LocalDate.now(), 1552);
//        parte1.contaTitoliPeriodo(1552, LocalDate.of(2024, 05,01), LocalDate.now());
//        parte1.validaAbbonamento(1);

        //parte2.creaBus(true, LocalDate.of(2023,1,4), LocalDate.now());
        //parte2.creaBus(false, LocalDate.of(2024,1,4), LocalDate.now());
        //parte2.gestisciServizio(202);
        //parte2.creaTram(true,LocalDate.of(2023,3,2),LocalDate.now());
        //parte2.vidimaBiglietto(2302,202,LocalDate.of(2024,5,1));
        //parte2.contaTimbri(202);
        parte2.contaTimbriPeriodo(202, LocalDate.of(2024,5,1),LocalDate.now());

    }

}
