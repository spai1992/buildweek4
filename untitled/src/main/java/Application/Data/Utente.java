package Application.Data;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "utenti")
@NamedQuery(name = "getTessera", query = "SELECT u FROM Utente u WHERE u.numeroTessera = :numero")
public class Utente extends BaseEntity{
    private int numeroTessera;
    //assegnare random
    private String nome;
    private String cognome;
    private LocalDate creazioneTessera;
    private LocalDate scadenzaTessera;
    @OneToOne(mappedBy = "utente")
    private Abbonamento abbonamento;



    public Utente(int numeroTessera, String nome, String cognome, LocalDate creazioneTessera, Abbonamento abbonamento) {
        this.nome = nome;
        this.cognome = cognome;
        this.numeroTessera = numeroTessera;
        this.creazioneTessera = creazioneTessera;
        scadenzaTessera = creazioneTessera.plusDays(365);
        this.abbonamento = abbonamento;
    }

    public Utente(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getNumeroTessera() {
        return numeroTessera;
    }

    public void setNumeroTessera(int numeroTessera) {
        this.numeroTessera = numeroTessera;
    }

    public LocalDate getCreazioneTessera() {
        return creazioneTessera;
    }

    public void setCreazioneTessera(LocalDate creazioneTessera) {
        this.creazioneTessera = creazioneTessera;
    }

    public LocalDate getScadenzaTessera() {
        return scadenzaTessera;
    }

    public void setScadenzaTessera(LocalDate scadenzaTessera) {
        this.scadenzaTessera = scadenzaTessera;
    }

    public Abbonamento getAbbonamento() {
        return abbonamento;
    }

    public void setAbbonamento(Abbonamento abbonamento) {
        this.abbonamento = abbonamento;
    }

    @Override
    public String toString() {
        return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", numeroTessera=" + numeroTessera +
                ", creazioneTessera=" + creazioneTessera +
                ", scadenzaTessera=" + scadenzaTessera +
                '}';
    }
}
