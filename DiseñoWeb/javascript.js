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
function maxDate(){
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