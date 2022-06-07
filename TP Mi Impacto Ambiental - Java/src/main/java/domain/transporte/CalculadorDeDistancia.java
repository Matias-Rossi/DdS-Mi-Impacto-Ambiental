package domain.transporte;

import domain.ubicacion.Ubicacion;

import java.io.IOException;

public interface CalculadorDeDistancia {
    public double calcularDistancia(Ubicacion origen, Ubicacion destino) throws IOException;
}
