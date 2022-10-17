package domain.persistenceExtend;

import impacto_ambiental.models.entities.ubicacion.NombreProvincia;
import impacto_ambiental.models.entities.ubicacion.Provincia;
import impacto_ambiental.models.repositorios.RepositorioProvincias;
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
}
