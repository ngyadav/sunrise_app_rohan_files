$("#units").on('change',function(){

    var pcs = document.getElementById('pcs');
    var kgs = document.getElementById('kgs');
    var ltr = document.getElementById('ltr');
    var gm = document.getElementById('gm');
    var ml = document.getElementById('ml');


   var units = document.getElementById('units');

   if(units.options[units.selectedIndex].value == "kilogram"){
       pcs.style.display = "none";
       kgs.style.display = "block";
       ltr.style.display = "none";
       gm.style.display = "none";
       ml.style.display = "none";



       $('#datatablekgs').empty();
       var database = firebase.database();
       database.ref().child("Grocery")
       .orderByChild("Units").equalTo("kilogram")
       .once('value', function(snapshot){
           if(snapshot.exists()){
               var content = '';
               var sn;
               sn=0;
               snapshot.forEach(function(data){
                   var val = data.val();      
                       content +='<tr>';
                       sn=sn+1;
                       content += '<td class="item_sn">' + sn + '</td>';
                       content += '<td class="item">'+ val.Name+'</td>';
                       content += '<td class="item_k1"><input type="number"  class="k1" value='+val.W1+' ></td>';
                       content += '<td class="item_k2"><input type="number"  class="k2" value='+val.W2+' ></td>';
                       content += '<td class="item_k3"><input type="number"  class="k3" value='+val.W3+' ></td>';
                       content += '<td class="item_k5"><input type="number"  class="k5" value='+val.W4+' ></td>';
                       content += '<td class="item_k6"><input type="number"  class="k6" value='+val.W5+' ></td>';
                       content += '<td class="item_k10"><input type="number"  class="k10" value='+val.W6+' ></td>';
                       content += '<td class="item_k15"><input type="number"  class="k15" value='+val.W7+' ></td>';
                       content += '<td class="item_k25"><input type="number"  class="k25" value='+val.W8+' ></td>';
                       content += '<td class="item_pushid" style="display:none">'+val.PushId+'</td>';
                       content += '<td class="actions-save" style="text-align:center"><a href="#" class="save-row"><i class="fa fa-save"></i></a></td>';
                       content += '</tr>'; 
               });
               $('#datatablekgs').append(content);
   
           }
       });  



   }
   else  if(units.options[units.selectedIndex].value == "gram"){
    pcs.style.display = "none";
    kgs.style.display = "none";
    ltr.style.display = "none";
    gm.style.display = "block";
    ml.style.display = "none";


    $('#datatablegm').empty();
    var database = firebase.database();
    database.ref().child("Grocery")
    .orderByChild("Units").equalTo("gram")
    .once('value', function(snapshot){
        if(snapshot.exists()){
            var content = '';
            var sn;
            sn=0;
            snapshot.forEach(function(data){
                var val = data.val();      
                    content +='<tr>';
                    sn=sn+1;
                    content += '<td class="item_sn">' + sn + '</td>';
                    content += '<td class="item">'+ val.Name+'</td>';
                    content += '<td class="item_g10"><input type="number"  class="g10" value='+val.W1+' ></td>';
                    content += '<td class="item_g25"><input type="number"  class="g25" value='+val.W2+' ></td>';
                    content += '<td class="item_g50"><input type="number"  class="g50" value='+val.W3+' ></td>';
                    content += '<td class="item_g100"><input type="number"  class="g100" value='+val.W4+' ></td>';
                    content += '<td class="item_g200"><input type="number"  class="g200" value='+val.W5+' ></td>';
                    content += '<td class="item_g250"><input type="number"  class="g250" value='+val.W6+' ></td>';
                    content += '<td class="item_g500"><input type="number"  class="g500" value='+val.W7+' ></td>';
                    content += '<td class="item_g750"><input type="number"  class="g750" value='+val.W8+' ></td>';
                    content += '<td class="item_pushid" style="display:none">'+val.PushId+'</td>';
                    content += '<td class="actions-save" style="text-align:center"><a href="#" class="save-row"><i class="fa fa-save"></i></a></td>';
                    content += '</tr>'; 
            });
            $('#datatablegm').append(content);

        }
    });  


    }
    else  if(units.options[units.selectedIndex].value == "litre"){
        pcs.style.display = "none";
        kgs.style.display = "none";
        ltr.style.display = "block";
        gm.style.display = "none";
        ml.style.display = "none";



        $('#datatableltr').empty();
        var database = firebase.database();
        database.ref().child("Grocery")
        .orderByChild("Units").equalTo("litre")
        .once('value', function(snapshot){
            if(snapshot.exists()){
                var content = '';
                var sn;
                sn=0;
                snapshot.forEach(function(data){
                    var val = data.val();      
                        content +='<tr>';
                        sn=sn+1;
                        content += '<td class="item_sn">' + sn + '</td>';
                        content += '<td class="item">'+ val.Name+'</td>';
                        content += '<td class="item_l1"><input type="number"  class="l1" value='+val.W1+' ></td>';
                        content += '<td class="item_l2"><input type="number"  class="l2" value='+val.W2+' ></td>';
                        content += '<td class="item_l3"><input type="number"  class="l3" value='+val.W3+' ></td>';
                        content += '<td class="item_l5"><input type="number"  class="l5" value='+val.W4+' ></td>';
                        content += '<td class="item_l6"><input type="number"  class="l6" value='+val.W5+' ></td>';
                        content += '<td class="item_l10"><input type="number"  class="l10" value='+val.W6+' ></td>';
                        content += '<td class="item_l15"><input type="number"  class="l15" value='+val.W7+' ></td>';
                        content += '<td class="item_l25"><input type="number"  class="l25" value='+val.W8+' ></td>';
                        content += '<td class="item_pushid" style="display:none">'+val.PushId+'</td>';
                        content += '<td class="actions-save" style="text-align:center"><a href="#" class="save-row"><i class="fa fa-save"></i></a></td>';
                        content += '</tr>'; 
                });
                $('#datatableltr').append(content);
    
            }
        }); 


    }
    else  if(units.options[units.selectedIndex].value == "ml"){
        pcs.style.display = "none";
        kgs.style.display = "none";
        ltr.style.display = "none";
        gm.style.display = "none";
        ml.style.display = "block";



        $('#datatableml').empty();
        var database = firebase.database();
        database.ref().child("Grocery")
        .orderByChild("Units").equalTo("ml")
        .once('value', function(snapshot){
            if(snapshot.exists()){
                var content = '';
                var sn;
                sn=0;
                snapshot.forEach(function(data){
                    var val = data.val();      
                        content +='<tr>';
                        sn=sn+1;
                        content += '<td class="item_sn">' + sn + '</td>';
                        content += '<td class="item">'+ val.Name+'</td>';
                        content += '<td class="item_m10"><input type="number"  class="m10" value='+val.W1+' ></td>';
                        content += '<td class="item_m25"><input type="number"  class="m25" value='+val.W2+' ></td>';
                        content += '<td class="item_m50"><input type="number"  class="m50" value='+val.W3+' ></td>';
                        content += '<td class="item_m100"><input type="number"  class="m100" value='+val.W4+' ></td>';
                        content += '<td class="item_m200"><input type="number"  class="m200" value='+val.W5+' ></td>';
                        content += '<td class="item_m250"><input type="number"  class="m250" value='+val.W6+' ></td>';
                        content += '<td class="item_m500"><input type="number"  class="m500" value='+val.W7+' ></td>';
                        content += '<td class="item_m750"><input type="number"  class="m750" value='+val.W8+' ></td>';
                        content += '<td class="item_pushid" style="display:none">'+val.PushId+'</td>';
                        content += '<td class="actions-save" style="text-align:center"><a href="#" class="save-row"><i class="fa fa-save"></i></a></td>';
                        content += '</tr>'; 
                });
                $('#datatableml').append(content);
    
            }
        });  



    }
    else  if(units.options[units.selectedIndex].value == "peices"){
        pcs.style.display = "block";
        kgs.style.display = "none";
        ltr.style.display = "none";
        gm.style.display = "none";
        ml.style.display = "none";



        $('#datatablepcs').empty();
        var database = firebase.database();
        database.ref().child("Grocery")
        .orderByChild("Units").equalTo("peices")
        .once('value', function(snapshot){
            if(snapshot.exists()){
                var content = '';
                var sn;
                sn=0;
                snapshot.forEach(function(data){
                    var val = data.val();      
                        content +='<tr>';
                        sn=sn+1;
                        content += '<td class="item_sn">' + sn + '</td>';
                        content += '<td class="item">'+ val.Name+'</td>';
                        content += '<td class="item_p1"><input type="number"  class="p1" value='+val.W1+' ></td>';
                        content += '<td class="item_p2"><input type="number"  class="p2" value='+val.W2+' ></td>';
                        content += '<td class="item_p3"><input type="number"  class="p3" value='+val.W3+' ></td>';
                        content += '<td class="item_p5"><input type="number"  class="p5" value='+val.W4+' ></td>';
                        content += '<td class="item_p6"><input type="number"  class="p6" value='+val.W5+' ></td>';
                        content += '<td class="item_p10"><input type="number"  class="p10" value='+val.W6+' ></td>';
                        content += '<td class="item_p12"><input type="number"  class="p12" value='+val.W7+' ></td>';
                        content += '<td class="item_p30"><input type="number"  class="p30" value='+val.W8+' ></td>';
                        content += '<td class="item_pushid" style="display:none">'+val.PushId+'</td>';
                        content += '<td class="actions-save" style="text-align:center"><a href="#" class="save-row"><i class="fa fa-save"></i></a></td>';
                        content += '</tr>'; 
                });
                $('#datatablepcs').append(content);
    
            }
        }); 



    }
    else{
        pcs.style.display = "none";
        kgs.style.display = "none";
        ltr.style.display = "none";
        gm.style.display = "none";
        ml.style.display = "none";
    }
    
});



