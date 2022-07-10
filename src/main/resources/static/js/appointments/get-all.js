window.addEventListener('load', function () {
  const url = '/appointments';
  const settings = { method: 'GET' };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      let table = document.getElementById('appointmentData');
      data = [{ "id": 1, "patient": { "dni": 12345678 }, "dentist": { "professionalLicenseNumber": 111 }}]
      for (appointment of data) {
        let appointmentRow = table.insertRow();
        appointmentRow.id = 'tr_' + appointment.id;

        let updateButton = `<button
          type="button"
          id="btn_id_${appointment.id}"
          class="btn btn-info btn_id"
          onclick="findBy(${appointment.id})">
          Actualizar
        </button>`;

        let deleteButton = `<button
          type="button"
          id="btn_delete_${appointment.id}"
          class="btn btn-danger btn_delete"
          onclick="deleteBy(${appointment.id})">
          Eliminar
        </button>`;

        appointmentRow.innerHTML = `<td class="td_id">${appointment.id}</td>
          <td class="td_patient_dni">${appointment.patient.dni}</td>
          <td class="td_dentist_licenseNumber">${appointment.dentist.professionalLicenseNumber}</td>
          <td>${updateButton} ${deleteButton}</td>`;
      }
    });
});