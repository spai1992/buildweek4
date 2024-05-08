package Application.Data;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "punto_vendita")
public abstract class PuntoVendita extends BaseEntity{

    public PuntoVendita(){}
}
