const findById = (id) => {
  const url = `/patients/${id}`;
  const settings = { method: 'GET' };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
        const myPatientModal = new bootstrap.Modal(document.getElementById('savePatientForm'));
        const myPatientModalInstance = bootstrap.Modal.getInstance(myAppointmentModal);
         myPatientModalInstance.show();

        const inputId = `
         <div class="mb-3" style="display:none;">
            <label for="patientId" class="form-label">Id</label>
            <input type="number" class="form-control" id="patientId" value=${data.id} readonly>
         </div>
        `;

        const inputAddressId = `
          <div class="mb-3" style="display:none;">
             <label for="addressId" class="form-label">Address Id</label>
             <input type="number" class="form-control" id="addressId" value=${data.address.id} readonly>
          </div>
        `;

        const form = document.querySelector("#savePatientForm");
        form.innerHTML += inputId;
        form.innerHTML += inputAddressId;
        form.querySelector('#dni').value = data.dni;
        form.querySelector('#lastname').value = data.lastname;
        form.querySelector('#firstname').value = data.firstname;
        form.querySelector('#email').value = data.email;
        form.querySelector('#streetAddress').value = data.address.street;
        form.querySelector('#numberAddress').value = data.address.number;
        form.querySelector('#city').value = data.address.city;
        form.querySelector('#province').value = data.address.province;
    }).catch((error) => {
      alert('Error: ' + error);
    });
};