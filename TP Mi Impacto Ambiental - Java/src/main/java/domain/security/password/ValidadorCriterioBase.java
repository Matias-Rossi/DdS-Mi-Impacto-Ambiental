package main.java.domain.security.password;

public class ValidadorCriterioBase implements ValidadorCriterio {

    @Override
    public boolean validar(String contrasenia) {
        return true;
    }
    
}
