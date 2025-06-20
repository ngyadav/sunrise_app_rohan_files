var addbtn=document.getElementById('add');
window.onload = function () {
    var username=sessionStorage.getItem('role');
    if(username!=null){
        if(!username.includes('re'))
        {
         window.location = "http://sunrisebusinesses.com/Login/login.html";
        }
    }
    else
    {
        window.location = "http://sunrisebusinesses.com/Login/login.html";   
    }

    var select = document.getElementById("number");
    var firebaseref=firebase.database().ref().child("SellerUsers");
        return firebaseref.once('value').then(function(snapshot) {
           snapshot.forEach(function(child){
            var option = document.createElement('option');
            option.text = option.value = child.val().Phone;
            select.add(option, 0);
           });
           select.value="Select";
        });    
    
};



addbtn.addEventListener("click",function(){
    var amount = document.getElementById("amount");
    var mpin = document.getElementById("mpin");
    var select = document.getElementById("number");

    if(amount.value.length==0)
    {
        alert("Enter Amount");
        amount.focus();
        return;
    }
    if(mpin.value.length==0)
    {
        alert("Enter Mpin");
        mpin.focus();
        return;
    }
    if(isNaN(select.options[select.selectedIndex].value))
    {
        alert("Select Number");
        select.focus();
        return;
    }

    if(mpin.value=="1201")
    {
        addbtn.style.visibility="hidden";
        
          var firebaseref=firebase.database().ref().child("SellerUsers").child("+91"+select.options[select.selectedIndex].value);
         return firebaseref.once('value').then(function(snapshot) {
            var a = parseInt(snapshot.val().Rewards);
            a=a+parseInt(amount.value);
            firebaseref.child("Rewards").set(a.toString());
                    var firebaseref1=firebase.database().ref().child("Rewards");
                    return firebaseref1.once('value').then(function(snapshot){
                        var tot=parseInt(snapshot.numChildren());
                        var abc="TRANS"+tot.toString();

                        var firebaseref2=firebase.database().ref().child("Rewards").push();
                        firebaseref2.child("Pushid").set(String(firebaseref.getKey()));
                        firebaseref2.child("Authorised").set("+91"+select.options[select.selectedIndex].value);
                        firebaseref2.child("Amount").set(amount.value.toString());
                        firebaseref2.child("AuthorisedBalance").set(a.toString());
                        firebaseref2.child("TransactionId").set(abc);
                        var today = new Date();
                        var dd = today.getDate();
                        var mm = today.getMonth()+1; //January is 0!
                        var yyyy = today.getFullYear();
                        if(dd<10) {
                            dd = '0'+dd
                        } 
                        if(mm<10) {
                            mm = '0'+mm
                        } 
                        today = dd + '-' + mm + '-' + yyyy;
                        firebaseref2.child("Date").set(today);
                        firebaseref2.child("Generated").set("+919663191201");
                        firebaseref2.child("AuthorisedType").set("Cr");
                        firebaseref2.child("AuthorisedName").set("TopUp");
                        alert("Transaction Succesfful");
                        amount.value="";
                        mpin.value="";

                        addbtn.style.visibility="visible";
                    });
          

     });
    }
    else{
        alert("Mpin Incorrect");
        return;
    }

  
});