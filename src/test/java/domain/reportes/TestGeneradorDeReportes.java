package domain.reportes;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGeneradorDeReportes {
  /*
  @Test
  public void reportes(){
    RepositorioProvincias repositorioProvincias = new RepositorioProvincias();

    Provincia provincia_a = repositorioProvincias.getProvincia(NombreProvincia.Buenos_Aires);
    Provincia provincia_b = repositorioProvincias.getProvincia(NombreProvincia.Catamarca);

    MunicipiosODepartamentos vdp = provincia_a.crearMunicipio("vdp");
    MunicipiosODepartamentos vpr = provincia_b.crearMunicipio("vpr");
    Clasificacion clasificacion = new Clasificacion("clasificacion");
    Clasificacion clasificacionb = new Clasificacion("clasificacionb");
    Organizacion organizacion = vdp.crearOrganizacion(ApachePOI.getInstance(),"razon", Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
    Organizacion organizacionb = vpr.crearOrganizacion(ApachePOI.getInstance(),"razonb",Tipo.INSTITUCION,clasificacion,"locb","cpb","calb",1);
    repositorioProvincias.actualizar(provincia_a);
    repositorioProvincias.actualizar(provincia_b);

    RepositorioHCHistoricos repositorioReportes = new RepositorioHCHistoricos();
    HChistorico primero = new HChistorico(TipoActividadDA.COMBUSTION_FIJA, TipoConsumoDA.GAS_NATURAL,2022, Periodo.Abril,1.0, organizacionb);
    HChistorico segundo = new HChistorico(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0, organizacionb);
    repositorioReportes.actualizar(primero);
    repositorioReportes.actualizar(segundo);
    assertEquals(2.0, GeneradorDeReportes.getInstance().hCTotalPorTipoDeOrganizacion(clasificacion));
  }
  */

}
