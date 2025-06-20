package sunrise.release.rohan.sunrise.Payment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import sunrise.release.rohan.sunrise.Cart.Cart;
import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.Orders.OrdersFragment;
import sunrise.release.rohan.sunrise.R;


public class PaymentFragment extends Fragment {

    LinearLayout codlayout,paytmlayout;
    RadioButton codbutton,paytmbutton;
    Button proceed;
    LoginSession session;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    Fragment fragment=null;
    String b,name,number,rest;
    Bundle bundle=new Bundle();
    Map<String, String> paytmParams = new HashMap<String, String>();

    public PaymentFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_payment, container, false);
        codlayout=v.findViewById(R.id.codlayout);
        paytmlayout=v.findViewById(R.id.paytmlayout);
        codbutton=v.findViewById(R.id.codbutton);
        paytmbutton=v.findViewById(R.id.paytmbutton);
        proceed=v.findViewById(R.id.proceed);
        b=getArguments().getString("pushid");
        name=getArguments().getString("name");
        number=getArguments().getString("number");
        rest=getArguments().getString("rest");
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        session=new LoginSession(getContext());
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
                    DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Orders");
                                     ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                       @Override
                                       public void onDataChange(DataSnapshot dataSnapshot) {
                                           // get total available quest
                                           int size = (int) dataSnapshot.getChildrenCount();
                                           size++;
                                           final String abc="ORD"+Integer.toString(size);
                                           final DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("Orders").push();
                                           ref.child("Pushid").setValue(ref.getKey().toString());
                                           DatabaseReference ref3=FirebaseDatabase.getInstance().getReference().child("Users").child("+91"+session.getusename()).child("Cart").child(b);
                                           ref3.addListenerForSingleValueEvent(new ValueEventListener() {
                                               @Override
                                               public void onDataChange(DataSnapshot dataSnapshot) {
                                                   Cart mobile = dataSnapshot.getValue(Cart.class);



                                                   ref.child("Image1").setValue(mobile.Image1);
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
                                                   ref.child("Mrp").setValue(Integer.toString(price));
                                                   ref.child("Rewards").setValue(Integer.toString(rewards));
                                                   ref.child("OrderStatus").setValue("Processing");
                                                   ref.child("DeliveryName").setValue(name);
                                                   ref.child("DeliveryNumber").setValue(number);
                                                   ref.child("DeliveryAddress").setValue(rest);
                                                   ref.child("PaymentMode").setValue("Cash On Delivery");

                                                   Date currentDate = new Date(System.currentTimeMillis());
                                                   SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
                                                   String date1=df.format(currentDate);
                                                   ref.child("Date").setValue(date1);

                                                   AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                   builder.setTitle("Sucessful");
                                                   builder.setMessage("Your Order Number is "+abc)
                                                           .setCancelable(false)
                                                           .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                               public void onClick(DialogInterface dialog, int id) {

                                                                   fragment=new OrdersFragment();
                                                                   FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                                                   fragmentManager.beginTransaction()
                                                                           .replace(R.id.frame_container, fragment).commit();

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
                                       public void onCancelled(DatabaseError databaseError) {

                                       }
                                   });

                }
                else if(paytmbutton.isChecked()){

//                    PaytmPGService Service = PaytmPGService.getStagingService();
//                    String merchantMid = "SUNRIS35294557467490";
//                    // Key in your staging and production MID available in your dashboard
//                    String merchantKey = "#uP8inic_&8H&dCd";
//                    // Key in your staging and production MID available in your dashboard
//                    String orderId = "order1";
//                    String channelId = "WAP";
//                    String custId = "cust123";
//                    String mobileNo = "7777777777";
//                    String email = "username@emailprovider.com";
//                    String txnAmount = "100.12";
//                    String website = "WEBSTAGING";
//                    // This is the staging value. Production value is available in your dashboard
//                    String industryTypeId = "Retail";
//                    // This is the staging value. Production value is available in your dashboard
//                    String callbackUrl = "https://securegw-stage.paytm.in/theia/paytmCallback?ORDER_ID=order1";
//
//                    paytmParams.put("MID",merchantMid);
//                    paytmParams.put("ORDER_ID",orderId);
//                    paytmParams.put("CHANNEL_ID",channelId);
//                    paytmParams.put("CUST_ID",custId);
//                    paytmParams.put("MOBILE_NO",mobileNo);
//                    paytmParams.put("EMAIL",email);
//                    paytmParams.put("TXN_AMOUNT",txnAmount);
//                    paytmParams.put("WEBSITE",website);
//                    paytmParams.put("INDUSTRY_TYPE_ID",industryTypeId);
//                    paytmParams.put("CALLBACK_URL", callbackUrl);
//
//                    try {
////                        String paytmChecksum = CheckSumServiceHelper.getCheckSumServiceHelper().genrateCheckSum(merchantKey, (TreeMap<String, String>) paytmParams);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    PaytmOrder Order = new PaytmOrder((HashMap<String, String>) paytmParams);
//
//                    Service.initialize(Order, null);
//                    Service.startPaymentTransaction(getContext(), true, true, new PaytmPaymentTransactionCallback() {
//                        /*Call Backs*/
//                        public void someUIErrorOccurred(String inErrorMessage) {
//                            Toast.makeText(getContext(), "UI Error " + inErrorMessage , Toast.LENGTH_LONG).show();
//                        }
//                        public void onTransactionResponse(Bundle inResponse) {
//                            Toast.makeText(getContext(), "Payment Transaction response " + inResponse.toString(), Toast.LENGTH_LONG).show();
//                        }
//                        public void networkNotAvailable() {
//                            Toast.makeText(getContext(), "Network connection error: Check your internet connectivity", Toast.LENGTH_LONG).show();
//                        }
//                        public void clientAuthenticationFailed(String inErrorMessage) {
//                            Toast.makeText(getContext(), "Authentication failed: Server error", Toast.LENGTH_LONG).show();
//                        }
//                        public void onErrorLoadingWebPage(int iniErrorCode, String inErrorMessage, String inFailingUrl) {
//                            Toast.makeText(getContext(), "Unable to load webpage ", Toast.LENGTH_LONG).show();
//                        }
//                        public void onBackPressedCancelTransaction() {
//                            Toast.makeText(getContext(), "Transaction cancelled" , Toast.LENGTH_LONG).show();
//                        }
//                        public void onTransactionCancel(String inErrorMessage, Bundle inResponse) {
//                            Toast.makeText(getContext(), "Transaction Cancelled" + inResponse.toString(), Toast.LENGTH_LONG).show();
//                        }
//                    });


                    Toast.makeText(getContext(),"Coming Soon",Toast.LENGTH_SHORT).show();


                }
                else {
                    Toast.makeText(getContext(),"Select One Options",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

        return v;
    }


}
