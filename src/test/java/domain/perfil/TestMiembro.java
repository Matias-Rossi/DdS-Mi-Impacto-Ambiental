package domain.perfil;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMiembro {
/*
  @Test
  @DisplayName("Test Distancia De Trayecto")

  public void testMiembro() throws IOException {
    //Generar Organizacion y su Lista
    Importador importadorApache = new ApachePOI();
    RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
    Provincia BuenosAiresTest = repositorioProvincias.getProvincia(NombreProvincia.Buenos_Aires);
    MunicipiosODepartamentos BragadoTest = new MunicipiosODepartamentos(BuenosAiresTest, "Bragado");
    MunicipiosODepartamentos ChivilcoyTest = new MunicipiosODepartamentos(BuenosAiresTest, "Chivilcoy");

    Ubicacion ubicacionTest = new Ubicacion(
            BragadoTest,
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );

    Clasificacion clasificacionTest = new Clasificacion("dasdsa");
    Organizacion organizacionTest = new Organizacion(importadorApache, ubicacionTest, "testSA", Tipo.EMPRESA, clasificacionTest);
    Area area = organizacionTest.darAltaArea("arita");


    Ubicacion casaMiembro = new Ubicacion(
            BragadoTest,
              "Bragado",
              "C1234",
              "calle falsa",
              123
      );
    Miembro miembro = new Miembro("Persona",  "Falsa", TipoDocumento.DNI, 12345789,casaMiembro, "1234", "1234@1234");
    miembro.darseAltaEnOrganizacion(area);
    area.gestionarMiembrosPendientes(miembro.solicitudes.get(0), SolicitudEstado.ACEPTADA);
    List<Organizacion> organizacionesTest = new ArrayList<>();
    organizacionesTest.add(organizacionTest);
    Trayecto trayectoTest = miembro.generarTrayecto("Trayecto prueba", organizacionesTest,2022,1,20 );
    Ubicacion ubicacion = new Ubicacion(ChivilcoyTest, "Chivilcoy", "C1234", "Calle falsa", 123);
    Ubicacion ubicacion1 = new Ubicacion(ChivilcoyTest, "Chivilcoy", "C1234", "Calle falsa1", 124);
    Ubicacion ubicacion2 = new Ubicacion(ChivilcoyTest, "Chivilcoy", "C1234", "Calle falsa2", 125);

    SubTipoTransporte unAuto = new SubTipoTransporte(TipoTransporte.TIPO_PARTICULAR, "AUTO" ) ;
    Transporte trans = new Particular(unAuto, TipoCombustible.NAFTA, ServicioGeoDds.getInstancia(), 0.5);
    Transporte trans2 = new Particular(unAuto, TipoCombustible.NAFTA, ServicioGeoDds.getInstancia(), 0.4);
    Transporte trans3 = new Particular(unAuto, TipoCombustible.NAFTA, ServicioGeoDds.getInstancia(), 0.3);
    CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
    trayectoTest.aniadirNuevoTramo(ubicacion, ubicacion, trans );
    trayectoTest.aniadirNuevoTramo(ubicacion1, ubicacion, trans2);
    trayectoTest.aniadirNuevoTramo(ubicacion2, ubicacion, trans3);

    double distancia = trayectoTest.calcularDistanciaTotal();
    System.out.print(distancia);
    assertTrue(distancia > 0);

  }

  @Test
  @DisplayName("Los trayectos pueden ser compartidos")
  public void testCompartido() throws IOException {
    CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
    Importador importadorApache = new ApachePOI();
    RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
    Provincia BuenosAiresTest = repositorioProvincias.getProvincia(NombreProvincia.Buenos_Aires);
    MunicipiosODepartamentos BragadoTest = new MunicipiosODepartamentos(BuenosAiresTest, "Bragado");
    MunicipiosODepartamentos ChivilcoyTest = new MunicipiosODepartamentos(BuenosAiresTest, "Chivilcoy");
    Ubicacion ubicacionTest = new Ubicacion(
            BragadoTest,
              "Bragado",
              "C1234",
              "calle falsa",
              123
      );
      Clasificacion clasificacionTest = new Clasificacion("dasdsa");
      Organizacion organizacionTest = new Organizacion(importadorApache, ubicacionTest, "testSA", Tipo.EMPRESA, clasificacionTest);

      Area area = organizacionTest.darAltaArea("arita");



      List<Organizacion> organizacionesTest = new ArrayList<>();
      organizacionesTest.add(organizacionTest);

    Ubicacion casaMiembro = new Ubicacion(
            BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            123
    );
    Miembro miembroCompartido = new Miembro("PersonaCompartida",  "Falsa", TipoDocumento.DNI, 987654321,casaMiembro, "1234", "1234@1234");
    miembroCompartido.darseAltaEnOrganizacion(area);
    area.gestionarMiembrosPendientes(miembroCompartido.solicitudes.get(0),SolicitudEstado.ACEPTADA);

    Trayecto trayectoTest = miembroCompartido.generarTrayecto("Trayecto prueba", organizacionesTest ,2022,1,20 );
    Ubicacion ubicacion = new Ubicacion(ChivilcoyTest, "Chivilcoy", "C1234", "Calle falsa", 123);
      SubTipoTransporte unAuto = new SubTipoTransporte(TipoTransporte.TIPO_PARTICULAR, "AUTO" ) ;
      Transporte trans = new Particular(unAuto, TipoCombustible.NAFTA, ServicioGeoDds.getInstancia(), 0.5);

    Tramo tramoCompartido = trayectoTest.aniadirNuevoTramo(ubicacion,ubicacion,trans);
    tramoCompartido.compartirTramo(miembroCompartido);

    assertTrue(miembroCompartido.getTramosCompartidosAAceptar().size() == 1 );
    miembroCompartido.gestionarTramosCompartidos(tramoCompartido, trayectoTest, true);
    assertTrue(tramoCompartido.getIntegrantes() == 2 );
  }


*/
}


