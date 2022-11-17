package impacto_ambiental.models.entities;

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
    public int id;

}
