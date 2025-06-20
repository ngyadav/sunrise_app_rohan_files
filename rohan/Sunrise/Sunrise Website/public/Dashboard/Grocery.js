var updatebtn = document.getElementById("update");
var submitbtn = document.getElementById("submit");
var deletebtn = document.getElementById('delete');
var path1="";
var temp=0;
var pushid=[];
var units=[];
window.onload = function () {
   

    updatebtn.style.visibility="hidden";
    deletebtn.style.visibility="hidden";
    submitbtn.style.visibility="visible";

    $('#sname').empty();
    pushid=[];
    units=[];
var select = document.getElementById("sname");

var option = document.createElement('option');
    option.text = option.value = "Select";
    select.add(option, 0);


firebase.database().ref().child("Grocery").once('value').then(function(snapshot) {
       snapshot.forEach(function(child){
        var option = document.createElement('option');
        option.text = option.value = child.val().Name;
        select.add(option, 0);
        units.push(child.val().Name);
        pushid.push(child.val().PushId);
       });
       select.value="Select";
       units.reverse();
       pushid.reverse();
    });    
};



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
   }
   else  if(units.options[units.selectedIndex].value == "gram"){
    pcs.style.display = "none";
    kgs.style.display = "none";
    ltr.style.display = "none";
    gm.style.display = "block";
    ml.style.display = "none";
    }
    else  if(units.options[units.selectedIndex].value == "litre"){
        pcs.style.display = "none";
        kgs.style.display = "none";
        ltr.style.display = "block";
        gm.style.display = "none";
        ml.style.display = "none";
    }
    else  if(units.options[units.selectedIndex].value == "ml"){
        pcs.style.display = "none";
        kgs.style.display = "none";
        ltr.style.display = "none";
        gm.style.display = "none";
        ml.style.display = "block";
    }
    else  if(units.options[units.selectedIndex].value == "peices"){
        pcs.style.display = "block";
        kgs.style.display = "none";
        ltr.style.display = "none";
        gm.style.display = "none";
        ml.style.display = "none";
    }
    else{
        pcs.style.display = "none";
        kgs.style.display = "none";
        ltr.style.display = "none";
        gm.style.display = "none";
        ml.style.display = "none";
    }
    
    

});


