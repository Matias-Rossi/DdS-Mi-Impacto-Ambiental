function validadorFechaDosDigitos(fecha) {
  if (fecha.length == 1) {
    return '0' + fecha;
  }
  else {
    return fecha;
  }
}

function parserLocalDateStringToYYYYMMDD(str) {
  var dateSting = str.split('/');
  var DD = dateSting.at(0);
  var MM = dateSting.at(1);
  var YYYY = dateSting.at(2);
  var YYYYMMDD = YYYY + '-' + validadorFechaDosDigitos(MM) + '-' + validadorFechaDosDigitos(DD);
  return YYYYMMDD;
}
function maxDate() {
  let inputDate = document.getElementById('inputDate');
  var actualDate = new Date().toLocaleDateString('es-AR');
  inputDate.max = parserLocalDateStringToYYYYMMDD(actualDate);
}
function togglePasswordF1() {
  var togglePassword1 = document.getElementById('togglePassword1');
  var password1 = document.getElementById('inputContrseña');

  if (password1.type === 'password') {
    password1.setAttribute('type', 'text');
    togglePassword1.classList.add('hide');
  } else {
    password1.setAttribute('type', 'password');
    togglePassword1.classList.remove('hide');
  }
}

function togglePasswordF2() {
  var togglePassword2 = document.getElementById('togglePassword2');
  var password2 = document.getElementById('inputConfirmarContrseña');

  if (password2.type === 'password') {
    password2.setAttribute('type', 'text');
    togglePassword2.classList.add('hide');
  } else {
    password2.setAttribute('type', 'password');
    togglePassword2.classList.remove('hide');
  }
}

function setPasswordConfirmValidity(str) {
  var password1 = document.getElementById('inputContrseña');
  var password2 = document.getElementById('inputConfirmarContrseña');
  var contrseñaNoCoincide = document.getElementById('contrseñaNoCoincide');
  var contrseñaCoincide = document.getElementById('contrseñaCoincide');

  if (password1.value === password2.value) {
    contrseñaCoincide.classList.add('valid');
    contrseñaNoCoincide.classList.add('d-none');
    contrseñaCoincide.classList.remove('d-none');
  } else {
    contrseñaCoincide.classList.remove('valid');
    contrseñaNoCoincide.classList.remove('d-none');
    contrseñaCoincide.classList.add('d-none');
  }
}

function checkPassword(data) {

  var lowerCase = document.getElementById('lower');
  var upperCase = document.getElementById('upper');
  var numberCase = document.getElementById('number');
  var specialCase = document.getElementById('special');
  var lengthCase = document.getElementById('length');
  var requisitosCase = document.getElementById('requisitos');

  const lower = new RegExp('(?=.*[a-z])');
  const upper = new RegExp('(?=.*[A-Z])');
  const special = new RegExp('(?=.*[!@#\$%\^&\*_=+-])');
  const number = new RegExp('(?=.*[0-9])');
  const length = new RegExp('.{8,32}$');

  //lower case Validation check
  if (lower.test(data)) { lowerCase.classList.add('valid'); }
  else { lowerCase.classList.remove('valid'); }

  //upper case Validation check
  if (upper.test(data)) { upperCase.classList.add('valid'); }
  else { upperCase.classList.remove('valid'); }

  //number Validation check
  if (number.test(data)) { numberCase.classList.add('valid'); }
  else { numberCase.classList.remove('valid'); }

  //special character Validation check
  if (special.test(data)) { specialCase.classList.add('valid'); }
  else { specialCase.classList.remove('valid'); }

  //length Validation check
  if (length.test(data)) { lengthCase.classList.add('valid'); }
  else { lengthCase.classList.remove('valid'); }

  if (length.test(data) && special.test(data) && number.test(data) && upper.test(data) && lower.test(data)) { requisitosCase.classList.add('valid'); }
  else { requisitosCase.classList.remove('valid'); }
}

function setPasswordConfirmValidityOrg(str) {
  var password1 = document.getElementById('inputContrseñaOrg');
  var password2 = document.getElementById('inputConfirmarContrseñaOrg');
  var contrseñaNoCoincide = document.getElementById('contrseñaNoCoincideOrg');
  var contrseñaCoincide = document.getElementById('contrseñaCoincideOrg');

  if (password1.value === password2.value) {
    contrseñaCoincide.classList.add('valid');
    contrseñaNoCoincide.classList.add('d-none');
    contrseñaCoincide.classList.remove('d-none');
  } else {
    contrseñaCoincide.classList.remove('valid');
    contrseñaNoCoincide.classList.remove('d-none');
    contrseñaCoincide.classList.add('d-none');
  }
}
function togglePasswordF12() {
  var togglePassword12 = document.getElementById('togglePassword12');
  var password12 = document.getElementById('inputContrseñaOrg');

  if (password12.type === 'password') {
    password12.setAttribute('type', 'text');
    togglePassword12.classList.add('hide');
  } else {
    password12.setAttribute('type', 'password');
    togglePassword12.classList.remove('hide');
  }
}

