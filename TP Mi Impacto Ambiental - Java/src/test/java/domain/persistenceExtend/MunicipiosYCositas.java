package domain.persistenceExtend;

import impacto_ambiental.models.entities.ubicacion.NombreProvincia;
import impacto_ambiental.models.entities.ubicacion.Provincia;
import impacto_ambiental.models.entities.usuario.Rol;
import impacto_ambiental.models.entities.usuario.TipoUsuario;
import impacto_ambiental.models.repositorios.RepositorioProvincias;
import impacto_ambiental.models.repositorios.RepositorioRoles;
import org.junit.jupiter.api.Test;

public class MunicipiosYCositas {
    @Test
    public void municipioYCositas(){

        Provincia prov = new Provincia(NombreProvincia.Buenos_Aires);
        prov.crearMunicipio("Lanus");
        prov.crearMunicipio("Avellaneda");

        RepositorioProvincias repo = new RepositorioProvincias();
        repo.agregar(prov);

    }

    @Test
    public void roles() {
        Rol rol = new Rol(TipoUsuario.MIEMBRO);
        RepositorioRoles repo = new RepositorioRoles();
        repo.agregar(rol);

        Rol rol2 = new Rol(TipoUsuario.ORGANIZACION);
        repo.agregar(rol2);

        Rol rol3 = new Rol(TipoUsuario.AGENTE);
        repo.agregar(rol3);

        Rol rol4 = new Rol(TipoUsuario.ADMINISTRADOR);
        repo.agregar(rol4);
    }
}
