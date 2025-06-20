var updatebtn = document.getElementById("update");
var submitbtn = document.getElementById("add");
var submitbtn1 = document.getElementById("madd");
var submitbtn2 = document.getElementById("madd1");
var deletebtn = document.getElementById('delete');
var path1;
var path2;
var path3;
var path4;
var temp=0,mtemp=0;
var pushid=[];
var units=[];
window.onload = function () {
   
    var username=this.localStorage.getItem('name');
    if(username===null){
         window.location = "Login.html";    
    }

    var role=this.localStorage.getItem('role');
    if(role!=null){
        if(!role.includes('fashion')){
            window.location = "Login.html";
        }
    }
    else{
        window.location = "Login.html";
    }

    
    var select100 = document.getElementById("sname100");
    firebase.database().ref().child("CategoryNew")
    .orderByChild("Category").equalTo("Fashion")
    .once('value').then(function(snapshot) {
    snapshot.forEach(function(child){
        var option1 = document.createElement('option');
        option1.text = option1.value = child.val().Name;
        select100.add(option1,0);
      })
    });
   




    var select = document.getElementById("sname");
    var select1 = document.getElementById("sname1");
    var firebaseref=firebase.database().ref().child("CategoryNew");
        return firebaseref.orderByChild("Category").equalTo("Fashion").once('value').then(function(snapshot) {
           snapshot.forEach(function(child){
            var option = document.createElement('option');
            var option1 = document.createElement('option');
            option.text = option.value = child.val().Name;
            option1.text = option1.value = child.val().Name;
            units.push(child.val().Name);
            pushid.push(child.val().PushId);
            select.add(option, 0);
            select1.add(option1,0);
           });
           select.value="Select";
           select1.value="Select";
                units.reverse();
                pushid.reverse();
               });    
    
    
    
            };


$('#sname').on('change',function(){

    var name=document.getElementById("name");
    var pushidtext=document.getElementById("pushid");
    var sname=document.getElementById("sname");
    var price=document.getElementById("price");
    var mrp=document.getElementById("mrp");
    var size=document.getElementById("size");
    var reward=document.getElementById("rewardpoints");  
    var ratings=document.getElementById("ratings");  
    var quantity=document.getElementById("quantity");
    var stock=document.getElementById("stock");
    var brand=document.getElementById("brand");
    var type=document.getElementById("typeacc");
    var colour=document.getElementById("colour");
    var feature1=document.getElementById("feature1");
    var feature2=document.getElementById("feature2");
    var feature3=document.getElementById("feature3");
    var feature4=document.getElementById("feature4");
    var feature5=document.getElementById("feature5");
    var feature6=document.getElementById("feature6");
    var feature7=document.getElementById("feature7");
    var feature8=document.getElementById("feature8");
    var feature9=document.getElementById("feature9");
    var feature10=document.getElementById("feature10");
    var feature11=document.getElementById("feature11");
    var feature12=document.getElementById("feature12");
    var feature13=document.getElementById("feature13");
    var feature14=document.getElementById("feature14");
    var feature15=document.getElementById("feature15");
    var image1=document.getElementById("image1");
    var image2=document.getElementById("image2");
    var image3=document.getElementById("image3");

    if(sname.options[sname.selectedIndex].value=="Select")
        {
            alert("Select Fashion Name");
            sname.focus();
            return;
        }

    var firebaseref=firebase.database().ref().child("CategoryNew").child(pushid[sname.selectedIndex]);
    updatebtn.style.visibility="visible";
    deletebtn.style.visibility="visible";
    submitbtn.style.visibility="hidden";
    return firebaseref.once('value').then(function(snapshot) {
        price.value= snapshot.val().Price;
        pushidtext.value=snapshot.val().PushId;
        reward.value= snapshot.val().Cashback;
        feature1.value=snapshot.val().Feature1;
        feature2.value=snapshot.val().Feature2;
        feature3.value=snapshot.val().Feature3;
        feature4.value=snapshot.val().Feature4;
        feature5.value=snapshot.val().Feature5;
        feature6.value=snapshot.val().Feature6;
        feature7.value=snapshot.val().Feature7;
        feature8.value=snapshot.val().Feature8;
        feature9.value=snapshot.val().Feature9;
        feature10.value=snapshot.val().Feature10;
        feature11.value=snapshot.val().Feature11;
        feature12.value=snapshot.val().Feature12;
        feature13.value=snapshot.val().Feature13;
        feature14.value=snapshot.val().Feature14;
        feature15.value=snapshot.val().Feature15;
        quantity.value=snapshot.val().Quantity;
        ratings.value=snapshot.val().Ratings;
        mrp.value=snapshot.val().Mrp;
        brand.value=snapshot.val().Brand;
        stock.value=snapshot.val().Stock;
        name.value=snapshot.val().Name;
        colour.value=snapshot.val().Colour;
        type.value=snapshot.val().Type;
        size.value=snapshot.val().Size;
       
});
    
});



