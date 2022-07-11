const logoutUrl = `${window.location.origin}/logout`;
const logoutElement = document.getElementById('logout');

logoutElement.addEventListener('click', () => {
  window.location.replace(logoutUrl);
});
