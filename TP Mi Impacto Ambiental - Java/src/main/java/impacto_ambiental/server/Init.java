package impacto_ambiental.server;

import impacto_ambiental.models.entities.perfil.Clasificacion;
import impacto_ambiental.models.entities.servicios.geodds.ServicioGeoDds;
import impacto_ambiental.models.entities.servicios.geodds.entidades.Municipio;
import impacto_ambiental.models.entities.ubicacion.NombreProvincia;
import impacto_ambiental.models.entities.usuario.*;
import impacto_ambiental.models.entities.ubicacion.Provincia;
import impacto_ambiental.models.repositorios.RepositorioClasificacion;
import impacto_ambiental.models.repositorios.RepositorioProvincias;
import impacto_ambiental.models.repositorios.RepositorioRoles;

import java.io.IOException;
import java.util.List;

public class Init {
    static public void main(String[] args) throws IOException {
        inicializarRoles();
        inicializarClasificacion();
        inicializarProvincias();
        System.out.println(" ##### Inicialización finalizada correctamente ##### ");
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
            }).toList();

            //Agregar al repositorio
            RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
            provincias.forEach((_prov) -> repositorioProvincias.agregar(_prov));

        } catch(IOException e) {
            System.out.println("No se pudo contactar a GeoDDS");
            e.printStackTrace();
        }

        System.out.println("Provincias y municipios inicializados");
    }


}
