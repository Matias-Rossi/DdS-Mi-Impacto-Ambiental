package domain.perfil;

public class Miembro {
    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private Integer numeroDocumento;
    private List<Area> areas;
    //TODO private list<Troyacto> trayectos;


    public void darseAltaEnOrganizacion(Organizacion nuevaOrg){ //Falta agregar el area, como realaciono el empleado con el area??
        nuevaOrg.solicituAceptacion(this.Miembro);
    }

    public double calcularHC(){
        return 1.0;

    }

    public Organizacion decirOrganizacion(Area area){
        return area.getOrganizacion(); //TODO corregir encapsulamiento

    }
}
