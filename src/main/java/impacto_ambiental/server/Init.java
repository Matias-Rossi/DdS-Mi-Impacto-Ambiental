package impacto_ambiental.server;

import impacto_ambiental.models.entities.perfil.Clasificacion;
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
        //inicializarRoles();
        //inicializarClasificacion();
        //inicializarProvincias();
        //datosParaCrearTrayectos();
        //crearAdmin();
        inicializarAgenteSectorial();
        System.out.println(" ##### Inicialización finalizada correctamente ##### ");
    }

    static void crearAdmin() {
        RepositorioRoles repositorioRoles = new RepositorioRoles();
        RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();

        Rol rolAdmin = repositorioRoles.obtenerRol("ADMINISTRADOR");

        Usuario admin = new Usuario(rolAdmin, "admin", "admin");
        repositorioUsuarios.agregar(admin);
    }

    static void inicializarRoles(){
        RepositorioRoles repo = new RepositorioRoles();

        System.out.println("### Inicializando roles de usuarios ###");
        Rol rol = new Rol(TipoUsuario.MIEMBRO);
        new Permiso(Alcance.PROPIOS, Accion.TOTAL,Objeto.ORGANIZACION,rol);
        repo.agregar(rol);
        Rol rol2 = new Rol(TipoUsuario.ORGANIZACION);
        repo.agregar(rol2);
        Rol rol3 = new Rol(TipoUsuario.AGENTE);
        repo.agregar(rol3);
        Rol rol4 = new Rol(TipoUsuario.ADMINISTRADOR);
        repo.agregar(rol4);

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






        Transporte tPie = new ServicioEcologico(pie,null,5);
        Transporte tBici = new ServicioEcologico(bici,null,10);

        Transporte tContratado = new ServicioContratado(contratado1,null,20);
        Transporte tParticulargnc = new Particular(particular1,TipoCombustible.GNC,null,30);
        Transporte tParticulargasoil = new Particular(particular1,TipoCombustible.GASOIL,null,30);
        Transporte tParticularnafta = new Particular(particular1,TipoCombustible.NAFTA,null,30);
        Transporte tParticularelectrico = new Particular(particular1,TipoCombustible.ELECTRICO,null,30);

        RepositorioMunicipiosODepartamentos repo = new RepositorioMunicipiosODepartamentos();


        MunicipiosODepartamentos BragadoTest = repo.buscar(13);

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

        Parada parada1 = new Parada(ubicacion1,1,10,10,linea1);
        Parada parada2 = new Parada(ubicacion2,2,10,10,linea1);
        Parada parada3 = new Parada(ubicacion3,1,10,10,linea2);
        Parada parada4 = new Parada(ubicacion4,2,10,10,linea2);


        Transporte tPublico1 = new TransportePublico(publico1,linea1, null, 20);
        Transporte tPublico2 = new TransportePublico(publico2,linea2, null, 20);

        RepositorioTransportes repoTrans = new RepositorioTransportes();
        repoTrans.agregar(tContratado);
        repoTrans.agregar(tParticulargnc);
        repoTrans.agregar(tParticulargasoil);
        repoTrans.agregar(tParticularnafta);
        repoTrans.agregar(tParticularelectrico);
        repoTrans.agregar(tPie);
        repoTrans.agregar(tBici);
        repoTrans.agregar(tPublico1);
        repoTrans.agregar(tPublico2);

    }





    static void inicializarClasificacion() {
        RepositorioClasificacion repositorioClasificacion = new RepositorioClasificacion();

        System.out.println("### Inicializando clasificacion de organizaciones ###");
        Clasificacion clasificacion1 = new Clasificacion("Escuela");
        repositorioClasificacion.agregar(clasificacion1);
        Clasificacion clasificacion2 = new Clasificacion("Ministerio");
        repositorioClasificacion.agregar(clasificacion2);
        Clasificacion clasificacion3 = new Clasificacion("Universidad");
        repositorioClasificacion.agregar(clasificacion3);
        System.out.println("Inicializada clasificación de organizaciones");
        //TODO ¿Hay más?
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
