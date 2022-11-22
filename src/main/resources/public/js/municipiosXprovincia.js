
//Municipios al seleccionar provincia

const xhr = new XMLHttpRequest();

const selectoresProvincias = document.querySelectorAll('.selectProvincia');
    console.log("selector inicializado");

selectoresProvincias.forEach(selectorProvincia => {
    selectorProvincia.addEventListener('change', (event) => {
        console.log("actualizando municipios");

        //xhr.open("GET", "http://localhost:9000/geo/"+ selectorProvincia.value );
        xhr.open("GET", "https://miimpactoambiental-dds.herokuapp.com/geo/"+ selectorProvincia.value );
        xhr.send();
        xhr.responseType = "json";
        xhr.addEventListener("load", transferComplete);

        xhr.onload = function() {
            if (xhr.status != 200) { // analiza el estado HTTP de la respuesta
                console.log(`Error ${xhr.status}: ${xhr.statusText}`); // ej. 404: No encontrado
                } else { // muestra el resultado
                    console.log(`Hecho, obtenidos ${xhr.response.length} bytes`); // Respuesta del servidor
                    console.log(xhr.response);
                }


            var opcionesMunicipios = document.querySelectorAll('.selectMunicipio');

            opcionesMunicipios.forEach(opcionesMunicipio => {

            //Limpia entradas actuales
            while (opcionesMunicipio.firstChild) {
                opcionesMunicipio.removeChild(opcionesMunicipio.firstChild);
            }

            //Crea entrada no seleccionable
            var optionInicial = document.createElement("option");
            optionInicial.value = "" ;
            optionInicial.textContent = "-- Seleccionar municipio --" ;
            optionInicial.setAttribute("selected","")
            optionInicial.setAttribute("disabled","")
            opcionesMunicipio.appendChild(optionInicial);

            //Crea las entradas por municipio
            xhr.response.forEach( municipio => {
              var option = document.createElement("option");
              option.value = municipio.id ;
              option.textContent = municipio.nombre;
              opcionesMunicipio.appendChild(option);
            });

            });



         }
    });

});



function transferComplete (){
    console.log("transferComplete");
}
