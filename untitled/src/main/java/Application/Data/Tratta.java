package Application.Data;

import Application.Data.Enum.Tratte;
import jakarta.persistence.*;

@Entity
@Table(name="tratte")
@NamedQuery(name="cercaTratte", query = "SELECT t FROM Tratta t WHERE t.partenza= :partenza AND t.arrivo= :arrivo")
@NamedQuery(name="numeroVolteTratte", query = "SELECT count(t) FROM Tratta t WHERE t.partenza= :partenza AND t.arrivo= :arrivo")
@NamedQuery(name = "mediaTempoPercorrenzaTratte", query = "SELECT AVG(t.tempoPercorrenza) FROM Tratta t WHERE t.partenza = :partenza AND t.arrivo = :arrivo")

public class Tratta extends BaseEntity{
    private Tratte partenza;
    private Tratte arrivo;
    private int tempoPercorrenza;

    @ManyToOne
    @JoinColumn(name= "mezzo_id")
    private Mezzo mezzo;

    private Double tempoMedio = 0.0;

    public Tratta(Tratte partenza, Tratte arrivo, int tempoPercorrenza, Mezzo mezzo) {
        this.partenza = partenza;
        this.arrivo = arrivo;
        this.tempoPercorrenza = tempoPercorrenza;
        this.mezzo = mezzo;

    }

    public Tratta(Tratte partenza, Tratte arrivo, int tempoPercorrenza) {
        this.partenza = partenza;
        this.arrivo = arrivo;
        this.tempoPercorrenza = tempoPercorrenza;
    }

    public Double getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(Double tempoMedio) {
        this.tempoMedio = tempoMedio;
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
