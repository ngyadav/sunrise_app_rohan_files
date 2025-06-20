var mobilebtn=document.getElementById('mobilebtn');
var arrivalbtn=document.getElementById('arrivalbtn');
var baner1btn=document.getElementById('baner1btn');
var baner2btn=document.getElementById('baner2btn');
var baner1submit=document.getElementById('baner1submit');
var baner2submit=document.getElementById('baner2submit');
var carouselsubmit=document.getElementById('carouselsubmit');
var carouselbtn=document.getElementById('carouselbtn');
var path1,path2,path3;
window.onload = function () {
    this.document.getElementById('carouselname').value="Image";
    var select = document.getElementById("number");
   
    var firebaseref=firebase.database().ref().child("Category");
    return firebaseref.orderByChild("Category").equalTo("Mobile").once('value').then(function(snapshot) {
           snapshot.forEach(function(child){
            var option = document.createElement('option');
            option.text = option.value = child.val().Name;
            select.add(option, 0);
           });
           select.value="Select";
            var select1 = document.getElementById("newarrival1");
            var select3 = document.getElementById('newarrival')
            var firebaseref=firebase.database().ref().child("Category");
            return firebaseref.orderByChild("Stock").equalTo("In Stock").once('value').then(function(snapshot) {
                snapshot.forEach(function(child){
                 var option = document.createElement('option');
                 option.text = option.value = child.val().Name;
                 select1.add(option, 0);
                 select3.add(option,0);
                });
                select1.value="Select";
                load();
             }); 
        });    
       


  
};



function load(){
   var firebaseref=firebase.database().ref().child("Category");
   var select1 = document.getElementById("newarrival1");
            var firebaseref=firebase.database().ref().child("Category");
            return firebaseref.orderByChild("Stock").equalTo("In Stock").once('value').then(function(snapshot) {
                snapshot.forEach(function(child){
                 var option = document.createElement('option');
                 option.text = option.value = child.val().Name;
                 select1.add(option, 0);
                 });
                select1.value="Select";
             }); 
          
}


baner1btn.addEventListener('click',function(){  

if($('#image1').get(0).files.length==0){
    alert("Select Image 1");
     return;
  }

  var ref = firebase.storage().ref();
  var file = $('#image1').get(0).files[0];
  var name1 = (+new Date()) + '-' + file.name;
  var metadata = { contentType: file.type };
  var task = ref.child("Homepage").child("Baner1").child(name1).put(file, metadata);


  task
  .then(snapshot => snapshot.ref.getDownloadURL())
  .then(url=>        
       window.path1=url)
  .catch(console.error);

   alert('Image Added Successful');
   baner1submit.style.visibility="visible";
});

carouselbtn.addEventListener('click',function(){  

    if($('#image3').get(0).files.length==0){
        alert("Select Image ");
         return;
      }
    

      var ref = firebase.storage().ref();
      var file = $('#image3').get(0).files[0];
      var name1 = (+new Date()) + '-' + file.name;
      var metadata = { contentType: file.type };
      var task = ref.child("Homepage").child("Carousel").child(name1).put(file, metadata);
    
    
      task
      .then(snapshot => snapshot.ref.getDownloadURL())
      .then(url=>        
           window.path3=url)
      .catch(console.error);
    
       alert('Image Added Successful');
       carouselsubmit.style.visibility="visible";
    });




baner2btn.addEventListener('click',function(){   

if($('#image2').get(0).files.length==0){
    alert("Select Image 1");
     return;
  }

  var ref = firebase.storage().ref();
  var file = $('#image2').get(0).files[0];
  var name1 = (+new Date()) + '-' + file.name;
  var metadata = { contentType: file.type };
  var task = ref.child("Homepage").child("Baner2").child(name1).put(file, metadata);


  task
  .then(snapshot => snapshot.ref.getDownloadURL())
  .then(url=>        
       window.path2=url)
  .catch(console.error);

   alert('Image Added Successful');
   baner2submit.style.visibility="visible";
});


baner1submit.addEventListener('click',function(){
    var firebaseref=firebase.database().ref().child("AppContent").child("HomePage").child("Baner");
    firebaseref.child("Baner1").set(window.path1);
    
    $('#image1').val('');
    alert("Succesfully Added!!!");
    baner1submit.style.visibility="hidden";
});

baner2submit.addEventListener('click',function(){
    var firebaseref=firebase.database().ref().child("AppContent").child("HomePage").child("Baner");
    firebaseref.child("Baner2").set(window.path2);
    
    $('#image2').val('');
    alert("Succesfully Added!!!");
    baner2submit.style.visibility="hidden";
});

carouselsubmit.addEventListener('click',function(){
    var name=document.getElementById('carouselname');
    var select1 = document.getElementById("newarrival");
    if(name.value.length==0)
    {
        alert("Enter Name");
        name.focus();
        return;
    }



    var firebaseref=firebase.database().ref().child("AppContent").child("HomePage").child("Carousel").child(name.value);
    firebaseref.child("Name").set(window.path3);
    firebaseref.child("Path").set(select1.options[select1.selectedIndex].value)
    
    $('#image3').val('');
    name.value="Image";
    alert("Succesfully Added!!!");
    carouselsubmit.style.visibility="hidden";
});

mobilebtn.addEventListener('click',function(){
    // var name=document.getElementById("namem");    
  var type=document.getElementById("mobile");

//   if(name.value.length==0)
//   {
//       alert("Enter Name");
//       name.focus();
//       return;
//   }


  var firebaseref=firebase.database().ref().child("Category").child(number.options[number.selectedIndex].value);
  return firebaseref.once('value').then(function(snapshot) {
      var firebase1=firebase.database().ref().child('AppContent').child('HomePage').child('Mobiles').child(mobile.options[mobile.selectedIndex].value);
      firebase1.child("Image").set(snapshot.val().Image1);
      firebase1.child("Name").set(snapshot.val().Name);
      firebase1.child("Price").set(snapshot.val().Price);
      firebase1.child("Rewards").set(snapshot.val().Cashback);

    //   name.value="";
      alert("Change Successfull..!");
    });



});


arrivalbtn.addEventListener('click',function(){
    // var name=document.getElementById("namea");   
    var type=document.getElementById("arrival");

    // if(name.value.length==0)
    // {
    //     alert("Enter Name");
    //     name.focus();
    //     return;
    // }
  

    var firebaseref=firebase.database().ref().child("Category").child(newarrival.options[newarrival.selectedIndex].value);
    return firebaseref.once('value').then(function(snapshot) {
       var firebase1=firebase.database().ref().child('AppContent').child('HomePage').child('NewArrivals').child(arrival.options[arrival.selectedIndex].value);
        firebase1.child("Image").set(snapshot.val().Image1);
        firebase1.child("Name").set(snapshot.val().Name);
        firebase1.child("Price").set(snapshot.val().Price);
        firebase1.child("Rewards").set(snapshot.val().Cashback);

        // name.value="";
        alert("Change Successfull..!");
      });

});