package domain.servicios.geodds;

import domain.servicios.geodds.entidades.Distancia;
import domain.servicios.geodds.entidades.Localidad;
import domain.servicios.geodds.entidades.Municipio;
import domain.servicios.geodds.entidades.Provincia;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestServicioGeoDds {
  @Test
  public void testProvincias() throws IOException {
    List<Provincia> provincias = ServicioGeoDds.getInstancia().listadoProvincias();
    assertTrue(provincias.size() == 24);
  }

  @Test
  public void testMunicipios() throws IOException {
    List<Municipio> municipios = ServicioGeoDds.getInstancia().listadoMunicipios(178); //La Pampa
    assertTrue(municipios.size() == 23);
  }

  @Test
  public void testLocalidades() throws IOException {
    List<Localidad> localidades = ServicioGeoDds.getInstancia().listadoLocalidades(345); // Bragado
    assertTrue(localidades.size() == 11);
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
    System.out.print(distancia.getDistancia());
    assertFalse(distancia.getDistancia().isEmpty());
  }
}
