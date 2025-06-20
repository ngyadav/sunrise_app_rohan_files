
var submitbtn1 = document.getElementById("madd");
var submitbtn3 = document.getElementById("sadd");
var submitbtn2 = document.getElementById("madd1");
var submitbtn4 = document.getElementById("madd2");

var path4;

var pushid=[];
window.onload = function () {

    var username=this.localStorage.getItem('name');
    if(username===null){
         window.location = "Login.html";    
    }

    var role=this.localStorage.getItem('role');
    if(role!=null){
        if(!role.includes('homepage')){
            window.location = "Login.html";
        }
    }
    else{
        window.location = "Login.html";
    }


    


var select1 = document.getElementById("sname1");
var firebaseref=firebase.database().ref().child("CategoryNew");
    return firebaseref.once('value').then(function(snapshot) {
       snapshot.forEach(function(child){
        var option1 = document.createElement('option');
        option1.text = option1.value = child.val().Name;
        pushid.push(child.val().PushId);
        select1.add(option1,0);
       });
       select1.value="Select";
       pushid.reverse();

        var select100 = document.getElementById("sname100");
        firebase.database().ref().child("CategoryNew")
        .once('value').then(function(snapshot) {
        snapshot.forEach(function(child){
            var option1 = document.createElement('option');
            option1.text = option1.value = child.val().Name;
            select100.add(option1,0);
          })
        });


       var select2 = document.getElementById("sname2");
       var firebaseref=firebase.database().ref().child("Grocery");
           return firebaseref.once('value').then(function(snapshot) {
              snapshot.forEach(function(child){
               var option1 = document.createElement('option');
               option1.text =  child.val().Name;
               option1.value = child.val().PushId;
               select2.add(option1,0);
              });
              select2.value="Select";
                  });   

           });    


};





$("#mimage1").change(function(){

    var mname=document.getElementById("mname");

    if(mname.options[mname.selectedIndex].value=="Select")
        {
            alert("Select Homepage Baner Name");
            mname.focus();
            return;
        }

    if($('#mimage1').get(0).files.length==0){
        alert("Add Homepage Baner Image");
        return;
    }
    if(!window.path4=='')
    {
    window.mtemp--;
    }
     const ref = firebase.storage().ref("/Homepage/");
    const file = document.querySelector('#mimage1').files[0]
    const name = mname.options[mname.selectedIndex].value
    const metadata = {
    contentType: file.type
    };
    const task = ref.child(name).put(file, metadata);
    task
    .then(snapshot => snapshot.ref.getDownloadURL())
    .then((url) => {
       window.path4=url;
        window.mtemp++;
    })
    .catch(console.error);
});


submitbtn1.addEventListener('click',function(){
    
    var mname=document.getElementById("mname");
    var select=document.getElementById("sname100");

    if(mname.options[mname.selectedIndex].value=="Select")
        {
            alert("Select  Baner Name");
            mname.focus();
            return;
        }

        if(select.options[select.selectedIndex].value=="Select")
        {
            alert("Select  Product Name");
            select.focus();
            return;
        }

        if(window.path4.length==0){
            alert("Please Wait for image to upload");
            return;
        }

        
    var firebaseref=firebase.database().ref().child("AppContent").child("HomePage").child("Carousel").child(mname.options[mname.selectedIndex].value);
    firebaseref.child("Name").set(window.path4);
    firebaseref.child("Path").set(pushid[select.selectedIndex]);

    window.path4="";

    $('#mimage1').val('');

    alert("Successfully Added");

});


submitbtn2.addEventListener('click',function(){
    
    var mname=document.getElementById("mname1");
    var sname=document.getElementById("sname1");

    if(mname.options[mname.selectedIndex].value=="Select")
        {
            alert("Select  Baner Name");
            mname.focus();
            return;
        }

        if(sname.options[sname.selectedIndex].value=="Select")
        {
            alert("Select Product  Name");
            sname.focus();
            return;
        }

        
    var firebaseref=firebase.database().ref().child("AppContent").child("HomePage1").child(mname.options[mname.selectedIndex].value);
    

    firebase.database().ref().child("CategoryNew").child(pushid[sname.selectedIndex])
            .once('value') .then(function(snapshot) {
                 firebaseref.set(snapshot.val());
                alert("Successfully Added");
        });



});



submitbtn3.addEventListener('click',function(){
    
    var sheading=document.getElementById("sheading");
    var heading=document.getElementById("heading");

    if(sheading.options[sheading.selectedIndex].value=="Select")
        {
            alert("Select  Heading Number");
            sheading.focus();
            return;
        }

        if(heading.value.length==0)
        {
            alert("Enter Heading  Name");
            heading.focus();
            return;
        }

        
    firebase.database().ref().child("AppContent").child("Heading").child(sheading.options[sheading.selectedIndex].value).set(heading.value)
   alert("Successfully Added");
   
});



submitbtn4.addEventListener('click',function(){
    
    var mname=document.getElementById("mname2");
    var sname=document.getElementById("sname2");

    if(mname.options[mname.selectedIndex].value=="Select")
        {
            alert("Select  Product Number");
            mname.focus();
            return;
        }

        if(sname.options[sname.selectedIndex].value=="Select")
        {
            alert("Select Product  Name");
            sname.focus();
            return;
        }

        
    var firebaseref=firebase.database().ref().child("AppContent").child("HomePage2").child(mname.options[mname.selectedIndex].value);
    

    firebase.database().ref().child("Grocery").child(sname.options[sname.selectedIndex].value)
            .once('value') .then(function(snapshot) {

              

                 firebaseref.set(snapshot.val());
                alert("Successfully Added");
                    
        });



});