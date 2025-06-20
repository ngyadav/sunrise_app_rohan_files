var searchbtn=document.getElementById('search');
var printbtn=document.getElementById('print');

window.onload = function () {

    var username=sessionStorage.getItem('role');
    // if(username!=null){
    //     if(!username.includes('sr'))
    //     {
    //      window.location = "http://sunrisebusinesses.com/Login/login.html";
    //     }
    // }
    // else
    // {
    //     window.location = "http://sunrisebusinesses.com/Login/login.html";   
    // }



    var database = firebase.database();
    database.ref().child("Services").once('value', function(snapshot){
        if(snapshot.exists()){
            var content = '';
            var sn;
            sn=0;
            snapshot.forEach(function(data){

                var val = data.val();               
                if(val.Type=="APPLIANCES"){
                content +='<tr>';
                sn=sn+1;
                content += '<td>' + sn + '</td>';
                content += '<td>' + val.Date + '</td>';
                content += '<td>' + val.ServiceAknowledgment + '</td>';
                content += '<td>' + val.MobileNumber + '</td>';
                content += '<td>' + val.AlternateNumber + '</td>';
                content += '<td>' + val.Address + '</td>';    
                content += '<td>' + val.Company + '</td>';
                content += '<td>' + val.ModelNumber + '</td>';  
                content += '<td>' + val.Product + '</td>';              
                content += '<td>' + val.Issue + '</td>'; 
                content += '<td>' + val.Userid + '</td>'; 
                content += '</tr>';
                }
              });
            $('#data-table').append(content);
        }
    });  
};

searchbtn.addEventListener('click',function(){
    $("#data-table tbody").remove();
    var sack=document.getElementById('sack');
    var mno=document.getElementById('mno');
    var comp=document.getElementById('comp');
    var date=document.getElementById('date');
    if(sack.value.length!=0)
    {
        var database = firebase.database();
        database.ref().child("Services")
        .orderByChild('ServiceAknowledgment')
        .equalTo(sack.value.toString().toUpperCase())
        .once('value', function(snapshot){
            if(snapshot.exists()){
                var content = '';
                var sn;
                sn=0;
                snapshot.forEach(function(data){                    
                    var val = data.val();         
                    if(val.Type=="APPLIANCES"){
                        content+='<tbody>'; 
                        content +='<tr>';
                        sn=sn+1;
                        content += '<td>' + sn + '</td>';
                        content += '<td>' + val.Date + '</td>';
                        content += '<td>' + val.ServiceAknowledgment + '</td>';
                        content += '<td>' + val.MobileNumber + '</td>';
                        content += '<td>' + val.AlternateNumber + '</td>';
                        content += '<td>' + val.Address + '</td>';    
                        content += '<td>' + val.Company + '</td>';
                        content += '<td>' + val.ModelNumber + '</td>';  
                        content += '<td>' + val.Product + '</td>';              
                        content += '<td>' + val.Issue + '</td>'; 
                        content += '<td>' + val.Userid + '</td>'; 
                        content += '</tr>';
                        content+='</tbody>'; 
                        }
                });
                $('#data-table').append(content);
            }
        });  
    }
    else if(mno.value.length!=0){
        var database = firebase.database();
        database.ref().child("Services")
        .orderByChild('MobileNumber')
        .equalTo(mno.value.toString())
        .once('value', function(snapshot){
            if(snapshot.exists()){
                var content = '';
                var sn;
                sn=0;
                snapshot.forEach(function(data){                    
                    var val = data.val();         
                    if(val.Type=="APPLIANCES"){
                        content+='<tbody>'; 
                        content +='<tr>';
                        sn=sn+1;
                        content += '<td>' + sn + '</td>';
                        content += '<td>' + val.Date + '</td>';
                        content += '<td>' + val.ServiceAknowledgment + '</td>';
                        content += '<td>' + val.MobileNumber + '</td>';
                        content += '<td>' + val.AlternateNumber + '</td>';
                        content += '<td>' + val.Address + '</td>';    
                        content += '<td>' + val.Company + '</td>';
                        content += '<td>' + val.ModelNumber + '</td>';  
                        content += '<td>' + val.Product + '</td>';              
                        content += '<td>' + val.Issue + '</td>'; 
                        content += '<td>' + val.Userid + '</td>'; 
                        content += '</tr>';
                        content+='</tbody>'; 
                        }
                });
                $('#data-table').append(content);
            }
        });
    }
    else if(comp.value.length!=0){
        var database = firebase.database();
        database.ref().child("Services")
        .orderByChild('Company')
        .equalTo(comp.value.toString())
        .once('value', function(snapshot){
            if(snapshot.exists()){
                var content = '';
                var sn;
                sn=0;
                snapshot.forEach(function(data){                    
                    var val = data.val();         
                    if(val.Type=="APPLIANCES"){
                        content+='<tbody>'; 
                        content +='<tr>';
                        sn=sn+1;
                        content += '<td>' + sn + '</td>';
                        content += '<td>' + val.Date + '</td>';
                        content += '<td>' + val.ServiceAknowledgment + '</td>';
                        content += '<td>' + val.MobileNumber + '</td>';
                        content += '<td>' + val.AlternateNumber + '</td>';
                        content += '<td>' + val.Address + '</td>';    
                        content += '<td>' + val.Company + '</td>';
                        content += '<td>' + val.ModelNumber + '</td>';  
                        content += '<td>' + val.Product + '</td>';              
                        content += '<td>' + val.Issue + '</td>'; 
                        content += '<td>' + val.Userid + '</td>'; 
                        content += '</tr>';
                        content+='</tbody>'; 
                        }
                });
                $('#data-table').append(content);
            }
        });
    }
   
    else if(date.value.length==10){
        var database = firebase.database();
         database.ref().child("Services")
         .orderByChild('Date')
         .equalTo(date.value)
         .once('value', function(snapshot){
             if(snapshot.exists()){
                 var content = '';
                 var sn;
                 sn=0;
                 snapshot.forEach(function(data){
                    var val = data.val();         
                    if(val.Type=="APPLIANCES"){
                        content+='<tbody>'; 
                        content +='<tr>';
                        sn=sn+1;
                        content += '<td>' + sn + '</td>';
                        content += '<td>' + val.Date + '</td>';
                        content += '<td>' + val.ServiceAknowledgment + '</td>';
                        content += '<td>' + val.MobileNumber + '</td>';
                        content += '<td>' + val.AlternateNumber + '</td>';
                        content += '<td>' + val.Address + '</td>';    
                        content += '<td>' + val.Company + '</td>';
                        content += '<td>' + val.ModelNumber + '</td>';  
                        content += '<td>' + val.Product + '</td>';              
                        content += '<td>' + val.Issue + '</td>'; 
                        content += '<td>' + val.Userid + '</td>'; 
                        content += '</tr>';
                        content+='</tbody>'; 
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
    var wb = XLSX.utils.table_to_book(document.getElementById('data-table'), {sheet:"AppliancesReport"});
    var wbout = XLSX.write(wb, {bookType:'xlsx', bookSST:true, type: 'binary'});
   
    saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), 'ServiceAppliancesReport.xlsx');
   
});