$(document).on("click","#datatablekgs tr td a.save-row", function() {    
    var firebaseref=firebase.database().ref().child("Grocery").child($(this).closest('tr').find('.item_pushid').text());
    $(this).closest('tr').find('input').each(function(){
        if($(this).attr("class") == "k1") {
            if($(this).val() == ""){
                firebaseref.child("W1").set("0");
            }else{
             firebaseref.child("W1").set($(this).val());
            }
        }
        if($(this).attr("class") == "k2") {
            if($(this).val() == ""){
                firebaseref.child("W2").set("0");
            }else{
             firebaseref.child("W2").set($(this).val());
            }
        }
        if($(this).attr("class") == "k3") {
            if($(this).val() == ""){
                firebaseref.child("W3").set("0");
            }else{
             firebaseref.child("W3").set($(this).val());
            }
         }
         if($(this).attr("class") == "k5") {
            if($(this).val() == ""){
                firebaseref.child("W4").set("0");
            }else{
             firebaseref.child("W4").set($(this).val());
            }
         }
          if($(this).attr("class") == "k6") {
            if($(this).val() == ""){
                firebaseref.child("W5").set("0");
            }else{
             firebaseref.child("W5").set($(this).val());
            }
         }
         if($(this).attr("class") == "k10") {
            if($(this).val() == ""){
                firebaseref.child("W6").set("0");
            }else{
             firebaseref.child("W6").set($(this).val());
            }
         }
         if($(this).attr("class") == "k15") {
            if($(this).val() == ""){
                firebaseref.child("W7").set("0");
            }else{
             firebaseref.child("W7").set($(this).val());
            }
         }
         if($(this).attr("class") == "k25") {
            if($(this).val() == ""){
                firebaseref.child("W8").set("0");
            }else{
             firebaseref.child("W8").set($(this).val());
            }
         }        
    }); 
    alert("Saved Succesfully!!");
});    



