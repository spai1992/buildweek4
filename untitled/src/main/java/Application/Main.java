package Application;

import Application.DAOS.Parte1DAO;
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
        Rivenditore rivenditore = parte1.createReseller();
//        Distributore distributore = parte1.createDistributor();
        Utente utente1 = parte1.createUser(1, "paolo", "rossi", LocalDate.now());
//        Abbonamento abbonamento = parte1.creaAbbonamento(LocalDate.of(2023,02,05),Validita.MENSILE,rivenditore,utente1);
        //Abbonamento abbonamento1 = parte1.creaAbbonamento(LocalDate.of(2023,04,05),Validita.MENSILE,rivenditore,utente1);
        parte1.aggiornaScadenza(1602,Validita.SETTIMANALE);
//        parte1.createTicket(LocalDate.now(), rivenditore);
    }

}
