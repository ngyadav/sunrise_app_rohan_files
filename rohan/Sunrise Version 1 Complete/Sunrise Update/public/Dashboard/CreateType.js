var addbtn=document.getElementById('add');
var updatebtn=document.getElementById('update');
var deletebtn=document.getElementById('delete');
var searchbtn1=document.getElementById('search1');

window.onload = function () {

   
    var username=sessionStorage.getItem('username');
    // if(username===null){
       
    //      window.location = "Login.html";       
    // }

     var role=sessionStorage.getItem('role');
    // if(!role.includes("LO"))
    // {
    //      window.location = "Login.html";
    // }

    var select = document.getElementById("loc");
    var firebaseref=firebase.database().ref().child("Type")
    return firebaseref.once('value').then(function(snapshot) {
       snapshot.forEach(function(child){
        var option = document.createElement('option');
        option.text = option.value = child.key;
        select.add(option, 0);
       });
       select.value="Select";
    });

  
};


searchbtn1.addEventListener('click',function(){
    

 var username=document.getElementById("zone1");

  var location=document.getElementById("loc");

    username.value=location.options[location.selectedIndex].value;



    addbtn.style.visibility="hidden";
updatebtn.style.visibility="visible";
deletebtn.style.visibility="visible";

});


addbtn.addEventListener('click',function(){
     var username=document.getElementById("zone1");
     var stype=document.getElementById("stype");
     
     if(username.value.length==0)
      {
          alert("Enter Type");
          username.focus();
          return;
      }

       if(stype.options[stype.selectedIndex].value=="Select")
        {
            alert("Select Type");
            stype.focus();
            return;
        }
      
     
    var firebaseref=firebase.database().ref().child("Type");
        firebaseref.child(username.value.toUpperCase().trim()).set(String(stype.options[stype.selectedIndex].value));
        

        username.value="";
       
        alert("Successful!!!!");
        
          addbtn.style.visibility="visible";
            updatebtn.style.visibility="hidden";
            deletebtn.style.visibility="hidden";
});


updatebtn.addEventListener('click',function(){
  var username=document.getElementById("zone1");
     var stype=document.getElementById("stype");
     
     if(username.value.length==0)
      {
          alert("Enter Type");
          username.focus();
          return;
      }

       if(stype.options[stype.selectedIndex].value=="Select")
   {
       alert("Select Type");
       stype.focus();
       return;
   }
      
     
    var firebaseref=firebase.database().ref().child("Type");
        firebaseref.child(username.value.toUpperCase().trim()).set(String(stype.options[stype.selectedIndex].value));
        

        username.value="";
       
        alert("Successful!!!!");
        
          addbtn.style.visibility="visible";
            updatebtn.style.visibility="hidden";
            deletebtn.style.visibility="hidden";

});


deletebtn.addEventListener('click',function(){
     var username=document.getElementById("zone1");
     
     if(username.value.length==0)
      {
          alert("Enter Type");
          username.focus();
          return;
      }
      
     
    var firebaseref=firebase.database().ref().child("Type");
        firebaseref.child(username.value.toUpperCase().trim()).set(null);
        

        username.value="";
       
        alert("Successful!!!!");
         addbtn.style.visibility="visible";
        updatebtn.style.visibility="hidden";
        deletebtn.style.visibility="hidden";
});