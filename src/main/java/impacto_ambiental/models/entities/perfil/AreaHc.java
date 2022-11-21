package impacto_ambiental.models.entities.perfil;

import lombok.Getter;

public class AreaHc {
    public AreaHc(Area area, Double hc){
        this.area= area;
        this.hc=hc;
    }
    @Getter
    public Area area;
    @Getter
    public Double hc;
}
