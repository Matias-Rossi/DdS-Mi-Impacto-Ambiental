package impacto_ambiental.models.entities.security.password;

public class ValidadorCriterioBase implements ValidadorCriterio {

    @Override
    public boolean validar(String contrasenia) {
        return true;
    }
    
}
