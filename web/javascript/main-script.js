'use strict';
class JobVacancy{
    constructor(id,organizationId,name,uploadDate,requirement,status){
        this.id=id;
        this.organizationId=organizationId;
        this.name=name;
        this.uploadDate=uploadDate;
        this.requirement=requirement;
        this.status=status;
    }
}


let slideIndex = 1;
window.addEventListener('keydown', (event) => {
    const keyName = event.key;
    if (keyName === 'ArrowLeft') {
        showDivs(slideIndex -= 1);
    }
    else if (keyName === 'ArrowRight') {
        showDivs(slideIndex += 1);
    }
});

const showDots = function showDots() {
    let x = document.getElementsByClassName('notificationsSlides');
    let parent = document.getElementById('slider-selector');
    for (let i = 0; i < x.length; i++) {
        let child = document.createElement('span');
        child.setAttribute('class', 'slider-selection');
        child.innerHTML = '&#8718;';
        child.setAttribute('onclick', `currentDiv(${i + 1})`);
        parent.appendChild(child);
    }
};
const plusDivs = function plusDivs(n) {
    showDivs(slideIndex += n);
};

const currentDiv = function currentDiv(n) {
    showDivs(slideIndex = n);
};

const showDivs = function showDivs(n) {
    let i;
    let x = document.getElementsByClassName('notificationsSlides');
    let dots = document.getElementsByClassName('slider-selection');
    if (n > x.length) {
        slideIndex = 1
    }
    if (n < 1) {
        slideIndex = x.length
    }
    for (i = 0; i < x.length; i++) {
        x[i].style.display = 'none';
    }
    for (i = 0; i < dots.length; i++) {
        dots[i].className = dots[i].className.replace(' selected', '');
    }
    x[slideIndex - 1].style.display = 'block';
    dots[slideIndex - 1].className += ' selected';
};