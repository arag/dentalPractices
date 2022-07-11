window.addEventListener('load', function () {
  const url = '/dentists';
  const settings = { method: 'GET' };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      let table = document.getElementById('dentistData');
      for (dentist of data) {
        let dentistRow = table.insertRow();
        dentistRow.id = 'tr_' + dentist.id;

        let updateButton = `<button
          type="button"
          id="btn_id_${dentist.id}"
          class="btn btn-info btn_id"
          onclick="findById(${dentist.id})">
          Actualizar
        </button>`;

        let deleteButton = `<button
          type="button"
          id="btn_delete_${dentist.id}"
          class="btn btn-danger btn_delete"
          onclick="deleteById(${dentist.id})">
          Eliminar
        </button>`;

        dentistRow.innerHTML = `<td class="td_id">${dentist.id}</td>
          <td class="td_lastname">${dentist.lastname.toUpperCase()}</td>
          <td class="td_firstname">${dentist.firstname.toUpperCase()}</td>
          <td class="td_licenseNumber">${dentist.professionalLicenseNumber}</td>
          <td>${updateButton} ${deleteButton}</td>`;
      }
    });
});

