package Application.Data;

import Application.Data.Enum.Validita;

import java.time.LocalDate;

public class Abbonamento extends TitoloDiViaggio{

    public Abbonamento(LocalDate dataScadenza, Validita validita) {
        super(dataScadenza, validita);

    }

    public Abbonamento() {}
}
