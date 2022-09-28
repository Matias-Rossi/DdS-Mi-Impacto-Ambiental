
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


function validateField() {
    let cont = $('#passwordForm');
    if (cont.hasClass('display-none')) {
      cont.removeClass('display-none');
      $("#password").attr('data-bv-validatorname', true);
      $("#password2").attr('data-bv-validatorname', true);
    } else {
      cont.addClass('display-none');
      $("#password").attr('data-bv-validatorname', false);
      $("#password2").attr('data-bv-validatorname', false);
    }
    console.log(`$("#password").attr('data-bv-validatorname') is ${$("#password2").attr('data-bv-validatorname')}`);
    console.log(`$("#password2").attr('data-bv-validatorname') is ${$("#password2").attr('data-bv-validatorname')}`);
  }
  
  $('#passwordForm').bootstrapValidator({
        message: 'This value is not valid',
        live: 'enabled',
        fields: {
          password: {
            message: 'The password is not valid',
            validators: {
              notEmpty: {
                message: 'The password is required and cannot be empty'
              },
              identical: {
                field: 'password',
                message: 'The passwords must match. '
              },
              stringLength: {
                min: 6,
                max: 30,
                message: 'The password must be more than 6 and less than 30 characters long'
              },
              regexp: {
                regexp: /^[a-zA-Z0-9_]+$/,
                message: 'The password can only consist of alphabetical, number and underscore'
              }
            }
          },
          password2: {
            message: 'The password2 is not valid',
            validators: {
              notEmpty: {
                message: 'The password2 is required and cannot be empty'
              },
              identical: {
                field: 'password',
                message: 'The passwords must match. '
              },
              stringLength: {
                min: 6,
                max: 30,
                message: 'The password2 must be more than 6 and less than 30 characters long'
              },
              regexp: {
                regexp: /^[a-zA-Z0-9_]+$/,
                message: 'The password2 can only consist of alphabetical, number and underscore'
              }
            }
          }
        }
        });
  
  
      $('a.accountFormToggleBtn').on('click', function() {
        validateField();
      }); 