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

function submitForm() {
	var searchInput = document.getElementById("searchInput");
	var keyword = searchInput.value.trim();
	if (keyword !== "") {
		searchInput.value = keyword;
		document.getElementById("search-bar").submit();
	} else {
		location.reload();
	}
}