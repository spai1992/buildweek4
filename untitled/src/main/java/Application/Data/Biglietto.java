package Application.Data;

import Application.Data.Enum.Validita;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "biglietti")
@DiscriminatorValue("biglietto")
public class Biglietto extends TitoloDiViaggio{
    private boolean timbrato = false;
    private LocalDate timbratura;
    @ManyToOne
    @JoinColumn(name = "mezzo_id")
    private Mezzo mezzo;

    public Biglietto(LocalDate dataCreazione, Validita validita, PuntoVendita puntoVendita) {
        super(dataCreazione, validita, puntoVendita);
    }

    public Biglietto(LocalDate dataCreazione, Validita validita) {
        super(dataCreazione, validita);
    }



    public LocalDate getTimbratura() {
        return timbratura;
    }

    public void setTimbratura(LocalDate timbratura) {
        this.timbratura = timbratura;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public Biglietto(){}

    public boolean isTimbrato() {
        return timbrato;
    }

    public void setTimbrato(boolean timbrato) {
        this.timbrato = timbrato;
    }
}
