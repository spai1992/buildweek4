package Application.Data;

import Application.Data.Enum.Validita;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "titolo", discriminatorType = DiscriminatorType.STRING)
@NamedQuery(name = "updateScadenzaAbbonamento", query = "UPDATE TitoloDiViaggio t SET t.dataScadenza = :scadenza WHERE t.id = :abbonamentoId")
@NamedQuery(name = "getTitoliByDate", query = "SELECT count(t) FROM TitoloDiViaggio t WHERE t.puntoVendita = :puntoVendita AND t.dataCreazione BETWEEN :dataInizio and :dataFine")
@NamedQuery(name = "getTitoli", query = "SELECT count(t) FROM TitoloDiViaggio t WHERE t.puntoVendita = :puntoVendita")
public abstract class TitoloDiViaggio extends BaseEntity {
    private LocalDate dataCreazione;
    private LocalDate dataScadenza;
    private Validita validita;
    @ManyToOne
    @JoinColumn(name = "punto_vendita_id")
    private PuntoVendita puntoVendita;


    public TitoloDiViaggio(LocalDate dataCreazione, Validita validita, PuntoVendita puntoVendita) {
        this.dataCreazione = dataCreazione;
        this.puntoVendita = puntoVendita;
        this.validita = validita;
        switch (validita){
            case Validita.GIORNALIERO:
                this.dataScadenza = dataCreazione.plusDays(1);
                break;
            case Validita.SETTIMANALE:
                this.dataScadenza = dataCreazione.plusDays(7);
                break;
            case Validita.MENSILE:
                this.dataScadenza = dataCreazione.plusDays(30);
                break;
            case Validita.ANNUALE:
                this.dataScadenza = dataCreazione.plusDays(365);
                break;
        }
    }

    public TitoloDiViaggio(LocalDate dataCreazione, Validita validita) {
        this.dataCreazione = dataCreazione;
        this.validita = validita;
        switch (validita){
            case Validita.GIORNALIERO:
                this.dataScadenza = dataCreazione.plusDays(1);
                break;
            case Validita.SETTIMANALE:
                this.dataScadenza = dataCreazione.plusDays(7);
                break;
            case Validita.MENSILE:
                this.dataScadenza = dataCreazione.plusDays(30);
                break;
            case Validita.ANNUALE:
                this.dataScadenza = dataCreazione.plusDays(365);
                break;
        }
    }

    public TitoloDiViaggio() {

    }

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

    @Override
    public String toString() {
        return "TitoloDiViaggio{" +
                "dataCreazione=" + dataCreazione +
                ", dataScadenza=" + dataScadenza +
                ", validita=" + validita +
                '}';
    }
}
