var updatebtn = document.getElementById("updatebtn");
var submitbtn = document.getElementById("submitbtn");
var addbtn = document.getElementById("addbtn");
var searchbtn = document.getElementById("search");
var deletebtn = document.getElementById('deletebtn');
var path1;
var path2;
var path3;

window.onload = function () {
   
    var username=sessionStorage.getItem('role');
    if(username!=null){
        if(!username.includes('uc'))
        {
         window.location = "http://sunrisebusinesses.com/Login/login.html";
        }
    }
    else
    {
        window.location = "http://sunrisebusinesses.com/Login/login.html";   
    }
    
   




var select = document.getElementById("namelist");
var firebaseref=firebase.database().ref().child("Category");
    return firebaseref.orderByChild("Category").equalTo("Mobile").once('value').then(function(snapshot) {
       snapshot.forEach(function(child){
        var option = document.createElement('option');
        option.text = option.value = child.val().Name;
        select.add(option, 0);
       });
       select.value="Select";
           });    



};


searchbtn.addEventListener('click',function(){



    var name=document.getElementById("name");
    var price=document.getElementById("price");
var reward=document.getElementById("rewardpoints");
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
var quantity=document.getElementById("quantity");
var ratings=document.getElementById("ratings");
var brand=document.getElementById("brand");
var image1=document.getElementById("image1");
var image2=document.getElementById("image2");
var image3=document.getElementById("image3");
var stock=document.getElementById("stock");
    if(namelist.options[namelist.selectedIndex].value=="Select")
        {
            alert("Enter Name");
            namelist.focus();
            return;
        }
    var firebaseref=firebase.database().ref().child("Category").child(namelist.options[namelist.selectedIndex].value);
    updatebtn.style.visibility="visible";
    deletebtn.style.visibility="visible";
    submitbtn.style.visibility="hidden";
    return firebaseref.once('value').then(function(snapshot) {
        price.value= snapshot.val().Price;
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
        quantity.value=snapshot.val().Quantity;
        ratings.value=snapshot.val().Ratings;
        brand.value=snapshot.val().Brand;
        stock.value=snapshot.val().Stock;
        name.value=snapshot.val().Name;
        
        
  
       
});
    
});



submitbtn.addEventListener("click", function () {
   var name = document.getElementById("name");
    var price = document.getElementById("price");
var reward = document.getElementById("rewardpoints");
var feature1 = document.getElementById("feature1");
var feature2 = document.getElementById("feature2");
var feature3 = document.getElementById("feature3");
var feature4=document.getElementById("feature4");
var feature5=document.getElementById("feature5");
var feature6=document.getElementById("feature6");
var feature7=document.getElementById("feature7");
var feature8=document.getElementById("feature8");
var feature9=document.getElementById("feature9");
var feature10=document.getElementById("feature10");
var feature11=document.getElementById("feature11");
var feature12=document.getElementById("feature12");
var quantity=document.getElementById("quantity");
var ratings=document.getElementById("ratings");
var brand=document.getElementById("brand");
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
    if(reward.value.length==0)
        {
            alert("Enter Reward Points");
            reward.focus();
            return;
        }
    
    var firebaseref=firebase.database().ref().child("Category").child(name.value.trim());
    firebaseref.child("Name").set(name.value.trim());
    firebaseref.child("Price").set(price.value);
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
    firebaseref.child("Quantity").set(quantity.value);
    firebaseref.child("Ratings").set(ratings.value);
    firebaseref.child("Stock").set("In Stock");
    firebaseref.child("Brand").set(brand.options[brand.selectedIndex].value);
    firebaseref.child("Category").set("Mobile");
    firebaseref.child("CategoryName").set("Mobile "+name.value.trim());
    firebaseref.child("CategoryBrand").set("Mobile "+brand.options[brand.selectedIndex].value);
    firebaseref.child("Image1").set(window.path1);
    firebaseref.child("Image2").set(window.path2);
    firebaseref.child("Image3").set(window.path3);
    firebaseref.child("Stock").set(stock.options[stock.selectedIndex].value);
   
    
    
    name.value="";
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
    quantity.value="";
    ratings.value="";
    brand.value="";
    $('#image1').val('');
    $('#image2').val('');
    $('#image3').val('');
    submitbtn.style.visibility="hidden";
    alert("Succesfully Added!!!");

});

