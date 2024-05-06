package Application.Data;

import java.util.ArrayList;
import java.util.List;

public class Rivenditore extends BaseEntity{
    private List<TitoloDiViaggio> titoli = new ArrayList<>();

    public Rivenditore(){}

    public List<TitoloDiViaggio> getTitoli() {
        return titoli;
    }

    public void setTitoli(List<TitoloDiViaggio> titoli) {
        this.titoli = titoli;
    }

    @Override
    public String toString() {
        return "Rivenditore{" +
                "titoli=" + titoli +
                '}';
    }
}


