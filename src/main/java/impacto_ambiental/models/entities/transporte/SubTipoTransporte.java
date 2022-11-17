package impacto_ambiental.models.entities.transporte;

import impacto_ambiental.models.entities.EntidadPersistente;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "subtipos_transportes")
public class SubTipoTransporte extends EntidadPersistente {
    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transporte")
    private TipoTransporte tipo;
    @Getter
    @Column(name = "nombre",unique = true)
    public String subTipo;

    public SubTipoTransporte(TipoTransporte tipo, String nombre){
        this.tipo = tipo;
        this.subTipo = nombre;
    }

    public SubTipoTransporte() {

    }

    public TipoTransporte decirTipoTransporte(){
        return tipo;
    }

    public String getNombre() {
        return subTipo;
    }
}
