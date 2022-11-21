package impacto_ambiental.spark.utils;

import impacto_ambiental.models.entities.ubicacion.MunicipiosODepartamentos;

public class MunicipioJson {
    public int id;
    public String nombre;

    public MunicipioJson(MunicipiosODepartamentos mun) {
        this.id = mun.getId();
        this.nombre = mun.getMunicipioOLocalidad();
    }
}
