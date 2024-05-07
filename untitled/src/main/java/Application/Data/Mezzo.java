package Application.Data;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public abstract class Mezzo extends BaseEntity {

    private int capienza;
    private boolean inServizio;
    private LocalDate tempoManutenzione;
    private LocalDate tempoServizio;
    //colegare a biglietti OneToMany
    private List<Biglietto> biglietti;
    private List<Tratta> tratte;

    public Mezzo(int capienza, LocalDate tempoServizio) {
        this.capienza = capienza;
        this.inServizio = true;
        this.tempoServizio = tempoServizio;
    }

    public Mezzo(int capienza, boolean inServizio, LocalDate tempoManutenzione) {
        this.capienza = capienza;
        this.inServizio = inServizio;
        this.tempoManutenzione = tempoManutenzione;
    }


    public Mezzo() {}

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

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
                "capienza=" + capienza +
                ", inServizio=" + inServizio +
                ", tempoManutenzione=" + tempoManutenzione +
                ", tempoServizio=" + tempoServizio +
                '}';
    }
}
