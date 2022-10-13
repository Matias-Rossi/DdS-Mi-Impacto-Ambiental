package impacto_ambiental.models.entities.importadorExcel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class VaraianzaLogistica {
    @Getter
    @Setter
    @Column(name = "varianza_logistica")
    double varaianzaLogistica;
    public VaraianzaLogistica(double varaianzaLogistica){
        this.varaianzaLogistica = varaianzaLogistica;

}
    public VaraianzaLogistica() {

    }
}