$('#sname').on('change',function(){


    var pcs = document.getElementById('pcs');
    var kgs = document.getElementById('kgs');
    var ltr = document.getElementById('ltr');
    var gm = document.getElementById('gm');
    var ml = document.getElementById('ml');

    var name=document.getElementById("name");
    var desc=document.getElementById("desc");
    var category=document.getElementById("category");
    var status=document.getElementById("status1");
    var units=document.getElementById("units");
    var p1=document.getElementById("p1");
    var p2=document.getElementById("p2");
    var p3=document.getElementById("p3");
    var p5=document.getElementById("p5");
    var p6=document.getElementById("p6");
    var p10=document.getElementById("p10");
    var p12=document.getElementById("p12");
    var p30=document.getElementById("p30");
    var k1=document.getElementById("k1");
    var k2=document.getElementById("k2");
    var k3=document.getElementById("k3");
    var k5=document.getElementById("k5");
    var k6=document.getElementById("k6");
    var k10=document.getElementById("k10");
    var k15=document.getElementById("k15");
    var k25=document.getElementById("k25");
    var l1=document.getElementById("l1");
    var l2=document.getElementById("l2");
    var l3=document.getElementById("l3");
    var l5=document.getElementById("l5");
    var l6=document.getElementById("l6");
    var l10=document.getElementById("l10");
    var l15=document.getElementById("l15");
    var l25=document.getElementById("l25");
    var g10=document.getElementById("g10");
    var g25=document.getElementById("g25");
    var g50=document.getElementById("g50");
    var g100=document.getElementById("g100");
    var g200=document.getElementById("g200");
    var g250=document.getElementById("g250");
    var g500=document.getElementById("g500");
    var g750=document.getElementById("g750");
    var m10=document.getElementById("m10");
    var m25=document.getElementById("m25");
    var m50=document.getElementById("m50");
    var m100=document.getElementById("m100");
    var m200=document.getElementById("m200");
    var m250=document.getElementById("m250");
    var m500=document.getElementById("m500");
    var m750=document.getElementById("m750");
    



    if(sname.options[sname.selectedIndex].value=="Select")
        {
            alert("Select Product Name");
            sname.focus();
            return;
        }

    var firebaseref=firebase.database().ref().child("Grocery").child(pushid[sname.selectedIndex]);
    updatebtn.style.visibility="visible";
    deletebtn.style.visibility="visible";
    submitbtn.style.visibility="hidden";
    return firebaseref.once('value').then(function(snapshot) {
        name.value= snapshot.val().Name;
        desc.value= snapshot.val().Desc;
        category.value= snapshot.val().Category;
        status.value= snapshot.val().Status;
        units.value= snapshot.val().Units;
        window.path1=snapshot.val().Image;
        
        window.temp++;


        if(snapshot.val().Units == "kilogram"){


            pcs.style.display = "none";
            kgs.style.display = "block";
            ltr.style.display = "none";
            gm.style.display = "none";
            ml.style.display = "none";

             k1.value=snapshot.val().W1;
             k2.value=snapshot.val().W2;
             k3.value=snapshot.val().W3;
             k5.value=snapshot.val().W4;
             k6.value=snapshot.val().W5;
             k10.value=snapshot.val().W6;
             k15.value=snapshot.val().W7;
             k25.value=snapshot.val().W8;

        }
        else  if(snapshot.val().Units == "gram"){


            pcs.style.display = "none";
            kgs.style.display = "none";
            ltr.style.display = "none";
            gm.style.display = "block";
            ml.style.display = "none";

            g10.value=snapshot.val().W1;
            g25.value=snapshot.val().W2;
            g50.value=snapshot.val().W3;
            g100.value=snapshot.val().W4;
            g200.value=snapshot.val().W5;
            g250.value=snapshot.val().W6;
            g500.value=snapshot.val().W7;
            g750.value=snapshot.val().W8;

        }
        else  if(snapshot.val().Units == "litre"){


            pcs.style.display = "none";
            kgs.style.display = "none";
            ltr.style.display = "block";
            gm.style.display = "none";
            ml.style.display = "none";

            l1.value=snapshot.val().W1;
            l2.value=snapshot.val().W2;
            l3.value=snapshot.val().W3;
            l5.value=snapshot.val().W4;
            l6.value=snapshot.val().W5;
            l10.value=snapshot.val().W6;
            l15.value=snapshot.val().W7;
            l25.value=snapshot.val().W8;

        }
        else  if(snapshot.val().Units == "ml"){

            pcs.style.display = "none";
            kgs.style.display = "none";
            ltr.style.display = "none";
            gm.style.display = "none";
            ml.style.display = "block";


            m10.value=snapshot.val().W1;
            m25.value=snapshot.val().W2;
            m50.value=snapshot.val().W3;
            m100.value=snapshot.val().W4;
            m200.value=snapshot.val().W5;
            m250.value=snapshot.val().W6;
            m500.value=snapshot.val().W7;
            m750.value=snapshot.val().W8;

        }
        else  if(snapshot.val().Units == "peices"){

            pcs.style.display = "block";
            kgs.style.display = "none";
            ltr.style.display = "none";
            gm.style.display = "none";
            ml.style.display = "none";

            p1.value=snapshot.val().W1;
            p2.value=snapshot.val().W2;
            p3.value=snapshot.val().W3;
            p5.value=snapshot.val().W4;
            p6.value=snapshot.val().W5;
            p10.value=snapshot.val().W6;
            p12.value=snapshot.val().W7;
            p30.value=snapshot.val().W8;

        }
       
});
    
});