function togglePasswordF22() {
  var togglePassword22 = document.getElementById('togglePassword22');
  var password22 = document.getElementById('inputConfirmarContrseñaOrg');

  if (password22.type === 'password') {
    password22.setAttribute('type', 'text');
    togglePassword22.classList.add('hide');
  } else {
    password22.setAttribute('type', 'password');
    togglePassword22.classList.remove('hide');
  }
}

function checkPasswordOrg(data) {

  var lowerCase = document.getElementById('lowerOrg');
  var upperCase = document.getElementById('upperOrg');
  var numberCase = document.getElementById('numberOrg');
  var specialCase = document.getElementById('specialOrg');
  var lengthCase = document.getElementById('lengthOrg');
  var requisitosCase = document.getElementById('requisitosOrg');

  const lower = new RegExp('(?=.*[a-z])');
  const upper = new RegExp('(?=.*[A-Z])');
  const special = new RegExp('(?=.*[!@#\$%\^&\*_=+-])');
  const number = new RegExp('(?=.*[0-9])');
  const length = new RegExp('.{8,32}$');

  //lower case Validation check
  if (lower.test(data)) { lowerCase.classList.add('valid'); }
  else { lowerCase.classList.remove('valid'); }

  //upper case Validation check
  if (upper.test(data)) { upperCase.classList.add('valid'); }
  else { upperCase.classList.remove('valid'); }

  //number Validation check
  if (number.test(data)) { numberCase.classList.add('valid'); }
  else { numberCase.classList.remove('valid'); }

  //special character Validation check
  if (special.test(data)) { specialCase.classList.add('valid'); }
  else { specialCase.classList.remove('valid'); }

  //length Validation check
  if (length.test(data)) { lengthCase.classList.add('valid'); }
  else { lengthCase.classList.remove('valid'); }

  if (length.test(data) && special.test(data) && number.test(data) && upper.test(data) && lower.test(data)) { requisitosCase.classList.add('valid'); }
  else { requisitosCase.classList.remove('valid'); }
}


function setPasswordConfirmValidityAS(str) {
  var password1 = document.getElementById('inputContrseñaAS');
  var password2 = document.getElementById('inputConfirmarContrseñaAS');
  var contrseñaNoCoincide = document.getElementById('contrseñaNoCoincideAS');
  var contrseñaCoincide = document.getElementById('contrseñaCoincideAS');

  if (password1.value === password2.value) {
    contrseñaCoincide.classList.add('valid');
    contrseñaNoCoincide.classList.add('d-none');
    contrseñaCoincide.classList.remove('d-none');
  } else {
    contrseñaCoincide.classList.remove('valid');
    contrseñaNoCoincide.classList.remove('d-none');
    contrseñaCoincide.classList.add('d-none');
  }
}
function togglePasswordF13() {
  var togglePassword1 = document.getElementById('togglePassword13');
  var password1 = document.getElementById('inputContrseñaAS');

  if (password1.type === 'password') {
    password1.setAttribute('type', 'text');
    togglePassword1.classList.add('hide');
  } else {
    password1.setAttribute('type', 'password');
    togglePassword1.classList.remove('hide');
  }
}

function togglePasswordF23() {
  var togglePassword2 = document.getElementById('togglePassword23');
  var password2 = document.getElementById('inputConfirmarContrseñaAS');

  if (password2.type === 'password') {
    password2.setAttribute('type', 'text');
    togglePassword2.classList.add('hide');
  } else {
    password2.setAttribute('type', 'password');
    togglePassword2.classList.remove('hide');
  }
}