submitbtn.addEventListener("click", function () {
    var name=document.getElementById("name");
    var sname=document.getElementById("sname");
    var price=document.getElementById("price");
    var size=document.getElementById("size");
    var mrp=document.getElementById("mrp");
    var reward=document.getElementById("rewardpoints");  
    var ratings=document.getElementById("ratings");  
    var quantity=document.getElementById("quantity");
    var stock=document.getElementById("stock");
    var brand=document.getElementById("brand");
    var type=document.getElementById("typeacc");
    var colour=document.getElementById("colour");
    var feature1=document.getElementById("feature1");
    var feature2=document.getElementById("feature2");
    var feature3=document.getElementById("feature3");
    var feature4=document.getElementById("feature4");
    var feature5=document.getElementById("feature5");
    var feature6=document.getElementById("feature6");
    var feature7=document.getElementById("feature7");
    var feature8=document.getElementById("feature8");
    var feature9=document.getElementById("feature9");
    var feature10=document.getElementById("feature10");
    var feature11=document.getElementById("feature11");
    var feature12=document.getElementById("feature12");
    var feature13=document.getElementById("feature13");
    var feature14=document.getElementById("feature14");
    var feature15=document.getElementById("feature15");
    var image1=document.getElementById("image1");
    var image2=document.getElementById("image2");
    var image3=document.getElementById("image3");
    
    if(name.value.length==0)
        {
            alert("Enter Name");
            name.focus();
            return;
        }
    if(price.value.length==0)
        {
            alert("Enter Price");
            price.focus;
            return;
        }
        if(mrp.value.length==0)
        {
            alert("Enter MRP");
            mrp.focus;
            return;
        }
    if(reward.value.length==0)
        {
            alert("Enter Reward Points");
            reward.focus();
            return;
        }

        if(size.value.length==0)
        {
            alert("Enter Size");
            size.focus();
            return;
        }
    
        if(feature1.value.length==0)
        {
            alert("Enter Fetaure1");
            feature1.focus();
            return;
        }


        for(var i=0;i<units.length;i++) {
            if(name.value===units[i])
            {alert("Item already Exists!!!");
            name.focus;
            return;}
          }

          if(window.temp<3){
            alert('Wait for images to upload');
            return;
        }
    

    var firebaseref=firebase.database().ref().child("CategoryNew").push();
    firebaseref.child("PushId").set(firebaseref.getKey());
    firebaseref.child("Name").set(name.value.trim());
    firebaseref.child("Price").set(price.value);
    firebaseref.child("Mrp").set(mrp.value);
    firebaseref.child("Size").set(size.value);
    firebaseref.child("Cashback").set(reward.value);
    firebaseref.child("Feature1").set(feature1.value);
    firebaseref.child("Feature2").set(feature2.value);
    firebaseref.child("Feature3").set(feature3.value);
    firebaseref.child("Feature4").set(feature4.value);
    firebaseref.child("Feature5").set(feature5.value);
    firebaseref.child("Feature6").set(feature6.value);
    firebaseref.child("Feature7").set(feature7.value);
    firebaseref.child("Feature8").set(feature8.value);
    firebaseref.child("Feature9").set(feature9.value);
    firebaseref.child("Feature10").set(feature10.value);
    firebaseref.child("Feature11").set(feature11.value);
    firebaseref.child("Feature12").set(feature12.value);
    firebaseref.child("Feature13").set(feature13.value);
    firebaseref.child("Feature14").set(feature14.value);
    firebaseref.child("Feature15").set(feature15.value);
    firebaseref.child("Quantity").set(quantity.value);
    firebaseref.child("Ratings").set(ratings.value);
    firebaseref.child("Stock").set("In Stock");
    firebaseref.child("Colour").set(colour.value);
    firebaseref.child("Brand").set(brand.value);
    firebaseref.child("Type").set(type.options[type.selectedIndex].value);
    firebaseref.child("Category").set("Fashion");
    firebaseref.child("CategoryName").set("Fashion "+name.value.trim());
    firebaseref.child("CategoryType").set("Fashion "+type.options[type.selectedIndex].value);
    firebaseref.child("CategoryBrand").set("Fashion "+brand.value);
    firebaseref.child("Image1").set(window.path1);
    firebaseref.child("Image2").set(window.path2);
    firebaseref.child("Image3").set(window.path3);
    firebaseref.child("Stock").set(stock.options[stock.selectedIndex].value);
   
    
    
    name.value="";
    mrp.value="";
    price.value="";
    size.value="";
    reward.value="";
    feature1.value="";
    feature2.value="";
    feature3.value="";
    feature4.value="";
    feature5.value="";
    feature6.value="";
    feature7.value="";
    feature8.value="";
    feature9.value="";
    feature10.value="";
    feature11.value="";
    feature12.value="";
    feature13.value="";
    feature14.value="";
    feature15.value="";
    quantity.value="";
    ratings.value="";
    brand.value="";
    colour.value="";
    window.temp=0;
    $('#image1').val('');
    $('#image2').val('');
    $('#image3').val('');
    submitbtn.style.visibility="hidden";
    alert("Succesfully Added!!!");

});





    
    
