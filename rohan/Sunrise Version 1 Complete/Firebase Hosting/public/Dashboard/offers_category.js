var addbtn=document.getElementById('addbtn');
var deletebtn=document.getElementById('deletebtn');
var submitbtn=document.getElementById('submitbtn');
var path1;

deletebtn.addEventListener('click',function(){
alert("hi");
});


submitbtn.addEventListener('click',function(){
    var offerno=document.getElementById('number');
    var terms=document.getElementById('terms');
    if(offerno.value.length==0)
    {
        alert("Enter OfferNumber");
        offerno.focus();
        return;
    }
    if(terms.value.length==0)
    {
        alert("Enter Terms");
        terms.focus();
        return;
    }

    var firebaseref=firebase.database().ref().child("Offers").push();
    firebaseref.child("Pushid").set(String(firebaseref.getKey()));
    firebaseref.child("Offerno").set(offerno.value);
    firebaseref.child("Image").set(window.path1);
    firebaseref.child("Text").set(terms.value);
    offerno.value="";
    terms.value="";
    $('#image1').val('');
    alert("Succesfully Added!!!");
    submitbtn.style.visibility="hidden";
});

addbtn.addEventListener('click',function(){



if($('#image1').get(0).files.length==0){
    alert("Select Image 1");
     return;
  }

  var ref = firebase.storage().ref();
  var file = $('#image1').get(0).files[0];
  var name1 = (+new Date()) + '-' + file.name;
  var metadata = { contentType: file.type };
  var task = ref.child("Offers").child(name1).put(file, metadata);


  task
  .then(snapshot => snapshot.ref.getDownloadURL())
  .then(url=>        
       window.path1=url)
  .catch(console.error);

   alert('Image Added Successful');
   submitbtn.style.visibility="visible";

});