function checkPasswordAS(data) {

  var lowerCase = document.getElementById('lowerAS');
  var upperCase = document.getElementById('upperAS');
  var numberCase = document.getElementById('numberAS');
  var specialCase = document.getElementById('specialAS');
  var lengthCase = document.getElementById('lengthAS');
  var requisitosCase = document.getElementById('requisitosAS');

  const lower = new RegExp('(?=.*[a-z])');
  const upper = new RegExp('(?=.*[A-Z])');
  const special = new RegExp('(?=.*[!@#\$%\^&\*_=+-])');
  const number = new RegExp('(?=.*[0-9])');
  const length = new RegExp('.{8,32}$');

  //lower case Validation check
  if (lower.test(data)) { lowerCase.classList.add('valid'); }
  else { lowerCase.classList.remove('valid'); }

  //upper case Validation check
  if (upper.test(data)) { upperCase.classList.add('valid'); }
  else { upperCase.classList.remove('valid'); }

  //number Validation check
  if (number.test(data)) { numberCase.classList.add('valid'); }
  else { numberCase.classList.remove('valid'); }

  //special character Validation check
  if (special.test(data)) { specialCase.classList.add('valid'); }
  else { specialCase.classList.remove('valid'); }

  //length Validation check
  if (length.test(data)) { lengthCase.classList.add('valid'); }
  else { lengthCase.classList.remove('valid'); }

  if (length.test(data) && special.test(data) && number.test(data) && upper.test(data) && lower.test(data)) { requisitosCase.classList.add('valid'); }
  else { requisitosCase.classList.remove('valid'); }
}










var mostrarValidaciones = function (id) {
  var current = document.getElementById(id);
  current.classList.add('valid-feedback');
  current.classList.add('invalid-feedback');
  current.classList.add('validation');
  current.classList.remove('d-none');
}

var ocultarValidaciones = function (id) {
  var current = document.getElementById(id);
  current.classList.remove('valid-feedback');
  current.classList.remove('invalid-feedback');
  current.classList.remove('validation');
  current.classList.add('d-none');
}
function buscarOrganizacion() {
  // Declare variables
  var input, filter, ul, li, a, i, txtValue;
  input = document.getElementById('inputBuscarOrganizacion');
  filter = input.value.toUpperCase();
  ul = document.getElementById("listaDeOrgs");
  li = ul.getElementsByTagName('li');

  // Loop through all list items, and hide those who don't match the search query
  for (i = 0; i < li.length; i++) {
    a = li[i].getElementsByTagName("a")[0];
    txtValue = a.textContent || a.innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      li[i].style.display = "";
    } else {
      li[i].style.display = "none";
    }
  }
}

function buscarAreaSector() {
  // Declare variables
  var input, filter, ul, li, a, i, txtValue;
  input = document.getElementById('inputBuscarSectoresAreas');
  filter = input.value.toUpperCase();
  ul = document.getElementById("listaDeSectoresAreas");
  li = ul.getElementsByTagName('li');

  // Loop through all list items, and hide those who don't match the search query
  for (i = 0; i < li.length; i++) {
    a = li[i].getElementsByTagName("a")[0];
    txtValue = a.textContent || a.innerText;
    if (txtValue.toUpperCase().indexOf(filter) > -1) {
      li[i].style.display = "";
    } else {
      li[i].style.display = "none";
    }
  }
}

//window.onload =
function mostrarAreas() {
  //Get html elements
  var category_sel = document.getElementById("category_sel");
  var topic_sel = document.getElementById("topic_sel");
  //Load countries
  for (var country in countryStateInfo) {
    category_sel.options[category_sel.options.length] = new Option(country, country);
  }
  //County Changed
  category_sel.onchange = function () {
    topic_sel.length = 1; // remove all options bar first
    if (this.selectedIndex < 1)
      return; // done

    for (var state in countryStateInfo[this.value]) {
      topic_sel.options[topic_sel.options.length] = new Option(state, countryStateInfo[this.value][state]);
    }
  }
}

function grabOrgArea() {
  var listOrg, valueOrg, listArea, valueArea;
  listOrg = document.getElementById("category_sel");
  valueOrg = listOrg.options[listOrg.selectedIndex].value;

  listArea = document.getElementById("topic_sel");
  valueArea = listArea.options[listArea.selectedIndex].value;

  //document.write(valueOrg);
  //document.write(valueArea);

}






function mostrarParadas() { //pasar parametros para poder llamar a la funcion arriba
  //Get html elements
  var linea = document.getElementById("linea");
  var paradaInicio = document.getElementById("paradaInicio");
  var paradaFin = document.getElementById("paradaFin");
  var lineaInfo = tipoDeTransportePublicoInfo[this.value];


  //County Changed
  linea.onchange = function () {
    paradaInicio.length = 1; // remove all options bar first
    if (this.selectedIndex < 1)
      return; // done

    for (var paradaInicioTransporte in lineaInfo[this.value]) {
      paradaInicio.options[paradaInicio.options.length] = new Option(paradaInicioTransporte, lineaInfo[this.value][paradaInicioTransporte]);
    }
  }
}




