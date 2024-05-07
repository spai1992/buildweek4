package Application.Data;

import Application.Data.Enum.Validita;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "abbonamenti")
@DiscriminatorValue("abbonamento")
public class Abbonamento extends TitoloDiViaggio{
    @OneToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    public Abbonamento(LocalDate dataCreazione,  Validita validita, PuntoVendita puntoVendita) {
        super(dataCreazione, validita, puntoVendita);

    }
    public Abbonamento(LocalDate dataCreazione, Validita validita) {
        super(dataCreazione, validita);

    }

    public Abbonamento() {}
}
