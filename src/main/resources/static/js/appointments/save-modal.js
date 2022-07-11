const saveNewAppointment = () => {
    const patientId = document.querySelector('#patientId')?.value;
    const dentistId = document.querySelector('#dentistId')?.value;
    const appointmentDate = document.querySelector('#appointmentDate')?.value;

    const formData = {
      patientId,
      dentistId,
      appointmentDate,
    };

    const endpointUrl = '/appointments';

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
    <div class="modal fade" tabindex="-1" id="saveAppointment">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header bg-light">
            <h5 class="modal-title">${title}</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form>
              <div class="mb-3">
                <label for="patientId" class="form-label">Id de Paciente</label>
                <input type="number" class="form-control" id="patientId" required>
              </div>
              <div class="mb-3">
                <label for="dentistId" class="form-label">Id de Dentista</label>
                <input type="number" class="form-control" id="dentistId" required>
              </div>
              <div class="mb-3">
                <label for="appointmentDate" class="form-label">Fecha de Turno</label>
                <input type="date" class="form-control" id="appointmentDate" required>
              </div>
              <button type="submit" class="btn btn-primary modal-success-btn" data-bs-dismiss="modal">${yesBtnLabel}</button>
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancelar</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  `;

  modalWrap.querySelector('#saveAppointment').onsubmit = saveNewAppointment;

  document.body.append(modalWrap);

  var modal = new bootstrap.Modal(modalWrap.querySelector('.modal'));
  modal.show();
};