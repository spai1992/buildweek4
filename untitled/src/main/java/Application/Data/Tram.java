package Application.Data;

import java.time.LocalDate;

public class Tram extends Mezzo{


    public Tram(int capienza, LocalDate tempoServizio) {
        super(capienza = 70, tempoServizio);

    }

    public Tram(int capienza, boolean inServizio, LocalDate tempoManutenzione) {
        super(capienza = 70, inServizio, tempoManutenzione);

    }

    public Tram() {}

    @Override
    public String toString() {
        return "Tram{}";
    }
}
