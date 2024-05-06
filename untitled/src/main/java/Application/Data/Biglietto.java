package Application.Data;

import Application.Data.Enum.Validita;

import java.time.LocalDate;

public class Biglietto extends TitoloDiViaggio{
    private boolean timbrato = false;

    public Biglietto(LocalDate dataScadenza, Validita validita) {
        super(dataScadenza, validita);

    }

    public Biglietto(){}

    public boolean isTimbrato() {
        return timbrato;
    }

    public void setTimbrato(boolean timbrato) {
        this.timbrato = timbrato;
    }
}
