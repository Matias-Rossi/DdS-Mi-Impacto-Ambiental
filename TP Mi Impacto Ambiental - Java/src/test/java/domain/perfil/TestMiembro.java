package domain.perfil;
/* //TODO corregir linea 22
import domain.servicios.geodds.ServicioGeoDds;
import domain.transporte.*;
import domain.trayecto.Tramo;
import domain.trayecto.Trayecto;
import domain.ubicacion.Provincia;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

//Test temporal
public class TestMiembro {
  @Test
  @DisplayName("Test Distancia De Trayecto")
  public void testMiembro() throws IOException {
    Miembro miembro = new Miembro("Persona",  "Falsa", TipoDocumento.DNI, 12345789);
    Trayecto trayectoTest = miembro.generarTrayecto("Trayecto prueba");
    Ubicacion ubicacion = new Ubicacion(Provincia.Buenos_Aires, "Chivilcoy", "Chivilcoy", "C1234", "Calle falsa", 123);
    Transporte trans = new Particular(TipoParticular.AUTO, TipoCombustible.NAFTA, ServicioGeoDds.getInstancia()); //TODO Ver si explota

    trayectoTest.aniadirNuevoTramo(ubicacion, ubicacion, trans);
    trayectoTest.aniadirNuevoTramo(ubicacion, ubicacion, trans);
    trayectoTest.aniadirNuevoTramo(ubicacion, ubicacion, trans);


    double distancia = trayectoTest.calcularDistanciaTotal();
    //System.out.print(distancia);
    assertTrue(distancia > 0);

  }

  @Test
  @DisplayName("Test trayecto compartido")
  public void testCompartido() throws IOException {

    Miembro miembroCompartido = new Miembro("PersonaCompartida",  "Falsa", TipoDocumento.DNI, 987654321);

    Trayecto trayectoTest = miembroCompartido.generarTrayecto("Trayecto prueba");
    Ubicacion ubicacion = new Ubicacion(Provincia.Buenos_Aires, "Chivilcoy", "Chivilcoy", "C1234", "Calle falsa", 123);
    Transporte trans = new Particular(TipoParticular.AUTO, TipoCombustible.NAFTA, ServicioGeoDds.getInstancia()); //TODO Ver si explota

    Tramo tramoCompartido = new Tramo(ubicacion, ubicacion, trans);
    tramoCompartido.compartirTramo(miembroCompartido);

    assertTrue(miembroCompartido.getTramosCompartidosAAceptar().size() == 1 );
    miembroCompartido.gestionarTramosCompartidos(tramoCompartido, trayectoTest, true);
    assertTrue(tramoCompartido.getIntegrantes() == 2 );
  }
}
*/

