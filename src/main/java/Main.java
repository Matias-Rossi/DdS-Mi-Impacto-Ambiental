import impacto_ambiental.server.Router;
import spark.Spark;
import spark.debug.DebugScreen;

import java.io.File;

import static spark.Spark.*;

public class Main  {

	public static void main(String[] args) {
		//Spark.staticFiles.externalLocation("upload");
		port(getHerokuAssignedPort());
		Router.init();
		DebugScreen.enableDebugScreen();
	}

	static int getHerokuAssignedPort() {
		ProcessBuilder processBuilder = new ProcessBuilder();
		if (processBuilder.environment().get("PORT") != null) {
			return Integer.parseInt(processBuilder.environment().get("PORT"));
		}
		return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
	}
}
