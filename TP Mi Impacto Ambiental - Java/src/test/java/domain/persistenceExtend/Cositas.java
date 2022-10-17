package domain.persistenceExtend;

import impacto_ambiental.models.entities.perfil.*;
import impacto_ambiental.models.entities.ubicacion.MunicipiosODepartamentos;
import impacto_ambiental.models.entities.ubicacion.NombreProvincia;
import impacto_ambiental.models.entities.ubicacion.Provincia;
import impacto_ambiental.models.entities.ubicacion.Ubicacion;
import impacto_ambiental.models.entities.usuario.Rol;
import impacto_ambiental.models.entities.usuario.TipoUsuario;
import impacto_ambiental.models.entities.usuario.Usuario;
import impacto_ambiental.models.repositorios.*;
import org.junit.jupiter.api.Test;

public class Cositas {
    
    @Test
    public void unaProbinciaBSASYMunicipio(){
        RepositorioProvincias repositorioProvincias = new RepositorioProvincias();

        Provincia BuenosAiresTest = new Provincia(NombreProvincia.BUENOS_AIRES);
        MunicipiosODepartamentos BragadoTest = BuenosAiresTest.crearMunicipio("Bragado");
        
        Ubicacion ubicacionTest = new Ubicacion(
                BragadoTest,
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );
        repositorioProvincias.agregar(BuenosAiresTest);
    }

    @Test
    public void miembroConOrganizacionesYAreas() {
        RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();
        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
        RepositorioAreas repositorioAreas = new RepositorioAreas();
        RepositorioRoles repositorioRoles = new RepositorioRoles();
        RepositorioMunicipiosODepartamentos repositorioMunicipiosODepartamentos = new repositorioMunicipios();
        RepositorioClasificacion repositorioClasificacion = new RepositorioClasificacion();

        MunicipiosODepartamentos BragadoTest = repositori
        Ubicacion ubicacionTest = new Ubicacion(
                BragadoTest,
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );
        Clasificacion clasificacion1 = repositorioClasificacion.buscar(1);
        Rol rolOrg = repositorioRoles.obtenerRol("ORGANIZACION");
        Rol rolMiem = repositorioRoles.obtenerRol("MIEMBRO");

        System.out.println(rolOrg.getTipoUsuario());

        Usuario unUsuario = new Usuario(rolOrg,"prueba@123","prueba@456");
        Usuario unUsuarioMIEMBRO = new Usuario(rolMiem,"prueba@789","prueba@456");
        Organizacion organizacionPrueba = new Organizacion(null,ubicacionTest,"PRUEBAORG",Tipo.EMPRESA,clasificacion1,unUsuario);
        //Organizacion organizacionPrueba2 = repositorioOrganizaciones.buscar(2);

        Miembro miembro = new Miembro("prueba","prueba",TipoDocumento.DNI,"38455",ubicacionTest,"qwe@123","qwe",unUsuarioMIEMBRO    );

        Area area1 = organizacionPrueba.darAltaArea("RRHH");
        //Area area2 = organizacionPrueba2.darAltaArea("RRHH");
        Solicitud solicitudAArea1 = miembro.darseAltaEnOrganizacion(area1);
        //Solicitud solicitudAArea2 = miembro.darseAltaEnOrganizacion(area2);
        area1.gestionarMiembrosPendientes(solicitudAArea1,SolicitudEstado.ACEPTADA);
        //area2.gestionarMiembrosPendientes(solicitudAArea2,SolicitudEstado.ACEPTADA);

        repositorioProvincias.agregar(BuenosAiresTest);

        //System.out.println("FASE 1");
        //repositorioAreas.agregar(area1);
        //System.out.println("FASE 2");
        //repositorioAreas.agregar(area2);
        //System.out.println("FASE 3");
       // repositorioOrganizaciones.agregar(organizacionPrueba);
        //System.out.println("FASE 4");
        //repositorioOrganizaciones.agregar(organizacionPrueba2);
        //System.out.println("FASE 5");


        //repositorioMiembros.agregar(miembro);
        //System.out.println("FASE FIN");
    }

}
