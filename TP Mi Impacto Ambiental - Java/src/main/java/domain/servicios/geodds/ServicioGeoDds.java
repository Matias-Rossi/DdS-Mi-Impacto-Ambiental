package domain.servicios.geodds;

import domain.servicios.geodds.entidades.Distancia;
import domain.servicios.geodds.entidades.Localidad;
import domain.servicios.geodds.entidades.Municipio;
import domain.servicios.geodds.entidades.Provincia;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

import static java.net.URLEncoder.encode;

public class ServicioGeoDds {
  private static ServicioGeoDds instancia = null;
  private static final String urlAPI = "https://ddstpa.com.ar/api/";
  private Retrofit retrofit;

  private ServicioGeoDds() {
    this.retrofit = new Retrofit.Builder()
        .baseUrl(urlAPI)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  public static ServicioGeoDds getInstancia() {
    if(instancia == null) {
      instancia = new ServicioGeoDds();
    }
    return instancia;
  }

  public List<Provincia> listadoProvincias() throws IOException {
    GeoDdsAPI geoDdsAPI = this.retrofit.create(GeoDdsAPI.class);
    Call<List<Provincia>> requestProvinciasArgentinas = geoDdsAPI.getProvincias();
    Response<List<Provincia>> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
    return responseProvinciasArgentinas.body();
  }

  public List<Municipio> listadoMunicipios(int idProvincia) throws IOException {
    GeoDdsAPI geoDdsAPI = this.retrofit.create(GeoDdsAPI.class);
    Call<List<Municipio>> requestMunicipiosArgentinas = geoDdsAPI.getMunicipios(idProvincia);
    Response<List<Municipio>> responseMunicipiosArgentinas = requestMunicipiosArgentinas.execute();
    return responseMunicipiosArgentinas.body();
  }

  public List<Localidad> listadoLocalidades(int idMunicipio) throws IOException {
    GeoDdsAPI geoDdsAPI = this.retrofit.create(GeoDdsAPI.class);
    Call<List<Localidad>> requestLocalidadesArgentinas = geoDdsAPI.getLocalidades(idMunicipio);
    Response<List<Localidad>> responseLocalidadesArgentinas = requestLocalidadesArgentinas.execute();
    return responseLocalidadesArgentinas.body();
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
    Call<Distancia> requestDistancia = geoDdsAPI.getDistancia(localidadOrigenId, calleOrigen, alturaOrigen, localidadDestinoId, calleDestino, alturaDestino);
    System.out.println("Request: " + requestDistancia.request());
    Response<Distancia> responseDistancia = requestDistancia.execute();
    System.out.println(responseDistancia.raw());
    return responseDistancia.body();
  }
}
