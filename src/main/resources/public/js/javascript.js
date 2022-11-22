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

function mostrarFormulario(id) {
  var formularios = document.getElementsByClassName("formulariosTransporte");
  for (var i = 0; i < formularios.length; i++) {
      formularios[i].style.display = "none";
  }
  if (id.style.display == "none") {
      id.style.display = "block";

  } else if (id.style.display == "block") {
      id.style.display = "none";
  }
}
