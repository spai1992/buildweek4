package Application.Data;

import Application.Data.Enum.Tratte;

public class Tratta extends BaseEntity{
    private Tratte partenza;
    private Tratte arrivo;
    private int tempoPercorrenza;

    public Tratta(Tratte partenza, Tratte arrivo, int tempoPercorrenza) {
        this.partenza = partenza;
        this.arrivo = arrivo;
        this.tempoPercorrenza = tempoPercorrenza;
    }

    public int getTempoPercorrenza() {
        return tempoPercorrenza;
    }

    public void setTempoPercorrenza(int tempoPercorrenza) {
        this.tempoPercorrenza = tempoPercorrenza;
    }

    public Tratte getArrivo() {
        return arrivo;
    }

    public void setArrivo(Tratte arrivo) {
        this.arrivo = arrivo;
    }

    public Tratte getPartenza() {
        return partenza;
    }

    public void setPartenza(Tratte partenza) {
        this.partenza = partenza;
    }

    public Tratta() {}

    @Override
    public String toString() {
        return "Tratta{" +
                "partenza=" + partenza +
                ", arrivo=" + arrivo +
                ", tempoPercorrenza=" + tempoPercorrenza +
                '}';
    }
}
