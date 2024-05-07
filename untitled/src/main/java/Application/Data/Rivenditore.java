package Application.Data;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rivenditori")
@DiscriminatorValue("rivenditore")
public class Rivenditore extends PuntoVendita{

    public Rivenditore(){}

    @Override
    public String toString() {
        return "Rivenditore{" +
                '}';
    }
}


