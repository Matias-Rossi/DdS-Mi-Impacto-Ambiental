package domain.transporte;

import domain.persistenceExtend.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "subtipos_transportes")
public class SubTipoTransporte extends EntidadPersistente {
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_transporte")
    private TipoTransporte tipo;
    @Column(name = "nombre")
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
