package impacto_ambiental.server;

import impacto_ambiental.models.entities.calculadorHC.FactorDeEmision;
import impacto_ambiental.models.entities.calculadorHC.TipoActividadDA;
import impacto_ambiental.models.entities.calculadorHC.TipoConsumoDA;
import impacto_ambiental.models.entities.notificaciones.Recomendacion;
import impacto_ambiental.models.entities.perfil.*;
import impacto_ambiental.models.entities.servicios.geodds.ServicioGeoDds;
import impacto_ambiental.models.entities.servicios.geodds.entidades.Municipio;
import impacto_ambiental.models.entities.transporte.*;
import impacto_ambiental.models.entities.ubicacion.*;
import impacto_ambiental.models.entities.usuario.*;
import impacto_ambiental.models.repositorios.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class Init {

    static public void main(String[] args) throws IOException {
        inicializarRoles();
        inicializarClasificacion();
        inicializarProvincias();
        crearOrganizacionYMiembro();
        datosParaCrearTrayectos();
        crearAdmin();
        inicializarAgenteSectorial();
        inicializarFactorDeEmision();

        System.out.println(" ##### Inicialización finalizada correctamente ##### ");
        System.exit(0);
    }

    static void crearAdmin() {
        RepositorioRecomendaciones repositorioRecomendaciones = new RepositorioRecomendaciones();
        Recomendacion recomendacion= new Recomendacion("TITULO","SUBTITULO","TEXTO");
        repositorioRecomendaciones.agregar(recomendacion);
        RepositorioRoles repositorioRoles = new RepositorioRoles();
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();

        Rol rolAdmin = repositorioRoles.obtenerRol("ADMINISTRADOR");

        Usuario admin = new Usuario(rolAdmin, "admin", "admin");
        repositorioUsuarios.agregar(admin);
    }

    static void inicializarRoles(){
        RepositorioRoles repo = new RepositorioRoles();

        System.out.println("### Inicializando roles de usuarios ###");

        //Miembro
        Rol rolMiembro = new Rol(TipoUsuario.MIEMBRO);
        Permiso permisoMiembro = new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.MIEMBRO);
        rolMiembro.agregarPermiso(permisoMiembro);
        repo.agregar(rolMiembro);

        //Organizacion
        Rol rolOrganizacion = new Rol(TipoUsuario.ORGANIZACION);
        Permiso permisoOrganizacion = new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ORGANIZACION);
        rolOrganizacion.agregarPermiso(permisoOrganizacion);
        repo.agregar(rolOrganizacion);

        //Agente
        Rol rolAgente = new Rol(TipoUsuario.AGENTE);
        Permiso permisoAgente = new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.MUNICIPIO_O_DEPARTAMENTO);
        rolAgente.agregarPermiso(permisoAgente);
        repo.agregar(rolAgente);

        //Admin
        Rol rolAdministrador = new Rol(TipoUsuario.ADMINISTRADOR);
        Permiso permisoAdmin = new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.ADMIN);
        rolAdministrador.agregarPermiso(permisoAdmin);
        repo.agregar(rolAdministrador);

        System.out.println("Roles de usuarios inicializados");
    }

    static void datosParaCrearTrayectos(){
        SubTipoTransporte pie = new SubTipoTransporte(TipoTransporte.TIPO_ECOLOGICO,"PIE");
        SubTipoTransporte bici = new SubTipoTransporte(TipoTransporte.TIPO_ECOLOGICO,"BICI");
        SubTipoTransporte contratado1 = new SubTipoTransporte(TipoTransporte.TIPO_CONTRATADO,"CONTRATADO1");
        SubTipoTransporte contratado2 = new SubTipoTransporte(TipoTransporte.TIPO_CONTRATADO,"CONTRATADO2");
        SubTipoTransporte particular1 = new SubTipoTransporte(TipoTransporte.TIPO_PARTICULAR,"PARTICULAR1");
        SubTipoTransporte particular2 = new SubTipoTransporte(TipoTransporte.TIPO_PARTICULAR,"PARTICULAR2");
        SubTipoTransporte publico1 = new SubTipoTransporte(TipoTransporte.TIPO_PUBLICO,"PUBLICO1");
        SubTipoTransporte publico2 = new SubTipoTransporte(TipoTransporte.TIPO_PUBLICO,"PUBLICO2");
        SubTipoTransporte subte = new SubTipoTransporte(TipoTransporte.TIPO_PUBLICO, "Subte");
        SubTipoTransporte colectivo = new SubTipoTransporte(TipoTransporte.TIPO_PUBLICO, "Colectivo");

        Transporte tPie = new ServicioEcologico(pie,null,5);
        Transporte tBici = new ServicioEcologico(bici,null,10);

        Transporte tContratado = new ServicioContratado(contratado1,null,20);
        Transporte tParticulargnc = new Particular(particular1,TipoCombustible.GNC,null,30);
        Transporte tParticulargasoil = new Particular(particular1,TipoCombustible.GASOIL,null,30);
        Transporte tParticularnafta = new Particular(particular1,TipoCombustible.NAFTA,null,30);
        Transporte tParticularelectrico = new Particular(particular1,TipoCombustible.ELECTRICO,null,30);

        RepositorioMunicipiosODepartamentos repo = new RepositorioMunicipiosODepartamentos();


        MunicipiosODepartamentos BragadoTest = repo.buscar(18);

        Ubicacion ubicacion1 = new Ubicacion(
                BragadoTest,
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );
        Ubicacion ubicacion2 = new Ubicacion(
                BragadoTest,
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );
        Ubicacion ubicacion3 = new Ubicacion(
                BragadoTest,
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );
        Ubicacion ubicacion4 = new Ubicacion(
                BragadoTest,
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );


        Linea linea1 = new Linea("linea1");
        Linea linea2 =  new Linea("linea2");
        Linea lineaB = new Linea("Linea B");
        Linea lineaC = new Linea("Linea C");
        Linea linea78 = new Linea("78");

        Parada parada1 = new Parada(ubicacion1,1,10,10,linea1);
        Parada parada2 = new Parada(ubicacion2,2,10,10,linea1);
        Parada parada3 = new Parada(ubicacion3,1,10,10,linea2);
        Parada parada4 = new Parada(ubicacion4,2,10,10,linea2);
        Parada lacrozeB = new Parada("Federico Lacroze", ubicacion1, 1, 15, 15, lineaB);
        Parada medrano = new Parada("Medrano", ubicacion2, 2, 15, 15, lineaB);
        Parada constitucion = new Parada("Constitucion", ubicacion3, 1, 50, 50, lineaC);
        Parada retiro = new Parada("Retiro", ubicacion4, 2, 50, 50, lineaC);
        Parada lacroze78 = new Parada("Federico Lacroze", ubicacion1, 1, 15, 15, linea78);
        Parada sl = new Parada("Solano Lopez", ubicacion3, 2, 15, 15, linea78);

        Transporte tPublico1 = new TransportePublico(publico1,linea1, null, 20);
        Transporte tPublico2 = new TransportePublico(publico2,linea2, null, 20);

        Transporte tp_b = new TransportePublico(subte, lineaB, null, 5);
        Transporte tp_c = new TransportePublico(subte, lineaC, null, 5);
        Transporte tp_78 = new TransportePublico(colectivo, linea78, null, 15);


        RepositorioTransportes repoTrans = new RepositorioTransportes();
        repoTrans.agregar(tContratado);
        repoTrans.agregar(tParticulargnc);
        repoTrans.agregar(tParticulargasoil);
        repoTrans.agregar(tParticularnafta);
        repoTrans.agregar(tParticularelectrico);
        repoTrans.agregar(tPie);
        repoTrans.agregar(tBici);
        //repoTrans.agregar(tPublico1);
        //repoTrans.agregar(tPublico2);
        repoTrans.agregar(tp_b);
        repoTrans.agregar(tp_c);
        repoTrans.agregar(tp_78);
    }


    static void inicializarClasificacion() {
        RepositorioClasificacion repositorioClasificacion = new RepositorioClasificacion();

        System.out.println("### Inicializando clasificacion de organizaciones ###");
        Clasificacion escuela = new Clasificacion("Escuela");
        repositorioClasificacion.agregar(escuela);
        Clasificacion ministerio = new Clasificacion("Ministerio");
        repositorioClasificacion.agregar(ministerio);
        Clasificacion universidad = new Clasificacion("Universidad");
        repositorioClasificacion.agregar(universidad);
        Clasificacion primario = new Clasificacion("Sector primario");
        repositorioClasificacion.agregar(primario);
        Clasificacion secundario = new Clasificacion("Sector secundario");
        repositorioClasificacion.agregar(secundario);

        System.out.println("Inicializada clasificación de organizaciones");
        //TODO ¿Hay más?
    }

    static void crearOrganizacionYMiembro() {
        RepositorioRoles repositorioRoles = new RepositorioRoles();
        RepositorioOrganizaciones repositorioOrganizaciones = new RepositorioOrganizaciones();
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
        RepositorioMiembros repositorioMiembros = new RepositorioMiembros();

        MunicipiosODepartamentos Bragado = new RepositorioMunicipiosODepartamentos().buscar(18);
        Ubicacion ubicacion = new Ubicacion(
            Bragado,
            "Bragado",
            "C1234",
            "calle falsa",
            123
        );

        //Miembro en organización
        Rol rolMiembro = repositorioRoles.obtenerRol("MIEMBRO");
        Usuario usuarioMiembro = new Usuario(rolMiembro, "miembro1", "miembro1");
        Miembro miembro = new Miembro("Martin", "Martinez", TipoDocumento.DNI, "12345678", ubicacion, "miembro1", "miembro1", usuarioMiembro);

        //Miembro laSerenisima
        Usuario usuarioMiembroLS = new Usuario(rolMiembro, "miembro3", "miembro3");
        Miembro miembroLS = new Miembro("Ramiro", "Ramirez", TipoDocumento.DNI, "13245678", ubicacion, "miembro3", "miembro3", usuarioMiembroLS);


        //Organizacion
        Rol rolOrganizacion = repositorioRoles.obtenerRol("ORGANIZACION");
        Usuario usuarioOrganizacion = new Usuario(rolOrganizacion, "organizacion1", "organizacion1");
        Clasificacion universidad = new RepositorioClasificacion().buscar(3);
        Organizacion organizacion = new Organizacion(null, ubicacion, "Universidad Tecnologica Nacional", Tipo.INSTITUCION, universidad, usuarioOrganizacion);
        organizacion.darAltaArea("Sistemas");
        Area sistemas = organizacion.getAreas().get(0);
        Solicitud solicitud = miembro.darseAltaEnOrganizacion(sistemas);
        sistemas.gestionarMiembrosPendientes(solicitud, SolicitudEstado.ACEPTADA);


        //Organizacion2
        Usuario usuarioOrganizacion2 = new Usuario(rolOrganizacion, "orgnaizacion2", "organizacion2");
        Clasificacion secundaria = new RepositorioClasificacion().buscar(5);
        Organizacion organizacion2 = new Organizacion(null, ubicacion, "La Serenisima", Tipo.EMPRESA, secundaria, usuarioOrganizacion2);
        organizacion2.darAltaArea("Recursos Humanos");
        organizacion2.darAltaArea("Sistemas");
        Area rrhh = organizacion2.getAreas().get(1);
        Solicitud sol2 = miembroLS.darseAltaEnOrganizacion(rrhh);
        rrhh.gestionarMiembrosPendientes(sol2, SolicitudEstado.ACEPTADA);

        //Miembro fuera de organización
        Usuario usuarioMiembroIndependiente = new Usuario(rolMiembro, "miembro2", "miembro2");
        Miembro miembroIndependiente = new Miembro("Rodrigo", "Rodriguez", TipoDocumento.DNI, "87654321", ubicacion, "miembro2", "miembro2", usuarioMiembroIndependiente);

        //Persistencia
        repositorioOrganizaciones.agregar(organizacion);
        repositorioOrganizaciones.agregar(organizacion2);
        repositorioMiembros.agregar(miembro);
        repositorioMiembros.agregar(miembroIndependiente);
        repositorioMiembros.agregar(miembroLS);

    }

    static void inicializarProvincias() throws IOException{
        System.out.println("### Inicializando provincias y municipios ###");
        try{
            //Obtener provincias de GeoDdS
            ServicioGeoDds geoDdS = ServicioGeoDds.getInstancia();
            List<impacto_ambiental.models.entities.servicios.geodds.entidades.Provincia> provinciasGeoDdS = geoDdS.listadoProvincias();

            //Convertir a provincias "usables"
            List<Provincia> provincias = provinciasGeoDdS.stream().
                    map((_prov) -> {
                        System.out.println("Inicializando provincia " + _prov.getNombre());
                        NombreProvincia nombre = NombreProvincia.toEnum(_prov.getNombre());
                        Provincia nuevaProvincia = new Provincia(nombre); //TODO comprobar funcionamiento con Enum

                        try {
                            List<Municipio> municipios = geoDdS.listadoMunicipios(_prov.getId());
                            municipios.forEach((_mun) -> {
                                System.out.println("Inicializando municipio " + _mun.getNombre());
                                nuevaProvincia.crearMunicipio(_mun.getNombre());
                            });
                        } catch (IOException e) {
                            System.out.println("No se pudo contactar a GeoDDS");
                            e.printStackTrace();
                        }
                        return nuevaProvincia;
            }).collect(Collectors.toList());

            //Agregar al repositorio
            RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
            provincias.forEach((_prov) -> repositorioProvincias.agregar(_prov));

        } catch(IOException e) {
            System.out.println("No se pudo contactar a GeoDDS");
            e.printStackTrace();
        }

        System.out.println("Provincias y municipios inicializados");
    }

    static void inicializarFactorDeEmision() {
        RepositorioFactorDeEmision repositorio = new RepositorioFactorDeEmision();
        //generar lista con todos los TipoActividadDA y TipoConsumoDA

        for (TipoActividadDA tipoActividadDA : TipoActividadDA.values()) {
            for (TipoConsumoDA tipoConsumoDA : TipoConsumoDA.values()) {
                FactorDeEmision factorDeEmision = new FactorDeEmision(tipoActividadDA, tipoConsumoDA, 0.5);
                repositorio.agregar(factorDeEmision);
            }
        }
    }

    static void inicializarAgenteSectorial() {
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
        RepositorioRoles repositorioRoles = new RepositorioRoles();
        MunicipiosODepartamentos municipio = new RepositorioMunicipiosODepartamentos().buscar(18);
        Rol rol = repositorioRoles.obtenerRol("AGENTE");
        Usuario agenteSectorial = new Usuario(rol, "agenteSectorial", "agenteSectorial");
        agenteSectorial.agregarSector(municipio);
        repositorioUsuarios.agregar(agenteSectorial);
    }

}
