package impacto_ambiental.models.entities.usuario;

import impacto_ambiental.models.entities.EntidadPersistente;
import impacto_ambiental.models.entities.perfil.*;
import impacto_ambiental.models.entities.ubicacion.MunicipiosODepartamentos;
import impacto_ambiental.models.entities.ubicacion.SectorTerritorial;
import impacto_ambiental.models.entities.ubicacion.Ubicacion;
import lombok.Getter;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name = "usuarios")
public class Usuario extends EntidadPersistente {

    @ManyToOne @Getter
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    private Rol rol;
    @Getter
    @Column(name = "usuario")
    public String usuario;

    @Column(name = "contrasenia")
    private String contrasenia;

    @ManyToOne
    @JoinColumn(name = "sectorSolicitado_id", referencedColumnName = "id")
    private SectorTerritorial sectorTerritorialSolicitado;

    @ManyToOne
    @JoinColumn(name = "sectorActual_id", referencedColumnName = "id")
    @Getter
    private SectorTerritorial sectorTerritorial;


    public Usuario(Rol rol, String email, String contrasenia) {
        this.rol = rol;
        this.usuario = email;
        this.contrasenia = contrasenia;
        //TODO ?
    }

    public Usuario() {

    }

    public void solicitarSector(SectorTerritorial sectorTerritorial) {
        this.sectorTerritorialSolicitado = sectorTerritorial;
        sectorTerritorial.solicitarUnirse(this);
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