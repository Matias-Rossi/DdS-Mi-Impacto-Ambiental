package domain.reportes;

import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.importadorExcel.ApachePOI;
import domain.perfil.Clasificacion;
import domain.perfil.Organizacion;
import domain.perfil.Tipo;
import domain.persistenceExtend.repositorios.RepositorioProvincias;
import domain.persistenceExtend.repositorios.RepositorioReportes;
import domain.ubicacion.MunicipiosODepartamentos;
import domain.ubicacion.NombreProvincia;
import domain.ubicacion.Provincia;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGeneradorDeReportes {
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

    RepositorioReportes repositorioReportes = new RepositorioReportes();
    Reporte primero = new Reporte(TipoActividadDA.COMBUSTION_FIJA, TipoConsumoDA.GAS_NATURAL,2022, Periodo.Abril,1.0,organizacionb);
    Reporte segundo = new Reporte(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacionb);
    repositorioReportes.actualizar(primero);
    repositorioReportes.actualizar(segundo);
    assertEquals(0.0,GeneradorDeReportes.getInstance().hCTotalPorTipoDeOrganizacion(clasificacion));
  }
}
