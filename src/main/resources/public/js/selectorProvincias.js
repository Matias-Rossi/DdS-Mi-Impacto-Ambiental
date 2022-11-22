//La clase es "selectorProvincia"




let primerValorInsertado = false;
let provinciasActivas = [];

const boton = document.getElementById("hcProvincias");

boton.addEventListener("click", genrarReporte );

function genrarReporte(){
    const provinciasSeleccionadas = document.querySelectorAll('input[type="checkbox"]:checked');

    let queryParams = "?id="
    var url = "https://miimpactoambiental-dds.herokuapp.com/HCNacional/ComposicionHC"

    provinciasSeleccionadas.forEach( unaProvincia => {
        if(!primerValorInsertado) {
            queryParams += unaProvincia.value;
            primerValorInsertado = true;
        } else {
            queryParams += "," + unaProvincia.value;
        }
    });
    console.log(url + queryParams);
    window.location.href = url + queryParams;

}