package domain.transporte;

import domain.ubicacion.Ubicacion;

public interface CalculadorDeDistancia {
    public double calcularDistancia(Ubicacion origen, Ubicacion destino);
}
