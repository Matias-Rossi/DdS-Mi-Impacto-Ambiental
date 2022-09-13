package domain.reportes;

import lombok.Getter;

public class ReporteComposicion {

    @Getter
    Double tipoDeActividadCOMBUSTION_FIJA=0.0;
    @Getter
    Double tipoDeActividadCOMBUSTION_MOVIL=0.0;
    @Getter
    Double tipoDeActividadELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA=0.0;
    @Getter
    Double tipoDeActividadLOGISTICA_DE_PRODUCTOS_Y_RESIDUOS=0.0;
    @Getter
    Double tipoDeActividadTRANSPORTE_PUBLICO=0.0;
    @Getter
    Double tipoDeActividadTRANSPORTE_PARTICULAR=0.0;
    @Getter
    Double tipoDeActividadSERVICIO_CONTRATADO=0.0;
    @Getter
    Double tipoDeActividadSERVICIO_ECOLOGICO=0.0;
    public ReporteComposicion() {
    }
    public void sumarTipoDeActividadCOMBUSTION_FIJA(Double valor){
        tipoDeActividadCOMBUSTION_FIJA += valor;
    }
    public void sumarTipoDeActividadCOMBUSTION_MOVIL(Double valor){
        tipoDeActividadCOMBUSTION_MOVIL += valor;
    }
    public void sumarTipoDeActividadELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA(Double valor){
        tipoDeActividadELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA += valor;
    }
    public void sumarTipoDeActividadLOGISTICA_DE_PRODUCTOS_Y_RESIDUOS(Double valor){
        tipoDeActividadLOGISTICA_DE_PRODUCTOS_Y_RESIDUOS += valor;
    }
    public void sumarTipoDeActividadTRANSPORTE_PUBLICO(Double valor){
        tipoDeActividadTRANSPORTE_PUBLICO += valor;
    }
    public void sumarTipoDeActividadTRANSPORTE_PARTICULAR(Double valor){
        tipoDeActividadTRANSPORTE_PARTICULAR += valor;
    }
    public void sumarTipoDeActividadSERVICIO_CONTRATADO(Double valor){
        tipoDeActividadSERVICIO_CONTRATADO += valor;
    }
    public void sumarTipoDeActividadSERVICIO_ECOLOGICO(Double valor){
        tipoDeActividadSERVICIO_ECOLOGICO += valor;
    }
    public void sumar(String actividad,Double valor){
        switch (actividad){
            case "COMBUSTION_FIJA":
                sumarTipoDeActividadCOMBUSTION_FIJA(valor);
                break;
            case "COMBUSTION_MOVIL":
                sumarTipoDeActividadCOMBUSTION_MOVIL(valor);
                break;
            case "ELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA":
                sumarTipoDeActividadELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA(valor);
                break;
            case "LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS":
                sumarTipoDeActividadLOGISTICA_DE_PRODUCTOS_Y_RESIDUOS(valor);
                break;
            case "TRANSPORTE_PUBLICO":
                sumarTipoDeActividadTRANSPORTE_PUBLICO(valor);
                break;
            case "TRANSPORTE_PARTICULAR":
                sumarTipoDeActividadTRANSPORTE_PARTICULAR(valor);
                break;
            case "SERVICIO_CONTRATADO":
                sumarTipoDeActividadSERVICIO_CONTRATADO(valor);
                break;
            case "SERVICIO_ECOLOGICO":
                sumarTipoDeActividadSERVICIO_ECOLOGICO(valor);
                break;
        }
    }
}
