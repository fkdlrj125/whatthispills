fetch('/header')
  .then(response => response.text())
  .then(html => {
    document.getElementById('header-placeholder').innerHTML = html;
  });

fetch('/footer')
  .then(response => response.text())
  .then(html => {
    document.getElementById('footer-placeholder').innerHTML = html;
  });

src = "../assets/dist/js/bootstrap.bundle.min.js"

const heartButtons = document.querySelectorAll('.heart-button');

heartButtons.forEach(function (button) {
  button.addEventListener('click', function () {
    this.classList.toggle('active');
  });
});