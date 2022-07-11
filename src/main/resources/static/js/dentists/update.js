window.addEventListener('load', function () {
  const formulario = document.querySelector('#update_dentist_form');

  formulario.addEventListener('submit', function (event) {
    const formData = {
      id: document.querySelector('#dentist_id').value,
      lastName: document.querySelector('#lastname').value,
      firstName: document.querySelector('#firstname').value,
      professionalId: document.querySelector('#professionalId').value,
    };

    const url = '/dentists';
    const settings = {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(formData),
    };

    fetch(url, settings).then((response) => response.json());
  });
});

const findBy = (id) => {
  const url = `/dentists/${id}`;
  const settings = { method: 'GET' };

  fetch(url, settings)
    .then((response) => response.json())
    .then((data) => {
      document.querySelector('#dentist_id').value = data.id;
      document.querySelector('#lastName').value = data.lastName;
      document.querySelector('#firstName').value = data.firstName;
      document.querySelector('#professionalId').value = data.professionalId;
      document.querySelector('#update_dentist_container').style.display =
        'block';
    })
    .catch((error) => {
      alert('Error: ' + error);
    });
};