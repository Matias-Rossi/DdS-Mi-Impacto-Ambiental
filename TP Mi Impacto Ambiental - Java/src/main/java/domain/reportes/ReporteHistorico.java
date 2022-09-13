package domain.reportes;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class ReporteHistorico {
    @Getter
    List<Double> factoresDeEmision=new ArrayList<>();
    @Getter
    Periodo mesInicio;
    @Getter
    Periodo mesFin;
    @Getter
    Integer anioInicio;
    @Getter
    Integer anioFin;
    public ReporteHistorico(Periodo mesInicio,Integer anioInicio,Periodo mesFin,Integer anioFin){
        this.mesInicio=mesInicio;
        this.mesFin=mesFin;
        this.anioInicio=anioInicio;
        this.anioFin=anioFin;
        Integer cantMeses = (anioFin-anioInicio)*12+(Periodo.toInteger(mesFin)-Periodo.toInteger(mesInicio));
        for (int i=0;i<cantMeses;i++){
            factoresDeEmision.add(0.0);
        }

    }
    public void agregarFactorDeEmision(Double factorDeEmision,Integer anio,Periodo mes){
        Integer cantMeses = (anio-anioInicio)*12+(Periodo.toInteger(mes)-Periodo.toInteger(mesInicio));
        Double sum = factoresDeEmision.get(cantMeses)+factorDeEmision;
        factoresDeEmision.set(cantMeses,sum);
    }
    public void agregarAnual(Integer anio,Double factorDeEmision){
        Integer cantMeses = (anio-anioInicio)*12;
        for (int i=0;i<12;i++){
            Double sum = factoresDeEmision.get(cantMeses+i)+factorDeEmision/12;
            factoresDeEmision.set(cantMeses+i,sum);
        }
    }

    }
