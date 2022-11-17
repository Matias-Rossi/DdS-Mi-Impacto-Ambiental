package domain.context;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHibernate {
    /*
    private RepositorioProvincias repositorioProvincias = new RepositorioProvincias();

    @Test
    public void hidratarFactorDeEmision(){
        //Carga a db
        RepositorioFactorDeEmision repositorio = new RepositorioFactorDeEmision();
        FactorDeEmision saliente = new FactorDeEmision(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL, 0.5);
        repositorio.agregar(saliente);

        //Fetch de db
        FactorDeEmision entrante = repositorio.buscar(saliente.getId());
        assertEquals(0.5, entrante.getFactorEmision());
    }

    @Test
    public void pruebasHidratacion() throws IOException {
        Provincia bsAs = repositorioProvincias.getProvincia(NombreProvincia.Buenos_Aires);
        MunicipiosODepartamentos chivilcoy = bsAs.crearMunicipio("Chivilcoy");
        Clasificacion clasificacion = new Clasificacion("clasificacion");
        Organizacion organizacion = chivilcoy.crearOrganizacion(ApachePOI.getInstance(),"razon",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Area area = organizacion.darAltaArea("area");
        Ubicacion ubicacion = new Ubicacion(chivilcoy,"loc", "cp", "cal", 1);
        Miembro miembro = new Miembro("nombre",  "apellido", TipoDocumento.DNI, 12345678,ubicacion,"mail","pass");
        Solicitud solicitud = miembro.darseAltaEnOrganizacion(area);
        area.gestionarMiembrosPendientes(solicitud, SolicitudEstado.ACEPTADA);
        List<Organizacion> organizacions = new ArrayList<Organizacion>();
        organizacions.add(organizacion);
        Trayecto trayecto = miembro.generarTrayecto("descripcion", organizacions, 2022, 1, 28);
        Ubicacion llegada = new Ubicacion(chivilcoy,"Chivilcoy", "231", "man", 65);
        Ubicacion salida = new Ubicacion(chivilcoy, "Chivilcoy", "532", "bol", 23);
        SubTipoTransporte subtipo = new SubTipoTransporte(TipoTransporte.TIPO_PARTICULAR,"subtipo1");
        Particular particular = new Particular(subtipo, TipoCombustible.NAFTA, ServicioGeoDds.getInstancia(),0.5);
        Tramo tramo = trayecto.aniadirNuevoTramo(salida,llegada,particular);
        repositorioProvincias.actualizar(bsAs); //Cambiado de persist
    }
    @Test
    public void traerHidratacion(){
        Provincia bsas = repositorioProvincias.getProvincia(NombreProvincia.Buenos_Aires);
        RepositorioMunicipiosODepartamentos repositorioMunicipiosODepartamentos = new RepositorioMunicipiosODepartamentos();
        MunicipiosODepartamentos saliente = repositorioMunicipiosODepartamentos.getMunicipio(bsas, "Chivilcoy");
        repositorioMunicipiosODepartamentos.actualizar(saliente);
        MunicipiosODepartamentos entrante = repositorioMunicipiosODepartamentos.getMunicipioDesdeNombre("Chivilcoy");
        assertEquals("Buenos Aires",entrante.getProvincia().toString());
    }

<<<<<<< HEAD
        Reportes primero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2022, Periodo.Abril,1.0,organizacionb);
        Reportes segundo = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacionb);
        EntityManagerHelper.persist(primero);
        EntityManagerHelper.persist(segundo);
        assertEquals(2.0,GeneradorDeReportes.getInstance().hCTotalPorTipoDeOrganizacion(clasificacion));
    }
    @Test
    public void reportesHcTotalSectorTerritorial(){
        Provincias prova = new Provincias(Provincia.Jujuy);
        MunicipiosODepartamentos vdp = prova.crearMunicipio("vdp");
        MunicipiosODepartamentos jojo = prova.crearMunicipio("lrm");
        Clasificacion clasificacion = new Clasificacion("clasificacion");
        Organizacion organizacion = vdp.crearOrganizacion(ApachePOI.getInstance(),"razonSocialisima",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion2 = vdp.crearOrganizacion(ApachePOI.getInstance(),"pedro",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion3 = jojo.crearOrganizacion(ApachePOI.getInstance(),"papa",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        EntityManagerHelper.persist(prova);

        Reportes primero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2022, Periodo.Abril,1.0,organizacion);
        Reportes segundo = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion2);
        Reportes tercero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion3);
        EntityManagerHelper.persist(primero);
        EntityManagerHelper.persist(segundo);
        EntityManagerHelper.persist(tercero);
        assertEquals(2.0,GeneradorDeReportes.getInstance().hCTotalPorSectorTerriorial(vdp));
    }
    @Test
    public void reportesCompSectorTerr(){
        Provincias prova = new Provincias(Provincia.Jujuy);
        MunicipiosODepartamentos vdp = prova.crearMunicipio("vdp");
        MunicipiosODepartamentos jojo = prova.crearMunicipio("lrm");
        Clasificacion clasificacion = new Clasificacion("clasificacion");
        Organizacion organizacion = vdp.crearOrganizacion(ApachePOI.getInstance(),"razonSocialisima",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion2 = vdp.crearOrganizacion(ApachePOI.getInstance(),"pedro",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion3 = jojo.crearOrganizacion(ApachePOI.getInstance(),"papa",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        EntityManagerHelper.persist(prova);

        Reportes primero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2022, Periodo.Abril,1.0,organizacion);
        Reportes segundo = new Reportes(TipoActividadDA.COMBUSTION_MOVIL,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion2);
        Reportes tercero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion);
        EntityManagerHelper.persist(primero);
        EntityManagerHelper.persist(segundo);
        EntityManagerHelper.persist(tercero);
        assertEquals(2.0,GeneradorDeReportes.getInstance().composicionDeaHCTotalDeUnDeterminadoSectorTerritorial(vdp).getTipoDeActividadCOMBUSTION_FIJA());
    }
    @Test
    public void reportesCompPais(){
        Provincias prova = new Provincias(Provincia.Jujuy);
        Provincias tucu = new Provincias(Provincia.Tucuman);
        Provincias salta = new Provincias(Provincia.Salta);
        Provincias mendoza = new Provincias(Provincia.Mendoza);
        MunicipiosODepartamentos vdp = prova.crearMunicipio("vdp");
        MunicipiosODepartamentos jojo = prova.crearMunicipio("lrm");
        MunicipiosODepartamentos tucuMuni = tucu.crearMunicipio("das");
        MunicipiosODepartamentos saltaMuni = salta.crearMunicipio("asdgad");
        MunicipiosODepartamentos mendoMuni = mendoza.crearMunicipio("dfd");
        Clasificacion clasificacion = new Clasificacion("clasificacion");
        Organizacion organizacion = vdp.crearOrganizacion(ApachePOI.getInstance(),"razonSocialisima",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion2 = vdp.crearOrganizacion(ApachePOI.getInstance(),"pedro",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion3 = jojo.crearOrganizacion(ApachePOI.getInstance(),"papa",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion4 = tucuMuni.crearOrganizacion(ApachePOI.getInstance(),"gdsv",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion5 = saltaMuni.crearOrganizacion(ApachePOI.getInstance(),"rhwrh",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion6 = mendoMuni.crearOrganizacion(ApachePOI.getInstance(),"sfgfb",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);

        EntityManagerHelper.persist(prova);
        EntityManagerHelper.persist(tucu);
        EntityManagerHelper.persist(salta);
        EntityManagerHelper.persist(mendoza);

        Reportes primero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2022, Periodo.Abril,1.0,organizacion);
        Reportes segundo = new Reportes(TipoActividadDA.COMBUSTION_MOVIL,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion2);
        Reportes tercero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion);
        Reportes cuarto = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2022, Periodo.Abril,1.0,organizacion3);
        Reportes quinto = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion4);
        Reportes sexto = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion5);
        Reportes septimo = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion6);
        EntityManagerHelper.persist(primero);
        EntityManagerHelper.persist(segundo);
        EntityManagerHelper.persist(tercero);
        EntityManagerHelper.persist(cuarto);
        EntityManagerHelper.persist(quinto);
        EntityManagerHelper.persist(sexto);
        EntityManagerHelper.persist(septimo);
        List<Provincias> provincias = new ArrayList<>();
        provincias.add(prova);
        provincias.add(tucu);
        assertEquals(4.0,GeneradorDeReportes.getInstance().composicionDeaHCTotalANivelPais(provincias).getTipoDeActividadCOMBUSTION_FIJA());
    }
    @Test
    public void reportesCompSectorOrg(){
        Provincias prova = new Provincias(Provincia.Jujuy);
        MunicipiosODepartamentos vdp = prova.crearMunicipio("vdp");
        MunicipiosODepartamentos jojo = prova.crearMunicipio("lrm");
        Clasificacion clasificacion = new Clasificacion("clasificacion");
        Organizacion organizacion = vdp.crearOrganizacion(ApachePOI.getInstance(),"razonSocialisima",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion2 = vdp.crearOrganizacion(ApachePOI.getInstance(),"pedro",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion3 = jojo.crearOrganizacion(ApachePOI.getInstance(),"papa",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        EntityManagerHelper.persist(prova);

        Reportes primero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2022, Periodo.Abril,1.0,organizacion);
        Reportes segundo = new Reportes(TipoActividadDA.COMBUSTION_MOVIL,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion);
        Reportes tercero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion);
        EntityManagerHelper.persist(primero);
        EntityManagerHelper.persist(segundo);
        EntityManagerHelper.persist(tercero);
        assertEquals(2.0,GeneradorDeReportes.getInstance().composicionDeHCDeUnaOrganizacion(organizacion).getTipoDeActividadCOMBUSTION_FIJA());
    }
    @Test
    public void reportesHistoricos(){
        Provincias prova = new Provincias(Provincia.Jujuy);
        MunicipiosODepartamentos vdp = prova.crearMunicipio("vdp");
        Clasificacion clasificacion = new Clasificacion("clasificacion");
        Organizacion organizacion = vdp.crearOrganizacion(ApachePOI.getInstance(),"razonSocialisima",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        Organizacion organizacion2 = vdp.crearOrganizacion(ApachePOI.getInstance(),"pedro",Tipo.INSTITUCION,clasificacion,"loc","cp","cal",1);
        EntityManagerHelper.persist(prova);

        Reportes primero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2022, Periodo.Abril,1.0,organizacion);
        Reportes segundo = new Reportes(TipoActividadDA.COMBUSTION_MOVIL,TipoConsumoDA.GAS_NATURAL,2020, Periodo.Marzo,1.0,organizacion);
        Reportes tercero = new Reportes(TipoActividadDA.COMBUSTION_FIJA,TipoConsumoDA.GAS_NATURAL,2021, Periodo.Marzo,1.0,organizacion);
        EntityManagerHelper.persist(primero);
        EntityManagerHelper.persist(segundo);
        EntityManagerHelper.persist(tercero);
        assertEquals(2022,GeneradorDeReportes.getInstance().EvolucionDeHCTotalDeUnDeterminadoSectorTerritorial(vdp).getAnioFin());
    }
=======
>>>>>>> dfe3fc8179e05180af67ed24bf315b1053bdd45d

     */
}
