package proservices.models.entities.perfil;

import proservices.models.entities.EntidadPersistente;
import proservices.db.EntityManagerHelper;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "clasificaciones")
public class Clasificacion extends EntidadPersistente {
    @Getter
    @Column(name = "nombre",unique = true)
    private String nombre;
    public Clasificacion(String nombre){

        this.nombre = nombre;
        EntityManagerHelper.persist(this);
    }


    public Clasificacion() {
    }
}
