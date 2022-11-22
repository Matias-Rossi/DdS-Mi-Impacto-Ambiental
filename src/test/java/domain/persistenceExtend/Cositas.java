package domain.persistenceExtend;

import impacto_ambiental.models.entities.calculadorHC.FactorDeEmision;
import impacto_ambiental.models.entities.calculadorHC.TipoActividadDA;
import impacto_ambiental.models.entities.calculadorHC.TipoConsumoDA;
import impacto_ambiental.models.entities.notificaciones.Recomendacion;
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
    public void fdeemision(){
        //Implementado en Init.java
        RepositorioFactorDeEmision repositorio = new RepositorioFactorDeEmision();
        //generar lista con todos los TipoActividadDA y TipoConsumoDA

        for (TipoActividadDA tipoActividadDA : TipoActividadDA.values()) {
            for (TipoConsumoDA tipoConsumoDA : TipoConsumoDA.values()) {
                FactorDeEmision factorDeEmision = new FactorDeEmision(tipoActividadDA, tipoConsumoDA, 0.5);
                repositorio.agregar(factorDeEmision);
            }
        }
    }

    @Test
    public void cargarReco(){
        RepositorioRecomendaciones repositorioRecomendaciones = new RepositorioRecomendaciones();
        Recomendacion recomendacion= new Recomendacion("TITULO","SUBTITULO","TEXTO");
        repositorioRecomendaciones.agregar(recomendacion);
    }


    @Test
    public void miembroConOrganizacionesYAreas() {
        //Implementado en Init.java
        RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();
        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();
        RepositorioAreas repositorioAreas = new RepositorioAreas();
        RepositorioRoles repositorioRoles = new RepositorioRoles();
        RepositorioMunicipiosODepartamentos repositorioMunicipiosODepartamentos = new RepositorioMunicipiosODepartamentos();
        RepositorioClasificacion repositorioClasificacion = new RepositorioClasificacion();

        MunicipiosODepartamentos BragadoTest = repositorioMunicipiosODepartamentos.buscar(18); //id 18 bragado
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

        Miembro miembro = new Miembro("Ricardo","Fort",TipoDocumento.DNI,"38455",ubicacionTest,"qwe@123","qwe",unUsuarioMIEMBRO);

        Usuario unUsuario2 = new Usuario(rolMiem,"qwe@234","prueba@456");
        Miembro miembro2 = new Miembro("Tom","Soiffer",TipoDocumento.DNI,"3845455",ubicacionTest,"qwe@234","qwe",unUsuario2);


        Area area1 = organizacionPrueba.darAltaArea("RRHH");
        //Area area2 = organizacionPrueba2.darAltaArea("RRHH");
        Solicitud solicitudAArea1 = miembro.darseAltaEnOrganizacion(area1);
        Solicitud solicitud = miembro2.darseAltaEnOrganizacion(area1);
        //Solicitud solicitudAArea2 = miembro.darseAltaEnOrganizacion(area2);
        area1.gestionarMiembrosPendientes(solicitudAArea1,SolicitudEstado.ACEPTADA);
        //area2.gestionarMiembrosPendientes(solicitudAArea2,SolicitudEstado.ACEPTADA);

        repositorioOrganizaciones.agregar(organizacionPrueba);

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