$(document).on("click","#datatableltr tr td a.save-row", function() {    
    var firebaseref=firebase.database().ref().child("Grocery").child($(this).closest('tr').find('.item_pushid').text());
    $(this).closest('tr').find('input').each(function(){
        if($(this).attr("class") == "l1") {
            if($(this).val() == ""){
                firebaseref.child("W1").set("0");
            }else{
             firebaseref.child("W1").set($(this).val());
            }
        }
        if($(this).attr("class") == "l2") {
            if($(this).val() == ""){
                firebaseref.child("W2").set("0");
            }else{
             firebaseref.child("W2").set($(this).val());
            }
        }
        if($(this).attr("class") == "l3") {
            if($(this).val() == ""){
                firebaseref.child("W3").set("0");
            }else{
             firebaseref.child("W3").set($(this).val());
            }
         }
         if($(this).attr("class") == "l5") {
            if($(this).val() == ""){
                firebaseref.child("W4").set("0");
            }else{
             firebaseref.child("W4").set($(this).val());
            }
         }
          if($(this).attr("class") == "l6") {
            if($(this).val() == ""){
                firebaseref.child("W5").set("0");
            }else{
             firebaseref.child("W5").set($(this).val());
            }
         }
         if($(this).attr("class") == "l10") {
            if($(this).val() == ""){
                firebaseref.child("W6").set("0");
            }else{
             firebaseref.child("W6").set($(this).val());
            }
         }
         if($(this).attr("class") == "l15") {
            if($(this).val() == ""){
                firebaseref.child("W7").set("0");
            }else{
             firebaseref.child("W7").set($(this).val());
            }
         }
         if($(this).attr("class") == "l25") {
            if($(this).val() == ""){
                firebaseref.child("W8").set("0");
            }else{
             firebaseref.child("W8").set($(this).val());
            }
         }        
    }); 
    alert("Saved Succesfully!!");
});




