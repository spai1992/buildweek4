package Application.Data;

import Application.Data.Enum.Validita;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "abbonamenti")
@DiscriminatorValue("abbonamento")
@NamedQuery(name = "isUserAbbonato", query = "SELECT a FROM Abbonamento a WHERE a.utente= :utente")

public class Abbonamento extends TitoloDiViaggio{
    @OneToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    public Abbonamento(LocalDate dataCreazione,  Validita validita,PuntoVendita puntoVendita, Utente utente) {
        super(dataCreazione, validita, puntoVendita);
        this.utente = utente;
    }

    public Abbonamento() {}

}
