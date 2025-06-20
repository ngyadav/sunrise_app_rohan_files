
window.onload = function () {

    var username=this.localStorage.getItem('name');
    if(username===null){
         window.location = "Login.html";    
    }

    var role=this.localStorage.getItem('role');
    if(role!=null){
        if(!role.includes('usercreation')){
            window.location = "Login.html";
        }
    }
    else{
        window.location = "Login.html";
    }

    document.getElementById('dashboard').style.display="none";
    document.getElementById('mobile').style.display="none";
    document.getElementById('accessories').style.display="none";
    document.getElementById('computers').style.display="none";
    document.getElementById('homeappliances').style.display="none";
    document.getElementById('fashion').style.display="none";
    document.getElementById('homepage').style.display="none";
    document.getElementById('offers').style.display="none";
    document.getElementById('rewards').style.display="none";
    document.getElementById('usercreation').style.display="none";
    document.getElementById('userreport').style.display="none";
    document.getElementById('ordersreport').style.display="none";
    document.getElementById('rewardsreport').style.display="none";
    document.getElementById('categoryreport').style.display="none";
    document.getElementById('servicereport').style.display="none";
    document.getElementById('generatedreport').style.display="none";
    document.getElementById('rechargereport').style.display="none";
    document.getElementById('settings').style.display="none";
    document.getElementById('recommended').style.display="none";
    document.getElementById('status').style.display="none";
    document.getElementById('assign').style.display="none";

    var role=localStorage.getItem('role');
    if(role!=null){
    if(role.includes('dashboard'))
        document.getElementById('dashboard').style.display="block";
    if(role.includes('mobile'))
        document.getElementById('mobile').style.display="block";
    if(role.includes('accessories'))    
        document.getElementById('accessories').style.display="block";
    if(role.includes('computers'))    
        document.getElementById('computers').style.display="block";
    if(role.includes('homeappliances'))
        document.getElementById('homeappliances').style.display="block";
    if(role.includes('fashion'))
        document.getElementById('fashion').style.display="block";
    if(role.includes('homepage'))
        document.getElementById('homepage').style.display="block";
    if(role.includes('offers'))
        document.getElementById('offers').style.display="block";
    if(role.includes('rewards'))
        document.getElementById('rewards').style.display="block";
    if(role.includes('usercreation'))
        document.getElementById('usercreation').style.display="block";
        if(role.includes('userreport'))
        document.getElementById('userreport').style.display="block";
        if(role.includes('ordersreport'))
        document.getElementById('ordersreport').style.display="block";
        if(role.includes('rewardsreport'))
        document.getElementById('rewardsreport').style.display="block";
        if(role.includes('categoryreport'))
        document.getElementById('categoryreport').style.display="block";
        if(role.includes('servicereport'))
        document.getElementById('servicereport').style.display="block";
        if(role.includes('generatedreport'))
        document.getElementById('generatedreport').style.display="block";
        if(role.includes('rechargereport'))
        document.getElementById('rechargereport').style.display="block";
        if(role.includes('settings'))
        document.getElementById('settings').style.display="block";
        if(role.includes('status'))
        document.getElementById('status').style.display="block";
        if(role.includes('assign'))
        document.getElementById('assign').style.display="block";
        if(role.includes("recommended"))
        document.getElementById('recommended').style.display="block";
        
        
    }

};


