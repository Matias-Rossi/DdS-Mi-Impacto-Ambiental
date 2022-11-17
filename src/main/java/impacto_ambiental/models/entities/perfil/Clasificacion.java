package impacto_ambiental.models.entities.perfil;

import impacto_ambiental.models.entities.EntidadPersistente;
import impacto_ambiental.db.EntityManagerHelper;
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
    }


    public Clasificacion() {
    }
}
