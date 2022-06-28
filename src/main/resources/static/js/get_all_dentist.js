window.addEventListener('load', function () {
  const url = '/dentists';
  const settings = { method: 'GET' };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      for (dentist of data) {
        var table = document.getElementById('dentistTable');
        var dentistRow = table.insertRow();
        let tr_id = 'tr_' + dentist.id;
        dentistRow.id = tr_id;

        let updateButton = `<button
          type="button"
          id="btn_id_${dentist.id}"
          class="btn btn-info btn_id"
          onclick="findBy(${dentist.id})">
          ${dentist.id}
        </button>`;

        let deleteButton = `<button
          type="button"
          id="btn_delete_${dentist.id}"
          class="btn btn-danger btn_delete"
          onclick="deleteBy(${dentist.id})">
          Eliminar
        </button>`;

        dentistRow.innerHTML = `<td>${updateButton}</td>
          <td class="td_lastName">${dentist.lastName.toUpperCase()}</td>
          <td class="td_firstName">${dentist.firstName.toUpperCase()}</td>
          <td class="td_professionalId">${dentist.professionalId}</td>
          <td>${deleteButton}</td>`;
      }
    });
});