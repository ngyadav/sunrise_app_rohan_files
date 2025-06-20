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
     var firebaseref=firebase.database().ref().child("Web").child("Users").child(username.value);
               
    return firebaseref.once('value').then(function(snapshot) {
               
                if(password.value===(snapshot.val().Password)){    
                        sessionStorage.setItem('name', snapshot.val().Name);  
                        sessionStorage.setItem('role',snapshot.val().Role); 
                        sessionStorage.setItem('srole',snapshot.val().SRole); 

                       
                                
                        window.location='Dashboard.html';
                }               
                else
                alert("Wrong Password");


        });
});