package impacto_ambiental.models.entities.usuario;

import impacto_ambiental.models.entities.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name = "permisos")
public class Permiso extends EntidadPersistente {

    @ManyToOne
            @JoinColumn(name = "rol_id", referencedColumnName = "id")
    Rol rol;

    @Enumerated(EnumType.STRING)
    @Column(name = "alcance")
    Alcance alcance;

    @Enumerated(EnumType.STRING)
    @Column(name = "accion")
    Accion accion;

    @Enumerated(EnumType.STRING)
    @Column(name = "objeto")
    Objeto objeto;

    public Permiso() {

    }

    public Boolean permite(Permiso permiso) {
        return this.alcance.equals(permiso.alcance) && (this.accion.equals(permiso.accion) || this.accion.equals(Accion.TOTAL)) && this.objeto.equals(permiso.objeto);
    }

    public Permiso(Alcance alcance, Accion accion, Objeto objeto, Rol rol) {
        this.alcance = alcance;
        this.accion = accion;
        this.objeto = objeto;
        this.rol = rol;
        rol.agregarPermiso(this);
    }

    public Permiso(Alcance alcance, Accion accion, Objeto objeto) {
        this.alcance = alcance;
        this.accion = accion;
        this.objeto = objeto;
    }

}
