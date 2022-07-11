package domain.notificaciones;

import domain.servicios.twilio.ServicioTwilio;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class ObservadorRecomendaciones implements Job {
    private static GestorNotificaciones gestorNotificaciones = new ServicioTwilio();
    private static Difusor difusor = new Difusor(gestorNotificaciones);

    @Override
    public void execute(JobExecutionContext jce) throws JobExecutionException {
        JobKey jobKey = jce.getJobDetail().getKey();
        difusor.difundirRecomendaciones();
    }
}
