window.onload = function () {

    var username=this.localStorage.getItem('name');
    if(username===null){
         window.location = "Login.html";    
    }

    var role=this.localStorage.getItem('role');
    if(role!=null){
        if(!role.includes('userreport')){
            window.location = "Login.html";
        }
    }
    else{
        window.location = "Login.html";
    }

    var database = firebase.database();
    database.ref().child("Users").once('value', function(snapshot){
        if(snapshot.exists()){
            var content = '';
            var sn;
            sn=0;
            snapshot.forEach(function(data){
                var val = data.val();               
                content +='<tr>';
                sn=sn+1;
                content += '<td>' + sn + '</td>';
                content += '<td>' + val.Name + '</td>';
                content += '<td>' + val.Email + '</td>';
                content += '<td>' + val.Phone + '</td>';
                content += '<td>' + val.Referral + '</td>';    
                content += '<td>' + val.Rewards + '</td>';
                content += '<td>' + val.Role + '</td>';
                content += '<td>' + val.Status + '</td>';
                content += '<td>' + val.IMEI + '</td>';
                content += '</tr>';
            });
            $('#datatable').append(content);
        }
    });  
};

// searchbtn.addEventListener('click',function(){
//     $("#data-table tbody").remove();
//     var name=document.getElementById('name');
//     var number=document.getElementById('number');
//     var role=document.getElementById('role');
//     var status=document.getElementById('status');
//     if(name.value.length!=0)
//     {
//         var database = firebase.database();
//         database.ref().child("Users")
//         .orderByChild('Name')
//         .equalTo(name.value.toString().toUpperCase())
//         .once('value', function(snapshot){
//             if(snapshot.exists()){
//                 var content = '';
//                 var sn;
//                 sn=0;
//                 snapshot.forEach(function(data){
//                     var val = data.val();         
//                     content+='<tbody>';      
//                     content +='<tr>';
//                     sn=sn+1;
//                     content += '<td>' + sn + '</td>';
//                     content += '<td>' + val.Name + '</td>';
//                     content += '<td>' + val.Email + '</td>';
//                     content += '<td>' + val.Phone + '</td>';
//                     content += '<td>' + val.Referral + '</td>';    
//                     content += '<td>' + val.Rewards + '</td>';
//                     content += '<td>' + val.Role + '</td>';
//                     content += '<td>' + val.Status + '</td>';
//                     content += '<td>' + val.IMEI + '</td>';
//                     content += '</tr>';
//                     content+='</tbody>';
//                 });
//                 $('#data-table').append(content);
//             }
//         });  
//     }
//     else if(number.value.length==10){
//         var database = firebase.database();
//         database.ref().child("Users")
//         .orderByChild('Phone')
//         .equalTo(number.value.toString().toUpperCase())
//         .once('value', function(snapshot){
//             if(snapshot.exists()){
//                 var content = '';
//                 var sn;
//                 sn=0;
//                 snapshot.forEach(function(data){
//                     var val = data.val();         
//                     content+='<tbody>';      
//                     content +='<tr>';
//                     sn=sn+1;
//                     content += '<td>' + sn + '</td>';
//                     content += '<td>' + val.Name + '</td>';
//                     content += '<td>' + val.Email + '</td>';
//                     content += '<td>' + val.Phone + '</td>';
//                     content += '<td>' + val.Referral + '</td>';    
//                     content += '<td>' + val.Rewards + '</td>';
//                     content += '<td>' + val.Role + '</td>';
//                     content += '<td>' + val.Status + '</td>';
//                     content += '<td>' + val.IMEI + '</td>';
//                     content += '</tr>';
//                     content+='</tbody>';
//                 });
//                 $('#data-table').append(content);
//             }
//         });
//     }
//     else if(role.options[role.selectedIndex].value!="Select"){
//        var database = firebase.database();
//         database.ref().child("Users")
//         .orderByChild('Role')
//         .equalTo(role.options[role.selectedIndex].value)
//         .once('value', function(snapshot){
//             if(snapshot.exists()){
//                 var content = '';
//                 var sn;
//                 sn=0;
//                 snapshot.forEach(function(data){
//                     var val = data.val();         
//                     content+='<tbody>';      
//                     content +='<tr>';
//                     sn=sn+1;
//                     content += '<td>' + sn + '</td>';
//                     content += '<td>' + val.Name + '</td>';
//                     content += '<td>' + val.Email + '</td>';
//                     content += '<td>' + val.Phone + '</td>';
//                     content += '<td>' + val.Referral + '</td>';    
//                     content += '<td>' + val.Rewards + '</td>';
//                     content += '<td>' + val.Role + '</td>';
//                     content += '<td>' + val.Status + '</td>';
//                     content += '<td>' + val.IMEI + '</td>';
//                     content += '</tr>';
//                     content+='</tbody>';
//                 });
//                 $('#data-table').append(content);
//             }
//         });
//     }
//     else if(status.options[status.selectedIndex].value!="Select"){
//        var database = firebase.database();
//         database.ref().child("Users")
//         .orderByChild('Status')
//         .equalTo(status.options[status.selectedIndex].value)
//         .once('value', function(snapshot){
//             if(snapshot.exists()){
//                 var content = '';
//                 var sn;
//                 sn=0;
//                 snapshot.forEach(function(data){
//                     var val = data.val();         
//                     content+='<tbody>';      
//                     content +='<tr>';
//                     sn=sn+1;
//                     content += '<td>' + sn + '</td>';
//                     content += '<td>' + val.Name + '</td>';
//                     content += '<td>' + val.Email + '</td>';
//                     content += '<td>' + val.Phone + '</td>';
//                     content += '<td>' + val.Referral + '</td>';    
//                     content += '<td>' + val.Rewards + '</td>';
//                     content += '<td>' + val.Role + '</td>';
//                     content += '<td>' + val.Status + '</td>';
//                     content += '<td>' + val.IMEI + '</td>';
//                     content += '</tr>';
//                     content+='</tbody>';
//                 });
//                 $('#data-table').append(content);
//             }
//         });
//     }

   
// });


    document.getElementById('print').addEventListener('click',function(){
        var wb = XLSX.utils.table_to_book(document.getElementById('data-table'), {sheet:"UserReports"});
        var wbout = XLSX.write(wb, {bookType:'xlsx', bookSST:true, type: 'binary'});       
        saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), 'UserReports.xlsx');       
      });
      
      
      function s2ab(s) {
        var buf = new ArrayBuffer(s.length);
        var view = new Uint8Array(buf);
        for (var i=0; i<s.length; i++) view[i] = s.charCodeAt(i) & 0xFF;
        return buf;
      }