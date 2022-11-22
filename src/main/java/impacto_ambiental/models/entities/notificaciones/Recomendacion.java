package impacto_ambiental.models.entities.notificaciones;

import impacto_ambiental.models.entities.EntidadPersistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "recomendaciones")
public class Recomendacion extends EntidadPersistente {
    @Setter
    @Getter
    @Column(name = "titulo")
    public String titulo;

    @Setter
    @Getter
    @Column(name = "subtitulo")
    public String subtitulo;

    @Setter
    @Getter
    @Column(name = "texto")
    public String texto;

    public Recomendacion(){}

    public Recomendacion(String titulo,String subtitulo,String texto){
        this.titulo=titulo;
        this.subtitulo=subtitulo;
        this.texto=texto;
    }

}
