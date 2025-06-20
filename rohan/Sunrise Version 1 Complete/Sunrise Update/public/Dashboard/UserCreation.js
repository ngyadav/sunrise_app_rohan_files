var addbtn=document.getElementById('add');

window.onload = function () {

    var username=sessionStorage.getItem('role');
   //  if(username!=null){
   //      if(!username.includes('us'))
   //      {
   //       window.location = "http://sunrisebusinesses.com/Login/login.html";
   //      }
   //  }
   //  else
   //  {
   //      window.location = "http://sunrisebusinesses.com/Login/login.html";   
   //  }



   //  var database = firebase.database();
   //  database.ref().child("Web").child("Users").once('value', function(snapshot){
   //      if(snapshot.exists()){
   //          var content = '';
   //          snapshot.forEach(function(data){
   //              var val = data.val();
   //              var sn=parseInt("1");
   //              content +='<tr>';
   //              content += '<td>' +sn++ + '</td>';
   //              content += '<td>' + val.UserName + '</td>';
   //              content += '<td>' + val.Password + '</td>';
   //              content += '<td>' + val.Role + '</td>';
   //              content += '</tr>';
   //          });
   //          $('#data-table').append(content);
   //      }
   //  });  
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
    


        $role="";
        if(document.getElementById('role1').checked==true)
            $role =$role+document.getElementById('role1').value+",";
        if(document.getElementById('role2').checked==true)
            $role =$role+document.getElementById('role2').value+",";
        if(document.getElementById('role3').checked==true)
            $role =$role+document.getElementById('role3').value+",";
        if(document.getElementById('role4').checked==true)
            $role =$role+document.getElementById('role4').value+",";
        if(document.getElementById('role5').checked==true)
            $role =$role+document.getElementById('role5').value+",";
        if(document.getElementById('role6').checked==true)
            $role =$role+document.getElementById('role6').value+",";
        if(document.getElementById('role7').checked==true)
           $role =$role+document.getElementById('role7').value+",";
        if(document.getElementById('role8').checked==true)
          $role =$role+document.getElementById('role8').value+",";
          if(document.getElementById('role9').checked==true)
          $role =$role+document.getElementById('role9').value+",";
          if(document.getElementById('role10').checked==true)
          $role =$role+document.getElementById('role10').value+",";
          if(document.getElementById('role11').checked==true)
          $role =$role+document.getElementById('role11').value+",";


          $srole="";
        if(document.getElementById('srole1').checked==true)
            $srole =$srole+document.getElementById('srole1').value+",";
        if(document.getElementById('srole2').checked==true)
            $srole =$srole+document.getElementById('srole2').value+",";
        if(document.getElementById('srole3').checked==true)
            $srole =$srole+document.getElementById('srole3').value+",";
        if(document.getElementById('srole4').checked==true)
            $srole =$srole+document.getElementById('srole4').value+",";
        if(document.getElementById('srole5').checked==true)
            $srole =$srole+document.getElementById('srole5').value+",";
        if(document.getElementById('srole6').checked==true)
            $srole =$srole+document.getElementById('srole6').value+",";
        if(document.getElementById('srole7').checked==true)
           $srole =$srole+document.getElementById('srole7').value+",";
        if(document.getElementById('srole8').checked==true)
          $srole =$srole+document.getElementById('srole8').value+",";
          if(document.getElementById('srole9').checked==true)
          $srole =$srole+document.getElementById('srole9').value+",";
          if(document.getElementById('srole10').checked==true)
          $srole =$srole+document.getElementById('srole10').value+",";
          if(document.getElementById('srole11').checked==true)
          $srole =$srole+document.getElementById('srole11').value+",";
         

          var firebaseref=firebase.database().ref().child("Web").child("Users").child(username.value);
          firebaseref.child('UserName').set(username.value);
          firebaseref.child('Password').set(password.value);
          firebaseref.child('Name').set(name.value);
          firebaseref.child('Role').set($role);
          firebaseref.child('SRole').set($srole);

  
          name.value="";
          username.value="";
          password.value="";
          cpassword.value="";
          document.getElementById('role1').checked=false;
          document.getElementById('role2').checked=false;
          document.getElementById('role3').checked=false;
          document.getElementById('role4').checked=false;
          document.getElementById('role5').checked=false;
          document.getElementById('role6').checked=false;
          document.getElementById('role7').checked=false;
          document.getElementById('role8').checked=false;
          document.getElementById('role9').checked=false;
          document.getElementById('role10').checked=false;
          document.getElementById('role11').checked=false;

          document.getElementById('srole1').checked=false;
          document.getElementById('srole2').checked=false;
          document.getElementById('srole3').checked=false;
          document.getElementById('srole4').checked=false;
          document.getElementById('srole5').checked=false;
          document.getElementById('srole6').checked=false;
          document.getElementById('srole7').checked=false;
          document.getElementById('srole8').checked=false;
          document.getElementById('srole9').checked=false;
          document.getElementById('srole10').checked=false;
          document.getElementById('srole11').checked=false;

          alert('Successful..!!!');
});