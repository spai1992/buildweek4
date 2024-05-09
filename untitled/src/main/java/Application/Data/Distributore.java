package Application.Data;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "distributori")
@DiscriminatorValue("distributore")
public class Distributore extends PuntoVendita{
    private boolean isActive;

    public Distributore(boolean isActive){
        this.isActive = isActive;
    }

    public Distributore(){};

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Distributore{" +
                "isActive=" + isActive +
                '}';
    }
}
