package Application.Data;

import Application.Data.Enum.Tratte;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="tratte")
public class Tratta extends BaseEntity{
    private Tratte partenza;
    private Tratte arrivo;
    private int tempoPercorrenza;

    @ManyToOne
    @JoinColumn(name= "mezzo_id")
    private Mezzo mezzo;

    private int tempoMedio;

    public Tratta(Tratte partenza, Tratte arrivo, int tempoPercorrenza, Mezzo mezzo, int tempoMedio) {
        this.partenza = partenza;
        this.arrivo = arrivo;
        this.tempoPercorrenza = tempoPercorrenza;
        this.mezzo = mezzo;
        this.tempoMedio = tempoMedio;
    }

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
