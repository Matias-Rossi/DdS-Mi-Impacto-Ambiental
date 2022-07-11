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





//Test temporal
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
    List<Organizacion> organizacionesTest = new ArrayList<Organizacion>();
    organizacionesTest.add(organizacionTest);

    List<Integer> indices = new ArrayList<>();
    indices.add(0);

        //
    Miembro miembro = new Miembro("Persona",  "Falsa", TipoDocumento.DNI, 12345789);
    Trayecto trayectoTest = miembro.generarTrayecto("Trayecto prueba", indices,2022,1,20 );
    Ubicacion ubicacion = new Ubicacion(Provincia.Buenos_Aires, "Chivilcoy", "Chivilcoy", "C1234", "Calle falsa", 123);
    Ubicacion ubicacion1 = new Ubicacion(Provincia.Buenos_Aires, "Chivilcoy", "Chivilcoy", "C1234", "Calle falsa1", 124);
    Ubicacion ubicacion2 = new Ubicacion(Provincia.Buenos_Aires, "Chivilcoy", "Chivilcoy", "C1234", "Calle falsa2", 125);
    Transporte trans = new Particular(TipoParticular.AUTO, TipoCombustible.NAFTA, ServicioGeoDds.getInstancia(), 0.5); //TODO Ver si explota
    Transporte trans2 = new Particular(TipoParticular.AUTO, TipoCombustible.NAFTA, ServicioGeoDds.getInstancia(), 0.4); //TODO Ver si explota
    Transporte trans3 = new Particular(TipoParticular.AUTO, TipoCombustible.NAFTA, ServicioGeoDds.getInstancia(), 0.3); //TODO Ver si explota
    CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
    trayectoTest.aniadirNuevoTramo(ubicacion, ubicacion, trans ,calculadorDeHCTest);
    trayectoTest.aniadirNuevoTramo(ubicacion1, ubicacion, trans2,calculadorDeHCTest);
    trayectoTest.aniadirNuevoTramo(ubicacion2, ubicacion, trans3,calculadorDeHCTest);

    double distancia = trayectoTest.calcularDistanciaTotal();
    System.out.print(distancia);
    assertTrue(distancia > 0);

  }

  @Test
  @DisplayName("Test trayecto compartido")
  public void testCompartido() throws IOException {
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
      List<Organizacion> organizacionesTest = new ArrayList<Organizacion>();
      organizacionesTest.add(organizacionTest);

      List<Integer> indices = new ArrayList<>();
      indices.add(0);

      Miembro miembroCompartido = new Miembro("PersonaCompartida",  "Falsa", TipoDocumento.DNI, 987654321);

    Trayecto trayectoTest = miembroCompartido.generarTrayecto("Trayecto prueba", indices,2022,1,20 );
    Ubicacion ubicacion = new Ubicacion(Provincia.Buenos_Aires, "Chivilcoy", "Chivilcoy", "C1234", "Calle falsa", 123);
    Transporte trans = new Particular(TipoParticular.AUTO, TipoCombustible.NAFTA, ServicioGeoDds.getInstancia(), 0.5); //TODO Ver si explota

    Tramo tramoCompartido = new Tramo(ubicacion, ubicacion, trans,new CalculadorDeHC());
    tramoCompartido.compartirTramo(miembroCompartido);

    assertTrue(miembroCompartido.getTramosCompartidosAAceptar().size() == 1 );
    miembroCompartido.gestionarTramosCompartidos(0, 0, true);
    assertTrue(tramoCompartido.getIntegrantes() == 2 );
  }
}


