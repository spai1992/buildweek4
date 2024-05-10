package Application.Data;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.List;

@Entity
@DiscriminatorValue("bus")
public class Bus extends Mezzo {
    private  int capienza = 55;

    public Bus(boolean inServizio, LocalDate dataInizioServizio, LocalDate tempoManutenzione, LocalDate tempoServizio, List<Tratta> tratte, int capienza) {
        super(inServizio, dataInizioServizio, tempoManutenzione, tempoServizio, tratte);
    }

    public Bus(boolean inServizio, LocalDate dataInizioServizio, LocalDate tempoManutenzione) {
        super(inServizio, dataInizioServizio, tempoManutenzione);


    }

    public Bus() {}

    @Override
    public String toString() {
        return "Bus{}";
    }
}
