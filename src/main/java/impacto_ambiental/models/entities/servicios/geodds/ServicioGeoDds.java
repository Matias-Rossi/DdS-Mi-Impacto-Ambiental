package impacto_ambiental.models.entities.servicios.geodds;

import impacto_ambiental.models.entities.servicios.geodds.entidades.Distancia;
import impacto_ambiental.models.entities.servicios.geodds.entidades.Localidad;
import impacto_ambiental.models.entities.servicios.geodds.entidades.Municipio;
import impacto_ambiental.models.entities.servicios.geodds.entidades.Provincia;
import impacto_ambiental.models.entities.ubicacion.Ubicacion;
import impacto_ambiental.models.entities.transporte.CalculadorDeDistancia;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import static java.net.URLEncoder.encode;

//Singleton
public class ServicioGeoDds implements CalculadorDeDistancia {
  private static ServicioGeoDds instancia = null;
  private static final String urlAPI = "https://ddstpa.com.ar/api/";
  private static String token;
  private final Retrofit retrofit;

  private ServicioGeoDds() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    Properties prop = new Properties();
    ClassLoader loader = Thread.currentThread().getContextClassLoader();
    InputStream stream = loader.getResourceAsStream("tokens.properties");
    try {
      prop.load(stream);
      token = "Bearer ".concat(prop.getProperty("geoDdsToken"));
    } catch(IOException e) {
      token = "";
    }



  }

  public static ServicioGeoDds getInstancia() throws IOException {
    if(instancia == null) {
      instancia = new ServicioGeoDds();
    }
    return instancia;
  }

  public List<Provincia> listadoProvincias() throws IOException {
    GeoDdsAPI geoDdsAPI = this.retrofit.create(GeoDdsAPI.class);
    Call<List<Provincia>> requestProvinciasArgentinas = geoDdsAPI.getProvincias(token);
    Response<List<Provincia>> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
    return responseProvinciasArgentinas.body();
  }

  public Map<String, Integer> mapProvincias() throws IOException {
    List<Provincia> lista = this.listadoProvincias();
    return lista.stream().collect(Collectors.toMap(Provincia::getNombre, Provincia::getId));
  }

  public List<Municipio> listadoMunicipios(int idProvincia) throws IOException {
    GeoDdsAPI geoDdsAPI = this.retrofit.create(GeoDdsAPI.class);
    Call<List<Municipio>> requestMunicipiosArgentinas = geoDdsAPI.getMunicipios(token, idProvincia);
    Response<List<Municipio>> responseMunicipiosArgentinas = requestMunicipiosArgentinas.execute();
    return responseMunicipiosArgentinas.body();
  }

  public Map<String, Integer> mapMunicipios(int idProvincia) throws IOException {
    List<Municipio> lista = this.listadoMunicipios(idProvincia);
    return lista.stream().collect(Collectors.toMap(Municipio::getNombre, Municipio::getId));
  }

  public List<Localidad> listadoLocalidades(int idMunicipio) throws IOException {
    GeoDdsAPI geoDdsAPI = this.retrofit.create(GeoDdsAPI.class);
    Call<List<Localidad>> requestLocalidadesArgentinas = geoDdsAPI.getLocalidades(token, idMunicipio);
    Response<List<Localidad>> responseLocalidadesArgentinas = requestLocalidadesArgentinas.execute();
    return responseLocalidadesArgentinas.body();
  }

  public Map<String, Integer> mapLocalidades(int idMunicipio) throws IOException {
    List<Localidad> lista = this.listadoLocalidades(idMunicipio);
    return lista.stream().collect(Collectors.toMap(Localidad::getNombre, Localidad::getId));
  }

  public Distancia distanciaEntrePuntos(
      int localidadOrigenId,
      String calleOrigen,
      int alturaOrigen,
      int localidadDestinoId,
      String calleDestino,
      int alturaDestino
  ) throws IOException {

    //Encoding URL
    calleOrigen = encode(calleOrigen, "UTF-8");
    calleDestino = encode(calleDestino, "UTF-8");

    GeoDdsAPI geoDdsAPI = this.retrofit.create(GeoDdsAPI.class);
    Call<Distancia> requestDistancia = geoDdsAPI.getDistancia(token, localidadOrigenId, calleOrigen, alturaOrigen, localidadDestinoId, calleDestino, alturaDestino);
    Response<Distancia> responseDistancia = requestDistancia.execute();
    return responseDistancia.body();
  }

  public int getIdLocalidad(String prov, String mun, String loc) throws IOException {
    int idProvincia;
    int idMunicipio = 0;

    try {
      idProvincia = mapProvincias().get(prov.toUpperCase(Locale.ROOT));
      idMunicipio = mapMunicipios(idProvincia).get(mun.toUpperCase(Locale.ROOT));

    } catch(Exception e) {
      System.out.println("# ERROR al buscar el ID de la localidad:");
      System.out.printf("Provincia: %s, \nMunicipio: %s, \nLocalidad: %s\n\n", prov, mun, loc);
      e.printStackTrace();

    }

    return mapLocalidades(idMunicipio).get(loc.toUpperCase(Locale.ROOT));



  }

  public Distancia getDistanciaEntrePuntos(Ubicacion origen, Ubicacion destino) throws IOException {
    int idLocalidadOrigen = getIdLocalidad(
        origen.getNombreProvincia().toString().toUpperCase(Locale.ROOT),
        origen.getMunicipalidad().getMunicipioOLocalidad().toUpperCase(Locale.ROOT),
        origen.getLocalidad().toUpperCase(Locale.ROOT)
    );

    int idLocalidadDestino = getIdLocalidad(
        origen.getNombreProvincia().toString().toUpperCase(Locale.ROOT),
        origen.getMunicipalidad().getMunicipioOLocalidad().toUpperCase(Locale.ROOT),
        origen.getLocalidad().toUpperCase(Locale.ROOT)
    );

    return distanciaEntrePuntos(
        idLocalidadOrigen, origen.getCalle(), origen.getNumeracion(),
        idLocalidadDestino, destino.getCalle(), destino.getNumeracion()
    );

  }

  public double calcularDistancia(Ubicacion origen, Ubicacion destino) throws IOException {
    return getDistanciaEntrePuntos(origen, destino).getValor();
  }

}
