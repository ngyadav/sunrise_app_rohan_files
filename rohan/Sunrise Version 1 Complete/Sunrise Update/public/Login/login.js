var login=document.getElementById('login');
var reset=document.getElementById('reset');
var username=document.getElementById('username');
var password=document.getElementById('password');

username.focus();

login.addEventListener("click",function(){
    var user=username.value;
    var pass=password.value;

    if(user==='')
    {
            alert("Enter Username");
            name.focus;
            return ;
    }

    var firebaseref=firebase.database().ref().child("Web").child("Users").child(user);
               
    return firebaseref.once('value').then(function(snapshot) {
               
                if(pass===(snapshot.val().Password)){    
                        sessionStorage.setItem('username', user);  
                        sessionStorage.setItem('role',snapshot.val().Role);  
                       
                                
                        window.location='../Dashboard/welcome.html';
                }               
                else
                alert("Wrong Password");


        });
       
        
});

reset.addEventListener("click",function(){
        username.value="";
        password.value="";
    
});