$(document).on("click","#datatablepcs tr td a.save-row", function() {    
    var firebaseref=firebase.database().ref().child("Grocery").child($(this).closest('tr').find('.item_pushid').text());
    $(this).closest('tr').find('input').each(function(){
        if($(this).attr("class") == "p1") {
            if($(this).val() == ""){
                firebaseref.child("W1").set("0");
            }else{
             firebaseref.child("W1").set($(this).val());
            }
        }
        if($(this).attr("class") == "p2") {
            if($(this).val() == ""){
                firebaseref.child("W2").set("0");
            }else{
             firebaseref.child("W2").set($(this).val());
            }
        }
        if($(this).attr("class") == "p3") {
            if($(this).val() == ""){
                firebaseref.child("W3").set("0");
            }else{
             firebaseref.child("W3").set($(this).val());
            }
         }
         if($(this).attr("class") == "p5") {
            if($(this).val() == ""){
                firebaseref.child("W4").set("0");
            }else{
             firebaseref.child("W4").set($(this).val());
            }
         }
          if($(this).attr("class") == "p6") {
            if($(this).val() == ""){
                firebaseref.child("W5").set("0");
            }else{
             firebaseref.child("W5").set($(this).val());
            }
         }
         if($(this).attr("class") == "p10") {
            if($(this).val() == ""){
                firebaseref.child("W6").set("0");
            }else{
             firebaseref.child("W6").set($(this).val());
            }
         }
         if($(this).attr("class") == "p12") {
            if($(this).val() == ""){
                firebaseref.child("W7").set("0");
            }else{
             firebaseref.child("W7").set($(this).val());
            }
         }
         if($(this).attr("class") == "p30") {
            if($(this).val() == ""){
                firebaseref.child("W8").set("0");
            }else{
             firebaseref.child("W8").set($(this).val());
            }
         }        
    }); 
    alert("Saved Succesfully!!");
});


