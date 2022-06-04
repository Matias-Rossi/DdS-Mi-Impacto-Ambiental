package domain.transporte;
public class Linea {
    private String nombre;
    private List<Parada> paradas;

    public float calcularDistancia(Ubicacion inicio,Ubicacion fin){
        Parada paradaInicio = paradas.find(parada => parada.ubicacionParada == inicio);
        // TODO ver como es find en java
        Parada paradaFin = paradas.find(parada => parada.ubicacionParada == fin);
        if(paradaFin.index > paradaInicio.index){
            return distanciaEntreParadas(paradaInicio,paradaFin);
        }
        return distanciaEntreParadas(paradaFin,paradaInicio);

    }
    public float distanciaEntreParadas(Parada paradaInicio,Parada paradaFin){  //TODO revisar funcionam
        return paradas.filter(unaParada => unaParada.index < paradaFin.index ||  unaParada.index <= paradaInicio.index).map( unaParada => unaParada.distanciaASiguiente ).sum();

    }
}
