package Application.Data;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("tram")
public class Tram extends Mezzo{
    private int capienza= 70;

    public Tram( boolean inServizio, LocalDate tempoManutenzione,  LocalDate dataInizioServizio) {
        super( inServizio, dataInizioServizio, tempoManutenzione);

    }

    public Tram() {}

    @Override
    public String toString() {
        return "Tram{}";
    }
}
