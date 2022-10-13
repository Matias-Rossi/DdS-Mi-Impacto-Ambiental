package impacto_ambiental.models.entities.transporte;

import impacto_ambiental.models.entities.ubicacion.Ubicacion;

import java.io.IOException;

public interface CalculadorDeDistancia {
    public double calcularDistancia(Ubicacion origen, Ubicacion destino) throws IOException;
}
