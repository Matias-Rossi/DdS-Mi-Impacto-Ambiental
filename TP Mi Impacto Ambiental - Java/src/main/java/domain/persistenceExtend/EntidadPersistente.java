package domain.persistenceExtend;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@MappedSuperclass
public abstract class EntidadPersistente {
    @Getter
    @Setter
    @Id
    @GeneratedValue
    protected int id;

}
