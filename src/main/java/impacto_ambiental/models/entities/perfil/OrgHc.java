package impacto_ambiental.models.entities.perfil;

import impacto_ambiental.models.entities.reportes.GeneradorDeReportes;
import lombok.Getter;

public class OrgHc {
  @Getter
  public Double hc;
  @Getter
  public Double porcentaje;
  @Getter
  public String nombre;
  public OrgHc(Double hcTotal,Organizacion organizacion){
    Double hcOrg = GeneradorDeReportes.round(organizacion.getHCTotal());
    this.nombre=organizacion.getRazonSocial();
    this.hc=hcOrg;
    if(hcTotal==0.0) {
      this.porcentaje = 0.0;
    }else this.porcentaje=GeneradorDeReportes.round((hcOrg/hcTotal)*100);
  }
}
