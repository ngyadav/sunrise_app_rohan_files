window.onload = function () {

    var username=this.localStorage.getItem('name');
    if(username===null){
         window.location = "Login.html";    
    }

    var role=this.localStorage.getItem('role');
    if(role!=null){
        if(!role.includes('rechargereport')){
            window.location = "Login.html";
        }
    }
    else{
        window.location = "Login.html";
    }


};