submitbtn.addEventListener("click", function () {
    var name=document.getElementById("name");
    var desc=document.getElementById("desc");
    var category=document.getElementById("category");
    var status=document.getElementById("status1");
    var units=document.getElementById("units");
    var p1=document.getElementById("p1");
    var p2=document.getElementById("p2");
    var p3=document.getElementById("p3");
    var p5=document.getElementById("p5");
    var p6=document.getElementById("p6");
    var p10=document.getElementById("p10");
    var p12=document.getElementById("p12");
    var p30=document.getElementById("p30");
    var k1=document.getElementById("k1");
    var k2=document.getElementById("k2");
    var k3=document.getElementById("k3");
    var k5=document.getElementById("k5");
    var k6=document.getElementById("k6");
    var k10=document.getElementById("k10");
    var k15=document.getElementById("k15");
    var k25=document.getElementById("k25");
    var l1=document.getElementById("l1");
    var l2=document.getElementById("l2");
    var l3=document.getElementById("l3");
    var l5=document.getElementById("l5");
    var l6=document.getElementById("l6");
    var l10=document.getElementById("l10");
    var l15=document.getElementById("l15");
    var l25=document.getElementById("l25");
    var g10=document.getElementById("g10");
    var g25=document.getElementById("g25");
    var g50=document.getElementById("g50");
    var g100=document.getElementById("g100");
    var g200=document.getElementById("g200");
    var g250=document.getElementById("g250");
    var g500=document.getElementById("g500");
    var g750=document.getElementById("g750");
    var m10=document.getElementById("m10");
    var m25=document.getElementById("m25");
    var m50=document.getElementById("m50");
    var m100=document.getElementById("m100");
    var m200=document.getElementById("m200");
    var m250=document.getElementById("m250");
    var m500=document.getElementById("m500");
    var m750=document.getElementById("m750");
    
    if(name.value.length==0)
        {
            alert("Enter Name");
            name.focus();
            return;
        }


        if(desc.value.length==0)
        {
            alert("Enter Description");
            desc.focus();
            return;
        }

        if(category.options[category.selectedIndex].value=="Select")
        {
            alert("Select Category Name");
            category.focus();
            return;
        }

        if(units.options[units.selectedIndex].value=="Select")
        {
            alert("Select Units");
            units.focus();
            return;
        }
        else if(units.options[units.selectedIndex].value=="kilogram")
        {

            
            if(k1.value.length==0)
            {
                alert("Enter 1 KG Price");
                l1.focus();
                return;
            }

            if(k2.value.length==0)
            {
                alert("Enter 2 KG Price");
                l2.focus();
                return;
            }

            if(k3.value.length==0)
            {
                alert("Enter 3 KG Price");
                l3.focus();
                return;
            }

            if(k5.value.length==0)
            {
                alert("Enter 5 KG Price");
                l5.focus();
                return;
            }

            if(k6.value.length==0)
            {
                alert("Enter 6 KG Price");
                l6.focus();
                return;
            }

            if(k10.value.length==0)
            {
                alert("Enter 10 KG Price");
                l10.focus();
                return;
            }

            if(k15.value.length==0)
            {
                alert("Enter 15 KG Price");
                l15.focus();
                return;
            }

            if(k25.value.length==0)
            {
                alert("Enter 25 KG Price");
                l25.focus();
                return;
            }
                
        }
        else if(units.options[units.selectedIndex].value=="gram")
        {
            if(g10.value.length==0)
            {
                alert("Enter 10 GM Price");
                g10.focus();
                return;
            }

            if(g25.value.length==0)
            {
                alert("Enter 25 GM Price");
                g25.focus();
                return;
            }

            if(g50.value.length==0)
            {
                alert("Enter 50 GM Price");
                g50.focus();
                return;
            }

            if(g100.value.length==0)
            {
                alert("Enter 100 GM Price");
                g100.focus();
                return;
            }

            if(g200.value.length==0)
            {
                alert("Enter 200 GM Price");
                g200.focus();
                return;
            }

            if(g250.value.length==0)
            {
                alert("Enter 250 GM Price");
                g250.focus();
                return;
            }

            if(g500.value.length==0)
            {
                alert("Enter 500 GM Price");
                g500.focus();
                return;
            }

            if(g750.value.length==0)
            {
                alert("Enter 750 GM Price");
                g750.focus();
                return;
            }
        }

        else if(units.options[units.selectedIndex].value=="litre")
        {
            
            if(l1.value.length==0)
                {
                    alert("Enter 1 Ltr Price");
                    l1.focus();
                    return;
                }

                if(l2.value.length==0)
                {
                    alert("Enter 2 Ltr Price");
                    l2.focus();
                    return;
                }

                if(l3.value.length==0)
                {
                    alert("Enter 3 Ltr Price");
                    l3.focus();
                    return;
                }

                if(l5.value.length==0)
                {
                    alert("Enter 5 Ltr Price");
                    l5.focus();
                    return;
                }

                if(l6.value.length==0)
                {
                    alert("Enter 6 Ltr Price");
                    l6.focus();
                    return;
                }

                if(l10.value.length==0)
                {
                    alert("Enter 10 Ltr Price");
                    l10.focus();
                    return;
                }

                if(l15.value.length==0)
                {
                    alert("Enter 15 Ltr Price");
                    l15.focus();
                    return;
                }

                if(l25.value.length==0)
                {
                    alert("Enter 25 Ltr Price");
                    l25.focus();
                    return;
                }

        }
        else if(units.options[units.selectedIndex].value=="ml")
        {
            if(m10.value.length==0)
                {
                    alert("Enter 10 ML Price");
                    m10.focus();
                    return;
                }

                if(m25.value.length==0)
                {
                    alert("Enter 25 ML Price");
                    m25.focus();
                    return;
                }

                if(m50.value.length==0)
                {
                    alert("Enter 50 ML Price");
                    m50.focus();
                    return;
                }

                if(m100.value.length==0)
                {
                    alert("Enter 100 ML Price");
                    m100.focus();
                    return;
                }

                if(m200.value.length==0)
                {
                    alert("Enter 200 ML Price");
                    m200.focus();
                    return;
                }

                if(m250.value.length==0)
                {
                    alert("Enter 250 ML Price");
                    m250.focus();
                    return;
                }

                if(m500.value.length==0)
                {
                    alert("Enter 500 ML Price");
                    m500.focus();
                    return;
                }

                if(m750.value.length==0)
                {
                    alert("Enter 750 ML Price");
                    m750.focus();
                    return;
                }
        }
        else if(units.options[units.selectedIndex].value=="peices")
        {
            if(p1.value.length==0)
                {
                    alert("Enter 1 Pcs Price");
                    p1.focus();
                    return;
                }

                if(p2.value.length==0)
                {
                    alert("Enter 2 Pcs Price");
                    p2.focus();
                    return;
                }

                if(p3.value.length==0)
                {
                    alert("Enter 3 Pcs Price");
                    p3.focus();
                    return;
                }

                if(p5.value.length==0)
                {
                    alert("Enter 5 Pcs Price");
                    p5.focus();
                    return;
                }

                if(p6.value.length==0)
                {
                    alert("Enter 6 Pcs Price");
                    p6.focus();
                    return;
                }

                if(p10.value.length==0)
                {
                    alert("Enter 10 Pcs Price");
                    p10.focus();
                    return;
                }

                if(p12.value.length==0)
                {
                    alert("Enter 12 Pcs Price");
                    p12.focus();
                    return;
                }

                if(p30.value.length==0)
                {
                    alert("Enter 30 Pcs Price");
                    p30.focus();
                    return;
                }
        }

        for(var i=0;i<units.length;i++) {
            if(name.value===units[i])
            {alert("Item already Exists!!!");
            name.focus;
            return;}
          }

          if(window.temp<1){
              alert('Wait for images to upload');
              return;
          }
    

    var firebaseref=firebase.database().ref().child("Grocery").push();
    firebaseref.child("PushId").set(firebaseref.getKey());
    firebaseref.child("Name").set(String(name.value.trim()));
    firebaseref.child("Desc").set(String(desc.value.trim()));
    firebaseref.child("Category").set(String(category.options[category.selectedIndex].value));
    firebaseref.child("Status").set(String(status.options[status.selectedIndex].value));
    firebaseref.child("CategoryName").set(String(category.options[category.selectedIndex].value + name.value.trim()));
    firebaseref.child("Units").set(String(units.options[units.selectedIndex].value));
    firebaseref.child("Image").set(String(window.path1));



    if(units.options[units.selectedIndex].value=="kilogram")
    {
        firebaseref.child("W1").set(String(k1.value.trim()));
        firebaseref.child("W2").set(String(k2.value.trim()));
        firebaseref.child("W3").set(String(k3.value.trim()));
        firebaseref.child("W4").set(String(k5.value.trim()));
        firebaseref.child("W5").set(String(k6.value.trim()));
        firebaseref.child("W6").set(String(k10.value.trim()));
        firebaseref.child("W7").set(String(k15.value.trim()));
        firebaseref.child("W8").set(String(k25.value.trim()));

    }
    else if(units.options[units.selectedIndex].value=="gram")
    {
        firebaseref.child("W1").set(String(g10.value.trim()));
        firebaseref.child("W2").set(String(g25.value.trim()));
        firebaseref.child("W3").set(String(g50.value.trim()));
        firebaseref.child("W4").set(String(g100.value.trim()));
        firebaseref.child("W5").set(String(g200.value.trim()));
        firebaseref.child("W6").set(String(g250.value.trim()));
        firebaseref.child("W7").set(String(g500.value.trim()));
        firebaseref.child("W8").set(String(g750.value.trim()));


    }
    else if(units.options[units.selectedIndex].value=="litre")
    {
        firebaseref.child("W1").set(String(l1.value.trim()));
        firebaseref.child("W2").set(String(l2.value.trim()));
        firebaseref.child("W3").set(String(l3.value.trim()));
        firebaseref.child("W4").set(String(l5.value.trim()));
        firebaseref.child("W5").set(String(l6.value.trim()));
        firebaseref.child("W6").set(String(l10.value.trim()));
        firebaseref.child("W7").set(String(l15.value.trim()));
        firebaseref.child("W8").set(String(l25.value.trim()));


    }
    else if(units.options[units.selectedIndex].value=="ml")
    {
        firebaseref.child("W1").set(String(m10.value.trim()));
        firebaseref.child("W2").set(String(m25.value.trim()));
        firebaseref.child("W3").set(String(m50.value.trim()));
        firebaseref.child("W4").set(String(m100.value.trim()));
        firebaseref.child("W5").set(String(m200.value.trim()));
        firebaseref.child("W6").set(String(m250.value.trim()));
        firebaseref.child("W7").set(String(m500.value.trim()));
        firebaseref.child("W8").set(String(m750.value.trim()));



    }
    else if(units.options[units.selectedIndex].value=="peices")
    {

        firebaseref.child("W1").set(String(p1.value.trim()));
        firebaseref.child("W2").set(String(p2.value.trim()));
        firebaseref.child("W3").set(String(p3.value.trim()));
        firebaseref.child("W4").set(String(p5.value.trim()));
        firebaseref.child("W5").set(String(p6.value.trim()));
        firebaseref.child("W6").set(String(p10.value.trim()));
        firebaseref.child("W7").set(String(p12.value.trim()));
        firebaseref.child("W8").set(String(p30.value.trim()));

    }

    firebaseref.child("S1").set(0);
    firebaseref.child("S2").set(0);
    firebaseref.child("S3").set(0);
    firebaseref.child("S4").set(0);
    firebaseref.child("S5").set(0);
    firebaseref.child("S6").set(0);
    firebaseref.child("S7").set(0);
    firebaseref.child("S8").set(0);
   
    
    
    name.value="";
    desc.value="";
    category.value="Select";
    units.value="Select";
    window.path1="";
         k1.value="";
         k2.value="";
         k3.value="";
         k5.value="";
         k6.value="";
         k10.value="";
         k15.value="";
         k25.value="";
        g10.value="";
        g25.value="";
        g50.value="";
        g100.value="";
        g200.value="";
        g250.value="";
        g500.value="";
        g750.value="";
        l1.value="";
        l2.value="";
        l3.value="";
        l5.value="";
        l6.value="";
        l10.value="";
        l15.value="";
        l25.value="";
        m10.value="";
        m25.value="";
        m50.value="";
        m100.value="";
        m200.value="";
        m250.value="";
        m500.value="";
        m750.value="";
        p1.value="";
        p2.value="";
        p3.value="";
        p5.value="";
        p6.value="";
        p10.value="";
        p12.value="";
        p30.value="";
    $('#image1').val('');
    updatebtn.style.visibility="hidden";
    deletebtn.style.visibility="hidden";
    submitbtn.style.visibility="visible";


    var pcs = document.getElementById('pcs');
    var kgs = document.getElementById('kgs');
    var ltr = document.getElementById('ltr');
    var gm = document.getElementById('gm');
    var ml = document.getElementById('ml');

    pcs.style.display = "none";
    kgs.style.display = "none";
    ltr.style.display = "none";
    gm.style.display = "none";
    ml.style.display = "none";

    $('#sname').empty();
    pushid=[];
    units=[];
var select = document.getElementById("sname");

var option = document.createElement('option');
    option.text = option.value = "Select";
    select.add(option, 0);


firebase.database().ref().child("Grocery").once('value').then(function(snapshot) {
       snapshot.forEach(function(child){
        var option = document.createElement('option');
        option.text = option.value = child.val().Name;
        select.add(option, 0);
        units.push(child.val().Name);
        pushid.push(child.val().PushId);
       });
       select.value="Select";
       units.reverse();
       pushid.reverse();
    });  


    alert("Succesfully Added!!!");

});


