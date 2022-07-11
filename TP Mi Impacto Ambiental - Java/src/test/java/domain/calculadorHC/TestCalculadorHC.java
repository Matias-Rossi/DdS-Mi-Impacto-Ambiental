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

    @BeforeEach
    public void beforeStatement() throws IOException{
        Importador apachePOI = new ApachePOI();
        CalculadorDeHC calculadorDeHC = new CalculadorDeHC();
        Clasificacion clasificacion = new Clasificacion("clasificado");
        Provincias catamarca = new Provincias(Provincia.Catamarca);

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



        MunicipiosODepartamentos municipio1 = catamarca.crearMunicipio("muni1");
        MunicipiosODepartamentos municipio2 = catamarca.crearMunicipio("muni2");
        Organizacion organizacion1 = municipio1.crearOrganizacion(apachePOI, "organizacion1", ONG, clasificacion, "loc1", "cp1", "cal1", 1);
        Organizacion organizacion2 = municipio2.crearOrganizacion(apachePOI, "organizacion2", ONG, clasificacion, "loc1", "cp1", "cal1", 1);

        Area area11 = organizacion1.darAltaArea("area11"); // 1 2
        Area area12 = organizacion1.darAltaArea("area12"); // 3
        Area area21 = organizacion2.darAltaArea("area21"); // 1

        Miembro miembro1 = new Miembro("miembro1", "apellido1", TipoDocumento.DNI, 1);
        miembro1.darseAltaEnOrganizacion(area11);
        area11.gestionarMiembrosPendientes(0, true);
        miembro1.darseAltaEnOrganizacion(area21);
        area21.gestionarMiembrosPendientes(0, true);//1 trayecto 2 orgs

        Miembro miembro2 = new Miembro("miembro2", "apellido2", TipoDocumento.DNI, 2);
        miembro2.darseAltaEnOrganizacion(area11);
        area11.gestionarMiembrosPendientes(0, true);// 1 trayecto

        Miembro miembro3 = new Miembro("miembro3", "apellido3", TipoDocumento.DNI, 3);
        miembro3.darseAltaEnOrganizacion(area12);
        area12.gestionarMiembrosPendientes(0, true);// 2 trayectos

        List<Integer> indices = new ArrayList<>();
        indices.add(0);
        indices.add(1);

        List<Integer> indices2 = new ArrayList<>();
        indices2.add(0);

        Trayecto trayecto11 = miembro1.generarTrayecto("trayecto11",indices,2022,1,5);

        Trayecto trayecto21 = miembro2.generarTrayecto("trayecto21",indices2,2022,1,5);

        Trayecto trayecto31 = miembro3.generarTrayecto("trayecto31",indices2,2022,1,5);

        Trayecto trayecto32 = miembro3.generarTrayecto("trayecto32",indices2,2022,1,5);

        Transporte transporte11 = new Particular(TipoParticular.AUTO, TipoCombustible.GASOIL, ServicioGeoDds.getInstancia() , 0.5);
        Transporte transporte12 = new Particular(TipoParticular.AUTO, TipoCombustible.GASOIL, ServicioGeoDds.getInstancia() , 0.5);
        Transporte transporte22 = new Particular(TipoParticular.AUTO, TipoCombustible.GASOIL, ServicioGeoDds.getInstancia() , 0.5);
        Transporte transporte31 = new Particular(TipoParticular.AUTO, TipoCombustible.GASOIL, ServicioGeoDds.getInstancia() , 0.5);
        Transporte transporte32 = new Particular(TipoParticular.AUTO, TipoCombustible.GASOIL, ServicioGeoDds.getInstancia() , 0.5);

        Tramo tramo111 = trayecto11.aniadirNuevoTramo(ubicacionTest1,ubicacionTest2,transporte11,calculadorDeHC);
        Tramo tramo112 = trayecto11.aniadirNuevoTramo(ubicacionTest1,ubicacionTest2,transporte11,calculadorDeHC);

        Tramo tramo211 = trayecto21.aniadirNuevoTramo(ubicacionTest1,ubicacionTest2,transporte11,calculadorDeHC);
        Tramo tramo311 = trayecto31.aniadirNuevoTramo(ubicacionTest1,ubicacionTest2,transporte11,calculadorDeHC);

        tramo211.compartirTramo(miembro3); //Miembro 2 le comparte al miembro 3

        miembro3.gestionarTramosCompartidos(0,0,true);
        organizacion1.cargarMediciones("src/test/java/domain/importadorExcel/fechas.xlsx",calculadorDeHC);
        organizacion2.cargarMediciones("src/test/java/domain/importadorExcel/fechas.xlsx",calculadorDeHC);








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
