package Application.Data;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "utente")
public class Utente extends BaseEntity{
    @Id
    @GeneratedValue
    private int numeroTessera;
    private String nome;
    private String cognome;
    private LocalDate creazioneTessera;
    private LocalDate scadenzaTessera;
    @OneToOne
    @JoinColumn(name = "abbonamento_id")
    private Abbonamento abbonamento;

    public Utente(String nome, String cognome, LocalDate creazioneTessera, Abbonamento abbonamento) {
        this.nome = nome;
        this.cognome = cognome;
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