$(document).on("click","#datatablegm tr td a.save-row", function() {    
    var firebaseref=firebase.database().ref().child("Grocery").child($(this).closest('tr').find('.item_pushid').text());
    $(this).closest('tr').find('input').each(function(){
        if($(this).attr("class") == "g10") {
            if($(this).val() == ""){
                firebaseref.child("W1").set("0");
            }else{
             firebaseref.child("W1").set($(this).val());
            }
        }
        if($(this).attr("class") == "g25") {
            if($(this).val() == ""){
                firebaseref.child("W2").set("0");
            }else{
             firebaseref.child("W2").set($(this).val());
            }
        }
        if($(this).attr("class") == "g50") {
            if($(this).val() == ""){
                firebaseref.child("W3").set("0");
            }else{
             firebaseref.child("W3").set($(this).val());
            }
         }
         if($(this).attr("class") == "g100") {
            if($(this).val() == ""){
                firebaseref.child("W4").set("0");
            }else{
             firebaseref.child("W4").set($(this).val());
            }
         }
          if($(this).attr("class") == "g200") {
            if($(this).val() == ""){
                firebaseref.child("W5").set("0");
            }else{
             firebaseref.child("W5").set($(this).val());
            }
         }
         if($(this).attr("class") == "g250") {
            if($(this).val() == ""){
                firebaseref.child("W6").set("0");
            }else{
             firebaseref.child("W6").set($(this).val());
            }
         }
         if($(this).attr("class") == "g500") {
            if($(this).val() == ""){
                firebaseref.child("W7").set("0");
            }else{
             firebaseref.child("W7").set($(this).val());
            }
         }
         if($(this).attr("class") == "g750") {
            if($(this).val() == ""){
                firebaseref.child("W8").set("0");
            }else{
             firebaseref.child("W8").set($(this).val());
            }
         }        
    }); 
    alert("Saved Succesfully!!");
});


$(document).on("click","#datatableml tr td a.save-row", function() {    
    var firebaseref=firebase.database().ref().child("Grocery").child($(this).closest('tr').find('.item_pushid').text());
    $(this).closest('tr').find('input').each(function(){
        if($(this).attr("class") == "m10") {
            if($(this).val() == ""){
                firebaseref.child("W1").set("0");
            }else{
             firebaseref.child("W1").set($(this).val());
            }
        }
        if($(this).attr("class") == "m25") {
            if($(this).val() == ""){
                firebaseref.child("W2").set("0");
            }else{
             firebaseref.child("W2").set($(this).val());
            }
        }
        if($(this).attr("class") == "m50") {
            if($(this).val() == ""){
                firebaseref.child("W3").set("0");
            }else{
             firebaseref.child("W3").set($(this).val());
            }
         }
         if($(this).attr("class") == "m100") {
            if($(this).val() == ""){
                firebaseref.child("W4").set("0");
            }else{
             firebaseref.child("W4").set($(this).val());
            }
         }
          if($(this).attr("class") == "m200") {
            if($(this).val() == ""){
                firebaseref.child("W5").set("0");
            }else{
             firebaseref.child("W5").set($(this).val());
            }
         }
         if($(this).attr("class") == "m250") {
            if($(this).val() == ""){
                firebaseref.child("W6").set("0");
            }else{
             firebaseref.child("W6").set($(this).val());
            }
         }
         if($(this).attr("class") == "m500") {
            if($(this).val() == ""){
                firebaseref.child("W7").set("0");
            }else{
             firebaseref.child("W7").set($(this).val());
            }
         }
         if($(this).attr("class") == "m750") {
            if($(this).val() == ""){
                firebaseref.child("W8").set("0");
            }else{
             firebaseref.child("W8").set($(this).val());
            }
         }        
    }); 
    alert("Saved Succesfully!!");
});

