package domain.servicios.geodds;

public class TestServicioGeoDds {
/*
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
    assertEquals(11, localidades.size());
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
            new MunicipiosODepartamentos(repositorioProvincias.getProvincia(NombreProvincia.Buenos_Aires), "Bragado"),
            "Bragado",
            "C1234",
            "calle falsa",
            123
        ),
        new Ubicacion(
            new MunicipiosODepartamentos(repositorioProvincias.getProvincia(NombreProvincia.Buenos_Aires), "Chivilcoy"),
            "Chivilcoy",
            "C5678",
            "falso",
            456
        )
    );
    assertEquals("KM", distancia.getUnidad());
  }
*/


}
