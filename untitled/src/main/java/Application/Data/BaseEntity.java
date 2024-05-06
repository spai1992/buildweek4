package Application.Data;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue
    private long id;

    @Override
    public String toString() {
        return "Base{" +
                "id=" + id +
                '}';
    }

    public BaseEntity() {
    }

    public long getId() {
        return id;
    }
}
