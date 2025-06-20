package sunrise.release.rohan.sunrise.Payment;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import sunrise.release.rohan.sunrise.Cart.Cart;
import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.Functionality.RetrofitClient;
import sunrise.release.rohan.sunrise.HomeAppliances.Home;
import sunrise.release.rohan.sunrise.MainActivity;
import sunrise.release.rohan.sunrise.Notifications.Constants;
import sunrise.release.rohan.sunrise.Notifications.MyNotificationManager;
import sunrise.release.rohan.sunrise.Orders.OrdersFragment;
import sunrise.release.rohan.sunrise.R;
import sunrise.release.rohan.sunrise.home.home;
import sunrise.release.rohan.sunrise.splash.SplashScreen;

//implementing PaytmPaymentTransactionCallback to track the payment result.
public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {
    private static final String TAG = "RazorPay";
    LinearLayout codlayout,paytmlayout;
    RadioButton codbutton,paytmbutton;
    Button proceed;
    LoginSession session;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    Fragment fragment=null;
    String b,name,number,rest,c;
    Bundle bundle=new Bundle();
    int qty,price,rewards;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Checkout.preload(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        codlayout=findViewById(R.id.codlayout);
        paytmlayout=findViewById(R.id.paytmlayout);
        codbutton=findViewById(R.id.codbutton);
        paytmbutton=findViewById(R.id.paytmbutton);
        proceed=findViewById(R.id.proceed);
        Bundle extras=getIntent().getExtras();
        b=extras.getString("pushid");
        c=extras.getString("price");
        name=extras.getString("name");
        number=extras.getString("number");
        rest=extras.getString("rest");


        mFirebaseDatabase = FirebaseDatabase.getInstance();

        session=new LoginSession(PaymentActivity.this);
        codlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paytmbutton.setChecked(false);
                codbutton.setChecked(true);
            }
        });

        paytmlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codbutton.setChecked(false);
                paytmbutton.setChecked(true);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(codbutton.isChecked())
                {
                            final DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Orders").push();
                            ref.child("Pushid").setValue(ref.getKey().toString());
                            DatabaseReference ref3=FirebaseDatabase.getInstance().getReference().child("Users").child("+91"+session.getusename()).child("Cart").child(b);
                            ref3.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    final Cart mobile = dataSnapshot.getValue(Cart.class);

                                    Date currentDate = new Date(System.currentTimeMillis());
                                    SimpleDateFormat df = new SimpleDateFormat("ddMMyy");
                                    String date1 = df.format(currentDate);
                                    final int random = new Random().nextInt(10000);
                                    final String abc=session.getusename().substring(0,3)+date1+random;

                                    ref.child("Image1").setValue(mobile.Image1);
                                    ref.child("Userid").setValue("+91"+session.getusename());
                                    ref.child("ProductName").setValue(mobile.ProductName);
                                    ref.child("Size").setValue(mobile.Size);
                                    ref.child("OrderNo").setValue(abc);
                                    ref.child("Referral").setValue(session.getreferral());
                                    ref.child("Quantity").setValue(mobile.Quantity);
                                    ref.child("Colour").setValue(mobile.Colour);
                                    qty=Integer.parseInt(mobile.Quantity);
                                    price=Integer.parseInt(mobile.Mrp.substring(1));
                                    rewards=Integer.parseInt(mobile.Rewards);
                                    price=qty*price;
                                    rewards=qty*rewards;
                                    ref.child("Mrp").setValue(Integer.toString(price));
                                    ref.child("Rewards").setValue(Integer.toString(rewards));
                                    ref.child("OrderStatus").setValue("Processing");
                                    ref.child("DeliveryName").setValue(name);
                                    ref.child("DeliveryNumber").setValue(number);
                                    ref.child("DeliveryAddress").setValue(rest);
                                    ref.child("PaymentMode").setValue("Cash On Delivery");

                                    Call<ResponseBody> call= RetrofitClient
                                            .getInstance()
                                            .getApi()
                                            .sendsms("sunris","sunris2019",session.getusename(),"sunris","","The order of the item:"+mobile.ProductName+" has been placed.The Order Number is "+abc+".An Amount of Rs."+price+" Has to be paid during the delivery.","19","");

                                    call.enqueue(new Callback<ResponseBody>() {
                                        @Override
                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                            try {
                                                String s=response.body().string();
                                                Toast.makeText(PaymentActivity.this,s,Toast.LENGTH_LONG).show();
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }


                                        }

                                        @Override
                                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                                            Toast.makeText(PaymentActivity.this,"Failed",Toast.LENGTH_LONG).show();
                                        }
                                    });






                                    Date currentDate1 = new Date(System.currentTimeMillis());
                                    SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
                                    String date2=df1.format(currentDate1);
                                    ref.child("Date").setValue(date2);

                                    AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
                                    builder.setTitle("Sucessful");
                                    builder.setMessage("Your Order Number is "+abc)
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {

                                                    Call<ResponseBody> call= RetrofitClient
                                                            .getInstance()
                                                            .getApi()
                                                            .sendsms("sunris","sunris2019","9980232717","sunris","","The order of the item:"+mobile.ProductName+" has been placed by customer "+session.getusename()+".The Order Number is "+abc+".Amount  Rs."+price,"19","");

                                                    call.enqueue(new Callback<ResponseBody>() {
                                                        @Override
                                                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                            try {
                                                                String s=response.body().string();
                                                                Toast.makeText(PaymentActivity.this,s,Toast.LENGTH_LONG).show();
                                                            } catch (IOException e) {
                                                                e.printStackTrace();
                                                            }


                                                        }

                                                        @Override
                                                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                                                            Toast.makeText(PaymentActivity.this,"Failed",Toast.LENGTH_LONG).show();
                                                        }
                                                    });



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
                                                    MyNotificationManager.getInstance(PaymentActivity.this).displayNotification("Order Placed Successfully!!"," Please pay the amount and get the product Delviered");


                                                    Intent i=new Intent(PaymentActivity.this,home.class);
                                                    i.putExtra("key","Orders");
                                                    startActivity(i);
                                                    finish();

                                                }
                                            });


                                    AlertDialog alertDialog = builder.create();
                                    alertDialog.show();
                                    mref = mFirebaseDatabase.getReference("Users").child("+91"+session.getusename()).child("Cart");
                                    mref.child(b).removeValue();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });



                }
                else if(paytmbutton.isChecked()){

                    LoginSession session=new LoginSession(PaymentActivity.this);
                    session.setdeliveryaddress(rest);
                    session.setdeliveryname(name);
                    session.setdeliverynumber(number);
                    session.setcartid(b);

                    startPayment();

//                    Toast.makeText(PaymentActivity.this,"Coming Soon",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(PaymentActivity.this,"Select One Options",Toast.LENGTH_SHORT).show();
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
            options.put("name", "Sunrise Enterprises");

            /**
             * Description can be anything
             * eg: Order #123123
             *     Invoice Payment
             *     etc.
             */
            options.put("description", session.getusename());

            options.put("currency", "INR");

            /**
             * Amount is always passed in PAISE
             * Eg: "500" = Rs 5.00
             */
            int amount=Integer.parseInt(c)*100;
            options.put("amount", amount);

            checkout.open(activity, options);

        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        session=new LoginSession(PaymentActivity.this);
                final DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Orders").push();
                ref.child("Pushid").setValue(ref.getKey().toString());
                DatabaseReference ref3=FirebaseDatabase.getInstance().getReference().child("Users").child("+91"+session.getusename()).child("Cart").child(session.getcartid());
                ref3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot1) {
                        Cart mobile = dataSnapshot1.getValue(Cart.class);


                        Date currentDate = new Date(System.currentTimeMillis());
                        SimpleDateFormat df = new SimpleDateFormat("ddMMyy");
                        String date1 = df.format(currentDate);
                        final int random = new Random().nextInt(10000);
                        String abc=session.getusename().substring(0,3)+date1+random;




                        ref.child("Userid").setValue("+91"+session.getusename());
                        ref.child("ProductName").setValue(mobile.ProductName);
                        ref.child("Size").setValue(mobile.Size);
                        ref.child("OrderNo").setValue(abc);
                        ref.child("Referral").setValue(session.getreferral());
                        ref.child("Quantity").setValue(mobile.Quantity);
                        Integer qty=Integer.parseInt(mobile.Quantity);
                        Integer price=Integer.parseInt(mobile.Mrp.substring(1));
                        Integer rewards=Integer.parseInt(mobile.Rewards);
                        price=qty*price;
                        rewards=qty*rewards;
                        ref.child("Image1").setValue(mobile.Image1);
                        ref.child("Colour").setValue(mobile.Colour);
                        ref.child("Mrp").setValue(Integer.toString(price));
                        ref.child("Rewards").setValue(Integer.toString(rewards));
                        ref.child("OrderStatus").setValue("Processing");
                        ref.child("DeliveryName").setValue(session.getdeliveryname());
                        ref.child("DeliveryNumber").setValue(session.getdeliverynumber());
                        ref.child("DeliveryAddress").setValue(session.getdeliveryaddress());
                        ref.child("PaymentMode").setValue("Paid Online");

                        Date currentDate1 = new Date(System.currentTimeMillis());
                        SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
                        String date2=df1.format(currentDate1);
                        ref.child("Date").setValue(date2);


                        Call<ResponseBody> call= RetrofitClient
                                .getInstance()
                                .getApi()
                                .sendsms("sunris","sunris2019",session.getusename(),"sunris","","The order of the item:"+mobile.ProductName+" has been placed.The Order Number is "+abc,"19","");

                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    String s=response.body().string();
                                    Toast.makeText(PaymentActivity.this,s,Toast.LENGTH_LONG).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(PaymentActivity.this,"Failed",Toast.LENGTH_LONG).show();
                            }
                        });


                        Call<ResponseBody> call1= RetrofitClient
                                .getInstance()
                                .getApi()
                                .sendsms("sunris","sunris2019","9980232717","sunris","","The order of the item:"+mobile.ProductName+" has been placed by customer "+session.getusename()+".The Order Number is "+abc+"Amount has been paid Online.","19","");
                        call1.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    String s=response.body().string();
                                    Toast.makeText(PaymentActivity.this,s,Toast.LENGTH_LONG).show();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }


                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(PaymentActivity.this,"Failed",Toast.LENGTH_LONG).show();
                            }
                        });


                        AlertDialog.Builder builder = new AlertDialog.Builder(PaymentActivity.this);
                        builder.setTitle("Sucessful");
                        builder.setMessage("Your Order Number is "+abc)
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        Intent i=new Intent(PaymentActivity.this,home.class);
                                        i.putExtra("key","Orders");
                                         startActivity(i);
                                         finish();
//
//                                        fragment=new OrdersFragment();
//                                        FragmentManager fragmentManager = getSupportFragmentManager();
//                                        fragmentManager.beginTransaction()
//                                                .replace(R.id.frame_container, fragment).commit();

                                    }
                                });


                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        mref = mFirebaseDatabase.getReference("Users").child("+91"+session.getusename()).child("Cart");
                        mref.child(b).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot a : dataSnapshot.getChildren())
                                    a.getRef().removeValue();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });



    }

    @Override
    public void onPaymentError(int i, String s) {

        if(Checkout.NETWORK_ERROR==1)
            Toast.makeText(PaymentActivity.this,"Netwrok Error",Toast.LENGTH_SHORT).show();
        if(Checkout.INVALID_OPTIONS==1)
            Toast.makeText(PaymentActivity.this,"Invalid Options",Toast.LENGTH_SHORT).show();
        if(Checkout.PAYMENT_CANCELED==1)
            Toast.makeText(PaymentActivity.this,"Payment Cancelled",Toast.LENGTH_SHORT).show();

        Toast.makeText(PaymentActivity.this,s,Toast.LENGTH_SHORT).show();


    }
}


