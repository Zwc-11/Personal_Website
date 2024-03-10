const wrapper = document.querySelector('.wrapper');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');
const btnPopup = document.querySelector('.btnLogin-pop');
const iconClose = document.querySelector('.icon-close');
const projectloginbtn = document.querySelector('.Product-login-btn');



registerLink.addEventListener('click', ()=> {
    wrapper.classList.add('active');
});

loginLink.addEventListener('click', ()=> {
    wrapper.classList.remove('active');
});

btnPopup.addEventListener('click', ()=> {
    wrapper.classList.add('active-popup');
});
iconClose.addEventListener('click', ()=> {
    wrapper.classList.remove('active-popup');
});
projectloginbtn.getElementsByClassName("Product-login-btn").onclick = function(){
    location.href = "login_register.html";
};

