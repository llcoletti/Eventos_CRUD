const menu = document.querySelector('.mainMenu');
const subMenu = document.querySelector('.submenu');

menu.addEventListener('click', ()=>{
    console.log('ok')
    subMenu.style.display = 'block';
})

function handleClick(event){
    subMenu.classList.toggle('d-block')
    //subMenu.style.display = 'block';
    //subMenu.classList.
}