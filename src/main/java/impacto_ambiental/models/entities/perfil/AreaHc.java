package impacto_ambiental.models.entities.perfil;

import lombok.Getter;

public class AreaHc {
    public AreaHc(Area area, Double hc){
        this.area= area;
        this.hc=hc;
    }
    public AreaHc(Solicitud sol){
        this.solicitud=sol;
        this.area=sol.getArea();
        this.organizacion=sol.getArea().getOrganizacion();
        this.hc=sol.getArea().getOrganizacion().getHCTotal();
    }
    @Getter
    public Solicitud solicitud;
    @Getter
    public Organizacion organizacion;
    @Getter
    public Area area;
    @Getter
    public Double hc;
}
