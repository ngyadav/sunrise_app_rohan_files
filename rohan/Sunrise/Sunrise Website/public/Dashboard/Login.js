var addbtn=document.getElementById('submit');
addbtn.addEventListener('click',function(){
     var username=document.getElementById("username");
     var password=document.getElementById("password");
      if(username.value.length==0)
      {
          alert("Enter Username");
          username.focus();
          return;
      }
       if(password.value.length==0)
      {
          alert("Enter Password");
          password.focus();
          return;
      }
     var firebaseref=firebase.database().ref().child("WebUser").child(username.value);
               
    return firebaseref.once('value').then(function(snapshot) {

            if(snapshot.exists())
               {
                if(password.value===(snapshot.val().Password)){    
                        localStorage.setItem('name', snapshot.val().Name);  
                        localStorage.setItem('role',snapshot.val().Role); 
                        window.location='index.html';
                }               
                else
                alert("Wrong Password");
            }
            else{
                alert("User Not Registered");
            }


        });
});