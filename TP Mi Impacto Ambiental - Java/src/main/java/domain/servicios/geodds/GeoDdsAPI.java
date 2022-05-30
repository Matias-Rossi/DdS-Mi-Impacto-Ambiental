package domain.servicios.geodds;

import domain.servicios.geodds.entidades.Distancia;
import domain.servicios.geodds.entidades.Localidad;
import domain.servicios.geodds.entidades.Municipio;
import domain.servicios.geodds.entidades.Provincia;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;
import java.util.List;

public interface GeoDdsAPI {
  //TODO: Se podria generar más adelante una "caché" de IDs de lugares

  //Listado provincias
  @Headers({
      "accept: application/json",
      "authorization: Bearer cHgeS/yxxZzUNPQzTy3v9FMB5DkTaqO1uO37nw7zocY="
  })
  @GET("provincias?offset=1&paisId=9") //Cambiar el 9 por un "id_pais" para internacionalizar el SW
  Call<List<Provincia>> getProvincias();

  //Listado municipios para provincia
  @Headers({
      "accept: application/json",
      "authorization: Bearer cHgeS/yxxZzUNPQzTy3v9FMB5DkTaqO1uO37nw7zocY="
  })
  @GET("municipios?offset=1")
  Call<List<Municipio>> getMunicipios(@Query("provinciaId") int provinciaId);

  //Listado localidades para municipio
  @Headers({
      "accept: application/json",
      "authorization: Bearer cHgeS/yxxZzUNPQzTy3v9FMB5DkTaqO1uO37nw7zocY="
  })
  @GET("localidades?offset=1")
  Call<List<Localidad>> getLocalidades(@Query("municipioId") int municipioId);

  //Distancia entre dos puntos
  @Headers({
      "accept: application/json",
      "authorization: Bearer cHgeS/yxxZzUNPQzTy3v9FMB5DkTaqO1uO37nw7zocY="
  })
  @GET("distancia")
  Call<Distancia> getDistancia(
      @Query("localidadOrigenId") int localidadOrigenId,
      @Query("calleOrigen") String calleOrigen,
      @Query("alturaOrigen") int alturaOrigen,
      @Query("localidadDestinoId") int localidadDestinoId,
      @Query("calleDestino") String calleDestino,
      @Query("alturaDestino") int alturaDestino
    );
}
