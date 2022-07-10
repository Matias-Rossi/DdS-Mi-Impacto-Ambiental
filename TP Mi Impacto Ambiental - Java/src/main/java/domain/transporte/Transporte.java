package domain.transporte;

import domain.calculadorHC.TipoActividadDA;
import domain.calculadorHC.TipoConsumoDA;
import domain.perfil.Tipo;
import domain.ubicacion.Ubicacion;

public interface Transporte {
    //TODO hacer que los transportes implementen Transporte
    public TipoTransporte decirTipoTransporte();
    public double consumoDeTransoporte();
    public TipoActividadDA tipoDeActividadDA();
    public TipoConsumoDA tipoConsumoDA();
    public double calcularDistancia(Ubicacion inicio, Ubicacion fin);
}
