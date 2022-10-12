package proservices.models.entities.transporte;

import proservices.models.entities.ubicacion.Ubicacion;

import java.io.IOException;

public interface CalculadorDeDistancia {
    public double calcularDistancia(Ubicacion origen, Ubicacion destino) throws IOException;
}
