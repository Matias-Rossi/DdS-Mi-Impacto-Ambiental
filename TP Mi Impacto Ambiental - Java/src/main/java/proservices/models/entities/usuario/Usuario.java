package proservices.models.entities.usuario;

import proservices.models.entities.perfil.Miembro;
import proservices.models.entities.perfil.Organizacion;
import proservices.models.entities.ubicacion.MunicipiosODepartamentos;

public class Usuario {

    Rol rol;

    String usuario;

    String contrasenia;

    Miembro miembro;

    Organizacion organizacion;

    MunicipiosODepartamentos municipioODepartamento;

    public Usuario(Rol rol, String usuario, String contrasenia, Miembro miembro, Organizacion organizacion, MunicipiosODepartamentos municipioODepartamento) {
        this.rol = rol;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.miembro = miembro;
        this.organizacion = organizacion;
        this.municipioODepartamento = municipioODepartamento;
    }



}
