var updatebtn=document.getElementById('updatebtn');


window.onload = function () {
   
        var username=sessionStorage.getItem('role');
        if(username!=null){
            if(!username.includes('ii'))
            {
             window.location = "http://sunrisebusinesses.com/Login/login.html";
            }
        }
        else
        {
            window.location = "http://sunrisebusinesses.com/Login/login.html";   
        }
  
};

updatebtn.addEventListener('click',function(){
var number=document.getElementById('mobilenumber');
var imei=document.getElementById('imei1');
if(number.value.length==0)
{
    alert("Enter Mobile Number");
    number.focus();
    return;
}
if(imei.value.length==0)
{
    alert("Enter Imei");
    imei.focus;
    return;
}
alert(imei.value.length);
if(imei.value.length!=16)
{
    alert("Enter 16 digits Imei");
    imei.focus;
    return;
}

var firebaseref=firebase.database().ref().child("Users").child("+91"+number.value);
firebaseref.child("IMEI").set(imei.value);

alert("Change Successful....!!!");
imei.value="";
number.value="";
});