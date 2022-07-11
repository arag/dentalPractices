const saveNewPatient = () => {
    const dniField = document.querySelector('#dni')?.value;
    const lastnameField = document.querySelector('#lastname')?.value;
    const firstnameField = document.querySelector('#firstname')?.value;
    const emailField = document.querySelector('#email')?.value;
    const streetAddressField = document.querySelector('#streetAddress')?.value;
    const numberAddressField = document.querySelector('#numberAddress')?.value;
    const cityField = document.querySelector('#city')?.value;
    const provinceField = document.querySelector('#province')?.value;

    const formData = {
      dni: dniField,
      lastname: lastnameField,
      firstname: firstnameField,
      email: emailField,
      address: {
        street: streetAddressField,
        number: numberAddressField,
        city: cityField,
        province: provinceField
      }
    };

    const endpointUrl = '/patients';

    const settings = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    };

  fetch(endpointUrl, settings)
    .then((response) => response.json())
    .then((data) => {
      console.log('OK', data);
    })
    .catch((error) => {
      console.error(error);
    });
};

var modalWrap = null;
/**
 *
 * @param {string} title
 * @param {object} data dentist data
 * @param {string} yesBtnLabel label of Yes button
 */
const showModal = (title, data, yesBtnLabel = 'Yes') => {
  if (modalWrap !== null) {
    modalWrap.remove();
  }

  modalWrap = document.createElement('div');
  modalWrap.innerHTML = `
    <div class="modal fade" tabindex="-1" id="savePatient">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header bg-light">
            <h5 class="modal-title">${title}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form>
            <div class="mb-3">
               <label for="dni" class="form-label">DNI</label>
               <input type="number" class="form-control" id="dni" required>
              </div>
              <div class="mb-3">
                <label for="lastname" class="form-label">Apellido</label>
                <input type="text" class="form-control" id="lastname" required>
              </div>
              <div class="mb-3">
                <label for="firstname" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="firstname" required>
              </div>
              <div class="mb-3">
                <label for="email" class="form-label">Email</label>
                <input type="email" class="form-control" id="email" required>
              </div>
              <h3>Domicilio</h3>
              <div class="mb-3">
                <label for="street" class="form-label">Calle</label>
                <input type="text" class="form-control" id="streetAddress" required>
              </div>
              <div class="mb-3">
                <label for="numberStreet" class="form-label">Número</label>
                <input type="number" class="form-control" id="numberStreet" required>
              </div>
              <div class="mb-3">
                <label for="city" class="form-label">Ciudad</label>
                <input type="text" class="form-control" id="city" required>
              </div>
              <div class="mb-3">
                <label for="province" class="form-label">Provincia</label>
                <input type="text" class="form-control" id="province" required>
              </div>
              <button type="submit" class="btn btn-primary modal-success-btn" data-bs-dismiss="modal">${yesBtnLabel}</button>
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  `;

  modalWrap.querySelector('#savePatient').onsubmit = saveNewPatient;

  document.body.append(modalWrap);

  var modal = new bootstrap.Modal(modalWrap.querySelector('.modal'));
  modal.show();
};
