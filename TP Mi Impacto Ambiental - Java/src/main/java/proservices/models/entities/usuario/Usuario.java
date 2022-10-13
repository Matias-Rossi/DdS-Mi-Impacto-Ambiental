package proservices.models.entities.usuario;

import proservices.models.entities.EntidadPersistente;
import proservices.models.entities.perfil.Miembro;
import proservices.models.entities.perfil.Organizacion;
import proservices.models.entities.ubicacion.MunicipiosODepartamentos;
import proservices.models.entities.ubicacion.SectorTerritorial;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario extends EntidadPersistente {

    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    private Rol rol;
    @Column(name = "usuario")
    private String usuario;

    @Column(name = "contrasenia")
    private String contrasenia;

    @ManyToOne
    @JoinColumn(name = "sector_id", referencedColumnName = "id")
    private SectorTerritorial sectorTerritorial;


    public Usuario(Rol rol, String usuario, String contrasenia, Miembro miembro, Organizacion organizacion, MunicipiosODepartamentos municipioODepartamento) {
        this.rol = rol;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        if(rol.esTipo(TipoUsuario.MIEMBRO)){
            new Miembro(this);
        }
        if(rol.esTipo(TipoUsuario.ORGANIZACION)){
            new Organizacion(this);
        }
    }

    public Usuario() {

    }

    public void agregarSector(SectorTerritorial sectorTerritorial){
        this.sectorTerritorial = sectorTerritorial;
    }

    public Rol getRol() {
        return rol;
    }


}


/* #TODO Hasheo contraseña
    public void setHashPassword(String password2) {
        String sha256hex = Hashing.sha256()
                .hashString(password2, StandardCharsets.UTF_8)
                .toString();


        this.password = sha256hex;

    }

    <dependency>
    		<groupId>com.google.guava</groupId>
    		<artifactId>guava</artifactId>
    		<version>31.0.1-jre</version>
		</dependency>

    */