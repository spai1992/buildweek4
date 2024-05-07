package Application.Data;

import Application.Data.Enum.Validita;

import java.time.LocalDate;

public class Biglietto extends TitoloDiViaggio{
    private boolean timbrato = false;
    private LocalDate timbratura;
    //Many to one
    private Mezzo mezzo;

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
