package domain.calculadorHC;


import domain.importadorExcel.*;
import domain.perfil.*;
import domain.servicios.geodds.ServicioGeoDds;
import domain.transporte.Particular;
import domain.transporte.TipoCombustible;
import domain.transporte.TipoParticular;
import domain.transporte.Transporte;
import domain.trayecto.Tramo;
import domain.ubicacion.MunicipiosODepartamentos;
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

    @BeforeEach
    public void beforeStatement() {
        Importador apachePOI = new ApachePOI();
        Clasificacion clasificacion = new Clasificacion("clasificado");
        Provincias catamarca = new Provincias(Provincia.Catamarca);
        MunicipiosODepartamentos municipio1 = catamarca.crearMunicipio("muni1");
        MunicipiosODepartamentos municipio2 = catamarca.crearMunicipio("muni2");
        Organizacion organizacion11 = municipio1.crearOrganizacion(apachePOI, "organizacion11", ONG, clasificacion, "loc1", "cp1", "cal1", 1);
        Organizacion organizacion12 = municipio1.crearOrganizacion(apachePOI, "organizacion11", ONG, clasificacion, "loc1", "cp1", "cal1", 1);
        Organizacion organizacion21 = municipio2.crearOrganizacion(apachePOI, "organizacion11", ONG, clasificacion, "loc1", "cp1", "cal1", 1);
        Organizacion organizacion22 = municipio2.crearOrganizacion(apachePOI, "organizacion11", ONG, clasificacion, "loc1", "cp1", "cal1", 1);
        Organizacion organizacion23 = municipio2.crearOrganizacion(apachePOI, "organizacion11", ONG, clasificacion, "loc1", "cp1", "cal1", 1);


        List<Miembro> miembros = new ArrayList<Miembro>();
        int i =0;

        Area area111 = organizacion11.darAltaArea("area111");
        Area area112 = organizacion11.darAltaArea("area112");
        Area area121 = organizacion11.darAltaArea("area121");
        Area area211 = organizacion11.darAltaArea("area211");
        Area area212 = organizacion11.darAltaArea("area212");
        Area area221 = organizacion11.darAltaArea("area221");
        Area area222 = organizacion11.darAltaArea("area222");
        Area area223 = organizacion11.darAltaArea("area223");


    }

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
    public void testCalculadorHCOrganizacion(){
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
}