updatebtn.addEventListener('click',function(){
    var name=document.getElementById("name");
    var pushid=document.getElementById("pushid");
    var sname=document.getElementById("sname");
    var price=document.getElementById("price");
    var mrp=document.getElementById("mrp");
    var size=document.getElementById("size");
    var reward=document.getElementById("rewardpoints");  
    var ratings=document.getElementById("ratings");  
    var quantity=document.getElementById("quantity");
    var stock=document.getElementById("stock");
    var brand=document.getElementById("brand");
    var type=document.getElementById("typeacc");
    var colour=document.getElementById("colour");
    var feature1=document.getElementById("feature1");
    var feature2=document.getElementById("feature2");
    var feature3=document.getElementById("feature3");
    var feature4=document.getElementById("feature4");
    var feature5=document.getElementById("feature5");
    var feature6=document.getElementById("feature6");
    var feature7=document.getElementById("feature7");
    var feature8=document.getElementById("feature8");
    var feature9=document.getElementById("feature9");
    var feature10=document.getElementById("feature10");
    var feature11=document.getElementById("feature11");
    var feature12=document.getElementById("feature12");
    var feature13=document.getElementById("feature13");
    var feature14=document.getElementById("feature14");
    var feature15=document.getElementById("feature15");
    var image1=document.getElementById("image1");
    var image2=document.getElementById("image2");
    var image3=document.getElementById("image3");
    
    if(name.value.length==0)
        {
            alert("Enter Name");
            name.focus();
            return;
        }
    if(price.value.length==0)
        {
            alert("Enter Price");
            price.focus;
            return;
        }
        if(mrp.value.length==0)
        {
            alert("Enter MRP");
            mrp.focus;
            return;
        }
        if(size.value.length==0)
        {
            alert("Enter Size");
            size.focus;
            return;
        }
    if(reward.value.length==0)
        {
            alert("Enter Reward Points");
            reward.focus();
            return;
        }
    
        if(feature1.value.length==0)
        {
            alert("Enter Fetaure1");
            feature1.focus();
            return;
        }

        if(pushid.value.length==0)
        {
            alert("Select Proper Item name");
            sname.focus();
            return;
        }

    var firebaseref=firebase.database().ref().child("CategoryNew").child(pushid.value);
    firebaseref.child("Name").set(name.value.trim());
    firebaseref.child("Price").set(price.value);
    firebaseref.child("Mrp").set(mrp.value);
    firebaseref.child("Size").set(size.value);
    firebaseref.child("Cashback").set(reward.value);
    firebaseref.child("Feature1").set(feature1.value);
    firebaseref.child("Feature2").set(feature2.value);
    firebaseref.child("Feature3").set(feature3.value);
    firebaseref.child("Feature4").set(feature4.value);
    firebaseref.child("Feature5").set(feature5.value);
    firebaseref.child("Feature6").set(feature6.value);
    firebaseref.child("Feature7").set(feature7.value);
    firebaseref.child("Feature8").set(feature8.value);
    firebaseref.child("Feature9").set(feature9.value);
    firebaseref.child("Feature10").set(feature10.value);
    firebaseref.child("Feature11").set(feature11.value);
    firebaseref.child("Feature12").set(feature12.value);
    firebaseref.child("Feature13").set(feature13.value);
    firebaseref.child("Feature14").set(feature14.value);
    firebaseref.child("Feature15").set(feature15.value);
    firebaseref.child("Quantity").set(quantity.value);
    firebaseref.child("Ratings").set(ratings.value);
    firebaseref.child("Stock").set("In Stock");
    firebaseref.child("Colour").set(colour.value);
    firebaseref.child("Brand").set(brand.value);
    firebaseref.child("Type").set(type.options[type.selectedIndex].value);
    firebaseref.child("Category").set("Fashion");
    firebaseref.child("CategoryName").set("Fashion "+name.value.trim());
    firebaseref.child("CategoryType").set("Fashion "+type.options[type.selectedIndex].value);
    firebaseref.child("CategoryBrand").set("Fashion "+brand.value);
    firebaseref.child("Stock").set(stock.options[stock.selectedIndex].value);
   
    
    
    name.value="";
    mrp.value="";
    price.value="";
    reward.value="";
    feature1.value="";
    feature2.value="";
    feature3.value="";
    feature4.value="";
    feature5.value="";
    feature6.value="";
    feature7.value="";
    feature8.value="";
    feature9.value="";
    feature10.value="";
    feature11.value="";
    feature12.value="";
    feature13.value="";
    feature14.value="";
    feature15.value="";
    quantity.value="";
    ratings.value="";
    brand.value="";
    colour.value="";
    pushid.value="";
    size.value="";
    $('#image1').val('');
    $('#image2').val('');
    $('#image3').val('');
    submitbtn.style.visibility="hidden";
    alert("Succesfully Updated!!!");

  });


  deletebtn.addEventListener('click',function(){
    var name=document.getElementById("name");
    var pushid=document.getElementById("pushid");
    var sname=document.getElementById("sname");
    var price=document.getElementById("price");
    var mrp=document.getElementById("mrp");
    var size=document.getElementById("size");
    var reward=document.getElementById("rewardpoints");  
    var ratings=document.getElementById("ratings");  
    var quantity=document.getElementById("quantity");
    var stock=document.getElementById("stock");
    var brand=document.getElementById("brand");
    var colour=document.getElementById("colour");
    var feature1=document.getElementById("feature1");
    var feature2=document.getElementById("feature2");
    var feature3=document.getElementById("feature3");
    var feature4=document.getElementById("feature4");
    var feature5=document.getElementById("feature5");
    var feature6=document.getElementById("feature6");
    var feature7=document.getElementById("feature7");
    var feature8=document.getElementById("feature8");
    var feature9=document.getElementById("feature9");
    var feature10=document.getElementById("feature10");
    var feature11=document.getElementById("feature11");
    var feature12=document.getElementById("feature12");
    var feature13=document.getElementById("feature13");
    var feature14=document.getElementById("feature14");
    var feature15=document.getElementById("feature15");
    var image1=document.getElementById("image1");
    var image2=document.getElementById("image2");
    var image3=document.getElementById("image3");
 
  if(name.value.length==0)
  {
      alert("Enter Name");
      name.focus();
      return;
  }


    var name=document.getElementById("name");
    var firebaseref=firebase.database().ref().child("CategoryNew").child(pushid.value);
    firebaseref.remove();
    
    

    name.value="";
    mrp.value="";
    price.value="";
    reward.value="";
    size.value="";
    feature1.value="";
    feature2.value="";
    feature3.value="";
    feature4.value="";
    feature5.value="";
    feature6.value="";
    feature7.value="";
    feature8.value="";
    feature9.value="";
    feature10.value="";
    feature11.value="";
    feature12.value="";
    feature13.value="";
    feature14.value="";
    feature15.value="";
    quantity.value="";
    ratings.value="";
    brand.value="";
    colour.value="";
    $('#image1').val('');
    $('#image2').val('');
    $('#image3').val('');
    deletebtn.style.visibility="hidden";
    updatebtn.style.visibility="hidden";
    alert('success');
  });



  $("#image1").change(function(){


    if($('#image1').get(0).files.length==0){
        alert("Add Image1");
        return;
    }
    if(!window.path1=='')
    {
    window.temp--;
    }
     const ref = firebase.storage().ref("/Images");
    const file = document.querySelector('#image1').files[0]
    const name = (+new Date()) + '-' + file.name;
    const metadata = {
    contentType: file.type
    };
    const task = ref.child(name).put(file, metadata);
    task
    .then(snapshot => snapshot.ref.getDownloadURL())
    .then((url) => {
       window.path1=url;
        window.temp++;
    })
    .catch(console.error);
});


