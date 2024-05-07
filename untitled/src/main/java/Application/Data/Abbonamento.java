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

    public Abbonamento(LocalDate dataScadenza, Validita validita, PuntoVendita puntoVendita) {
        super(dataScadenza, validita, puntoVendita);

    }

    public Abbonamento() {}
}
