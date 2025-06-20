var searchbtn=document.getElementById('search');
var printbtn=document.getElementById('print');

window.onload = function () {

    var username=sessionStorage.getItem('role');
    if(username!=null){
        if(!username.includes('car'))
        {
         window.location = "http://sunrisebusinesses.com/Login/login.html";
        }
    }
    else
    {
        window.location = "http://sunrisebusinesses.com/Login/login.html";   
    }

};

searchbtn.addEventListener('click',function(){
    $("#data-table tbody").remove();
   
    var category=document.getElementById('role');
    
    if(category.options[category.selectedIndex].value!="Select"){
       var database = firebase.database();
        database.ref().child("Category")
        .orderByChild('Category')
        .equalTo(category.options[category.selectedIndex].value)
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
                    content += '<td>' + val.Name + '</td>';
                    content += '<td>' + val.Price + '</td>';
                    content += '<td>' + val.Cashback + '</td>';
                    content += '<td>' + val.Type + '</td>';    
                    content += '<td>' + val.Brand + '</td>';
                    content += '<td>' + val.Quantity + '</td>';
                    content += '<td>' + val.Ratings + '</td>';
                    content += '<td>' + val.Stock + '</td>';
                    content += '<td>' + val.Feature1 + '</td>';
                    content += '<td>' + val.Feature2 + '</td>';
                    content += '<td>' + val.Feature3 + '</td>';
                    content += '<td>' + val.Feature4 + '</td>';
                    content += '<td>' + val.Feature5 + '</td>';
                    content += '<td>' + val.Feature6 + '</td>';
                    content += '<td>' + val.Feature7 + '</td>';
                    content += '<td>' + val.Feature8 + '</td>';
                    content += '<td>' + val.Feature9 + '</td>';
                    content += '<td>' + val.Feature10 + '</td>';
                    content += '<td>' + val.Feature11 + '</td>';
                    content += '<td>' + val.Feature12 + '</td>';
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
    var wb = XLSX.utils.table_to_book(document.getElementById('data-table'), {sheet:"CategoryReport"});
    var wbout = XLSX.write(wb, {bookType:'xlsx', bookSST:true, type: 'binary'});
   
    saveAs(new Blob([s2ab(wbout)],{type:"application/octet-stream"}), 'CategoryReport.xlsx');
   
});