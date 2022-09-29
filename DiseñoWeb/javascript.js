function validateAll() {
    const forms = document.querySelectorAll('.requires-validation')
    Array.from(forms)
      .forEach(function (form) {
        form.addEventListener('idRegistrarse', function (event) {
          if (!form.checkValidity()) {
            event.preventDefault()
            event.stopPropagation()
          }
    
          form.classList.add('was-validated')
        }, false)
      })
    }
// Toggle the type of input between password and text
function togglePasswordF() {
    const togglePassword1 = document.getElementById('togglePassword1');
    const password1 = document.getElementById('inputContrseña');

    if (password1.type === 'password') {
        password1.setAttribute('type', 'text');
        togglePassword1.classList.add('hide');
    } else {
        password1.setAttribute('type', 'password');
        togglePassword1.classList.remove('hide');
    }
}


function setPasswordConfirmValidity(str) {
    const password1 = document.getElementById('inputContrseña');
    const password2 = document.getElementById('inputConfirmarContrseña');

    if (password1.value === password2.value) {
         password2.setCustomValidity('');
    } else {
        password2.setCustomValidity('Passwords must match');
    }
    console.log('inputConfirmarContrseña customError ', document.getElementById('inputConfirmarContrseña').validity.customError);
    console.log('inputConfirmarContrseña validationMessage ', document.getElementById('inputConfirmarContrseña').validationMessage);
    }

const email = document.getElementById("inputEmail");

email.addEventListener("input", (event) => {
  if (email.validity.typeMismatch) {
    email.setCustomValidity("I am expecting an e-mail address!");
    email.reportValidity();
  } else {
    email.setCustomValidity("");
  }
});
