const savePatient = () => {
    const idField = document.querySelector('#patientId')?.value;
    const idAddressField = document.querySelector('#addressId')?.value;
    const dniField = document.querySelector('#dni')?.value;
    const lastnameField = document.querySelector('#lastname')?.value;
    const firstnameField = document.querySelector('#firstname')?.value;
    const emailField = document.querySelector('#email')?.value;
    const streetAddressField = document.querySelector('#streetAddress')?.value;
    const numberAddressField = document.querySelector('#numberAddress')?.value;
    const cityField = document.querySelector('#city')?.value;
    const provinceField = document.querySelector('#province')?.value;

    const formData = {
    ...(idField) && { id: idField },
      dni: dniField,
      lastname: lastnameField,
      firstname: firstnameField,
      email: emailField,
      address: {
        ...(idAddressField) && { id: idAddressField },
        street: streetAddressField,
        number: numberAddressField,
        city: cityField,
        province: provinceField
      }
    };

    const endpointUrl = '/patients';

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

document.querySelector('#savePatientForm').onsubmit = savePatient;
