const findById = (id) => {
  const url = `/appointments/${id}`;
  const settings = { method: 'GET' };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
    // no se por qué esto no me abre para actualizar
        const myAppointmentModal = new bootstrap.Modal(document.getElementById('saveAppointmentForm'));
        const myAppointmentModalInstance = bootstrap.Modal.getInstance(myAppointmentModal);
        myAppointmentModalInstance.show();

        const inputId = `
         <div class="mb-3" style="display:none;">
            <label for="appointmentId" class="form-label">Id</label>
            <input type="number" class="form-control" id="appointmentId" value=${data.id} readonly>
         </div>
        `;

        const form = document.querySelector("#saveAppointmentForm");
        form.innerHTML += inputId;
        form.querySelector('#patientId').value = data.patient.id;
        form.querySelector('#dentistId').value = data.dentist.id;
        form.querySelector('#appointmentDate').value = data.appointmentDate;
    }).catch((error) => {
      alert('Error: ' + error);
    });
};