addbtn.addEventListener("click",function(){
    
    
    if($('#image1').get(0).files.length==0){
       alert("Select Image 1");
        return;
     }
    
    if($('#image2').get(0).files.length==0){
       alert("Select Image 2");
        return;
     }
    
    if($('#image3').get(0).files.length==0){
       alert("Select Image 3");
        return;
     }
    
     var ref = firebase.storage().ref();
    var file = $('#image1').get(0).files[0];
    var file2 = $('#image2').get(0).files[0];
    var file3 = $('#image3').get(0).files[0];
    
    
    
    var name1 = (+new Date()) + '-' + file.name;
    var metadata = { contentType: file.type };
    var task = ref.child("Category").child("Mobile").child(name1).put(file, metadata);
    
    
    
    
    task
      .then(snapshot => snapshot.ref.getDownloadURL())
      .then(url =>            
           window.path1=url)
      .catch(console.error);
    
    
    
    var name2 = (+new Date()) + '-' + file2.name;
    var metadata2 = { contentType: file2.type };
    var task2 = ref.child("Category").child("Mobile").child(name2).put(file2, metadata2);
    
    task2
      .then(snapshot => snapshot.ref.getDownloadURL())
      .then(url =>
            
           window.path2=url)
      .catch(console.error);
    
     
    name3 = (+new Date()) + '-' + file3.name;
    var metadata3 = { contentType: file3.type };
    var task3 = ref.child("Category").child("Mobile").child(name3).put(file3, metadata3);
    
    task3
      .then(snapshot => snapshot.ref.getDownloadURL())
      .then(url =>            
           window.path3=url)  
           .then( submitbtn.style.visibility="visible")       
      .catch(console.error);
        
    
    alert("Images Added Successful");
    // submitbtn.style.visibility="visible";
    updatebtn.style.visibility="hidden";
    
});



    
    
updatebtn.addEventListener('click',function(){
    var name=document.getElementById("name");
    var price=document.getElementById("price");
  var reward=document.getElementById("rewardpoints");
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
  var quantity=document.getElementById("quantity");
  var ratings=document.getElementById("ratings");
  var brand=document.getElementById("brand");
  var type=document.getElementById("typeacc");
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
    if(reward.value.length==0)
        {
            alert("Enter Reward Points");
            reward.focus();
            return;
        }
    
    var firebaseref=firebase.database().ref().child("Category").child(name.value);
    firebaseref.child("Name").set(name.value.trim());
    firebaseref.child("Price").set(price.value);
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
    firebaseref.child("Quantity").set(quantity.value);
    firebaseref.child("Ratings").set(ratings.value);
    firebaseref.child("Stock").set(stock.options[stock.selectedIndex].value);
    firebaseref.child("Brand").set(brand.options[brand.selectedIndex].value);
    firebaseref.child("Category").set("Mobile");
    firebaseref.child("CategoryName").set("Mobile "+name.value.trim());
    firebaseref.child("CategoryBrand").set("Mobile "+brand.options[brand.selectedIndex].value);
    
   
    
    
    name.value="";
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
    quantity.value="";
    ratings.value="";
    $('#image1').val('');
    $('#image2').val('');
    $('#image3').val('');
    updatebtn.style.visibility="hidden";
    deletebtn.style.visibility="hidden";
    alert("Succesfully Added!!!");
  });


  deletebtn.addEventListener('click',function(){
    var name=document.getElementById("name");
    var price=document.getElementById("price");
  var reward=document.getElementById("rewardpoints");
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
  var quantity=document.getElementById("quantity");
  var ratings=document.getElementById("ratings");
  var brand=document.getElementById("brand");
  var type=document.getElementById("typeacc");
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
    var firebaseref=firebase.database().ref().child("Category").child(name.value);
    firebaseref.child("Name").set(null);
    firebaseref.child("Price").set(null);
    firebaseref.child("Cashback").set(null);
    firebaseref.child("Feature1").set(null);
    firebaseref.child("Feature2").set(null);
    firebaseref.child("Feature3").set(null);
    firebaseref.child("Feature4").set(null);
    firebaseref.child("Feature5").set(null);
    firebaseref.child("Feature6").set(null);
    firebaseref.child("Feature7").set(null);
    firebaseref.child("Feature8").set(null);
    firebaseref.child("Feature9").set(null);
    firebaseref.child("Feature10").set(null);
    firebaseref.child("Feature11").set(null);
    firebaseref.child("Feature12").set(null);
    firebaseref.child("Quantity").set(null);
    firebaseref.child("Ratings").set(null);
    firebaseref.child("Stock").set(null);
    firebaseref.child("Brand").set(null);
    firebaseref.child("Category").set(null);
    firebaseref.child("Image1").set(null);
    firebaseref.child("Image2").set(null);
    firebaseref.child("Image3").set(null);
    firebaseref.child("CategoryName").set(null);
    firebaseref.child("CategoryType").set(null);
    

    name.value="";
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
    quantity.value="";
    ratings.value="";
    brand.value="";
    $('#image1').val('');
    $('#image2').val('');
    $('#image3').val('');
    deletebtn.style.visibility="hidden";
    updatebtn.style.visibility="hidden";
    alert('success');
  });