package domain.ubicacion;

import domain.persistenceExtend.EntidadPersistente;
import lombok.Getter;

import javax.persistence.*;

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
    private NombreProvincia nombreProvincia;
    @Getter
    @ManyToOne
    @JoinColumn(name = "municipio_id", referencedColumnName = "id")
    private MunicipiosODepartamentos municipalidad;
    @Getter
    @Column(name = "localidad")
    private String localidad;

    public Ubicacion(MunicipiosODepartamentos mun, String loc, String cp, String cal, int num) {
        this.nombreProvincia = mun.getProvincia();
        this.municipalidad = mun;
        this.localidad = loc;
        this.codPostal = cp;
        this.calle = cal;
        this.numeracion = num;
    }

    public Ubicacion() {

    }

}
