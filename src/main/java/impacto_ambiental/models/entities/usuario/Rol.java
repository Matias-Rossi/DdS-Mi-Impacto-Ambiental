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


    @Enumerated(EnumType.STRING) @Getter
    @Column(name = "tipo")
    private TipoUsuario tipo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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
        if(tipo.equals(TipoUsuario.ADMINISTRADOR)) return true;
        return Arrays.stream(permisos).allMatch(this::tenesPermiso);
    }

    public Boolean esTipo(TipoUsuario tipoUsuario) {
        return this.tipo.equals(tipoUsuario);
    }

    public TipoUsuario getTipoUsuario() {
        return this.tipo;
    }
}
