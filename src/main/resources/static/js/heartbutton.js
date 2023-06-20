const heartButton = document.querySelectorAll('.heart-button');
heartButton.forEach((btn) => {
  btn.addEventListener('click', function () {
    this.classList.toggle('active');
  });
})
