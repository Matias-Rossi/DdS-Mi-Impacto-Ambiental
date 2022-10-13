package proservices.models.entities.usuario;

import proservices.models.entities.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "permisos")
public class Permiso extends EntidadPersistente {
    @Enumerated(EnumType.STRING)
    @Column(name = "alcance")
    Alcance alcance;

    @Enumerated(EnumType.STRING)
    @Column(name = "accion")
    Accion accion;

    @Enumerated(EnumType.STRING)
    @Column(name = "objeto")
    Objeto objeto;

    public Boolean permite(Permiso permiso) {
        return this.alcance.equals(permiso.alcance) && (this.accion.equals(permiso.accion) || this.accion.equals(Accion.TOTAL)) && this.objeto.equals(permiso.objeto);
    }
}
