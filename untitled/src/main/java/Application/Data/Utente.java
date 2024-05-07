package Application.Data;

import java.time.LocalDate;

public class Utente extends BaseEntity{
    private String nome;
    private String cognome;
    private int numeroTessera;
    private LocalDate creazioneTessera;
    private LocalDate scadenzaTessera;

    public Utente(String nome, String cognome, int numeroTessera, LocalDate creazioneTessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.numeroTessera = numeroTessera;
        this.creazioneTessera = creazioneTessera;
        scadenzaTessera = creazioneTessera.plusDays(365);
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
