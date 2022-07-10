package domain.notificaciones;

import domain.servicios.twilio.ServicioTwilio;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class ClasePrueba implements Job {
    private static GestorNotificaciones gestorNotificaciones = new ServicioTwilio();
    private static Difusor difusor;

    public ClasePrueba() {
        difusor = new Difusor(gestorNotificaciones);
    }

    @Override
    public void execute(JobExecutionContext jce) throws JobExecutionException {
        JobKey jobKey = jce.getJobDetail().getKey();
        System.out.println("Funciona!");
        difusor.difundirRecomendaciones();
    }
}
