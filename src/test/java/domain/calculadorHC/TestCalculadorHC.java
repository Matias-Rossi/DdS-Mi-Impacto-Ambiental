package domain.calculadorHC;

//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCalculadorHC {
    /*
  //TODO: Pendiente arreglos con repositorio
    @Test
    @DisplayName("Test Calcular HC")
    public void testCalculadorHC(){
        FactorDeEmision factorDeEmisionTest1 = new FactorDeEmision(TipoActividadDA.TRANSPORTE_PUBLICO, TipoConsumoDA.SUBTE_BASE,0.5);
        FactorDeEmision factorDeEmisionTest2 = new FactorDeEmision(TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, TipoConsumoDA.CAMION_DE_CARGA,0.2);
        DatoDeActividad datoDeActividadTest1 = new DatoDeActividad(TipoActividadDA.TRANSPORTE_PUBLICO, TipoConsumoDA.SUBTE_BASE, 200);
        DatoDeActividad datoDeActividadTest2 = new DatoDeActividad(TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, TipoConsumoDA.CAMION_DE_CARGA, 1000);
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();



        assertTrue(calculadorDeHCTest.calcularHC(factorDeEmisionTest1, 200.0) == 100);
        assertTrue(calculadorDeHCTest.calcularHC(factorDeEmisionTest2, 1000.0) == 200);

    }
    @Test
    @DisplayName("Test Calcular HC De Actividad Generica")
    public void testCalculadorHCActividadGenerica(){
      Importador moduloImportadorTest = new ApachePOI();
      RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
      Provincia BuenosAiresTest = repositorioProvincias.getProvincia(NombreProvincia.Cordoba);
      MunicipiosODepartamentos BragadoTest = new MunicipiosODepartamentos(BuenosAiresTest, "Bragado");
      Clasificacion clasificacion = new Clasificacion("Ministerio");
      Organizacion organizacionEj1 = BragadoTest.crearOrganizacion(moduloImportadorTest, "organizacionEj", ONG, clasificacion, "Cordoba Capital", "C2045", "Andes", 1200);

        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
        FactorDeEmision factorDeEmisionTest = new FactorDeEmision(TipoActividadDA.COMBUSTION_MOVIL, TipoConsumoDA.CARBON_LENIA,0.5);

        ActividadBase unaActividadBaseTest = new ActividadBase(TipoActividadDA.COMBUSTION_MOVIL,TipoConsumoDA.CARBON_LENIA,2022,12,200);
        unaActividadBaseTest.setFactorDeEmision(factorDeEmisionTest);

        double hcResultado = unaActividadBaseTest.calcularHC(2022,12, organizacionEj1);
        System.out.println("500 = " + hcResultado + "?");
        assertTrue(hcResultado == 100);
    }
    @Test
    @DisplayName("Test Calcular HC De Actividad Logistica")
    public void testCalculadorHCActividadLogistica(){
        Importador moduloImportadorTest = new ApachePOI();
        Clasificacion clasificacion = new Clasificacion("Ministerio");
        RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
        Provincia BuenosAiresTest = repositorioProvincias.getProvincia(NombreProvincia.Buenos_Aires);
        MunicipiosODepartamentos BragadoTest = new MunicipiosODepartamentos(BuenosAiresTest, "Bragado");
        Organizacion organizacionEj2 = BragadoTest.crearOrganizacion(moduloImportadorTest, "organizacionEj", ONG, clasificacion, "Cordoba Capital", "C2045", "Andes", 1200);
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();

        FactorDeEmision factorDeEmisionTest = new FactorDeEmision(
            TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS,
            TipoConsumoDA.CAMION_DE_CARGA,
            0.5
        );

        VaraianzaLogistica varaianzaLogisticaTest = new VaraianzaLogistica(0.5);


        ActividadBase unaActividadLogisticaTest = new ActividadBase(
            TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS,
            TipoConsumoDA.CAMION_DE_CARGA,
            2022,10,
            200,
            12,
            varaianzaLogisticaTest,
            TipoProductoTransportado.INSUMOS
        );

        unaActividadLogisticaTest.setFactorDeEmision(factorDeEmisionTest);
        double hcResultado = unaActividadLogisticaTest.calcularHC(2022,10, organizacionEj2);

        assertEquals(600.0, hcResultado);
        //1. TODO: Da 600 en ejecución, revisar si es correcto
        //2. Me da 600 haciendolo a mano, corroborar

    }
    @Test
    @DisplayName("Test Calcular HC De Tramo")
    public void testCalculadorHCTramo() throws IOException {
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
        FactorDeEmision factorDeEmisionTest = new FactorDeEmision(TipoActividadDA.TRANSPORTE_PARTICULAR, TipoConsumoDA.AUTO_GASOIL,0.5);
        SubTipoTransporte subTipoAuto = new SubTipoTransporte(TipoTransporte.TIPO_PARTICULAR, "AUTO");
        Transporte transporteTest = new Particular(subTipoAuto, TipoCombustible.GASOIL, ServicioGeoDds.getInstancia() , 0.5);
        RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
        Provincia BuenosAiresTest = repositorioProvincias.getProvincia(NombreProvincia.Buenos_Aires);
        MunicipiosODepartamentos BragadoTest = new MunicipiosODepartamentos(BuenosAiresTest, "Bragado");
        Importador moduloImportadorTest = new ApachePOI();
        Clasificacion clasificacion = new Clasificacion("Ministerio");
        Organizacion organizacionEj = BragadoTest.crearOrganizacion(moduloImportadorTest, "organizacionEj", ONG, clasificacion, "Cordoba Capital", "C2045", "Andes", 1200);
        Ubicacion ubicacionTest1 = new Ubicacion(
                BragadoTest,
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );
        Ubicacion ubicacionTest2 = new Ubicacion(
                BragadoTest,
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );
        Tramo tramoTest = new Tramo(ubicacionTest1, ubicacionTest2, transporteTest);
        tramoTest.setFactorDeEmision(factorDeEmisionTest);
        assertTrue(tramoTest.calcularHC(1, 6, 2, 6, 3, organizacionEj, 2022) > 0);
    }
    @Test
    @DisplayName("Test Calcular HC organizacion")
    public void testCalculadorHCDatoDeActividad(){
        FactorDeEmision factorDeEmisionTest1 = new FactorDeEmision(TipoActividadDA.TRANSPORTE_PUBLICO, TipoConsumoDA.SUBTE_BASE,0.5);
        FactorDeEmision factorDeEmisionTest2 = new FactorDeEmision(TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, TipoConsumoDA.CAMION_DE_CARGA,0.2);
        DatoDeActividad datoDeActividadTest1 = new DatoDeActividad(TipoActividadDA.TRANSPORTE_PUBLICO, TipoConsumoDA.SUBTE_BASE, 200);
        DatoDeActividad datoDeActividadTest2 = new DatoDeActividad(TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, TipoConsumoDA.CAMION_DE_CARGA, 1000);
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();

        assertTrue(calculadorDeHCTest.calcularHC(factorDeEmisionTest1, 200.0) == 100);
        assertTrue(calculadorDeHCTest.calcularHC(factorDeEmisionTest2, 1000.0) == 200);

    }


    @Test
    @DisplayName("Test Calcular HC Organizacion")
    public void testCalculadorHCOrganizacion(){
        Importador moduloImportadorTest = new ApachePOI();
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
        RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
        Provincia BuenosAiresTest = repositorioProvincias.getProvincia(NombreProvincia.Cordoba);
        MunicipiosODepartamentos BragadoTest = new MunicipiosODepartamentos(BuenosAiresTest, "Bragado");

        Ubicacion ubicacionTest1 = new Ubicacion(
                BragadoTest,
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );
        Clasificacion clasificacionTest = new Clasificacion("ClasificacionTest");
        Organizacion organizacionTest = new Organizacion(moduloImportadorTest,ubicacionTest1,"Organizacion Test",Tipo.EMPRESA,clasificacionTest);
        Area areaTest = organizacionTest.darAltaArea("Area Test");

        RepositorioFactorDeEmision repositorioFdE = new RepositorioFactorDeEmision();
        FactorDeEmision factorDeEmisionTest1 = new FactorDeEmision(TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, TipoConsumoDA.CAMION_DE_CARGA, 0.5);
        FactorDeEmision factorDeEmisionTest2 = new FactorDeEmision(TipoActividadDA.ELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA, TipoConsumoDA.ELECTRICIDAD, 0.5);
        FactorDeEmision factorDeEmisionTest3 = new FactorDeEmision(TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, TipoConsumoDA.CAMION_DE_CARGA, 0.5);
        FactorDeEmision factorDeEmisionTest4 = new FactorDeEmision(TipoActividadDA.COMBUSTION_FIJA, TipoConsumoDA.GAS_NATURAL, 0.5);
        FactorDeEmision factorDeEmisionTest5 = new FactorDeEmision(TipoActividadDA.COMBUSTION_MOVIL, TipoConsumoDA.COMBUSTIBLE_CONSUMIDO_GASOIL, 0.5);
        repositorioFdE.agregar(factorDeEmisionTest1);
        repositorioFdE.agregar(factorDeEmisionTest2);
        repositorioFdE.agregar(factorDeEmisionTest3);
        repositorioFdE.agregar(factorDeEmisionTest4);
        repositorioFdE.agregar(factorDeEmisionTest5);




        organizacionTest.cargarMediciones("src/test/java/domain/importadorExcel/fechas.xlsx");


        assertTrue(organizacionTest.calcularHC(2020,0) > 0);
    }

     //TODO se tienen que tener los Factores de Emision pre cargados para este test, lo cual vence el propósito del unit testing xd

    @Test
    @DisplayName("Test FactorDeEmision")
    public void testFactorDeEmision(){
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
        FactorDeEmision factorDeEmisionTest1 = new FactorDeEmision(TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, TipoConsumoDA.CAMION_DE_CARGA, 0.5);


    }



    @Test
    @DisplayName("Test Calcular HC miembro")
     public void testCalculadorHCMiembro() throws IOException{

        Importador moduloImportadorTest = new ApachePOI();
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
        Clasificacion clasificacion = new Clasificacion("Ministerio");
        RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
        Provincia Cordoba = repositorioProvincias.getProvincia(NombreProvincia.Cordoba);
        MunicipiosODepartamentos municipioEj = Cordoba.crearMunicipio("muniEj");
        Organizacion organizacionEj = municipioEj.crearOrganizacion(moduloImportadorTest, "organizacionEj", ONG, clasificacion, "Cordoba Capital", "C2045", "Andes", 1200);
        Area RRHH = organizacionEj.darAltaArea("RRHH");
        Provincia BuenosAiresTest = repositorioProvincias.getProvincia(NombreProvincia.Buenos_Aires);
        MunicipiosODepartamentos BragadoTest = new MunicipiosODepartamentos(BuenosAiresTest, "Bragado");

        Ubicacion ubicacionCasaJuan = new Ubicacion(
                BragadoTest,
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );
        Miembro Juan = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478,ubicacionCasaJuan, "Juan@123", "juan123" );
        Juan.darseAltaEnOrganizacion(RRHH);

        RRHH.gestionarMiembrosPendientes(Juan.solicitudes.get(0), SolicitudEstado.ACEPTADA);  //miembro 0 es juan?
        List<Organizacion> organizacionesTests = new ArrayList<>();
        organizacionesTests.add(organizacionEj);
        Trayecto trayectoIDA = Juan.generarTrayecto("Ida a organizacionEJ", organizacionesTests, 2022, 2, 20);


        Ubicacion ubicacionSalida = new Ubicacion(
                BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            120
        );
        Ubicacion ubicacionllegada = new Ubicacion(
                BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            121
        );

        FactorDeEmision factorDeEmisionTest = new FactorDeEmision(TipoActividadDA.TRANSPORTE_PARTICULAR, TipoConsumoDA.AUTO_GASOIL, 0.5);


        SubTipoTransporte subTipoAuto = new SubTipoTransporte(TipoTransporte.TIPO_PARTICULAR, "AUTO");
        Transporte autoDeJuan = new Particular(subTipoAuto, TipoCombustible.GASOIL, ServicioGeoDds.getInstancia() , 0.5);

        Tramo tramoTest = trayectoIDA.aniadirNuevoTramo(ubicacionSalida, ubicacionllegada, autoDeJuan);
        tramoTest.setFactorDeEmision(factorDeEmisionTest);

        assertTrue(Juan.calcularHC(2022, 8, organizacionEj) > 0);
        assertTrue( Juan.calcularHC(2022, 1, organizacionEj) == 0.0 );

    }
    @Test
    @DisplayName("Test Calcular HC Porcentual miembro")
    public void testCalculadorHCPorcentualMiembro() throws IOException{

    Importador moduloImportadorTest = new ApachePOI();
    CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
    Clasificacion clasificacion = new Clasificacion("Ministerio");
    RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
    Provincia Cordoba = repositorioProvincias.getProvincia(NombreProvincia.Cordoba);
    MunicipiosODepartamentos municipioEj = Cordoba.crearMunicipio("muniEj");
    Organizacion organizacionEj = municipioEj.crearOrganizacion(moduloImportadorTest, "organizacionEj", ONG, clasificacion, "Cordoba Capital", "C2045", "Andes", 1200);
    Area RRHH = organizacionEj.darAltaArea("RRHH");
    Provincia BuenosAiresTest = repositorioProvincias.getProvincia(NombreProvincia.Buenos_Aires);
        MunicipiosODepartamentos BragadoTest = new MunicipiosODepartamentos(BuenosAiresTest, "Bragado");

        Ubicacion ubicacionCasaJuan = new Ubicacion(
                BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            123
    );
    Miembro Juan = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478,ubicacionCasaJuan, "Juan@123", "juan123" );

    Juan.darseAltaEnOrganizacion(RRHH);
    RRHH.gestionarMiembrosPendientes(Juan.solicitudes.get(0),SolicitudEstado.ACEPTADA);  //miembro 0 es juan?
        List<Organizacion> organizacionesTests = new ArrayList<>();
        organizacionesTests.add(organizacionEj);
    Trayecto trayectoIDA = Juan.generarTrayecto("Ida a organizacionEJ", organizacionesTests, 2022, 2, 20);

    Ubicacion ubicacionSalida = new Ubicacion(
            BragadoTest,
        "Bragado",
        "C1234",
        "calle falsa",
        120
    );
    Ubicacion ubicacionllegada = new Ubicacion(
            BragadoTest,
        "Bragado",
        "C1234",
        "calle falsa",
        121
    );

    FactorDeEmision factorDeEmisionTest = new FactorDeEmision(TipoActividadDA.TRANSPORTE_PARTICULAR, TipoConsumoDA.AUTO_GASOIL, 0.5);

    SubTipoTransporte subTipoAuto = new SubTipoTransporte(TipoTransporte.TIPO_PARTICULAR, "AUTO");
    Transporte autoDeJuan = new Particular(subTipoAuto, TipoCombustible.GASOIL, ServicioGeoDds.getInstancia() , 0.5);
    Tramo tramoTest = trayectoIDA.aniadirNuevoTramo(ubicacionSalida, ubicacionllegada,autoDeJuan);
        tramoTest.setFactorDeEmision(factorDeEmisionTest);

    assertTrue(Juan.calcularHCPorcentual(2022, 8, RRHH) > 0);
    Miembro Juan2 = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478,ubicacionCasaJuan, "Juan@123", "juan123" );

        Juan2.darseAltaEnOrganizacion(RRHH);
        RRHH.gestionarMiembrosPendientes(Juan2.solicitudes.get(0), SolicitudEstado.ACEPTADA);  //miembro 0 es juan?
        List<Organizacion> organizacionesTests2 = new ArrayList<>();
        organizacionesTests2.add(organizacionEj);
        Trayecto trayectoIDA2 = Juan2.generarTrayecto("Ida a organizacionEJ", organizacionesTests2, 2022, 2, 20);

        Ubicacion ubicacionSalida2 = new Ubicacion(
                BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            120
        );
        Ubicacion ubicacionllegada2 = new Ubicacion(
                BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            121
        );


        Tramo tramoTest2 = trayectoIDA2.aniadirNuevoTramo(ubicacionSalida2, ubicacionllegada2,autoDeJuan);
        tramoTest2.setFactorDeEmision(factorDeEmisionTest);
        assertTrue(Juan2.calcularHCPorcentual(2022, 8, RRHH) > 0);
        assertTrue(Juan.calcularHCPorcentual(2022, 8, RRHH) > 0);
        assertTrue(Juan2.calcularHCPorcentual(2022, 8, RRHH) < 100);
        assertTrue(Juan.calcularHCPorcentual(2022, 8, RRHH) < 100);


  }
    @Test
    @DisplayName("Test Calcular HC por Area")
    public void testCalculadorHCPorArea() throws IOException{

        Importador moduloImportadorTest = new ApachePOI();
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
        Clasificacion clasificacion = new Clasificacion("Ministerio");
        RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
        Provincia Cordoba = repositorioProvincias.getProvincia(NombreProvincia.Cordoba);
        MunicipiosODepartamentos municipioEj = Cordoba.crearMunicipio("muniEj");
        Organizacion organizacionEj = municipioEj.crearOrganizacion(moduloImportadorTest, "organizacionEj", ONG, clasificacion, "Cordoba Capital", "C2045", "Andes", 1200);
        Area RRHH = organizacionEj.darAltaArea("RRHH");
        Provincia BuenosAiresTest = repositorioProvincias.getProvincia(NombreProvincia.Buenos_Aires);
        MunicipiosODepartamentos BragadoTest = new MunicipiosODepartamentos(BuenosAiresTest, "Bragado");

        Ubicacion ubicacionCasaJuan = new Ubicacion(
                BragadoTest,
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );
        Miembro Juan = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478,ubicacionCasaJuan, "Juan@123", "juan123" );
        Juan.darseAltaEnOrganizacion(RRHH);
        RRHH.gestionarMiembrosPendientes(Juan.solicitudes.get(0),SolicitudEstado.ACEPTADA);  //miembro 0 es juan?
        List<Organizacion> organizacionesTests = new ArrayList<>();
        organizacionesTests.add(organizacionEj);
        Trayecto trayectoIDA = Juan.generarTrayecto("Ida a organizacionEJ", organizacionesTests, 2022, 2, 20);

        Ubicacion ubicacionSalida = new Ubicacion(
                BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            120
        );
        Ubicacion ubicacionllegada = new Ubicacion(
                BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            121
        );

        FactorDeEmision factorDeEmisionTest = new FactorDeEmision(TipoActividadDA.TRANSPORTE_PARTICULAR, TipoConsumoDA.AUTO_GASOIL, 0.5);

        SubTipoTransporte subTipoAuto = new SubTipoTransporte(TipoTransporte.TIPO_PARTICULAR, "AUTO");
        Transporte autoDeJuan = new Particular(subTipoAuto, TipoCombustible.GASOIL, ServicioGeoDds.getInstancia() , 0.5);
        Tramo tramoTest = trayectoIDA.aniadirNuevoTramo(ubicacionSalida, ubicacionllegada,autoDeJuan);
        tramoTest.setFactorDeEmision(factorDeEmisionTest);

        assertTrue(Juan.calcularHCPorcentual(2022, 8, RRHH) > 0);
        Miembro Juan2 = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478,ubicacionCasaJuan, "Juan@123", "juan123" );
        Juan2.darseAltaEnOrganizacion(RRHH);
        RRHH.gestionarMiembrosPendientes(Juan2.solicitudes.get(0),SolicitudEstado.ACEPTADA);  //miembro 0 es juan?
        List<Organizacion> organizacionesTests2 = new ArrayList<>();
        organizacionesTests2.add(organizacionEj);
        Trayecto trayectoIDA2 = Juan2.generarTrayecto("Ida a organizacionEJ", organizacionesTests2, 2022, 2, 20);

        Ubicacion ubicacionSalida2 = new Ubicacion(
                BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            120
        );
        Ubicacion ubicacionllegada2 = new Ubicacion(
                BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            121
        );


        Tramo tramoTest2 = trayectoIDA2.aniadirNuevoTramo(ubicacionSalida, ubicacionllegada,autoDeJuan);
        tramoTest2.setFactorDeEmision(factorDeEmisionTest);
        assertTrue(RRHH.calcularHC(2022, 8) > 0);
        assertTrue(RRHH.calcularHCporMiembro(2022, 8) > 0);


    }

    @Test
    @DisplayName("Test Calcular HC por Municipio")
    public void testCalculadorHCPorMunicipio() throws IOException{

        Importador moduloImportadorTest = new ApachePOI();
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
        Clasificacion clasificacion = new Clasificacion("Ministerio");
        RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
        Provincia Cordoba = repositorioProvincias.getProvincia(NombreProvincia.Cordoba);
        MunicipiosODepartamentos municipioEj = Cordoba.crearMunicipio("muniEj");
        Organizacion organizacionEj = municipioEj.crearOrganizacion(moduloImportadorTest, "organizacionEj", ONG, clasificacion, "Cordoba Capital", "C2045", "Andes", 1200);
        Area RRHH = organizacionEj.darAltaArea("RRHH");
        Provincia BuenosAiresTest = repositorioProvincias.getProvincia(NombreProvincia.Buenos_Aires);
        MunicipiosODepartamentos BragadoTest = new MunicipiosODepartamentos(BuenosAiresTest, "Bragado");

        Ubicacion ubicacionCasaJuan = new Ubicacion(
                BragadoTest,
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );
        Miembro Juan = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478,ubicacionCasaJuan, "Juan@123", "juan123" );
        Juan.darseAltaEnOrganizacion(RRHH);
        RRHH.gestionarMiembrosPendientes(Juan.solicitudes.get(0),SolicitudEstado.ACEPTADA);  //miembro 0 es juan?
        List<Organizacion> organizacionesTests = new ArrayList<>();
        organizacionesTests.add(organizacionEj);
        Trayecto trayectoIDA = Juan.generarTrayecto("Ida a organizacionEJ", organizacionesTests, 2022, 2, 20);

        Ubicacion ubicacionSalida = new Ubicacion(
                BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            120
        );
        Ubicacion ubicacionllegada = new Ubicacion(
                BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            121
        );

        FactorDeEmision factorDeEmisionTest = new FactorDeEmision(TipoActividadDA.TRANSPORTE_PARTICULAR, TipoConsumoDA.AUTO_GASOIL, 0.5);

        SubTipoTransporte subTipoAuto = new SubTipoTransporte(TipoTransporte.TIPO_PARTICULAR, "AUTO");
        Transporte autoDeJuan = new Particular(subTipoAuto, TipoCombustible.GASOIL, ServicioGeoDds.getInstancia() , 0.5);
        Tramo tramoTest = trayectoIDA.aniadirNuevoTramo(ubicacionSalida, ubicacionllegada, autoDeJuan);
        tramoTest.setFactorDeEmision(factorDeEmisionTest);

        assertTrue(Juan.calcularHCPorcentual(2022, 8, RRHH) > 0);
        Miembro Juan2 = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478,ubicacionCasaJuan, "Juan@123", "juan123" );
        Juan2.darseAltaEnOrganizacion(RRHH);
        RRHH.gestionarMiembrosPendientes(Juan2.solicitudes.get(0),SolicitudEstado.ACEPTADA);  //miembro 0 es juan?
        List<Organizacion> organizacionesTests2 = new ArrayList<>();
        organizacionesTests2.add(organizacionEj);
        Trayecto trayectoIDA2 = Juan2.generarTrayecto("Ida a organizacionEJ", organizacionesTests2, 2022, 2, 20);

        Ubicacion ubicacionSalida2 = new Ubicacion(
                BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            120
        );
        Ubicacion ubicacionllegada2 = new Ubicacion(
                BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            121
        );


        Tramo tramoTest2 = trayectoIDA2.aniadirNuevoTramo(ubicacionSalida, ubicacionllegada,autoDeJuan);
        tramoTest2.setFactorDeEmision(factorDeEmisionTest);
        assertTrue(municipioEj.calcularHC(2022, 8) > 0);



    }
    @Test
    @DisplayName("Test Calcular HC por Provincia")
    public void testCalculadorHCPorProvincia() throws IOException{

        Importador moduloImportadorTest = new ApachePOI();
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
        Clasificacion clasificacion = new Clasificacion("Ministerio");
        RepositorioProvincias repositorioProvincias = new RepositorioProvincias();
        Provincia Cordoba = repositorioProvincias.getProvincia(NombreProvincia.Cordoba);
        MunicipiosODepartamentos municipioEj = Cordoba.crearMunicipio("muniEj");
        Organizacion organizacionEj = municipioEj.crearOrganizacion(moduloImportadorTest, "organizacionEj", ONG, clasificacion, "Cordoba Capital", "C2045", "Andes", 1200);
        Area RRHH = organizacionEj.darAltaArea("RRHH");
        Provincia BuenosAiresTest = repositorioProvincias.getProvincia(NombreProvincia.Buenos_Aires);
        MunicipiosODepartamentos BragadoTest = new MunicipiosODepartamentos(BuenosAiresTest, "Bragado");

        Ubicacion ubicacionCasaJuan = new Ubicacion(
                BragadoTest,
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );
        Miembro Juan = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478,ubicacionCasaJuan, "Juan@123", "juan123" );
        Juan.darseAltaEnOrganizacion(RRHH);
        RRHH.gestionarMiembrosPendientes(Juan.solicitudes.get(0),SolicitudEstado.ACEPTADA);  //miembro 0 es juan?
        List<Organizacion> organizacionesTests = new ArrayList<>();
        organizacionesTests.add(organizacionEj);
        Trayecto trayectoIDA = Juan.generarTrayecto("Ida a organizacionEJ", organizacionesTests, 2022, 2, 20);

        Ubicacion ubicacionSalida = new Ubicacion(
                BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            120
        );
        Ubicacion ubicacionllegada = new Ubicacion(
                BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            121
        );

        FactorDeEmision factorDeEmisionTest = new FactorDeEmision(TipoActividadDA.TRANSPORTE_PARTICULAR, TipoConsumoDA.AUTO_GASOIL, 0.5);

        SubTipoTransporte subTipoAuto = new SubTipoTransporte(TipoTransporte.TIPO_PARTICULAR, "AUTO");
        Transporte autoDeJuan = new Particular(subTipoAuto, TipoCombustible.GASOIL, ServicioGeoDds.getInstancia() , 0.5);
        Tramo tramoTest = trayectoIDA.aniadirNuevoTramo(ubicacionSalida, ubicacionllegada, autoDeJuan);
        tramoTest.setFactorDeEmision(factorDeEmisionTest);

        assertTrue(Juan.calcularHCPorcentual(2022, 8, RRHH) > 0);
        Miembro Juan2 = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478,ubicacionCasaJuan, "Juan@123", "juan123" );
        Juan2.darseAltaEnOrganizacion(RRHH);
        RRHH.gestionarMiembrosPendientes(Juan2.solicitudes.get(0),SolicitudEstado.ACEPTADA);  //miembro 0 es juan?
        List<Organizacion> organizacionesTests2 = new ArrayList<>();
        organizacionesTests2.add(organizacionEj);
        Trayecto trayectoIDA2 = Juan2.generarTrayecto("Ida a organizacionEJ", organizacionesTests2, 2022, 2, 20);

        Ubicacion ubicacionSalida2 = new Ubicacion(
                BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            120
        );
        Ubicacion ubicacionllegada2 = new Ubicacion(
                BragadoTest,
            "Bragado",
            "C1234",
            "calle falsa",
            121
        );


        Tramo tramoTest2 = trayectoIDA2.aniadirNuevoTramo(ubicacionSalida, ubicacionllegada,autoDeJuan);
        tramoTest2.setFactorDeEmision(factorDeEmisionTest);
        assertTrue(Cordoba.calcularHC(2022, 8) > 0);

    }

     
*/
}
