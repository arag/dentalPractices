const saveAppointment = () => {
    const idField = document.querySelector('#appointmentId')?.value;
    const patientId = document.querySelector('#patientId')?.value;
    const dentistId = document.querySelector('#dentistId')?.value;
    const appointmentDate = document.querySelector('#appointmentDate')?.value;

    const formData = {
      ...(idField) && { id: idField },
      patientId,
      dentistId,
      appointmentDate,
    };

    const endpointUrl = '/appointments';

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

document.querySelector('#saveAppointmentForm').onsubmit = saveAppointment;