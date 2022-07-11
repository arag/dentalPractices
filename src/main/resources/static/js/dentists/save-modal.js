const saveDentist = () => {
    const idField = document.querySelector('#dentistId')?.value;
    const lastnameField = document.querySelector('#lastname')?.value;
    const firstnameField = document.querySelector('#firstname')?.value;
    const licenseField = document.querySelector('#licenseNumber')?.value;

    const formData = {
      ...(idField) && { id: idField },
      lastname: lastnameField,
      firstname: firstnameField,
      professionalLicenseNumber: licenseField,
    };

    const endpointUrl = '/dentists';

    const method = idField ? 'PUT' : 'POST';

    const settings = {
      method,
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

document.querySelector('#saveDentistForm').onsubmit = saveDentist;