$("#image2").change(function(){


    if($('#image2').get(0).files.length==0){
        alert("Add Image2");
        return;
    }
    if(!window.path2=='')
    {
    window.temp--;
    }
     const ref = firebase.storage().ref("/Images");
    const file = document.querySelector('#image2').files[0]
    const name = (+new Date()) + '-' + file.name;
    const metadata = {
    contentType: file.type
    };
    const task = ref.child(name).put(file, metadata);
    task
    .then(snapshot => snapshot.ref.getDownloadURL())
    .then((url) => {
       window.path2=url;
        window.temp++;
    })
    .catch(console.error);
});


$("#image3").change(function(){


    if($('#image3').get(0).files.length==0){
        alert("Add Image3");
        return;
    }
    if(!window.path3=='')
    {
    window.temp--;
    }
     const ref = firebase.storage().ref("/Images");
    const file = document.querySelector('#image3').files[0]
    const name = (+new Date()) + '-' + file.name;
    const metadata = {
    contentType: file.type
    };
    const task = ref.child(name).put(file, metadata);
    task
    .then(snapshot => snapshot.ref.getDownloadURL())
    .then((url) => {
       window.path3=url;
        window.temp++;
    })
    .catch(console.error);
});


$("#mimage1").change(function(){

    var mname=document.getElementById("mname");

    if(mname.options[mname.selectedIndex].value=="Select")
        {
            alert("Select  Baner Name");
            mname.focus();
            return;
        }

    if($('#mimage1').get(0).files.length==0){
        alert("Add  Baner Image");
        return;
    }
    if(!window.path4=='')
    {
    window.mtemp--;
    }
     const ref = firebase.storage().ref("/Fashion/");
    const file = document.querySelector('#mimage1').files[0]
    const name = mname.options[mname.selectedIndex].value
    const metadata = {
    contentType: file.type
    };
    const task = ref.child(name).put(file, metadata);
    task
    .then(snapshot => snapshot.ref.getDownloadURL())
    .then((url) => {
       window.path4=url;
        window.mtemp++;
    })
    .catch(console.error);
});


