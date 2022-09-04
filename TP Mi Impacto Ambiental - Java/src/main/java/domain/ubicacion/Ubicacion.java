package domain.ubicacion;

import domain.persistenceExtend.EntidadPersistente;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "ubicaciones")
public class Ubicacion extends EntidadPersistente {
    @Getter
    @Column(name = "calle")
    private String calle;
    @Getter
    @Column(name = "altura")
    private Integer numeracion;
    @Getter
    @Column(name = "codPostal")
    private String codPostal;
    @Getter
    @Enumerated(javax.persistence.EnumType.STRING)
    @Column(name = "provincia")
    private Provincia provincia;
    @Getter
    @Column(name = "municipalidad")
    private String municipalidad;
    @Getter
    @Column(name = "localidad")
    private String localidad;

    public Ubicacion(Provincia prov, String mun, String loc, String cp, String cal, int num) {
        provincia = prov;
        municipalidad = mun;
        localidad = loc;
        codPostal = cp;
        calle = cal;
        numeracion = num;
    }

    public Ubicacion() {

    }
}
