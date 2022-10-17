package domain.persistenceExtend;

import impacto_ambiental.db.EntityManagerHelper;
import impacto_ambiental.models.entities.EntidadPersistente;
import impacto_ambiental.models.entities.reportes.Periodo;
import impacto_ambiental.models.entities.usuario.*;
import org.junit.jupiter.api.Test;

public class TestRoles {

    @Test
            public void testRoles() {
        Rol miembro = new Rol(TipoUsuario.MIEMBRO);
        Rol organizacion = new Rol(TipoUsuario.ORGANIZACION);
        Rol agente = new Rol(TipoUsuario.AGENTE);
        Rol administrador = new Rol(TipoUsuario.ADMINISTRADOR);

        Permiso totalSobreTrayectosPropios = new Permiso(Alcance.PROPIOS, Accion.TOTAL, Objeto.TRAYECTO);

        miembro.agregarPermiso(totalSobreTrayectosPropios);

        EntityManagerHelper.persist(miembro);
        EntityManagerHelper.persist(organizacion);
        EntityManagerHelper.persist(agente);
        EntityManagerHelper.persist(administrador);

    }

}
