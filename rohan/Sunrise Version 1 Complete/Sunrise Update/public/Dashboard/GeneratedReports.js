var searchbtn=document.getElementById('search');
var printbtn=document.getElementById('print');

window.onload = function () {

    var username=sessionStorage.getItem('role');
    // if(username!=null){
    //     if(!username.includes('ser'))
    //     {
    //      window.location = "http://sunrisebusinesses.com/Login/login.html";
    //     }
    // }
    // else
    // {
    //     window.location = "http://sunrisebusinesses.com/Login/login.html";   
    // }



    var database = firebase.database();
    database.ref().child("Rewards").once('value', function(snapshot){
        if(snapshot.exists()){
            var content = '';
            var sn;
            sn=0;
            snapshot.forEach(function(data){

                var val = data.val();               
                if(val.Generated!=null){
                content +='<tr>';
                sn=sn+1;
                content += '<td>' + sn + '</td>';
                content += '<td>' + val.Date + '</td>';
                content += '<td>' + val.TransactionId + '</td>';
                content += '<td>' + val.AuthorisedName + '</td>';
                content += '<td>' + val.AuthorisedType + '</td>';
                content += '<td>' + val.Amount + '</td>';    
                content += '<td>' + val.Authorised + '</td>';
                content += '<td>' + val.AuthorisedBalance + '</td>'; 
                content += '<td>' + val.Generated + '</td>';               
               
                content += '</tr>';
                }
              });
            $('#data-table').append(content);
        }
    });  
};

