package main.java;

import impacto_ambiental.models.entities.notificaciones.Itinerario;
import impacto_ambiental.server.Router;
import org.quartz.SchedulerException;
import spark.debug.DebugScreen;


import java.util.ArrayList;

import static spark.Spark.*;

public class Main  {

	public static void main(String[] args) {
		//Spark.staticFiles.externalLocation("upload");
		//EN LOCAL
		//port(9000);
		//EN HOST
		port(getHerokuAssignedPort());
		Router.init();
		DebugScreen.enableDebugScreen();
		//crearHiloCrono();
	}

	static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}
		return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
	}

	static void crearHiloCrono() {
		Thread t = null;
		t = new Thread(new Itinerario());
		t.start();

	}

}
