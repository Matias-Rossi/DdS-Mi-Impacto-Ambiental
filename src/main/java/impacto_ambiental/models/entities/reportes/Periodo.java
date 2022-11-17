package impacto_ambiental.models.entities.reportes;

public enum Periodo {
    Anual,
    Enero,
    Febrero,
    Marzo,
    Abril,
    Mayo,
    Junio,
    Julio,
    Agosto,
    Septiembre,
    Octubre,
    Noviembre,
    Diciembre;
    public static Periodo getPeriodo(Integer x){
        switch (x){
            case 0:
                return Anual;
            case 1:
                return Enero;
            case 2:
                return Febrero;
            case 3:
                return Marzo;
            case 4:
                return Abril;
            case 5:
                return Mayo;
            case 6:
                return Junio;
            case 7:
                return Julio;
            case 8:
                return Agosto;
            case 9:
                return Septiembre;
            case 10:
                return Octubre;
            case 11:
                return Noviembre;
            case 12:
                return Diciembre;
            default:
                return null;
        }
    }
    public static Integer toInteger(Periodo periodo){
        switch (periodo){
            case Anual:
                return 0;
            case Enero:
                return 1;
            case Febrero:
                return 2;
            case Marzo:
                return 3;
            case Abril:
                return 4;
            case Mayo:
                return 5;
            case Junio:
                return 6;
            case Julio:
                return 7;
            case Agosto:
                return 8;
            case Septiembre:
                return 9;
            case Octubre:
                return 10;
            case Noviembre:
                return 11;
            case Diciembre:
                return 12;
            default:
                return null;
        }
    }

}
