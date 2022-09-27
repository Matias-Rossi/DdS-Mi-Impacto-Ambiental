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
    @Column
    protected int id;

}
