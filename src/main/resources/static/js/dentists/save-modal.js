const saveNewDentist = () => {
    const lastnameField = document.querySelector('#lastname')?.value;
    const firstnameField = document.querySelector('#firstname')?.value;
    const licenseField = document.querySelector('#licenseNumber')?.value;

    const formData = {
      lastname: lastnameField,
      firstname: firstnameField,
      professionalLicenseNumber: licenseField,
    };

    const endpointUrl = '/dentists';

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
    <div class="modal fade" tabindex="-1" id="saveDentist">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header bg-light">
            <h5 class="modal-title">${title}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form>
              <div class="mb-3">
                <label for="lastname" class="form-label">Apellido</label>
                <input type="text" class="form-control" id="lastname" required>
              </div>
              <div class="mb-3">
                <label for="firstname" class="form-label">Nombre</label>
                <input type="text" class="form-control" id="firstname" required>
              </div>
              <div class="mb-3">
                <label for="licenseNumber" class="form-label">Matrícula</label>
                <input type="numer" class="form-control" id="licenseNumber" required>
              </div>
              <button type="submit" class="btn btn-primary modal-success-btn" data-bs-dismiss="modal">${yesBtnLabel}</button>
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  `;

  modalWrap.querySelector('#saveDentist').onsubmit = saveNewDentist;

  document.body.append(modalWrap);

  var modal = new bootstrap.Modal(modalWrap.querySelector('.modal'));
  modal.show();
};
