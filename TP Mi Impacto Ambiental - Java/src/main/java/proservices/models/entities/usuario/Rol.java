package proservices.models.entities.usuario;

import lombok.Getter;
import lombok.Setter;
import proservices.models.entities.EntidadPersistente;

import javax.persistence.*;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Table(name = "rol")
@Setter
@Getter
public class Rol extends EntidadPersistente {
    @Column
    private String nombre;

    @ManyToMany(mappedBy = "roles")
    private Set<Permiso> permisos;

    public Rol() {
        this.permisos = new LinkedHashSet<>();
    }

    public void agregarPermiso(Permiso permiso) {
        this.permisos.add(permiso);
    }

    public Boolean tenesPermiso(Permiso permiso) {
        return this.permisos.contains(permiso);
    }

    public Boolean tenesTodosLosPermisos(Permiso ... permisos) {
        return Arrays.stream(permisos).allMatch(p -> this.permisos.contains(p));
    }
}
