package rohan.groups.sunrise.Payment;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rohan.groups.sunrise.Entity.Rewards;
import rohan.groups.sunrise.Functionality.RetrofitClient;
import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.MainActivity;
import rohan.groups.sunrise.Notification.Constants;
import rohan.groups.sunrise.Notification.MyNotificationManager;
import rohan.groups.sunrise.R;

public class PaymentActivityGrocery extends AppCompatActivity implements PaymentResultListener {
    private static final String TAG = "RazorPay";
    Session session;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    String b,c,d,e;
    String flat,landmark,lattitude,longitude,number,address,name,ordersid,pincode;
    Button proceed;
    RadioButton codbutton,payonline;
    String paymenttype="";
    LinearLayout codlayout,paytmlayout;
    double dbalance=0;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Checkout.preload(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_grocery);


        session=new Session(PaymentActivityGrocery.this);
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.GONE);

        Bundle extras=getIntent().getExtras();
        b=extras.getString("amount");
//        c=extras.getString("deduct");
//        d=extras.getString("total");
//        e=extras.getString("rewards");


//        if(Double.parseDouble(b)==0){
//            onPaymentSuccess("Rewards");
//        }



        proceed=findViewById(R.id.proceed);
        codbutton=findViewById(R.id.codbutton);
        payonline=findViewById(R.id.payonline);

        codlayout=findViewById(R.id.codlayout);
        paytmlayout=findViewById(R.id.paytmlayout);



        if(!session.getpincode().substring(9).equals("561208")){
            codlayout.setAlpha(0.5f);
            codbutton.setClickable(false);
        }
        else{
            codlayout.setAlpha(1f);
            codbutton.setClickable(true);
        }


        if(TextUtils.isEmpty(number))
            number=session.getusername().substring(3);
        else
        number=session.getnumber().substring(8);
        pincode=session.getpincode().substring(9);
        flat=session.getdaf().substring(14);
        landmark=session.getdal().substring(10);
        lattitude=session.getdaloc();
        name=session.getdaname();
        address=session.getdaaddress();



        codlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(codlayout.getAlpha()==1f) {
                    payonline.setChecked(false);
                    codbutton.setChecked(true);
                }
                else{
                    Toast.makeText(PaymentActivityGrocery.this,"COD Availble only for 561208 Pincode",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        paytmlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codbutton.setChecked(false);
                payonline.setChecked(true);
            }
        });



        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(codbutton.isChecked()){
                    paymenttype="CASH";
                    onPaymentSuccess("CashOnDelivery");
                    progressBar.setVisibility(View.VISIBLE);
                }
                else if(payonline.isChecked()){
                    paymenttype="ONLINE";
                    int amount=Integer.parseInt(b)*100;
                    progressBar.setVisibility(View.VISIBLE);

                    Call<ResponseBody> call = RetrofitClient
                            .getInstance()
                            .getApi()
                            .sendsms(amount);

                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            try {


                                ordersid = response.body().string();

//                                Toast.makeText(PaymentActivityGrocery.this, ordersid, Toast.LENGTH_LONG).show();

                                startPayment();
                                progressBar.setVisibility(View.GONE);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }


                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            Toast.makeText(PaymentActivityGrocery.this, "Failed", Toast.LENGTH_LONG).show();
                        }
                    });





                }
                else {
                    Toast.makeText(PaymentActivityGrocery.this,"Please Select One Option",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

    }


    public void startPayment() {
        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        /**
         * Set your logo here
         */
        checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            /**
             * Merchant Name
             * eg: ACME Corp || HasGeek etc.
             */
            options.put("name", "Sunrise");

            /**
             * Description can be anything
             * eg: Order #123123
             *     Invoice Payment
             *     etc.
             */
            options.put("description", session.getname());

            options.put("currency", "INR");

            JSONObject preFill = new JSONObject();
            preFill.put("email", "abc@gmail.com");
            preFill.put("contact", session.getusername());
            preFill.put("name", session.getname());

            options.put("prefill", preFill);
            options.put("order_id",ordersid);

            /**
             * Amount is always passed in PAISE
             * Eg: "500" = Rs 5.00
             */
            int amount=Integer.parseInt(b)*100;
            options.put("amount", amount);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(final String s) {
        session=new Session(PaymentActivityGrocery.this);

        Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("ddMMyy");
        final String date1 = df.format(currentDate);


        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
        final String date2 = df1.format(currentDate);


        SimpleDateFormat df2 = new SimpleDateFormat("dd,MMM yyyy HH:mm");
        final String date3 = df2.format(currentDate);


            mref = mFirebaseDatabase.getReference().child("Orders1").push();
            mref.child("Pushid").setValue(mref.getKey());
            mref.child("UserId").setValue(session.getusername());
            mref.child("Payment").setValue(paymenttype);
            mref.child("Status").setValue("1");
            mref.child("Amount").setValue(b);
            mref.child("DeliveryCharges").setValue("0");
            mref.child("Number").setValue(number);
            mref.child("Flat").setValue(flat);
            mref.child("Landmark").setValue(landmark);
            mref.child("Pincode").setValue(pincode);
            mref.child("LocationCoordinates").setValue(lattitude);
            mref.child("Address").setValue(address);
            mref.child("CName").setValue(name);
            final int random = new Random().nextInt(10000);
            mref.child("OrderNo").setValue(session.getusername().substring(3, 6) + date1 + random);
            mref.child("Total").setValue(Double.toString(Double.parseDouble(b)));
            mref.child("OrderDate").setValue(date2);
            mref.child("OrderDateTime").setValue(date3);
            mref.child("RazorpayId").setValue(s);
            mref.child("OrderType").setValue("Grocery");
            mref.child("RazorpayId").setValue(s);
            mref.child("Cashback").setValue("0");
            mref.child("Verified").setValue("No");
            final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername());
            ref.child("CartGrocery").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    mref.child("Cart").setValue(dataSnapshot.getValue());

                    String items = "";
                    for (DataSnapshot v : dataSnapshot.getChildren()) {
                        items += v.child("Name").getValue().toString() + ":" + v.child("Qty").getValue().toString() + ",";
                    }

                    ref.child("CartGrocery").removeValue();
//                    mref.child("Items").setValue(session.getitemname("INAME"));
                    mref.child("ItemsDetails").setValue(items);
                    mref.child("Qty").setValue("" + session.getcartitem());


                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        NotificationManager mNotificationManager =
                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                        int importance = NotificationManager.IMPORTANCE_HIGH;
                        NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
                        mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
                        mChannel.enableLights(true);
                        mChannel.setLightColor(Color.RED);
                        mChannel.enableVibration(true);
                        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                        mNotificationManager.createNotificationChannel(mChannel);
                    }

                    /*
                     * Displaying a notification locally
                     */
                    MyNotificationManager.getInstance(PaymentActivityGrocery.this).displayNotification("Order Placed Successfully!!", "Order is Confirmed and will be delivered shortly");


                    session.setextras("Orders");

                    session.setcartitem("0");
                    session.setcarttotal("0");
                    ArrayList<String> iname=new ArrayList<String>();
                    iname.clear();
                    ArrayList<String> iprice=new ArrayList<String>();
                    iname.clear();
                    ArrayList<String> iqty=new ArrayList<String>();
                    iname.clear();
                    session.setitemname(iname,"INAME");
                    session.setitemprice(iprice,"IPRICE");
                    session.setitemqty(iqty,"IQTY");

                    Intent i = new Intent(PaymentActivityGrocery.this, MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                    finish();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {


                }
            });


    }



    @Override
    public void onPaymentError(int i, String s) {

        if(Checkout.NETWORK_ERROR==1)
            Toast.makeText(PaymentActivityGrocery.this,"Netwrok Error",Toast.LENGTH_SHORT).show();
        if(Checkout.INVALID_OPTIONS==1)
            Toast.makeText(PaymentActivityGrocery.this,"Invalid Options",Toast.LENGTH_SHORT).show();
        if(Checkout.PAYMENT_CANCELED==1)
            Toast.makeText(PaymentActivityGrocery.this,"Payment Cancelled",Toast.LENGTH_SHORT).show();

        Toast.makeText(PaymentActivityGrocery.this,s,Toast.LENGTH_SHORT).show();


    }
}



