package domain.servicios.geodds;

import impacto_ambiental.models.entities.servicios.geodds.ServicioGeoDds;
import impacto_ambiental.models.entities.servicios.geodds.entidades.Distancia;
import impacto_ambiental.models.entities.servicios.geodds.entidades.Localidad;
import impacto_ambiental.models.entities.servicios.geodds.entidades.Municipio;
import impacto_ambiental.models.entities.servicios.geodds.entidades.Provincia;
import impacto_ambiental.models.entities.ubicacion.MunicipiosODepartamentos;
import impacto_ambiental.models.entities.ubicacion.NombreProvincia;
import impacto_ambiental.models.entities.ubicacion.Ubicacion;
import impacto_ambiental.models.repositorios.RepositorioProvincias;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;

public class TestServicioGeoDds {

  @Test
  @DisplayName("GeoDds obtiene provincias")
  public void testProvincias() throws IOException {
    List<Provincia> provincias = ServicioGeoDds.getInstancia().listadoProvincias();
    assertEquals(24, provincias.size());
  }

  @Test
  @DisplayName("GeoDds obtiene municipios")
  public void testMunicipios() throws IOException {
    List<Municipio> municipios = ServicioGeoDds.getInstancia().listadoMunicipios(178); //La Pampa
    assertEquals(23, municipios.size());
  }

  @Test
  @DisplayName("GeoDds obtiene localidades")
  public void testLocalidades() throws IOException {
    List<Localidad> localidades = ServicioGeoDds.getInstancia().listadoLocalidades(345); // Bragado
    assertEquals(11, ((List<?>) localidades).size());
  }

  @Test
  @DisplayName("GeoDds obtiene distancia entre dos puntos")
  public void testDistancia() throws IOException {
    Distancia distancia = ServicioGeoDds.getInstancia().distanciaEntrePuntos(
        3415,
        "falsa",
        123,
        3415,
        "falsa",
        123
    );
    assertFalse(distancia.getUnidad().isEmpty());
  }

  @Test
  @DisplayName("GeoDds obtiene distancia utilizando implementación propia de provincias")
  public void testDistanciaUbicacion() throws IOException {
    RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
    Distancia distancia = ServicioGeoDds.getInstancia().getDistanciaEntrePuntos(
        new Ubicacion(
            new MunicipiosODepartamentos(repositorioProvincias.getProvincia(NombreProvincia.BUENOS_AIRES), "Bragado"),
            "Bragado",
            "C1234",
            "calle falsa",
            123
        ),
        new Ubicacion(
            new MunicipiosODepartamentos(repositorioProvincias.getProvincia(NombreProvincia.BUENOS_AIRES), "Chivilcoy"),
            "Chivilcoy",
            "C5678",
            "falso",
            456
        )
    );
    System.out.println("LA DISTANCIA ES " + distancia.getValor());
    assertEquals("KM", distancia.getUnidad());
  }



}
