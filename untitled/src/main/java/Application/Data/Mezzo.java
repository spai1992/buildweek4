package Application.Data;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_di_mezzo", discriminatorType = DiscriminatorType.STRING)
public abstract class Mezzo extends BaseEntity {

    private boolean inServizio;
    private LocalDate dataInizioServizio;
    private LocalDate tempoManutenzione;
    private LocalDate tempoServizio;
    //@OneToMany(mappedBy = "mezzo")
    //private List<Biglietto> biglietti = new ArrayList<>();
    //private List<Tratta> tratte = new ArrayList<>();


    public Mezzo(boolean inServizio, LocalDate dataInizioServizio, LocalDate tempoServizioManutenzione) {
        this.inServizio = inServizio;
        if(inServizio) this.tempoServizio = tempoServizioManutenzione;
        else this.tempoManutenzione = tempoServizioManutenzione;
        this.dataInizioServizio = dataInizioServizio;
    }


    public LocalDate getDataInizioServizio() {
        return dataInizioServizio;
    }

    public void setDataInizioServizio(LocalDate dataInizioServizio) {
        this.dataInizioServizio = dataInizioServizio;
    }

    public Mezzo() {}


    public boolean isInServizio() {
        return inServizio;
    }

    public void setInServizio(boolean inServizio) {
        this.inServizio = inServizio;
    }

    public LocalDate getTempoManutenzione() {
        return tempoManutenzione;
    }

    public void setTempoManutenzione(LocalDate tempoManutenzione) {
        this.tempoManutenzione = tempoManutenzione;
    }

    public LocalDate getTempoServizio() {
        return tempoServizio;
    }

    public void setTempoServizio(LocalDate tempoServizio) {
        this.tempoServizio = tempoServizio;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                ", inServizio=" + inServizio +
                ", dataInizioServizio=" + dataInizioServizio +
                ", tempoManutenzione=" + tempoManutenzione +
                ", tempoServizio=" + tempoServizio +
                '}';
    }

}
