package domain.perfil;

import domain.calculadorHC.CalculadorDeHC;
import domain.importadorExcel.ApachePOI;
import domain.servicios.geodds.ServicioGeoDds;
import domain.transporte.*;
import domain.trayecto.Tramo;
import domain.trayecto.Trayecto;
import domain.ubicacion.Provincia;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMiembro {
  @Test
  @DisplayName("Test Distancia De Trayecto")

  public void testMiembro() throws IOException {
        //Generar Organizacion y su Lista
        Importador importadorApache = new ApachePOI();
        Ubicacion ubicacionTest = new Ubicacion(
                domain.ubicacion.Provincia.Buenos_Aires,
                "Bragado",
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );

    Clasificacion clasificacionTest = new Clasificacion("dasdsa");
    Organizacion organizacionTest = new Organizacion(importadorApache, ubicacionTest, "testSA", Tipo.EMPRESA, clasificacionTest);
    Area area = organizacionTest.darAltaArea("arita");

      Ubicacion casaMiembro = new Ubicacion(
              domain.ubicacion.Provincia.Buenos_Aires,
              "Bragado",
              "Bragado",
              "C1234",
              "calle falsa",
              123
      );
    Miembro miembro = new Miembro("Persona",  "Falsa", TipoDocumento.DNI, 12345789,casaMiembro, "1234", "1234@1234");
    miembro.darseAltaEnOrganizacion(area);
    area.gestionarMiembrosPendientes(miembro.solicitudes.get(0),SolicitudEstado.ACEPTADA);
    List<Organizacion> organizacionesTest = new ArrayList<>();
    organizacionesTest.add(organizacionTest);
    Trayecto trayectoTest = miembro.generarTrayecto("Trayecto prueba", organizacionesTest,2022,1,20 );
    Ubicacion ubicacion = new Ubicacion(Provincia.Buenos_Aires, "Chivilcoy", "Chivilcoy", "C1234", "Calle falsa", 123);
    Ubicacion ubicacion1 = new Ubicacion(Provincia.Buenos_Aires, "Chivilcoy", "Chivilcoy", "C1234", "Calle falsa1", 124);
    Ubicacion ubicacion2 = new Ubicacion(Provincia.Buenos_Aires, "Chivilcoy", "Chivilcoy", "C1234", "Calle falsa2", 125);

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
  @DisplayName("Test trayecto compartido")
  public void testCompartido() throws IOException {
    CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
      Importador importadorApache = new ApachePOI();
      Ubicacion ubicacionTest = new Ubicacion(
              domain.ubicacion.Provincia.Buenos_Aires,
              "Bragado",
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
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            123
    );
    Miembro miembroCompartido = new Miembro("PersonaCompartida",  "Falsa", TipoDocumento.DNI, 987654321,casaMiembro, "1234", "1234@1234");
    miembroCompartido.darseAltaEnOrganizacion(area);
    area.gestionarMiembrosPendientes(miembroCompartido.solicitudes.get(0),SolicitudEstado.ACEPTADA);

    Trayecto trayectoTest = miembroCompartido.generarTrayecto("Trayecto prueba", organizacionesTest ,2022,1,20 );
    Ubicacion ubicacion = new Ubicacion(Provincia.Buenos_Aires, "Chivilcoy", "Chivilcoy", "C1234", "Calle falsa", 123);
      SubTipoTransporte unAuto = new SubTipoTransporte(TipoTransporte.TIPO_PARTICULAR, "AUTO" ) ;
      Transporte trans = new Particular(unAuto, TipoCombustible.NAFTA, ServicioGeoDds.getInstancia(), 0.5);

    Tramo tramoCompartido = trayectoTest.aniadirNuevoTramo(ubicacion,ubicacion,trans);
    tramoCompartido.compartirTramo(miembroCompartido);

    assertTrue(miembroCompartido.getTramosCompartidosAAceptar().size() == 1 );
    miembroCompartido.gestionarTramosCompartidos(tramoCompartido, trayectoTest, true);
    assertTrue(tramoCompartido.getIntegrantes() == 2 );
  }

}