document.getElementById('submit').addEventListener('click',function(){
    var username=document.getElementById('username');
    var name=document.getElementById('name');
    var password=document.getElementById('password');
    var cb1=document.getElementById('cb1');
    var cb2=document.getElementById('cb2');
    var cb3=document.getElementById('cb3');
    var cb4=document.getElementById('cb4');
    var cb5=document.getElementById('cb5');
    var cb6=document.getElementById('cb6');
    var cb7=document.getElementById('cb7');
    var cb8=document.getElementById('cb8');
    var cb9=document.getElementById('cb9');
    var cb10=document.getElementById('cb10');
    var cb11=document.getElementById('cb11');
    var cb12=document.getElementById('cb12');
    var cb13=document.getElementById('cb13');
    var cb14=document.getElementById('cb14');
    var cb15=document.getElementById('cb15');
    var cb16=document.getElementById('cb16');
    var cb17=document.getElementById('cb17');
    var cb18=document.getElementById('cb18');
    var cb19=document.getElementById('cb19');
    var cb20=document.getElementById('cb20');
    var cb21=document.getElementById('cb21');
    var cb22=document.getElementById('cb22');

    if(name.value.length==0){
        alert('Enter Name');
        name.focus;
        return;
    }

    if(username.value.length==0){
         alert('Enter UserName');
         username.focus;
         return;
     }


     if(password.value.length==0){
        alert('Enter Password');
        password.focus;
        return;
    }

    $role='';
    if(cb1.checked==true){
        $role +=cb1.value;        
      }
    if(cb2.checked==true){
    $role +=cb2.value;
    }
    if(cb3.checked==true){
    $role +=cb3.value;  
    }
    if(cb4.checked==true){
    $role +=cb4.value;       
    }
    if(cb5.checked==true){
        $role +=cb5.value;       
    }
    if(cb6.checked==true){
       $role +=cb6.value;       
    }
    if(cb7.checked==true){
        $role +=cb7.value;        
      }
    if(cb8.checked==true){
    $role +=cb8.value;
    }
    if(cb9.checked==true){
    $role +=cb9.value;  
    }
    if(cb10.checked==true){
    $role +=cb10.value;       
    }
    if(cb11.checked==true){
        $role +=cb11.value;       
    }
    if(cb12.checked==true){
       $role +=cb12.value;       
    }
    if(cb13.checked==true){
        $role +=cb13.value;        
      }
    if(cb14.checked==true){
    $role +=cb14.value;
    }
    if(cb15.checked==true){
    $role +=cb15.value;  
    }
    if(cb16.checked==true){
    $role +=cb16.value;       
    }
    if(cb17.checked==true){
        $role +=cb17.value;       
    }
    if(cb18.checked==true){
       $role +=cb18.value;       
    }
    if(cb19.checked==true){
        $role +=cb19.value;       
     }
     if(cb20.checked==true){
        $role +=cb20.value;       
     }
     if(cb21.checked==true){
        $role +=cb21.value;       
     }
     if(cb22.checked==true){
        $role +=cb22.value;       
     }

   var firebaseref=firebase.database().ref().child("WebUser").child(username.value.trim());
        firebaseref.child("UserName").set(String(username.value.trim()));
        firebaseref.child("Name").set(String(name.value.trim()));
        firebaseref.child("Password").set(String(password.value.trim()));
        firebaseref.child("Role").set(String($role));

        username.value="";
        password.value="";
        name.value="";
        cb1.checked=false;
        cb2.checked=false;
        cb3.checked=false;
        cb4.checked=false;
        cb5.checked=false;
        cb6.checked=false;
        cb7.checked=false;
        cb8.checked=false;
        cb9.checked=false;
        cb10.checked=false;
        cb11.checked=false;
        cb12.checked=false;
        cb13.checked=false;
        cb14.checked=false;
        cb15.checked=false;
        cb16.checked=false;
        cb17.checked=false;
        cb18.checked=false;
        cb19.checked=false;
        cb20.checked=false;
        cb21.checked=false;
        cb22.checked=false;
      

        alert('Sucessfully Created!!!');
});




document.getElementById('dsubmit').addEventListener('click',function(){
    var username=document.getElementById('dusername');
    var name=document.getElementById('dname');
    var password=document.getElementById('dpassword');
   
    if(name.value.length==0){
        alert('Enter Name');
        name.focus;
        return;
    }

    if(username.value.length==0){
         alert('Enter UserName');
         username.focus;
         return;
     }


     if(password.value.length==0){
        alert('Enter Password');
        password.focus;
        return;
    }

  
   var firebaseref=firebase.database().ref().child("DeliveryBoys").child(username.value.trim());
        firebaseref.child("UserName").set(String(username.value.trim()));
        firebaseref.child("Number").set(String(username.value.trim()));
        firebaseref.child("Name").set(String(name.value.trim()));
        firebaseref.child("Password").set(String(password.value.trim()));

        username.value="";
        password.value="";
        name.value="";
      alert('Sucessfully Created!!!');
});

