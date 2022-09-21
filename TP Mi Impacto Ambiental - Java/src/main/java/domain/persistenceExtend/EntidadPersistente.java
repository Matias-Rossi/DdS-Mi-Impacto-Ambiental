package domain.persistenceExtend;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class EntidadPersistente {
    @Getter
    @Setter
    @Id
    @GeneratedValue
    private int id;
}
