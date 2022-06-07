package domain.servicios.geodds;

import domain.servicios.geodds.entidades.Distancia;
import domain.servicios.geodds.entidades.Localidad;
import domain.servicios.geodds.entidades.Municipio;
import domain.servicios.geodds.entidades.Provincia;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestServicioGeoDds {
  @Test
  public void testProvincias() throws IOException {
    List<Provincia> provincias = ServicioGeoDds.getInstancia().listadoProvincias();
    assertEquals(24, provincias.size());
  }

  @Test
  public void testMunicipios() throws IOException {
    List<Municipio> municipios = ServicioGeoDds.getInstancia().listadoMunicipios(178); //La Pampa
    assertEquals(23, municipios.size());
  }

  @Test
  public void testLocalidades() throws IOException {
    List<Localidad> localidades = ServicioGeoDds.getInstancia().listadoLocalidades(345); // Bragado
    assertEquals(11, localidades.size());
  }

  @Test
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
  public void testDistanciaUbicacion() throws IOException {
    Distancia distancia = ServicioGeoDds.getInstancia().getDistanciaEntrePuntos(
        new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            123
        ),
        new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Chivilcoy",
            "Chivilcoy",
            "C5678",
            "falso",
            456
        )
    );
    assertEquals("KM", distancia.getUnidad());
  }
}
