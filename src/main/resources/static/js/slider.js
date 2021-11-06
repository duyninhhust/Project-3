const slider1 = document.getElementById('glide_1')
const slider2 = document.getElementById('glide_2')
const slider3 = document.getElementById('glide_3')
const slider4 = document.getElementById('glide_4')
const slider5 = document.getElementById('glide_5')
if(slider1){
   new Glide(slider1, {
      type : 'carousel',
      startAt : 0,
      autoplay : 3000,
      hoverpause : true,
      perView : 1,
      animationDuration : 800,
      AnimationTimingFunc : 'linear',
   }).mount()
}
if(slider2){
   new Glide(slider2, {
      type : 'carousel',
      startAt : 0,
      autoplay : 2000,
      hoverpause : true,
      perView : 4,
      animationDuration : 800,
      AnimationTimingFunc : 'cubic-bezier(.165, .840, .44, .1)',
      breakpoints : {
         1200: {
            perView: 3,
         }, 
         768: {
            perView: 2,
         }
      }
   }).mount()
}


// Testimonial
if(slider3){
   new Glide(slider3, {
      type: "carousel",
      startAt: 0,
      autoplay: 2000,
      hoverpause: true,
      perView: 1,
      animationDuration: 800,
      AnimationTimingFunc: "ease-in-out",
   }).mount();
}

if(slider4){
   new Glide(slider4, {
      type: "carousel",
      startAt: 0,
      hoverpause: true,
      autoplay: 2000,
      perView: 1,
      animationDuration: 800,
      AnimationTimingFunc: "ease-in-out",
      breakpoints:{
         998:{
            perView: 2
         },
         768:{
            perView: 1
         },
      }
   }).mount();
}

//slider 5 News

if (slider5) {
   new Glide("#glide_5", {
     type: "carousel",
     startAt: 0,
     perView: 3,
     rewin: false,
     autoplay: 3000,
     animationDuration: 800,
     animationTimingFunc: "cubic-bezier(0.165, 0.840, 0.440, 1.000)",
     breakpoints: {
       998: {
         perView: 2,
       },
       768: {
         perView: 1,
       },
     },
   }).mount();
 }