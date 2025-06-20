var searchbtn=document.getElementById('search');
var printbtn=document.getElementById('print');

window.onload = function () {

    var username=sessionStorage.getItem('role');
    // if(username!=null){
    //     if(!username.includes('usr'))
    //     {
    //      window.location = "http://sunrisebusinesses.com/Login/login.html";
    //     }
    // }
    // else
    // {
    //     window.location = "http://sunrisebusinesses.com/Login/login.html";   
    // }



    var database = firebase.database();
    database.ref().child("Orders").once('value', function(snapshot){
        if(snapshot.exists()){
            var content = '';
            var sn;
            sn=0;
            snapshot.forEach(function(data){
                var val = data.val();               
                content +='<tr>';
                sn=sn+1;
                content += '<td>' + sn + '</td>';
                content += '<td>' + val.ProductName + '</td>';
                content += '<td>' + val.Mrp + '</td>';
                content += '<td>' + val.Rewards + '</td>';
                content += '<td>' + val.Quantity + '</td>';    
                content += '<td>' + val.OrderNo + '</td>';
                content += '<td>' + val.OrderStatus + '</td>';
                content += '<td>' + val.Userid + '</td>';
                content += '<td>' + val.Referral + '</td>';
                content += '<td>' + val.Date + '</td>';
                content += '</tr>';
              });
            $('#data-table').append(content);
        }
    });  
};

searchbtn.addEventListener('click',function(){
    $("#data-table tbody").remove();
    var onumber=document.getElementById('onumber');
    var number=document.getElementById('number');
    var rnumber=document.getElementById('rnumber');
    var date=document.getElementById('date');
    var status=document.getElementById('status');

    if(onumber.value.length!=0)
    {
        var database = firebase.database();
        database.ref().child("Orders")
        .orderByChild('OrderNo')
        .equalTo(onumber.value.toString().toUpperCase())
        .once('value', function(snapshot){
            if(snapshot.exists()){
                var content = '';
                var sn;
                sn=0;
                snapshot.forEach(function(data){
                    var val = data.val();         
                    content+='<tbody>';      
                    content +='<tr>';
                    sn=sn+1;
                    content += '<td>' + sn + '</td>';
                content += '<td>' + val.ProductName + '</td>';
                content += '<td>' + val.Mrp + '</td>';
                content += '<td>' + val.Rewards + '</td>';
                content += '<td>' + val.Quantity + '</td>';    
                content += '<td>' + val.OrderNo + '</td>';
                content += '<td>' + val.OrderStatus + '</td>';
                content += '<td>' + val.Userid + '</td>';
                content += '<td>' + val.Referral + '</td>';
                content += '<td>' + val.Date + '</td>';
                content += '</tr>';
                    content+='</tbody>';
                });
                $('#data-table').append(content);
            }
        });  
    }
    else if(number.value.length==10){
        var database = firebase.database();
        database.ref().child("Orders")
        .orderByChild('Userid')
        .equalTo("+91"+number.value.toString().toUpperCase())
        .once('value', function(snapshot){
            if(snapshot.exists()){
                var content = '';
                var sn;
                sn=0;
                snapshot.forEach(function(data){
                    var val = data.val();         
                    content+='<tbody>';      
                    content +='<tr>';
                    sn=sn+1;
                    content += '<td>' + sn + '</td>';
                    content += '<td>' + val.ProductName + '</td>';
                    content += '<td>' + val.Mrp + '</td>';
                    content += '<td>' + val.Rewards + '</td>';
                    content += '<td>' + val.Quantity + '</td>';    
                    content += '<td>' + val.OrderNo + '</td>';
                    content += '<td>' + val.OrderStatus + '</td>';
                    content += '<td>' + val.Userid + '</td>';
                    content += '<td>' + val.Referral + '</td>';
                    content += '<td>' + val.Date + '</td>';
                    content += '</tr>';
                    content+='</tbody>';
                });
                $('#data-table').append(content);
            }
        });
    }
    else if(rnumber.value.length==10){
        var database = firebase.database();
        database.ref().child("Orders")
        .orderByChild('Referral')
        .equalTo(rnumber.value.toString().toUpperCase())
        .once('value', function(snapshot){
            if(snapshot.exists()){
                var content = '';
                var sn;
                sn=0;
                snapshot.forEach(function(data){
                    var val = data.val();         
                    content+='<tbody>';      
                    content +='<tr>';
                    sn=sn+1;
                    content += '<td>' + sn + '</td>';
                content += '<td>' + val.ProductName + '</td>';
                content += '<td>' + val.Mrp + '</td>';
                content += '<td>' + val.Rewards + '</td>';
                content += '<td>' + val.Quantity + '</td>';    
                content += '<td>' + val.OrderNo + '</td>';
                content += '<td>' + val.OrderStatus + '</td>';
                content += '<td>' + val.Userid + '</td>';
                content += '<td>' + val.Referral + '</td>';
                content += '<td>' + val.Date + '</td>';
                content += '</tr>';
                    content+='</tbody>';
                });
                $('#data-table').append(content);
            }
        });
    }
    else if(date.value.length==10){
       var database = firebase.database();
        database.ref().child("Orders")
        .orderByChild('Date')
        .equalTo(date.value)
        .once('value', function(snapshot){
            if(snapshot.exists()){
                var content = '';
                var sn;
                sn=0;
                snapshot.forEach(function(data){
                    var val = data.val();         
                    content+='<tbody>';      
                    content +='<tr>';
                    sn=sn+1;
                    content += '<td>' + sn + '</td>';
                content += '<td>' + val.ProductName + '</td>';
                content += '<td>' + val.Mrp + '</td>';
                content += '<td>' + val.Rewards + '</td>';
                content += '<td>' + val.Quantity + '</td>';    
                content += '<td>' + val.OrderNo + '</td>';
                content += '<td>' + val.OrderStatus + '</td>';
                content += '<td>' + val.Userid + '</td>';
                content += '<td>' + val.Referral + '</td>';
                content += '<td>' + val.Date + '</td>';
                content += '</tr>';
                    content+='</tbody>';
                });
                $('#data-table').append(content);
            }
        });
    }
    else if(status.options[status.selectedIndex].value!="Select"){
       var database = firebase.database();
        database.ref().child("Orders")
        .orderByChild('OrderStatus')
        .equalTo(status.options[status.selectedIndex].value)
        .once('value', function(snapshot){
            if(snapshot.exists()){
                var content = '';
                var sn;
                sn=0;
                snapshot.forEach(function(data){
                    var val = data.val();         
                    content+='<tbody>';      
                    content +='<tr>';
                    sn=sn+1;
                    content += '<td>' + sn + '</td>';
                    content += '<td>' + val.ProductName + '</td>';
                    content += '<td>' + val.Mrp + '</td>';
                    content += '<td>' + val.Rewards + '</td>';
                    content += '<td>' + val.Quantity + '</td>';    
                    content += '<td>' + val.OrderNo + '</td>';
                    content += '<td>' + val.OrderStatus + '</td>';
                    content += '<td>' + val.Userid + '</td>';
                    content += '<td>' + val.Referral + '</td>';
                    content += '<td>' + val.Date + '</td>';
                    content += '</tr>';
                    content+='</tbody>';
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
    var wb = XLSX.utils.table_to_book(document.getElementById('data-table'), {sheet:"OrderReport"});
    var wbout = XLSX.write(wb, {bookType:'xlsx', bookSST:true, type: 'binary'});
   
    saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), 'OrderReport.xlsx');
   
});