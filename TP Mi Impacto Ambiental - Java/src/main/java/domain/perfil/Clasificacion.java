package domain.perfil;

import domain.persistenceExtend.EntidadPersistente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clasificaciones")
public class Clasificacion extends EntidadPersistente {
    @Column(name = "nombre")
    private String nombre;
    public Clasificacion(String nombre){
        this.nombre = nombre;
    }


    public Clasificacion() {
    }
}
