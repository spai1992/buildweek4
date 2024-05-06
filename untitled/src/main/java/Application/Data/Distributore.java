package Application.Data;

import java.util.ArrayList;
import java.util.List;

public class Distributore extends BaseEntity{
    private boolean isActive = true;
    private List<TitoloDiViaggio> titoli = new ArrayList<>();

    public Distributore(){}

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
                ", titoli=" + titoli +
                '}';
    }
}