searchbtn.addEventListener('click',function(){
    $("#data-table tbody").remove();
    var tid=document.getElementById('tid');
    var tname=document.getElementById('tname');
    var ttype=document.getElementById('ttype');
    var date=document.getElementById('date');
    var uid=document.getElementById('uid');
    var aid=document.getElementById('aid');
    if(tid.value.length!=0)
    {
        var database = firebase.database();
        database.ref().child("Rewards")
        .orderByChild('TransactionId')
        .equalTo(tid.value.toString().toUpperCase())
        .once('value', function(snapshot){
            if(snapshot.exists()){
                var content = '';
                var sn;
                sn=0;
                snapshot.forEach(function(data){                    
                    var val = data.val();         
                    if(val.Generated!=null){
                    content+='<tbody>';      
                    content +='<tr>';
                    sn=sn+1;
                    content += '<td>' + sn + '</td>';
                    content += '<td>' + val.Date + '</td>';
                    content += '<td>' + val.TransactionId + '</td>';
                    content += '<td>' + val.AuthorisedName + '</td>';
                    content += '<td>' + val.AuthorisedType + '</td>';
                    content += '<td>' + val.Amount + '</td>';    
                    content += '<td>' + val.Authorised + '</td>';
                    content += '<td>' + val.AuthorisedBalance + '</td>'; 
                    content += '<td>' + val.Generated + '</td>';               
                
                    content += '</tr>';
                    content +='</tbody>';
                    }
                });
                $('#data-table').append(content);
            }
        });  
    }
    else if(tname.value.length!=0){
        var database = firebase.database();
        database.ref().child("Rewards")
        .orderByChild('AuthorisedName')
        .equalTo(tname.value.toString())
        .once('value', function(snapshot){
            if(snapshot.exists()){
                var content = '';
                var sn;
                sn=0;
                snapshot.forEach(function(data){                    
                    var val = data.val();         
                    if(val.Generated!=null){
                    content+='<tbody>';      
                    content +='<tr>';
                    sn=sn+1;
                    content += '<td>' + sn + '</td>';
                    content += '<td>' + val.Date + '</td>';
                    content += '<td>' + val.TransactionId + '</td>';
                    content += '<td>' + val.AuthorisedName + '</td>';
                    content += '<td>' + val.AuthorisedType + '</td>';
                    content += '<td>' + val.Amount + '</td>';    
                    content += '<td>' + val.Authorised + '</td>';
                    content += '<td>' + val.AuthorisedBalance + '</td>'; 
                    content += '<td>' + val.Generated + '</td>';               
                
                    content += '</tr>';
                    content +='</tbody>';
                    }
                });
                $('#data-table').append(content);
            }
        });
    }
    else if(ttype.options[ttype.selectedIndex].value!="Select"){
       var database = firebase.database();
        database.ref().child("Rewards")
        .orderByChild('AuthorisedType')
        .equalTo(ttype.options[ttype.selectedIndex].value)
        .once('value', function(snapshot){
            if(snapshot.exists()){
                var content = '';
                var sn;
                sn=0;
                snapshot.forEach(function(data){
                    var val = data.val();         
                    if(val.Generated!=null){
                    content+='<tbody>';      
                    content +='<tr>';
                    sn=sn+1;
                    content += '<td>' + sn + '</td>';
                    content += '<td>' + val.Date + '</td>';
                    content += '<td>' + val.TransactionId + '</td>';
                    content += '<td>' + val.AuthorisedName + '</td>';
                    content += '<td>' + val.AuthorisedType + '</td>';
                    content += '<td>' + val.Amount + '</td>';    
                    content += '<td>' + val.Authorised + '</td>';
                    content += '<td>' + val.AuthorisedBalance + '</td>'; 
                    content += '<td>' + val.Generated + '</td>';               
                
                    content += '</tr>';
                    content +='</tbody>';
                    }
                });
                $('#data-table').append(content);
            }
        });
    }
    else if(date.value.length==10){
        var database = firebase.database();
         database.ref().child("Rewards")
         .orderByChild('Date')
         .equalTo(date.value)
         .once('value', function(snapshot){
             if(snapshot.exists()){
                 var content = '';
                 var sn;
                 sn=0;
                 snapshot.forEach(function(data){
                    var val = data.val();         
                    var val = data.val();         
                    if(val.Generated!=null){
                    content+='<tbody>';      
                    content +='<tr>';
                    sn=sn+1;
                    content += '<td>' + sn + '</td>';
                    content += '<td>' + val.Date + '</td>';
                    content += '<td>' + val.TransactionId + '</td>';
                    content += '<td>' + val.AuthorisedName + '</td>';
                    content += '<td>' + val.AuthorisedType + '</td>';
                    content += '<td>' + val.Amount + '</td>';    
                    content += '<td>' + val.Authorised + '</td>';
                    content += '<td>' + val.AuthorisedBalance + '</td>'; 
                    content += '<td>' + val.Generated + '</td>';               
                
                    content += '</tr>';
                    content +='</tbody>';
                    }
                 });
                 $('#data-table').append(content);
             }
         });
     }
     else if(uid.value.length==10){
        var database = firebase.database();
        database.ref().child("Rewards")
        .orderByChild('Authorised')
        .equalTo("+91"+uid.value.toString().toUpperCase())
        .once('value', function(snapshot){
            if(snapshot.exists()){
                var content = '';
                var sn;
                sn=0;
                snapshot.forEach(function(data){
                    var val = data.val();         
                    var val = data.val();         
                    if(val.Generated!=null){
                    content+='<tbody>';      
                    content +='<tr>';
                    sn=sn+1;
                    content += '<td>' + sn + '</td>';
                    content += '<td>' + val.Date + '</td>';
                    content += '<td>' + val.TransactionId + '</td>';
                    content += '<td>' + val.AuthorisedName + '</td>';
                    content += '<td>' + val.AuthorisedType + '</td>';
                    content += '<td>' + val.Amount + '</td>';    
                    content += '<td>' + val.Authorised + '</td>';
                    content += '<td>' + val.AuthorisedBalance + '</td>'; 
                    content += '<td>' + val.Generated + '</td>';               
                
                    content += '</tr>';
                    content +='</tbody>';
                    }
                });
                $('#data-table').append(content);
            }
        });
    }
    else if(aid.value.length==10){
        var database = firebase.database();
        database.ref().child("Rewards")
        .orderByChild('Generated')
        .equalTo("+91"+aid.value.toString().toUpperCase())
        .once('value', function(snapshot){
            if(snapshot.exists()){
                var content = '';
                var sn;
                sn=0;
                snapshot.forEach(function(data){
                    var val = data.val();         
                    var val = data.val();         
                    if(val.Generated!=null){
                    content+='<tbody>';      
                    content +='<tr>';
                    sn=sn+1;
                    content += '<td>' + sn + '</td>';
                    content += '<td>' + val.Date + '</td>';
                    content += '<td>' + val.TransactionId + '</td>';
                    content += '<td>' + val.AuthorisedName + '</td>';
                    content += '<td>' + val.AuthorisedType + '</td>';
                    content += '<td>' + val.Amount + '</td>';    
                    content += '<td>' + val.Authorised + '</td>';
                    content += '<td>' + val.AuthorisedBalance + '</td>'; 
                    content += '<td>' + val.Generated + '</td>';               
                
                    content += '</tr>';
                    content +='</tbody>';
                    }
                });
                $('#data-table').append(content);
            }
        });
    }

   
});


function s2ab(s) {
                var buf = new ArrayBuffer(s.length);
                var view = new Uint8Array(buf);
                for (var i=0; i<s.length; i++) view[i] = s.charCodeAt(i) & 0xFF;
                return buf;
}


printbtn.addEventListener('click',function(){
    var wb = XLSX.utils.table_to_book(document.getElementById('data-table'), {sheet:"GeneratedRewardReport"});
    var wbout = XLSX.write(wb, {bookType:'xlsx', bookSST:true, type: 'binary'});
   
    saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), 'GeneratedRewardReport.xlsx');
   
});