//La clase es "selectorProvincia"






const boton = document.getElementById("hcProvincias");

boton.addEventListener("click", generarReporte );

function generarReporte(){
    let primerValorInsertado = false;
    let provinciasActivas = [];

    const provinciasSeleccionadas = document.querySelectorAll('input[type="checkbox"]:checked');

    let queryParams = "?id="
    var url = "https://miimpactoambiental-dds.herokuapp.com/agenteSectorial/HCNacional/ComposicionHC"
    //var url = "http://localhost:9000/agenteSectorial/HCNacional/ComposicionHC"

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