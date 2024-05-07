package Application.Data;

import java.time.LocalDate;

public class Bus extends Mezzo {


    public Bus(int capienza, LocalDate tempoServizio, Tratta tratta) {
        super(capienza = 55, tempoServizio);

    }

    public Bus(int capienza, boolean inServizio, LocalDate tempoManutenzione) {
        super(capienza = 55, inServizio, tempoManutenzione);

    }



    public Bus() {}

    @Override
    public String toString() {
        return "Bus{}";
    }
}
