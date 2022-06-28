window.addEventListener('load', function () {
    const formulario = document.querySelector('#add_new_dentist');

    formulario.addEventListener('submit', function (event) {
      const formData = {
        lastName: document.querySelector('#lastname').value,
        firstName: document.querySelector('#firstname').value,
        professionalId: document.querySelector('#professionalId').value,
      };

      const url = '/dentists';
      const settings = {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      };

      fetch(url, settings)
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            let successAlert = `<div class="alert alert-success alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Dentist added</strong>
                </div>`;

            document.querySelector('#response').innerHTML = successAlert;
            document.querySelector('#response').style.display = 'block';
        })
        .catch((error) => {
            let errorAlert = `<div class="alert alert-danger alert-dismissible">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong>Try again</strong>
                </div>`;

            document.querySelector('#response').innerHTML = errorAlert;
            document.querySelector('#response').style.display = 'block';
            console.log(error);
        });
    });
 });