package domain.transporte;

import domain.ubicacion.Ubicacion;

public interface Transporte {
    public int indiceHC();
    public double calcularDistancia(Ubicacion inicio, Ubicacion fin);
}