submitbtn1.addEventListener('click',function(){
    
    var mname=document.getElementById("mname");
    var select=document.getElementById("sname100");

    if(mname.options[mname.selectedIndex].value=="Select")
        {
            alert("Select  Baner Name");
            mname.focus();
            return;
        }

        if(select.options[select.selectedIndex].value=="Select")
        {
            alert("Select  Product Name");
            select.focus();
            return;
        }

        if(window.path4.length==0){
            alert("Please Wait for image to upload");
            return;
        }

        
    
    var firebaseref=firebase.database().ref().child("AppContent").child("Fashion").child("Baner").child(mname.options[mname.selectedIndex].value);
    firebaseref.child("Name").set(window.path4);
    firebaseref.child("Path").set(pushid[select.selectedIndex]);

    window.path4="";

    $('#mimage1').val('');

    alert("Successfully Added");


});


submitbtn2.addEventListener('click',function(){
    
    var mname=document.getElementById("mname1");
    var sname=document.getElementById("sname1");

    if(mname.options[mname.selectedIndex].value=="Select")
        {
            alert("Select  Baner Name");
            mname.focus();
            return;
        }
      
        
    var firebaseref=firebase.database().ref().child("AppContent").child("Fashion").child(mname.options[mname.selectedIndex].value);
    

    firebase.database().ref().child("CategoryNew").child(pushid[sname.selectedIndex])
            .once('value') .then(function(snapshot) {

                
              

                 firebaseref.set(snapshot.val());
                alert("Successfully Added");
                    
        });



});