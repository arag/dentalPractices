const findById = (id) => {
  const url = `/dentists/${id}`;
  const settings = { method: 'GET' };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
        const myDentistModal = new bootstrap.Modal(document.getElementById('saveDentistModal'));
        myDentistModal.show();

        const inputId = `
         <div class="mb-3" style="display:none;">
            <label for="dentistId" class="form-label">Id</label>
            <input type="number" class="form-control" id="dentistId" value=${data.id} readonly>
         </div>
        `;

        const form = document.querySelector("#saveDentistForm");
        form.innerHTML += inputId;
        form.querySelector('#lastname').value = data.lastname;
        form.querySelector('#firstname').value = data.firstname;
        form.querySelector('#licenseNumber').value = data.professionalLicenseNumber;
    }).catch((error) => {
      alert('Error: ' + error);
    });
};