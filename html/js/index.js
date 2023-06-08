let mainText = document.querySelector("#sle1");
window.addEventListener("scroll", function() {
    let value = this.window.scrollY;
    console.log("scrollY", value);

    if (value > 200){
        mainText.style.animation = 'slides 1s ease-out forwards';
    } else {
        mainText.style.animation = "outslide 1s ease-out forwards";
    }
});
