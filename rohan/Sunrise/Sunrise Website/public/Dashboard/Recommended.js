var submitbtn2 = document.getElementById("madd1");
var pushid=[];
var units=[];
window.onload = function () {
   
    var username=this.localStorage.getItem('name');
    if(username===null){
         window.location = "Login.html";    
    }

    var role=this.localStorage.getItem('role');
    if(role!=null){
        if(!role.includes('mobile')){
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
        var option = document.createElement('option');
        var option1 = document.createElement('option');
        option1.text = option1.value = child.val().Name;
        units.push(child.val().Name);
        pushid.push(child.val().PushId);
        select1.add(option1,0);
       });
       select1.value="Select";
            units.reverse();
            pushid.reverse();
           });    



};


submitbtn2.addEventListener('click',function(){
    
    var mname=document.getElementById("mname1");
    var sname=document.getElementById("sname1");

    if(mname.options[mname.selectedIndex].value=="Select")
        {
            alert("Select Product Number");
            mname.focus();
            return;
        }

        if(sname.options[sname.selectedIndex].value=="Select")
        {
            alert("Select Product  Name");
            sname.focus();
            return;
        }

        
    var firebaseref=firebase.database().ref().child("AppContent").child("Recommended").child(mname.options[mname.selectedIndex].value);
    

    firebase.database().ref().child("CategoryNew").child(pushid[sname.selectedIndex])
            .once('value') .then(function(snapshot) {

              

                 firebaseref.set(snapshot.val());
                alert("Successfully Added");
                    
        });



});