package domain.perfil;

public class Area {
    private String nombre;
    private List<Miembro> miembros;

    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    private Organizacion organizacion;

    public void agregarMiembro(Miembro nuevoMiembro){
        this.miembros.add(nuevoMiembro);
    }
}
