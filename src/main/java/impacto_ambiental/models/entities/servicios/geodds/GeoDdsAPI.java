package impacto_ambiental.models.entities.servicios.geodds;

import impacto_ambiental.models.entities.servicios.geodds.entidades.Distancia;
import impacto_ambiental.models.entities.servicios.geodds.entidades.Localidad;
import impacto_ambiental.models.entities.servicios.geodds.entidades.Municipio;
import impacto_ambiental.models.entities.servicios.geodds.entidades.Provincia;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface GeoDdsAPI {
  //TODO: Se podria generar más adelante una "caché" de IDs de lugares

  //Listado provincias
  @Headers({
      "accept: application/json",
  })
  @GET("provincias?offset=1&paisId=9") //Cambiar el 9 por un "id_pais" para internacionalizar el SW
  Call<List<Provincia>> getProvincias(@Header("authorization") String token);

  //Listado municipios para provincia
  @Headers({
      "accept: application/json",
  })
  @GET("municipios?offset=1")
  Call<List<Municipio>> getMunicipios(@Header("authorization") String token, @Query("provinciaId") int provinciaId);

  //Listado localidades para municipio
  @Headers({
      "accept: application/json",
  })
  @GET("localidades?offset=1")
  Call<List<Localidad>> getLocalidades(@Header("authorization") String token, @Query("municipioId") int municipioId);

  //Distancia entre dos puntos
  @Headers({
      "accept: application/json",
  })
  @GET("distancia")
  Call<Distancia> getDistancia(
      @Header("authorization") String token,
      @Query("localidadOrigenId") int localidadOrigenId,
      @Query("calleOrigen") String calleOrigen,
      @Query("alturaOrigen") int alturaOrigen,
      @Query("localidadDestinoId") int localidadDestinoId,
      @Query("calleDestino") String calleDestino,
      @Query("alturaDestino") int alturaDestino
    );
}
