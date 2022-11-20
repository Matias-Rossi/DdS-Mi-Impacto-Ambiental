package impacto_ambiental.models.entities.perfil;

import lombok.Getter;

public class AreaHc {
    AreaHc(Area area, Double hc){
        this.area= area;
        this.hc=hc;
    }
    @Getter
    Area area;
    @Getter
    Double hc;
}
