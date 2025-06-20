var addbtn=document.getElementById('add');

window.onload = function () {

    var username=sessionStorage.getItem('role');
 
};





addbtn.addEventListener('click',function(){
    var name=document.getElementById('name');
    var username=document.getElementById('uname');
    var password=document.getElementById('password');
    var cpassword=document.getElementById('cpassword');

    if(name.value==='')
     {
         alert('Enter Name');
         name.focus;
         return;
     }
     if(username.value==='')
     {
         alert('Enter UserName');
         username.focus;
         return;
     }
     if(password.value==='')
     {
         alert('Enter Password');
         password.focus;
         return;
     }
     if(cpassword.value==='')
     {
         alert('Enter Confirm Password');
         cpassword.focus;
         return;
     }

     if(password.value!==cpassword.value)
     {
         alert('Password & Confirm Password dont match');
         password.focus;
         return;
     }
            

          var firebaseref=firebase.database().ref().child("Users").child(username.value);
          firebaseref.child('UserName').set(username.value);
          firebaseref.child('Password').set(password.value);
          firebaseref.child('Name').set(name.value);
          firebaseref.child("Role").set("Employeee");
          firebaseref.child("Status").set("Active");
          firebaseref.child("EasyRecharge").set(0.0);
          firebaseref.child("Accounts").set(0.0);

  
          name.value="";
          username.value="";
          password.value="";
          cpassword.value="";
         

          alert('Successful..!!!');
});