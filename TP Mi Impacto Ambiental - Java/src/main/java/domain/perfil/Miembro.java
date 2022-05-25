package domain.perfil;

public class Miembro {
    private String nombre;
    private String apellido;
    private TipoDocumento tipoDocumento;
    private Integer numeroDocumento;
    private List<Area> areas;
    private domain.perfil.Miembro Miembro; //TODO ver que onda con el Miembro
    //TODO private list<Trayecto> trayectos;
    //TODO private list<Tramos> tramosCompartidosAAceptar;

    public void darseAltaEnOrganizacion(Area area){
        area.agregarAMiembroPendiente(this.Miembro);
    }

    public double calcularHC(){
        //TODO calcularHC
        return 1.0;
    }

    public Organizacion decirOrganizacion(Area area){
        return area.getOrganizacion(); //TODO consultar si es necesario que el miembro diga su organizacion, si lo evitamos mejoramos el encapsulamiento
    }

    //public void gestionarTramosCompartidos(Tramo  tramosCompartido, Trayecto miTrayecto ){
        //TODO gestionarTramosCompartidos
    //}

}
