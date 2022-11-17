package impacto_ambiental.models.entities.ubicacion;

public enum NombreProvincia {
    BUENOS_AIRES {
        public String toString() {
            return "Buenos Aires";
        }
        public String toEnumString() { return "Buenos_Aires"; }
    },
    CIUDAD_DE_BUENOS_AIRES {
        public String toString() {
            return "Ciudad de Buenos Aires";
        }
        public String toEnumString() { return "Ciudad_de_Buenos_Aires"; }
    },
    CORDOBA {
        public String toString() {
            return "Cordoba";
        }
        public String toEnumString() { return this.toString(); }
    },
    CATAMARCA {
        public String toString() {
            return "Catamarca";
        }
        public String toEnumString() { return this.toString(); }
    },
    CHACO {
        public String toString() {
            return "Chaco";
        }
        public String toEnumString() { return this.toString(); }
    },
    CHUBUT {
        public String toString() {
            return "Chubut";
        }
        public String toEnumString() { return this.toString(); }
    },
    CORRIENTES {
        public String toString() {
            return "Corrientes";
        }
        public String toEnumString() { return this.toString(); }
    },
    ENTRE_RIOS {
        public String toString() {
            return "Entre Rios";
        }
        public String toEnumString() { return this.toString(); }
    },
    FORMOSA {
        public String toString() {
            return "Formosa";
        }
        public String toEnumString() { return this.toString(); }
    },
    JUJUY {
        public String toString() {
            return "Jujuy";
        }
        public String toEnumString() { return this.toString(); }
    },
    LA_PAMPA {
        public String toString() {
            return "La Pampa";
        }
        public String toEnumString() { return this.toString(); }
    },
    LA_RIOJA {
        public String toString() {
            return "La Rioja";
        }
        public String toEnumString() { return this.toString(); }
    },
    MENDOZA {
        public String toString() {
            return "Mendoza";
        }
        public String toEnumString() { return this.toString(); }
    },
    MISIONES {
        public String toString() {
            return "Misiones";
        }
        public String toEnumString() { return this.toString(); }
    },
    NEUQUEN {
        public String toString() {
            return "Neuquen";
        }
        public String toEnumString() { return this.toString(); }
    },
    RIO_NEGRO {
        public String toString() {
            return "Rio Negro";
        }
        public String toEnumString() { return "Rio_Negro"; }
    },
    SALTA {
        public String toString() {
            return "Salta";
        }
        public String toEnumString() { return this.toString(); }
    },
    SAN_JUAN {
        public String toString() {
            return "San Juan";
        }
        public String toEnumString() { return "San_Juan"; }
    },
    SAN_LUIS {
        public String toString() {
            return "San Luis";
        }
        public String toEnumString() { return "San_Luis"; }
    },
    SANTA_CRUZ {
        public String toString() {
            return "Santa Cruz";
        }
        public String toEnumString() { return "Santa_Cruz"; }
    },
    SANTA_FE {
        public String toString() {
            return "Santa Fe";
        }
        public String toEnumString() { return "Santa_Fe"; }
    },
    SANTIAGO_DEL_ESTERO {
        public String toString() {
            return "Santiago del Estero";
        }
        public String toEnumString() { return "Santiago_del_Estero"; }
    },
    TIERRA_DEL_FUEGO {
        public String toString() {
            return "Tierra del Fuego";
        }
        public String toEnumString() { return "Tierra_del_Fuego"; }
    },
    TUCUMAN {
        public String toString() {
            return "Tucuman";
        }
        public String toEnumString() { return this.toString(); }
    };

    public String toEnumString() {
        return "";
    }
    public static NombreProvincia toEnum(String nombre) {
        String nombreConEspacios = nombre.replace(" ", "_");
        return NombreProvincia.valueOf(nombreConEspacios);
    }
}
