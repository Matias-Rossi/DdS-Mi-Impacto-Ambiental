package domain.ubicacion;

import java.io.IOException;

public interface CalculadorDeDistancia {
  double calcularDistancia(Ubicacion origen, Ubicacion destino) throws IOException; //Kilometros
}
