package impacto_ambiental.models.entities.usuario;

import lombok.Getter;
import lombok.Setter;
import impacto_ambiental.models.entities.EntidadPersistente;

import javax.persistence.*;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "rol")
@Setter
@Getter
public class Rol extends EntidadPersistente {


    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoUsuario tipo;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "rol_permiso", joinColumns = @JoinColumn(name = "rol_id"), inverseJoinColumns = @JoinColumn(name = "permiso_id"))
    private Set<Permiso> permisos;


    public Rol() {}

    public Rol(TipoUsuario tipo) {
        this.permisos = new LinkedHashSet<>();
        this.tipo = tipo;
    }

    public void agregarPermiso(Permiso permiso) {
        this.permisos.add(permiso);
    }

    public Boolean tenesPermiso(Permiso permiso) {
        if(tipo.equals(TipoUsuario.ADMINISTRADOR)) return true;
        return this.permisos.stream().anyMatch(p -> p.permite(permiso));
    }

    public Boolean tenesTodosLosPermisos(Permiso ... permisos) {
        return Arrays.stream(permisos).allMatch(this::tenesPermiso);
    }

    public Boolean esTipo(TipoUsuario tipoUsuario) {
        return this.tipo.equals(tipoUsuario);
    }

    public TipoUsuario getTipoUsuario() {
        return this.tipo;
    }
}
