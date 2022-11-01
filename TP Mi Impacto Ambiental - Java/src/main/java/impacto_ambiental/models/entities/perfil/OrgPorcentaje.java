package impacto_ambiental.models.entities.perfil;

import lombok.Getter;

public class OrgPorcentaje {
    @Getter
    public Organizacion organizacion;
    @Getter
    public Double porcentaje;
    public OrgPorcentaje(Organizacion organizacion, Double porcentaje){
        this.organizacion = organizacion;
        this.porcentaje = porcentaje;
    }
}
