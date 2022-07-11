const deleteById = (id) => {
  const endpointUrl = `/appointments/${id}`;
  const settings = { method: 'DELETE' };

  fetch(endpointUrl, settings)
    .then((response) => {
        if (response.ok) {
            let row_id = '#tr_' + id;
            document.querySelector(row_id).remove();
        }
        return response.json();
     })
    .catch((error) => {
      console.error(error);
    });
};