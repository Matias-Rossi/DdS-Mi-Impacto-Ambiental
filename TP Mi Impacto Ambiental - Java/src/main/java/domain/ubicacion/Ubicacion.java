package domain.ubicacion;

import lombok.Getter;

public class Ubicacion {
    @Getter
    private String calle;
    @Getter
    private Integer numeracion;
    @Getter
    private String codPostal;
    @Getter
    private Provincia provincia;
    @Getter
    private String municipalidad;
    @Getter
    private String localidad;

    public Ubicacion(Provincia prov, String mun, String loc, String cp, String cal, int num) {
        provincia = prov;
        municipalidad = mun;
        localidad = loc;
        codPostal = cp;
        calle = cal;
        numeracion = num;
    }
}
