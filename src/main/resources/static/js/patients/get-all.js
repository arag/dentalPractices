window.addEventListener('load', function () {
  const url = '/patients';
  const settings = { method: 'GET' };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      let table = document.getElementById('patientData');
      data = [{ "id": 1, "dni": 12345678, "lastname": "Gonzalez", "firstname": "Araceli", "email": "aaa@aaa.com", "admissionDate": "2022-07-10" }]
      for (patient of data) {
        let patientRow = table.insertRow();
        patientRow.id = 'tr_' + patient.id;

        let updateButton = `<button
          type="button"
          id="btn_id_${patient.id}"
          class="btn btn-info btn_id"
          onclick="findBy(${patient.id})">
          Actualizar
        </button>`;

        let deleteButton = `<button
          type="button"
          id="btn_delete_${patient.id}"
          class="btn btn-danger btn_delete"
          onclick="deleteBy(${patient.id})">
          Eliminar
        </button>`;

        patientRow.innerHTML = `<td class="td_id">${patient.id}</td>
          <td class="td_dni">${patient.dni}</td>
          <td class="td_lastname">${patient.lastname.toUpperCase()}</td>
          <td class="td_firstname">${patient.firstname.toUpperCase()}</td>
          <td class="td_email">${patient.email}</td>
          <td class="td_admissionDate">${patient.admissionDate}</td>
          <td>${updateButton} ${deleteButton}</td>`;
      }
    });
});