updatebtn.addEventListener("click", function () {
    var name=document.getElementById("name");
    var desc=document.getElementById("desc");
    var category=document.getElementById("category");
    var status=document.getElementById("status1");
    var units=document.getElementById("units");
    var p1=document.getElementById("p1");
    var p2=document.getElementById("p2");
    var p3=document.getElementById("p3");
    var p5=document.getElementById("p5");
    var p6=document.getElementById("p6");
    var p10=document.getElementById("p10");
    var p12=document.getElementById("p12");
    var p30=document.getElementById("p30");
    var k1=document.getElementById("k1");
    var k2=document.getElementById("k2");
    var k3=document.getElementById("k3");
    var k5=document.getElementById("k5");
    var k6=document.getElementById("k6");
    var k10=document.getElementById("k10");
    var k15=document.getElementById("k15");
    var k25=document.getElementById("k25");
    var l1=document.getElementById("l1");
    var l2=document.getElementById("l2");
    var l3=document.getElementById("l3");
    var l5=document.getElementById("l5");
    var l6=document.getElementById("l6");
    var l10=document.getElementById("l10");
    var l15=document.getElementById("l15");
    var l25=document.getElementById("l25");
    var g10=document.getElementById("g10");
    var g25=document.getElementById("g25");
    var g50=document.getElementById("g50");
    var g100=document.getElementById("g100");
    var g200=document.getElementById("g200");
    var g250=document.getElementById("g250");
    var g500=document.getElementById("g500");
    var g750=document.getElementById("g750");
    var m10=document.getElementById("m10");
    var m25=document.getElementById("m25");
    var m50=document.getElementById("m50");
    var m100=document.getElementById("m100");
    var m200=document.getElementById("m200");
    var m250=document.getElementById("m250");
    var m500=document.getElementById("m500");
    var m750=document.getElementById("m750");
    
    if(name.value.length==0)
        {
            alert("Enter Name");
            name.focus();
            return;
        }


        if(desc.value.length==0)
        {
            alert("Enter Description");
            desc.focus();
            return;
        }

        if(category.options[category.selectedIndex].value=="Select")
        {
            alert("Select Category Name");
            category.focus();
            return;
        }

        if(units.options[units.selectedIndex].value=="Select")
        {
            alert("Select Units");
            units.focus();
            return;
        }
        else if(units.options[units.selectedIndex].value=="kilogram")
        {

            
            if(k1.value.length==0)
            {
                alert("Enter 1 KG Price");
                l1.focus();
                return;
            }

            if(k2.value.length==0)
            {
                alert("Enter 2 KG Price");
                l2.focus();
                return;
            }

            if(k3.value.length==0)
            {
                alert("Enter 3 KG Price");
                l3.focus();
                return;
            }

            if(k5.value.length==0)
            {
                alert("Enter 5 KG Price");
                l5.focus();
                return;
            }

            if(k6.value.length==0)
            {
                alert("Enter 6 KG Price");
                l6.focus();
                return;
            }

            if(k10.value.length==0)
            {
                alert("Enter 10 KG Price");
                l10.focus();
                return;
            }

            if(k15.value.length==0)
            {
                alert("Enter 15 KG Price");
                l15.focus();
                return;
            }

            if(k25.value.length==0)
            {
                alert("Enter 25 KG Price");
                l25.focus();
                return;
            }
                
        }
        else if(units.options[units.selectedIndex].value=="gram")
        {
            if(g10.value.length==0)
            {
                alert("Enter 10 GM Price");
                g10.focus();
                return;
            }

            if(g25.value.length==0)
            {
                alert("Enter 25 GM Price");
                g25.focus();
                return;
            }

            if(g50.value.length==0)
            {
                alert("Enter 50 GM Price");
                g50.focus();
                return;
            }

            if(g100.value.length==0)
            {
                alert("Enter 100 GM Price");
                g100.focus();
                return;
            }

            if(g200.value.length==0)
            {
                alert("Enter 200 GM Price");
                g200.focus();
                return;
            }

            if(g250.value.length==0)
            {
                alert("Enter 250 GM Price");
                g250.focus();
                return;
            }

            if(g500.value.length==0)
            {
                alert("Enter 500 GM Price");
                g500.focus();
                return;
            }

            if(g750.value.length==0)
            {
                alert("Enter 750 GM Price");
                g750.focus();
                return;
            }
        }
        else if(units.options[units.selectedIndex].value=="litre")
        {
            
            if(l1.value.length==0)
                {
                    alert("Enter 1 Ltr Price");
                    l1.focus();
                    return;
                }

                if(l2.value.length==0)
                {
                    alert("Enter 2 Ltr Price");
                    l2.focus();
                    return;
                }

                if(l3.value.length==0)
                {
                    alert("Enter 3 Ltr Price");
                    l3.focus();
                    return;
                }

                if(l5.value.length==0)
                {
                    alert("Enter 5 Ltr Price");
                    l5.focus();
                    return;
                }

                if(l6.value.length==0)
                {
                    alert("Enter 6 Ltr Price");
                    l6.focus();
                    return;
                }

                if(l10.value.length==0)
                {
                    alert("Enter 10 Ltr Price");
                    l10.focus();
                    return;
                }

                if(l15.value.length==0)
                {
                    alert("Enter 15 Ltr Price");
                    l15.focus();
                    return;
                }

                if(l25.value.length==0)
                {
                    alert("Enter 25 Ltr Price");
                    l25.focus();
                    return;
                }

        }
        else if(units.options[units.selectedIndex].value=="ml")
        {
            if(m10.value.length==0)
                {
                    alert("Enter 10 ML Price");
                    m10.focus();
                    return;
                }

                if(m25.value.length==0)
                {
                    alert("Enter 25 ML Price");
                    m25.focus();
                    return;
                }

                if(m50.value.length==0)
                {
                    alert("Enter 50 ML Price");
                    m50.focus();
                    return;
                }

                if(m100.value.length==0)
                {
                    alert("Enter 100 ML Price");
                    m100.focus();
                    return;
                }

                if(m200.value.length==0)
                {
                    alert("Enter 200 ML Price");
                    m200.focus();
                    return;
                }

                if(m250.value.length==0)
                {
                    alert("Enter 250 ML Price");
                    m250.focus();
                    return;
                }

                if(m500.value.length==0)
                {
                    alert("Enter 500 ML Price");
                    m500.focus();
                    return;
                }

                if(m750.value.length==0)
                {
                    alert("Enter 750 ML Price");
                    m750.focus();
                    return;
                }
        }
        else if(units.options[units.selectedIndex].value=="peices")
        {
            if(p1.value.length==0)
                {
                    alert("Enter 1 Pcs Price");
                    p1.focus();
                    return;
                }

                if(p2.value.length==0)
                {
                    alert("Enter 2 Pcs Price");
                    p2.focus();
                    return;
                }

                if(p3.value.length==0)
                {
                    alert("Enter 3 Pcs Price");
                    p3.focus();
                    return;
                }

                if(p5.value.length==0)
                {
                    alert("Enter 5 Pcs Price");
                    p5.focus();
                    return;
                }

                if(p6.value.length==0)
                {
                    alert("Enter 6 Pcs Price");
                    p6.focus();
                    return;
                }

                if(p10.value.length==0)
                {
                    alert("Enter 10 Pcs Price");
                    p10.focus();
                    return;
                }

                if(p12.value.length==0)
                {
                    alert("Enter 12 Pcs Price");
                    p12.focus();
                    return;
                }

                if(p30.value.length==0)
                {
                    alert("Enter 30 Pcs Price");
                    p30.focus();
                    return;
                }
        }

        // for(var i=0;i<units.length;i++) {
        //     if(name.value===units[i])
        //     {alert("Item already Exists!!!");
        //     name.focus;
        //     return;}
        //   }

          if(window.temp<1){
              alert('Wait for images to upload');
              return;
          }
    

    var firebaseref=firebase.database().ref().child("Grocery").child(pushid[sname.selectedIndex]);
    firebaseref.child("PushId").set(firebaseref.getKey());
    firebaseref.child("Name").set(String(name.value.trim()));
    firebaseref.child("Desc").set(String(desc.value.trim()));
    firebaseref.child("Category").set(String(category.options[category.selectedIndex].value));
    firebaseref.child("Status").set(String(status.options[status.selectedIndex].value));
    firebaseref.child("CategoryName").set(String(category.options[category.selectedIndex].value + name.value.trim()));
    firebaseref.child("Units").set(String(units.options[units.selectedIndex].value));
    firebaseref.child("Image").set(String(window.path1));



    if(units.options[units.selectedIndex].value=="kilogram")
    {
        firebaseref.child("W1").set(String(k1.value.trim()));
        firebaseref.child("W2").set(String(k2.value.trim()));
        firebaseref.child("W3").set(String(k3.value.trim()));
        firebaseref.child("W4").set(String(k5.value.trim()));
        firebaseref.child("W5").set(String(k6.value.trim()));
        firebaseref.child("W6").set(String(k10.value.trim()));
        firebaseref.child("W7").set(String(k15.value.trim()));
        firebaseref.child("W8").set(String(k25.value.trim()));
     

    }
    else if(units.options[units.selectedIndex].value=="gram")
    {
        firebaseref.child("W1").set(String(g10.value.trim()));
        firebaseref.child("W2").set(String(g25.value.trim()));
        firebaseref.child("W3").set(String(g50.value.trim()));
        firebaseref.child("W4").set(String(g100.value.trim()));
        firebaseref.child("W5").set(String(g200.value.trim()));
        firebaseref.child("W6").set(String(g250.value.trim()));
        firebaseref.child("W7").set(String(g500.value.trim()));
        firebaseref.child("W8").set(String(g750.value.trim()));


    }
    else if(units.options[units.selectedIndex].value=="litre")
    {
        firebaseref.child("W1").set(String(l1.value.trim()));
        firebaseref.child("W2").set(String(l2.value.trim()));
        firebaseref.child("W3").set(String(l3.value.trim()));
        firebaseref.child("W4").set(String(l5.value.trim()));
        firebaseref.child("W5").set(String(l6.value.trim()));
        firebaseref.child("W6").set(String(l10.value.trim()));
        firebaseref.child("W7").set(String(l15.value.trim()));
        firebaseref.child("W8").set(String(l25.value.trim()));
    }
    else if(units.options[units.selectedIndex].value=="ml")
    {
        firebaseref.child("W1").set(String(m10.value.trim()));
        firebaseref.child("W2").set(String(m25.value.trim()));
        firebaseref.child("W3").set(String(m50.value.trim()));
        firebaseref.child("W4").set(String(m100.value.trim()));
        firebaseref.child("W5").set(String(m200.value.trim()));
        firebaseref.child("W6").set(String(m250.value.trim()));
        firebaseref.child("W7").set(String(m500.value.trim()));
        firebaseref.child("W8").set(String(m750.value.trim()));

    }
    else if(units.options[units.selectedIndex].value=="peices")
    {

        firebaseref.child("W1").set(String(p1.value.trim()));
        firebaseref.child("W2").set(String(p2.value.trim()));
        firebaseref.child("W3").set(String(p3.value.trim()));
        firebaseref.child("W4").set(String(p5.value.trim()));
        firebaseref.child("W5").set(String(p6.value.trim()));
        firebaseref.child("W6").set(String(p10.value.trim()));
        firebaseref.child("W7").set(String(p12.value.trim()));
        firebaseref.child("W8").set(String(p30.value.trim()));

    }
   
    
    
    name.value="";
    desc.value="";
    category.value="Select";
    units.value="Select";
    window.path1="";
    k1.value="";
    k2.value="";
    k3.value="";
    k5.value="";
    k6.value="";
    k10.value="";
    k15.value="";
    k25.value="";
   g10.value="";
   g25.value="";
   g50.value="";
   g100.value="";
   g200.value="";
   g250.value="";
   g500.value="";
   g750.value="";
   l1.value="";
   l2.value="";
   l3.value="";
   l5.value="";
   l6.value="";
   l10.value="";
   l15.value="";
   l25.value="";
   m10.value="";
   m25.value="";
   m50.value="";
   m100.value="";
   m200.value="";
   m250.value="";
   m500.value="";
   m750.value="";
   p1.value="";
   p2.value="";
   p3.value="";
   p5.value="";
   p6.value="";
   p10.value="";
   p12.value="";
   p30.value="";
    $('#image1').val('');
    updatebtn.style.visibility="hidden";
    deletebtn.style.visibility="hidden";
    submitbtn.style.visibility="visible";

    var pcs = document.getElementById('pcs');
    var kgs = document.getElementById('kgs');
    var ltr = document.getElementById('ltr');
    var gm = document.getElementById('gm');
    var ml = document.getElementById('ml');

    pcs.style.display = "none";
    kgs.style.display = "none";
    ltr.style.display = "none";
    gm.style.display = "none";
    ml.style.display = "none";

    $('#sname').empty();
    pushid=[];
    units=[];
var select = document.getElementById("sname");

var option = document.createElement('option');
    option.text = option.value = "Select";
    select.add(option, 0);


firebase.database().ref().child("Grocery").once('value').then(function(snapshot) {
       snapshot.forEach(function(child){
        var option = document.createElement('option');
        option.text = option.value = child.val().Name;
        select.add(option, 0);
        units.push(child.val().Name);
        pushid.push(child.val().PushId);
       });
       select.value="Select";
       units.reverse();
       pushid.reverse();
    });  


    alert("Succesfully Added!!!");

});



  deletebtn.addEventListener('click',function(){
    var name=document.getElementById("name");
    var desc=document.getElementById("desc");
    var category=document.getElementById("category");
    var status=document.getElementById("status1");
    var units=document.getElementById("units");
    var p1=document.getElementById("p1");
    var p2=document.getElementById("p2");
    var p3=document.getElementById("p3");
    var p5=document.getElementById("p5");
    var p6=document.getElementById("p6");
    var p10=document.getElementById("p10");
    var p12=document.getElementById("p12");
    var p30=document.getElementById("p30");
    var k1=document.getElementById("k1");
    var k2=document.getElementById("k2");
    var k3=document.getElementById("k3");
    var k5=document.getElementById("k5");
    var k6=document.getElementById("k6");
    var k10=document.getElementById("k10");
    var k15=document.getElementById("k15");
    var k25=document.getElementById("k25");
    var l1=document.getElementById("l1");
    var l2=document.getElementById("l2");
    var l3=document.getElementById("l3");
    var l5=document.getElementById("l5");
    var l6=document.getElementById("l6");
    var l10=document.getElementById("l10");
    var l15=document.getElementById("l15");
    var l25=document.getElementById("l25");
    var g10=document.getElementById("g10");
    var g25=document.getElementById("g25");
    var g50=document.getElementById("g50");
    var g100=document.getElementById("g100");
    var g200=document.getElementById("g200");
    var g250=document.getElementById("g250");
    var g500=document.getElementById("g500");
    var g750=document.getElementById("g750");
    var m10=document.getElementById("m10");
    var m25=document.getElementById("m25");
    var m50=document.getElementById("m50");
    var m100=document.getElementById("m100");
    var m200=document.getElementById("m200");
    var m250=document.getElementById("m250");
    var m500=document.getElementById("m500");
    var m750=document.getElementById("m750");
 
    if(sname.options[sname.selectedIndex].value=="Select")
        {
            alert("Select Product Name");
            sname.focus();
            return;
        }



    var firebaseref=firebase.database().ref().child("Grocery").child(pushid[sname.selectedIndex]);
    firebaseref.remove();
    
    

    name.value="";
    desc.value="";
    category.value="Select";
    units.value="Select";
    window.path1="";
    k1.value="";
    k2.value="";
    k3.value="";
    k5.value="";
    k6.value="";
    k10.value="";
    k15.value="";
    k25.value="";
   g10.value="";
   g25.value="";
   g50.value="";
   g100.value="";
   g200.value="";
   g250.value="";
   g500.value="";
   g750.value="";
   l1.value="";
   l2.value="";
   l3.value="";
   l5.value="";
   l6.value="";
   l10.value="";
   l15.value="";
   l25.value="";
   m10.value="";
   m25.value="";
   m50.value="";
   m100.value="";
   m200.value="";
   m250.value="";
   m500.value="";
   m750.value="";
   p1.value="";
   p2.value="";
   p3.value="";
   p5.value="";
   p6.value="";
   p10.value="";
   p12.value="";
   p30.value="";
    $('#image1').val('');
    updatebtn.style.visibility="hidden";
    deletebtn.style.visibility="hidden";
    submitbtn.style.visibility="visible";

    var pcs = document.getElementById('pcs');
    var kgs = document.getElementById('kgs');
    var ltr = document.getElementById('ltr');
    var gm = document.getElementById('gm');
    var ml = document.getElementById('ml');

    pcs.style.display = "none";
    kgs.style.display = "none";
    ltr.style.display = "none";
    gm.style.display = "none";
    ml.style.display = "none";

    $('#sname').empty();
    pushid=[];
    units=[];
var select = document.getElementById("sname");

var option = document.createElement('option');
    option.text = option.value = "Select";
    select.add(option, 0);


    
firebase.database().ref().child("Grocery").once('value').then(function(snapshot) {
       snapshot.forEach(function(child){
        var option = document.createElement('option');
        option.text = option.value = child.val().Name;
        select.add(option, 0);
        units.push(child.val().Name);
        pushid.push(child.val().PushId);
       });
       select.value="Select";
       units.reverse();
       pushid.reverse();
    });  


    alert('Successfully Deleted!');
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
     const ref = firebase.storage().ref("/Products");
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

