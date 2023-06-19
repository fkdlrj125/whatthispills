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

function refreshPage() {
	var searchInput = document.getElementById("searchInput");
	if (searchInput.value.trim() === "") {
		location.reload();
	}
}