window.onload = function () {

    var username=this.localStorage.getItem('name');
    if(username===null){
         window.location = "Login.html";    
    }

    var role=this.localStorage.getItem('role');
    if(role!=null){
        if(!role.includes('ordersreport')){
            window.location = "Login.html";
        }
    }
    else{
        window.location = "Login.html";
    }

    var database = firebase.database();
    database.ref().child("Orders1")
    .orderByChild('Status')
    .equalTo("5")
    .once('value', function(snapshot){
        if(snapshot.exists()){
            var content = '';
            var sn;
            sn=0;
            
            snapshot.forEach(function(data){
                var val = data.val();               
                content +='<tr>';
                sn=sn+1;


                if(val.OrderType == "Grocery"){

                    var totalprod="";

                    data.child('Cart').forEach(function(data1){
                        var val1=data1.val();
                        totalprod+=val1.Name+" - "+val1.Qty+" / "+val1.Units+",";
                    });

                    content += '<td>' + sn + '</td>';
                    content += '<td>' + val.OrderDateTime + '</td>';
                    content += '<td>' + val.CName + '</td>';
                    content += '<td>' + val.Number + '</td>';
                    content += '<td>' + val.Flat+","+val.Landmark+","+val.Address + '</td>';    
                    content += '<td>' + totalprod + '</td>';
                    content += '<td>' + val.Total + '</td>';
                    content += '<td>' + val.Cashback + '</td>';
                    content += '<td>' + val.OrderNo + '</td>';
                    content += '<td>' + val.Payment + '</td>';
                    content += '<td>Delivered</td>';
                    content += '<td>' + val.UserId + '</td>';
                    content += '<td>' + val.DeliveryBoy + '</td>';
                    content += '<td>' + val.Verified + '</td>';
                    content += '</tr>';

                }
                else{
                content += '<td>' + sn + '</td>';
                content += '<td>' + val.OrderDateTime + '</td>';
                content += '<td>' + val.CName + '</td>';
                content += '<td>' + val.Number + '</td>';
                content += '<td>' + val.Flat+","+val.Landmark+","+val.Address + '</td>';    
                content += '<td>' + val.Items + '</td>';
                content += '<td>' + val.Total + '</td>';
                content += '<td>' + val.Cashback + '</td>';
                content += '<td>' + val.OrderNo + '</td>';
                content += '<td>' + val.Payment + '</td>';
                content += '<td>Delivered</td>';
                content += '<td>' + val.UserId + '</td>';
                content += '<td>' + val.DeliveryBoy + '</td>';
                content += '<td>' + val.Verified + '</td>';
                content += '</tr>';
                }
              });
            $('#datatable').append(content);
        }
    });  


};


// var searchbtn=document.getElementById('search');
// var printbtn=document.getElementById('print');


// function s2ab(s) {
//                 var buf = new ArrayBuffer(s.length);
//                 var view = new Uint8Array(buf);
//                 for (var i=0; i<s.length; i++) view[i] = s.charCodeAt(i) & 0xFF;
//                 return buf;
// }


// printbtn.addEventListener('click',function(){
//     var wb = XLSX.utils.table_to_book(document.getElementById('data-table'), {sheet:"OrderReport"});
//     var wbout = XLSX.write(wb, {bookType:'xlsx', bookSST:true, type: 'binary'});
   
//     saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), 'OrderReport.xlsx');
   
// });