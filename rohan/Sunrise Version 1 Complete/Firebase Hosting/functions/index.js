const functions = require('firebase-functions');
const admin = require('firebase-admin');
admin.initializeApp(functions.config().firebase);

exports.sendNotification = functions.database.ref('/Users/{usercreated}').onCreate((snap,context) => {

    const transactionid = context.params.usercreated;

    console.log("UserCreated");
    const messagedata=snap.val();
    const clientid=messagedata;

    console.log("Client Id",clientid);


    return admin.database().ref("/Users/+919663191201").once('value').then(snap => {
        const token = snap.child("MessagingToken").val();
        console.log("token: ", token);

                                //we have everything we need
                                //Build the message payload and send the message
                                console.log("Construction the notification message.");
                                const payload = {
                                    data: {
                                        title: 'New User Registered',
                                        body: 'New User Has Been Logged In',
                                        sound: 'default',
                                        badge: '1'                                      
                                       }
                                };
                            


                        return admin.messaging().sendToDevice(token, payload)
                            .then(function(response) {
                            console.log("Successfully sent message:", response);
                            return
                            })
                            .catch(function(error) {
                            console.log("Error sending message:", error);
                            return
                            });

                 
                });

});





exports.sendNotification1 = functions.database.ref('/Orders/{order}').onCreate((snap,context) => {

    const transactionid = context.params.order;

    console.log("OrderPlaced");
    const messagedata=snap.val();
    const clientid=messagedata;

    console.log("Client Id",clientid);


    return admin.database().ref("/Users/+919663191201").once('value').then(snap => {
        const token = snap.child("MessagingToken").val();
        console.log("token: ", token);

                                //we have everything we need
                                //Build the message payload and send the message
                                console.log("Construction the notification message.");
                                const payload = {
                                    data: {
                                        title: 'New User Registered',
                                        body: 'New User Has Been Logged In',
                                        sound: 'default',
                                        badge: '1'                                      
                                       }
                                };
                            


                        return admin.messaging().sendToDevice(token, payload)
                            .then(function(response) {
                            console.log("Successfully sent message:", response);
                           return
                            })
                            .catch(function(error) {
                            console.log("Error sending message:", error);
                            return
                            });

                 
                });

});
