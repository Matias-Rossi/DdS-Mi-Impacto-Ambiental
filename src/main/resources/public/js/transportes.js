
//__________ JS CARGA DE TRAMOTRASPORTE PUBLICO


// CARGAR LAS LINEAS CUANDO SE SELECCIONA TRANSPORTE
const tipoTransporte = document.getElementById("tipoTransporte")
const xhr = new XMLHttpRequest();
tipoTransporte.addEventListener('change', (event) => {


  //console.log(tipoTransporte.value + ": " + tipoTransporte.value.split("/")[0] + " / " +tipoTransporte.value.split("/")[1]  );
  //xhr.open("GET", "https://miimpactoambiental-dds.herokuapp.com/transportes/"+ event.value );

  //xhr.open("GET", "http://localhost:9000/transportes/"+ tipoTransporte.value.split("/")[0] );
  xhr.open("GET", "https://miimpactoambiental-dds.herokuapp.com/transportes/"+ tipoTransporte.value.split("/")[0] );
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
  recargarLineas();

  var opcionesLineas = document.getElementById('linea');
  xhr.response.forEach( linea => {
  var option = document.createElement("option");
  option.value = linea.id ;
  option.textContent = linea.nombre;
  opcionesLineas.appendChild(option);
  console.log( "inserte la linea" + linea.nombre);
  });
};



});

const lineaSeleccionada = document.getElementById('linea');

  lineaSeleccionada.addEventListener('change', (event) => {

    //xhr.open("GET", "http://localhost:9000/transportes/"+ tipoTransporte.value.split("/")[0]+"/"+lineaSeleccionada.value );
    xhr.open("GET", "https://miimpactoambiental-dds.herokuapp.com/transportes/"+ tipoTransporte.value.split("/")[0]+"/"+lineaSeleccionada.value );
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

        recargarParadas();

        var opcionesParadasInicio = document.getElementById('paradaInicio');
        var opcionesParadasFin = document.getElementById('paradaFin');



        xhr.response.forEach( parada => {
          var option = document.createElement("option");
          option.value = parada.id ;
          option.textContent = parada.nombre;
          opcionesParadasInicio.appendChild(option);
          var option2 = document.createElement("option");
          option2.value = parada.id ;
          option2.textContent = parada.nombre;
          opcionesParadasFin.appendChild(option2);
          console.log( "inserte la parada" + parada.nombre);
        });


     }
});

function recargarLineas(){
   var opcionesLineas = document.getElementById('linea');
  while (opcionesLineas.firstChild) {
    opcionesLineas.removeChild(opcionesLineas.firstChild);
  }
  var optionInicial = document.createElement("option");
  optionInicial.value = ""  ;
  optionInicial.textContent = "-- Seleccionar Linea --";
  optionInicial.setAttribute("selected","")
  opcionesLineas.appendChild(optionInicial);

  recargarParadas()
}
function recargarParadas(){

        var opcionesParadasInicio = document.getElementById('paradaInicio');
        var opcionesParadasFin = document.getElementById('paradaFin');
        while (opcionesParadasInicio.firstChild) {
            opcionesParadasInicio.removeChild(opcionesParadasInicio.firstChild);
        }
        var optionInicial = document.createElement("option");
        optionInicial.value = "" ;
        optionInicial.textContent = "-- Seleccionar parada inicio --" ;
        optionInicial.setAttribute("selected","")
        opcionesParadasInicio.appendChild(optionInicial);
        while (opcionesParadasFin.firstChild) {
              opcionesParadasFin.removeChild(opcionesParadasFin.firstChild);
        }
        var optionInicial2 = document.createElement("option");
        optionInicial2.value = ""  ;
        optionInicial2.textContent = "-- Seleccionar parada fin --";
        optionInicial2.setAttribute("selected","")
        opcionesParadasFin.appendChild(optionInicial2);
}

function transferComplete (){
    console.log("transferComplete");
}
