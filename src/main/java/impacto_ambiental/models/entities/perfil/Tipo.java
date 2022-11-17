package impacto_ambiental.models.entities.perfil;

public enum Tipo {
        GUBERNAMENTAL,
        ONG,
        EMPRESA,
        INSTITUCION;
        public Tipo toEnum(String tipo){
            switch (tipo){
                case "GUBERNAMENTAL":
                    return GUBERNAMENTAL;
                case "ONG":
                    return ONG;
                case "EMPRESA":
                    return EMPRESA;
                case "INSTITUCION":
                    return INSTITUCION;
                default:
                    return null;
            }
        }
}
