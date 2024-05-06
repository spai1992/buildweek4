package Application.Data;

import Application.Data.Enum.Validita;

import java.time.LocalDate;

public class Tessera extends BaseEntity {
    private LocalDate dataCreazione;
    private LocalDate dataScadenza;
    private Validita validita;
    private Utente utente;

    public Tessera(Validita validita, Utente utente) {
        this.dataCreazione = LocalDate.now();
        this.dataScadenza = dataCreazione.plusDays(365);
        this.validita = validita;
        this.utente = utente;
    }

    public Tessera(){}

    public LocalDate getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(LocalDate dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public Validita getValidita() {
        return validita;
    }

    public void setValidita(Validita validita) {
        this.validita = validita;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "dataCreazione=" + dataCreazione +
                ", dataScadenza=" + dataScadenza +
                ", validita=" + validita +
                ", utente=" + utente +
                '}';
    }
}
