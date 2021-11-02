//scroll Smooth
$('.nav__list li a').on('click', function(e){
   if(this.hash !== ''){
      e.preventDefault();
      const hash = this.hash;
      $('html, body').animate({
         scrollTop : $(hash).offset().top
      }, 800);
   }
})

$(window).scroll(function(){
   var scrollbarLocation = $(this).scrollTop();
   scrollLink.each(function(){
      var sectionOffset = $(this.hash).offset().top
      if(sectionOffset <= scrollbarLocation){
         $(this).parent().addClass('active')
         $(this).parent().siblings().removeClass('active')
      }
   })
})






const navOpen = document.querySelector('.nav__hamburger');
const navClose = document.querySelector('.close__toggle');
const menu = document.querySelector('.nav__menu');
const navContainer = document.querySelector('.nav__menu');

navOpen.addEventListener('click', ()=>{
   menu.classList.add('open');
   document.body.classList.add("active");
   navContainer.style.left = "0";
   navContainer.style.width = "30rem";
})

navClose.addEventListener('click', ()=>{
   menu.classList.remove('open');
   document.body.classList.remove("active");
   navContainer.style.left = "-30rem";
   navContainer.style.width = "0";
})


const popup = document.querySelector('.popup');
const closePopup = document.querySelector('.popup__close');

if(popup){
   closePopup.addEventListener('click', ()=>{
      popup.classList.add("hide__popup");
   });
   window.addEventListener('load', ()=>{
      setTimeout(()=>{
         popup.classList.remove('hide__popup')
      }, 500);
   })
}


const navBar = document.querySelector('.navigation');
const gotoTop = document.querySelector('.goto-top');
const scrollLink = document.querySelectorAll('.scroll-link');

// Smooth Scroll
Array.from(scrollLink).map(link =>{
   link.addEventListener("click", e=>{
      e.preventDefault();

      const id = e.currentTarget.getAttribute("href").slice(1);
      const element = document.getElementById(id);
      const navHeight = navBar.getBoundingClientRect().height;
      const fixNav = navBar.classList.contains('fix__nav');
      let position = element.offsetTop - navHeight;

      if(!fixNav){
         position = position - navHeight;
      }
      window.scrollTo({
         left: 0,
         top: position,
      })
      navContainer.style.left = "-30rem";
      document.body.classList.remove("active");
   });
});
// Fix NavBar
window.addEventListener('scroll', e=>{
   const scrollHeight = window.pageYOffset;
   const navHeight = navBar.getBoundingClientRect().height;

   if(scrollHeight > navHeight){
      navBar.classList.add("fix__nav");
   }else{
      navBar.classList.remove("fix__nav");
   }
   if(scrollHeight > 300){
      gotoTop.classList.add('show-top');
   }else{
      gotoTop.classList.remove("show-top");
   }
})


// show navBar

window.addEventListener("scroll", e => {
   const scrollHeight = window.pageYOffset;
   const navHeight = navBar.getBoundingClientRect().height;
   if (scrollHeight > navHeight) {
     navBar.classList.add("fix__nav");
   } else {
     navBar.classList.remove("fix__nav");
   }
 
   if (scrollHeight > 300) {
     gotoTop.classList.add("show-top");
   } else {
     gotoTop.classList.remove("show-top");
   }
 })


















AOS.init()