package impacto_ambiental.server;

import spark.Spark;
import spark.debug.DebugScreen;

import java.io.File;

import static spark.Spark.staticFiles;

public class Server {

	public static void main(String[] args) {
		staticFiles.externalLocation("upload");


		Spark.port(9000);
		Router.init();
		DebugScreen.enableDebugScreen();
	}
}
