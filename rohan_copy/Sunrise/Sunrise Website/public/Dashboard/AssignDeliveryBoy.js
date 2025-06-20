var users=[];

window.onload = function () {
//   var role=sessionStorage.getItem('role');
    //   if(role!=null){
    //       if(!role.includes('Us')){
    //           window.location = "login.html";
    //       }
    //   }
    //   else{
    //       window.location = "login.html";
    //   }
    
    firebase.database().ref().child("Date").set("Time");

    var firebaseref1=firebase.database().ref().child("DeliveryBoys");
    firebaseref1.once('value',function(snapshot){

   
    
              if(snapshot.exists()){
                snapshot.forEach(function(data){
                    var val=data.val();
                    users.push(val.UserName);
                });
              }
          

           var database = firebase.database();
            database.ref().child("Orders1")
            .orderByChild("Status").startAt("3").endAt("3")
            .once('value', function(snapshot){
                if(snapshot.exists()){
                    var content = '';
                    var sn;
                    sn=0;
                    snapshot.forEach(function(data){
                        var val = data.val();           
                        
                        if(val.OrderType == "Grocery"){

                          var totalprod="";
    
                          data.child('Cart').forEach(function(data1){
                              var val1=data1.val();
                              totalprod+=val1.Name+" - "+val1.Qty+" / "+val1.Units+",";
                          });

                          content +='<tr>';
                          content += '<td class="item_orderno">' + val.OrderNo + '</td>';  
                          content += '<td class="item_name">' + val.CName + '</td>';  
                          content += '<td class="item_number">' + val.Number + '</td>';
                          content += '<td class="item_pincode">' + val.Pincode + '</td>';
                          content += '<td class="item_items">' + totalprod + '</td>';
                          content += '<td class="item_payment">' + val.Payment + '</td>';
                          content += '<td class="item_total">' + val.Total + '</td>';  
                          content += '<td class="item_delivery"><select class="form-control" id="items"><option>Select</option>';
                          for (var i=0;i<users.length;i++){
                            content += '<option value="'+ users[i] + '">' + users[i] + '</option>';
                          }
                          content += '<td class="actions"><a href="#" class="save-row">Save</a><br><a href="#" class="cancel-row">Cancel</a></td>';
                          content += '<td class="item_pushid" style="display:none">' + val.Pushid + '</td>';
                          content += '</tr>'; 

                        }
                        else{
                        
                            content +='<tr>';
                            content += '<td class="item_orderno">' + val.OrderNo + '</td>';  
                            content += '<td class="item_name">' + val.CName + '</td>';  
                            content += '<td class="item_number">' + val.Number + '</td>';
                            content += '<td class="item_pincode">' + val.Pincode + '</td>';
                            content += '<td class="item_items">' + val.Items + '</td>';
                            content += '<td class="item_payment">' + val.Payment + '</td>';
                            content += '<td class="item_total">' + val.Total + '</td>';  
                            content += '<td class="item_delivery"><select class="form-control" id="items"><option>Select</option>';
                            for (var i=0;i<users.length;i++){
                              content += '<option value="'+ users[i] + '">' + users[i] + '</option>';
                            }
                            content += '<td class="actions"><a href="#" class="save-row">Save</a><br><a href="#" class="cancel-row">Cancel</a></td>';
                            content += '<td class="item_pushid" style="display:none">' + val.Pushid + '</td>';
                            content += '</tr>'; 

                          }
                    });
                    $('#datatable').append(content);
                }
            });  
 
          });
    };
    
   
    $(document).on("click","#datatable tr td a.save-row", function() { 
    
      var firebaseref=firebase.database().ref().child("Orders1").child($(this).closest('tr').find('.item_pushid').text());
      $(this).closest('tr').find('select').each(function(){
          if($(this).attr("class") == "form-control") 
          firebaseref.child("DeliveryBoy").set(String($(this).val()));
          firebaseref.child("Status").set("4");
        });
      
  });



  $(document).on("click","#datatable tr td a.cancel-row", function() { 

    var r=confirm("Are you sure?");
    if (r == true) {
    var firebaseref=firebase.database().ref().child("Orders1").child($(this).closest('tr').find('.item_pushid').text());
    $(this).closest('tr').find('select').each(function(){
        if($(this).attr("class") == "form-control") 
        firebaseref.child("Status").set("10");
      });

      alert("Order Cancelled Successfully!");
    }

});