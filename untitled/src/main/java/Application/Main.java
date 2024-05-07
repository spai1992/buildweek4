package Application;

import Application.DAOS.Parte1DAO;
import Application.Data.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDate;
import java.util.Scanner;

import static jakarta.persistence.Persistence.createEntityManagerFactory;

public class Main {
    static Parte1DAO parte1 = new Parte1DAO();


    public static void main(String[] args) {
        EntityManagerFactory emf = createEntityManagerFactory("postgres");
        menu();
    }

    static Scanner scanner = new Scanner(System.in);
    public static void menu(){
        System.out.println("\n---MENU---");
        System.out.println("1 - Acquista titolo");
        System.out.println("0 - Esci dall'applicazione\n");
        System.out.print("Scegli una funzione: ");
        
            int function = scanner.nextInt();
            switch (function){
                case 1:
                    parte1.createTitle();

                    menu();
                    break;
                case 0:
                    break;
            }
            scanner.nextLine();
    }

}
