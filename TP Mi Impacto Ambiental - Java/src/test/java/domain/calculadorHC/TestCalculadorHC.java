package domain.calculadorHC;

import domain.ubicacion.MunicipiosODepartamentos;
import domain.importadorExcel.*;
import domain.perfil.*;
import domain.servicios.geodds.ServicioGeoDds;
import domain.transporte.Particular;
import domain.transporte.TipoCombustible;
import domain.transporte.TipoParticular;
import domain.transporte.Transporte;
import domain.trayecto.Tramo;
import domain.trayecto.Trayecto;
import domain.ubicacion.Provincia;
import domain.ubicacion.Provincias;
import domain.ubicacion.Ubicacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static domain.perfil.Tipo.ONG;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCalculadorHC {
/*
    @Test
    @DisplayName("Test Calcular HC")
    public void testCalculadorHC(){
        FactorDeEmision factorDeEmisionTest1 = new FactorDeEmision(TipoActividadDA.TRANSPORTE_PUBLICO, TipoConsumoDA.SUBTE_BASE,0.5);
        FactorDeEmision factorDeEmisionTest2 = new FactorDeEmision(TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, TipoConsumoDA.CAMION_DE_CARGA,0.2);
        DatoDeActividad datoDeActividadTest1 = new DatoDeActividad(TipoActividadDA.TRANSPORTE_PUBLICO, TipoConsumoDA.SUBTE_BASE, 200);
        DatoDeActividad datoDeActividadTest2 = new DatoDeActividad(TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, TipoConsumoDA.CAMION_DE_CARGA, 1000);
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();

        calculadorDeHCTest.agregarFactorDeEmision(factorDeEmisionTest1);
        calculadorDeHCTest.agregarFactorDeEmision(factorDeEmisionTest2);

        assertTrue(calculadorDeHCTest.calcularHC(datoDeActividadTest1) == 100);
        assertTrue(calculadorDeHCTest.calcularHC(datoDeActividadTest2) == 200);

    }
    @Test
    @DisplayName("Test Calcular HC De Actividad Generica")
    public void testCalculadorHCActividadGenerica(){
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
        FactorDeEmision factorDeEmisionTest = new FactorDeEmision(TipoActividadDA.COMBUSTION_MOVIL, TipoConsumoDA.CARBON_LENIA,0.5);
        calculadorDeHCTest.agregarFactorDeEmision(factorDeEmisionTest);
        ActividadGenericaCargada unaActivadGenericaTest = new ActividadGenericaCargada(calculadorDeHCTest, TipoActividadDA.COMBUSTION_MOVIL,TipoConsumoDA.CARBON_LENIA,200,2022,12);

        assertTrue(unaActivadGenericaTest.calcularHC(2022,12) == 100);
    }
    @Test
    @DisplayName("Test Calcular HC De Actividad Logistica")
    public void testCalculadorHCActividadLogistica(){
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
        FactorDeEmision factorDeEmisionTest = new FactorDeEmision(TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, TipoConsumoDA.CAMION_DE_CARGA,0.5);
        calculadorDeHCTest.agregarFactorDeEmision(factorDeEmisionTest);
        VaraianzaLogistica varaianzaLogisticaTest = new VaraianzaLogistica(0.5);
        ActividadLogisticaCargada unaActivadLogisticaTest = new ActividadLogisticaCargada(calculadorDeHCTest, TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, TipoProductoTransportado.INSUMOS ,TipoConsumoDA.CAMION_DE_CARGA, 200,10,2022,12,varaianzaLogisticaTest);
        assertTrue(unaActivadLogisticaTest.calcularHC(2022,12) == 500);
    }
    @Test
    @DisplayName("Test Calcular HC De Tramo")
    public void testCalculadorHCTramo() throws IOException {
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
        FactorDeEmision factorDeEmisionTest = new FactorDeEmision(TipoActividadDA.TRANSPORTE_PARTICULAR, TipoConsumoDA.AUTO_GASOIL,0.5);
        calculadorDeHCTest.agregarFactorDeEmision(factorDeEmisionTest);
        Transporte transporteTest = new Particular(TipoParticular.AUTO, TipoCombustible.GASOIL, ServicioGeoDds.getInstancia() , 0.5);
        Ubicacion ubicacionTest1 = new Ubicacion(
                domain.ubicacion.Provincia.Buenos_Aires,
                "Bragado",
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );
        Ubicacion ubicacionTest2 = new Ubicacion(
                domain.ubicacion.Provincia.Buenos_Aires,
                "Bragado",
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );
        Tramo tramoTest = new Tramo(ubicacionTest1,ubicacionTest2,transporteTest,calculadorDeHCTest);
        
        assertTrue(tramoTest.calcularHC() > 0);
    }
    @Test
    @DisplayName("Test Calcular HC organizacion")
    public void testCalculadorHCDatoDeActividad(){
        FactorDeEmision factorDeEmisionTest1 = new FactorDeEmision(TipoActividadDA.TRANSPORTE_PUBLICO, TipoConsumoDA.SUBTE_BASE,0.5);
        FactorDeEmision factorDeEmisionTest2 = new FactorDeEmision(TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, TipoConsumoDA.CAMION_DE_CARGA,0.2);
        DatoDeActividad datoDeActividadTest1 = new DatoDeActividad(TipoActividadDA.TRANSPORTE_PUBLICO, TipoConsumoDA.SUBTE_BASE, 200);
        DatoDeActividad datoDeActividadTest2 = new DatoDeActividad(TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, TipoConsumoDA.CAMION_DE_CARGA, 1000);
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();



        calculadorDeHCTest.agregarFactorDeEmision(factorDeEmisionTest1);
        calculadorDeHCTest.agregarFactorDeEmision(factorDeEmisionTest2);

        assertTrue(calculadorDeHCTest.calcularHC(datoDeActividadTest1) == 100);
        assertTrue(calculadorDeHCTest.calcularHC(datoDeActividadTest2) == 200);

    }
    @Test
    @DisplayName("Test Calcular HC Organizacion")
    public void testCalculadorHCOrganizacion(){

        Importador moduloImportadorTest = new ApachePOI();
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
        Ubicacion ubicacionTest1 = new Ubicacion(
                domain.ubicacion.Provincia.Buenos_Aires,
                "Bragado",
                "Bragado",
                "C1234",
                "calle falsa",
                123
        );
        Clasificacion clasificacionTest = new Clasificacion("ClasificacionTest");
        Organizacion organizacionTest = new Organizacion(moduloImportadorTest,ubicacionTest1,"Organizacion Test",Tipo.EMPRESA,clasificacionTest);
        Area areaTest = organizacionTest.darAltaArea("Area Test");
        FactorDeEmision factorDeEmisionTest1 = new FactorDeEmision(TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, TipoConsumoDA.CAMION_DE_CARGA, 0.5);
        FactorDeEmision factorDeEmisionTest2 = new FactorDeEmision(TipoActividadDA.ELECTRICIDAD_ADQUIRIDA_Y_CONSUMIDA, TipoConsumoDA.ELECTRICIDAD, 0.5);
        FactorDeEmision factorDeEmisionTest3 = new FactorDeEmision(TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, TipoConsumoDA.CAMION_DE_CARGA, 0.5);
        FactorDeEmision factorDeEmisionTest4 = new FactorDeEmision(TipoActividadDA.COMBUSTION_FIJA, TipoConsumoDA.GAS_NATURAL, 0.5);
        FactorDeEmision factorDeEmisionTest5 = new FactorDeEmision(TipoActividadDA.COMBUSTION_MOVIL, TipoConsumoDA.COMBUSTIBLE_CONSUMIDO_GASOIL, 0.5);

        calculadorDeHCTest.agregarFactorDeEmision(factorDeEmisionTest1);
        calculadorDeHCTest.agregarFactorDeEmision(factorDeEmisionTest2);
        calculadorDeHCTest.agregarFactorDeEmision(factorDeEmisionTest3);

        organizacionTest.cargarMediciones("src/test/java/domain/importadorExcel/fechas.xlsx", calculadorDeHCTest);


        assertTrue(organizacionTest.calcularHC(2020,0) > 0);
    }

    @Test
    @DisplayName("Test FactorDeEmision")
    public void testFactorDeEmision(){
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
        FactorDeEmision factorDeEmisionTest1 = new FactorDeEmision(TipoActividadDA.LOGISTICA_DE_PRODUCTOS_Y_RESIDUOS, TipoConsumoDA.CAMION_DE_CARGA, 0.5);
        calculadorDeHCTest.agregarFactorDeEmision(factorDeEmisionTest1);

    }



    @Test
    @DisplayName("Test Calcular HC miembro")
     public void testCalculadorHCMiembro() throws IOException{

        Importador moduloImportadorTest = new ApachePOI();
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
        Clasificacion clasificacion = new Clasificacion("Ministerio");
        Provincias Cordoba = new Provincias(Provincia.Cordoba);
        MunicipiosODepartamentos municipioEj = Cordoba.crearMunicipio("muniEj");
        Organizacion organizacionEj = municipioEj.crearOrganizacion(moduloImportadorTest, "organizacionEj", ONG, clasificacion, "Cordoba Capital", "C2045", "Andes", 1200);
        Area RRHH = organizacionEj.darAltaArea("RRHH");
        Miembro Juan = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478 );
        Juan.darseAltaEnOrganizacion(RRHH);
        RRHH.gestionarMiembrosPendientes(0,true);  //miembro 0 es juan?
        List<Integer> indices = new ArrayList<>();
        indices.add(0);
        Trayecto trayectoIDA = Juan.generarTrayecto("Ida a organizacionEJ", indices, 2022, 2, 20);

        Ubicacion ubicacionSalida = new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            120
        );
        Ubicacion ubicacionllegada = new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            121
        );

        FactorDeEmision factorDeEmisionTest = new FactorDeEmision(TipoActividadDA.TRANSPORTE_PARTICULAR, TipoConsumoDA.AUTO_GASOIL, 0.5);
        calculadorDeHCTest.agregarFactorDeEmision(factorDeEmisionTest);

        Transporte autoDeJuan = new Particular(TipoParticular.AUTO, TipoCombustible.GASOIL, ServicioGeoDds.getInstancia(), 0.5);
        Tramo tramoTest = trayectoIDA.aniadirNuevoTramo(ubicacionSalida, ubicacionllegada,autoDeJuan,calculadorDeHCTest);


        assertTrue(Juan.calcularHC(2022, 8, organizacionEj) > 0);
        assertTrue( Juan.calcularHC(2022, 1, organizacionEj) == 0.0 );

    }
    @Test
    @DisplayName("Test Calcular HC Porcentual miembro")
    public void testCalculadorHCPorcentualMiembro() throws IOException{

    Importador moduloImportadorTest = new ApachePOI();
    CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
    Clasificacion clasificacion = new Clasificacion("Ministerio");
    Provincias Cordoba = new Provincias(Provincia.Cordoba);
    MunicipiosODepartamentos municipioEj = Cordoba.crearMunicipio("muniEj");
    Organizacion organizacionEj = municipioEj.crearOrganizacion(moduloImportadorTest, "organizacionEj", ONG, clasificacion, "Cordoba Capital", "C2045", "Andes", 1200);
    Area RRHH = organizacionEj.darAltaArea("RRHH");
    Miembro Juan = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478 );
    Juan.darseAltaEnOrganizacion(RRHH);
    RRHH.gestionarMiembrosPendientes(0,true);  //miembro 0 es juan?
    List<Integer> indices = new ArrayList<>();
    indices.add(0);
    Trayecto trayectoIDA = Juan.generarTrayecto("Ida a organizacionEJ", indices, 2022, 2, 20);

    Ubicacion ubicacionSalida = new Ubicacion(
        domain.ubicacion.Provincia.Buenos_Aires,
        "Bragado",
        "Bragado",
        "C1234",
        "calle falsa",
        120
    );
    Ubicacion ubicacionllegada = new Ubicacion(
        domain.ubicacion.Provincia.Buenos_Aires,
        "Bragado",
        "Bragado",
        "C1234",
        "calle falsa",
        121
    );

    FactorDeEmision factorDeEmisionTest = new FactorDeEmision(TipoActividadDA.TRANSPORTE_PARTICULAR, TipoConsumoDA.AUTO_GASOIL, 0.5);
    calculadorDeHCTest.agregarFactorDeEmision(factorDeEmisionTest);

    Transporte autoDeJuan = new Particular(TipoParticular.AUTO, TipoCombustible.GASOIL, ServicioGeoDds.getInstancia(), 0.5);
    Tramo tramoTest = trayectoIDA.aniadirNuevoTramo(ubicacionSalida, ubicacionllegada,autoDeJuan,calculadorDeHCTest);


    assertTrue(Juan.calcularHCPorcentual(2022, 8, RRHH) > 0);
    Miembro Juan2 = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478 );
        Juan2.darseAltaEnOrganizacion(RRHH);
        RRHH.gestionarMiembrosPendientes(0,true);  //miembro 0 es juan?
        List<Integer> indices2 = new ArrayList<>();
        indices2.add(0);
        Trayecto trayectoIDA2 = Juan2.generarTrayecto("Ida a organizacionEJ", indices, 2022, 2, 20);

        Ubicacion ubicacionSalida2 = new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            120
        );
        Ubicacion ubicacionllegada2 = new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            121
        );


        Tramo tramoTest2 = trayectoIDA2.aniadirNuevoTramo(ubicacionSalida, ubicacionllegada,autoDeJuan,calculadorDeHCTest);

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
        Provincias Cordoba = new Provincias(Provincia.Cordoba);
        MunicipiosODepartamentos municipioEj = Cordoba.crearMunicipio("muniEj");
        Organizacion organizacionEj = municipioEj.crearOrganizacion(moduloImportadorTest, "organizacionEj", ONG, clasificacion, "Cordoba Capital", "C2045", "Andes", 1200);
        Area RRHH = organizacionEj.darAltaArea("RRHH");
        Miembro Juan = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478 );
        Juan.darseAltaEnOrganizacion(RRHH);
        RRHH.gestionarMiembrosPendientes(0,true);  //miembro 0 es juan?
        List<Integer> indices = new ArrayList<>();
        indices.add(0);
        Trayecto trayectoIDA = Juan.generarTrayecto("Ida a organizacionEJ", indices, 2022, 2, 20);

        Ubicacion ubicacionSalida = new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            120
        );
        Ubicacion ubicacionllegada = new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            121
        );

        FactorDeEmision factorDeEmisionTest = new FactorDeEmision(TipoActividadDA.TRANSPORTE_PARTICULAR, TipoConsumoDA.AUTO_GASOIL, 0.5);
        calculadorDeHCTest.agregarFactorDeEmision(factorDeEmisionTest);

        Transporte autoDeJuan = new Particular(TipoParticular.AUTO, TipoCombustible.GASOIL, ServicioGeoDds.getInstancia(), 0.5);
        Tramo tramoTest = trayectoIDA.aniadirNuevoTramo(ubicacionSalida, ubicacionllegada,autoDeJuan,calculadorDeHCTest);


        assertTrue(Juan.calcularHCPorcentual(2022, 8, RRHH) > 0);
        Miembro Juan2 = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478 );
        Juan2.darseAltaEnOrganizacion(RRHH);
        RRHH.gestionarMiembrosPendientes(0,true);  //miembro 0 es juan?
        List<Integer> indices2 = new ArrayList<>();
        indices2.add(0);
        Trayecto trayectoIDA2 = Juan2.generarTrayecto("Ida a organizacionEJ", indices, 2022, 2, 20);

        Ubicacion ubicacionSalida2 = new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            120
        );
        Ubicacion ubicacionllegada2 = new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            121
        );


        Tramo tramoTest2 = trayectoIDA2.aniadirNuevoTramo(ubicacionSalida, ubicacionllegada,autoDeJuan,calculadorDeHCTest);

        assertTrue(RRHH.calcularHC(2022, 8) > 0);
        assertTrue(RRHH.calcularHCporMiembro(2022, 8) > 0);


    }

    @Test
    @DisplayName("Test Calcular HC por Municipio")
    public void testCalculadorHCPorMunicipio() throws IOException{

        Importador moduloImportadorTest = new ApachePOI();
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
        Clasificacion clasificacion = new Clasificacion("Ministerio");
        Provincias Cordoba = new Provincias(Provincia.Cordoba);
        MunicipiosODepartamentos municipioEj = Cordoba.crearMunicipio("muniEj");
        Organizacion organizacionEj = municipioEj.crearOrganizacion(moduloImportadorTest, "organizacionEj", ONG, clasificacion, "Cordoba Capital", "C2045", "Andes", 1200);
        Area RRHH = organizacionEj.darAltaArea("RRHH");
        Miembro Juan = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478 );
        Juan.darseAltaEnOrganizacion(RRHH);
        RRHH.gestionarMiembrosPendientes(0,true);  //miembro 0 es juan?
        List<Integer> indices = new ArrayList<>();
        indices.add(0);
        Trayecto trayectoIDA = Juan.generarTrayecto("Ida a organizacionEJ", indices, 2022, 2, 20);

        Ubicacion ubicacionSalida = new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            120
        );
        Ubicacion ubicacionllegada = new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            121
        );

        FactorDeEmision factorDeEmisionTest = new FactorDeEmision(TipoActividadDA.TRANSPORTE_PARTICULAR, TipoConsumoDA.AUTO_GASOIL, 0.5);
        calculadorDeHCTest.agregarFactorDeEmision(factorDeEmisionTest);

        Transporte autoDeJuan = new Particular(TipoParticular.AUTO, TipoCombustible.GASOIL, ServicioGeoDds.getInstancia(), 0.5);
        Tramo tramoTest = trayectoIDA.aniadirNuevoTramo(ubicacionSalida, ubicacionllegada,autoDeJuan,calculadorDeHCTest);


        assertTrue(Juan.calcularHCPorcentual(2022, 8, RRHH) > 0);
        Miembro Juan2 = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478 );
        Juan2.darseAltaEnOrganizacion(RRHH);
        RRHH.gestionarMiembrosPendientes(0,true);  //miembro 0 es juan?
        List<Integer> indices2 = new ArrayList<>();
        indices2.add(0);
        Trayecto trayectoIDA2 = Juan2.generarTrayecto("Ida a organizacionEJ", indices, 2022, 2, 20);

        Ubicacion ubicacionSalida2 = new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            120
        );
        Ubicacion ubicacionllegada2 = new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            121
        );


        Tramo tramoTest2 = trayectoIDA2.aniadirNuevoTramo(ubicacionSalida, ubicacionllegada,autoDeJuan,calculadorDeHCTest);

        assertTrue(municipioEj.calcularHC(2022, 8) > 0);



    }
    @Test
    @DisplayName("Test Calcular HC por Provincia")
    public void testCalculadorHCPorProvincia() throws IOException{

        Importador moduloImportadorTest = new ApachePOI();
        CalculadorDeHC calculadorDeHCTest = new CalculadorDeHC();
        Clasificacion clasificacion = new Clasificacion("Ministerio");
        Provincias Cordoba = new Provincias(Provincia.Cordoba);
        MunicipiosODepartamentos municipioEj = Cordoba.crearMunicipio("muniEj");
        Organizacion organizacionEj = municipioEj.crearOrganizacion(moduloImportadorTest, "organizacionEj", ONG, clasificacion, "Cordoba Capital", "C2045", "Andes", 1200);
        Area RRHH = organizacionEj.darAltaArea("RRHH");
        Miembro Juan = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478 );
        Juan.darseAltaEnOrganizacion(RRHH);
        RRHH.gestionarMiembrosPendientes(0,true);  //miembro 0 es juan?
        List<Integer> indices = new ArrayList<>();
        indices.add(0);
        Trayecto trayectoIDA = Juan.generarTrayecto("Ida a organizacionEJ", indices, 2022, 2, 20);

        Ubicacion ubicacionSalida = new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            120
        );
        Ubicacion ubicacionllegada = new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            121
        );

        FactorDeEmision factorDeEmisionTest = new FactorDeEmision(TipoActividadDA.TRANSPORTE_PARTICULAR, TipoConsumoDA.AUTO_GASOIL, 0.5);
        calculadorDeHCTest.agregarFactorDeEmision(factorDeEmisionTest);

        Transporte autoDeJuan = new Particular(TipoParticular.AUTO, TipoCombustible.GASOIL, ServicioGeoDds.getInstancia(), 0.5);
        Tramo tramoTest = trayectoIDA.aniadirNuevoTramo(ubicacionSalida, ubicacionllegada,autoDeJuan,calculadorDeHCTest);


        assertTrue(Juan.calcularHCPorcentual(2022, 8, RRHH) > 0);
        Miembro Juan2 = new Miembro("Juan", "Perez", TipoDocumento.CEDULA, 39555478 );
        Juan2.darseAltaEnOrganizacion(RRHH);
        RRHH.gestionarMiembrosPendientes(0,true);  //miembro 0 es juan?
        List<Integer> indices2 = new ArrayList<>();
        indices2.add(0);
        Trayecto trayectoIDA2 = Juan2.generarTrayecto("Ida a organizacionEJ", indices, 2022, 2, 20);

        Ubicacion ubicacionSalida2 = new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            120
        );
        Ubicacion ubicacionllegada2 = new Ubicacion(
            domain.ubicacion.Provincia.Buenos_Aires,
            "Bragado",
            "Bragado",
            "C1234",
            "calle falsa",
            121
        );


        Tramo tramoTest2 = trayectoIDA2.aniadirNuevoTramo(ubicacionSalida, ubicacionllegada,autoDeJuan,calculadorDeHCTest);

        assertTrue(Cordoba.calcularHC(2022, 8) > 0);

    }
*/

}
