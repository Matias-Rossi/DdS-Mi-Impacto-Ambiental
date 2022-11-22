package impacto_ambiental.models.entities.reportes;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ReporteComposicion {

    @Getter
    public Double tipoDeActividadCOMBUSTION_FIJA=0.0;
    @Getter
    public Double porcentajetipoDeActividadCOMBUSTION_FIJA;

    @Getter
    public Double tipoDeActividadCOMBUSTION_MOVIL=0.0;
    @Getter
    public Double porcentajetipoDeActividadCOMBUSTION_MOVIL;

    @Getter
    public Double tipoDeActividadELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA=0.0;
    @Getter
    public Double porcentajetipoDeActividadELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA;

    @Getter
    public Double tipoDeActividadLOGISTICA_DE_PRODUCTOS_Y_RESIDUOS=0.0;
    @Getter
    public Double porcentajetipoDeActividadLOGISTICA_DE_PRODUCTOS_Y_RESIDUOS;

    @Getter
    public Double tipoDeActividadTRANSPORTE_PUBLICO=0.0;
    @Getter
    public Double porcentajetipoDeActividadTRANSPORTE_PUBLICO;

    @Getter
    public Double tipoDeActividadTRANSPORTE_PARTICULAR=0.0;
    @Getter
    public Double porcentajetipoDeActividadTRANSPORTE_PARTICULAR;

    @Getter
    public Double tipoDeActividadSERVICIO_CONTRATADO=0.0;
    @Getter
    public Double porcentajetipoDeActividadSERVICIO_CONTRATADO;

    @Getter
    public Double tipoDeActividadSERVICIO_ECOLOGICO=0.0;
    @Getter
    public Double porcentajetipoDeActividadSERVICIO_ECOLOGICO;

    public void calcularPorcentajes(){


        Double hcTotal = tipoDeActividadSERVICIO_ECOLOGICO+tipoDeActividadSERVICIO_CONTRATADO+tipoDeActividadCOMBUSTION_FIJA+tipoDeActividadCOMBUSTION_MOVIL+tipoDeActividadTRANSPORTE_PARTICULAR+tipoDeActividadTRANSPORTE_PUBLICO+tipoDeActividadELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA+tipoDeActividadLOGISTICA_DE_PRODUCTOS_Y_RESIDUOS;
        if(hcTotal==0)return;
        this.porcentajetipoDeActividadSERVICIO_ECOLOGICO=GeneradorDeReportes.round((this.tipoDeActividadSERVICIO_ECOLOGICO/hcTotal)*100);
        this.porcentajetipoDeActividadSERVICIO_CONTRATADO=GeneradorDeReportes.round((this.tipoDeActividadSERVICIO_CONTRATADO/hcTotal)*100);
        this.porcentajetipoDeActividadCOMBUSTION_FIJA=GeneradorDeReportes.round((this.tipoDeActividadCOMBUSTION_FIJA/hcTotal)*100);
        this.porcentajetipoDeActividadLOGISTICA_DE_PRODUCTOS_Y_RESIDUOS=GeneradorDeReportes.round((this.tipoDeActividadLOGISTICA_DE_PRODUCTOS_Y_RESIDUOS/hcTotal)*100);
        this.porcentajetipoDeActividadELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA=GeneradorDeReportes.round((this.tipoDeActividadELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA/hcTotal)*100);
        this.porcentajetipoDeActividadCOMBUSTION_MOVIL=GeneradorDeReportes.round((this.tipoDeActividadCOMBUSTION_MOVIL/hcTotal)*100);
        this.porcentajetipoDeActividadTRANSPORTE_PUBLICO=GeneradorDeReportes.round((this.tipoDeActividadTRANSPORTE_PUBLICO/hcTotal)*100);
        this.porcentajetipoDeActividadTRANSPORTE_PARTICULAR=GeneradorDeReportes.round((this.tipoDeActividadTRANSPORTE_PARTICULAR/hcTotal)*100);

        this.tipoDeActividadCOMBUSTION_FIJA=GeneradorDeReportes.round(this.tipoDeActividadCOMBUSTION_FIJA);
        this.tipoDeActividadCOMBUSTION_MOVIL=GeneradorDeReportes.round(this.tipoDeActividadCOMBUSTION_MOVIL);
        this.tipoDeActividadELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA=GeneradorDeReportes.round(this.tipoDeActividadELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA);
        this.tipoDeActividadLOGISTICA_DE_PRODUCTOS_Y_RESIDUOS=GeneradorDeReportes.round(this.tipoDeActividadLOGISTICA_DE_PRODUCTOS_Y_RESIDUOS);
        this.tipoDeActividadTRANSPORTE_PUBLICO=GeneradorDeReportes.round(this.tipoDeActividadTRANSPORTE_PUBLICO);
        this.tipoDeActividadTRANSPORTE_PARTICULAR=GeneradorDeReportes.round(this.tipoDeActividadTRANSPORTE_PARTICULAR);
        this.tipoDeActividadSERVICIO_CONTRATADO=GeneradorDeReportes.round(this.tipoDeActividadSERVICIO_CONTRATADO);
        this.tipoDeActividadSERVICIO_ECOLOGICO=GeneradorDeReportes.round(this.tipoDeActividadSERVICIO_ECOLOGICO);

    }





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
