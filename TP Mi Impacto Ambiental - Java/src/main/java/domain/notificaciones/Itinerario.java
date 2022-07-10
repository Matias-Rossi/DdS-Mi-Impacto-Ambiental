package domain.notificaciones;


import org.quartz.*;

import java.util.TimeZone;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.DateBuilder.*;

public class Itinerario {
  //Use Cron to notify contacts every month

  public static void main(String args[]) throws SchedulerException {
    SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();

    Scheduler sched = schedFact.getScheduler();

    CronTrigger trigger = newTrigger()
        .withIdentity("Enviar Guia de Recomendaciones", "group1")
        .withSchedule(cronSchedule("0 15 10 1 * ?"))
        .build();

    JobDetail job = newJob(Difusor.class)
        .withIdentity("myJob", "group1") // name "myJob", group "group1"
        .build();

    sched.scheduleJob(job, trigger);
  }




}
