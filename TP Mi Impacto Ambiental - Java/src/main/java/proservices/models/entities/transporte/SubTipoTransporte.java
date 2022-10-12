package proservices.models.entities.transporte;

import proservices.models.entities.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "subtipos_transportes")
public class SubTipoTransporte extends EntidadPersistente {
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transporte")
    private TipoTransporte tipo;
    @Column(name = "nombre",unique = true)
    private String subTipo;

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
