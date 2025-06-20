const functions = require('firebase-functions');

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });

const admin= require('firebase-admin');
admin.initializeApp(functions.config().firebase);


//Function for Order  notifications

exports.SendNotificationBP = functions.database.ref("/Orders/{pushid}/")
.onCreate((snap,context)=>{

    // const pushid=context.params.pushid;
    // console.log(pushid);
   
    return admin.database().ref("/"+snap.child("Userid").val()+"/MessagingToken").once('value').then(snap => {
        const token = snap.val();
        console.log("token: ", token);

                                var payload= {
                                    data:{
                                        title:"Order Recieved",
                                        body:"Open Orders to View the Status",    
                                        sound:"default",
                                        badge:"1"
                                    }
                                };
                            

                               return admin.messaging().sendToDevice(token,payload)
                                .then(function(response){
                                    console.log("Successfully sent message:",response);
                                    return
                                })
                                .catch(function(error){
                                    console.log("Error Sending message",error);
                                    return
                                });

                
                });
});




//Function for Admin Notification
exports.SendNotificationStatus = functions.database.ref("/Orders/{pushid}/")
.onUpdate((change,context)=>{

    // const pushid=context.params.pushid;
    // console.log(pushid);

    // console.log(change.before.val());
    // console.log(change.after.val());

        // return admin.database().ref("/SellerUsers/").once('value').then(snap => {
        //     const token = snap.forEach;
        //     console.log("token: ", token);
    
        //                             var payload= {
        //                                 data:{
        //                                     title:"New Task",
        //                                     body:"Press to assign the delivery boy",
        //                                     sound:"default",
        //                                     badge:"1"
        //                                 }
        //                             };
                                
    
        //                            return admin.messaging().sendToDevice(token,payload)
        //                             .then(function(response){
        //                                 console.log("Successfully sent message:",response);
        //                                 return
        //                             })
        //                             .catch(function(error){
        //                                 console.log("Error Sending message",error);
        //                                 return
        //                             });
    
                    
        //             });
    
});

