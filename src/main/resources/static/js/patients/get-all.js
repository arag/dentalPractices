window.addEventListener('load', function () {
  const url = '/patients';
  const settings = { method: 'GET' };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      let table = document.getElementById('patientData');
      for (patient of data) {
        let patientRow = table.insertRow();
        patientRow.id = 'tr_' + patient.id;

        let updateButton = `<button
          type="button"
          id="btn_id_${patient.id}"
          class="btn btn-info btn_id"
          onclick="findById(${patient.id})">
          Actualizar
        </button>`;

        let deleteButton = `<button
          type="button"
          id="btn_delete_${patient.id}"
          class="btn btn-danger btn_delete"
          onclick="deleteById(${patient.id})">
          Eliminar
        </button>`;

        patientRow.innerHTML = `<td class="td_id">${patient.id}</td>
          <td class="td_dni">${patient.dni}</td>
          <td class="td_lastname">${patient.lastname.toUpperCase()}</td>
          <td class="td_firstname">${patient.firstname.toUpperCase()}</td>
          <td class="td_email">${patient.email}</td>
          <td>${updateButton} ${deleteButton}</td>`;
      }
    });
});