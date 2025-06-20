package sunrise.release.rohan.sunriseseller.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import sunrise.release.rohan.sunriseseller.Functionality.Encryption;
import sunrise.release.rohan.sunriseseller.Functionality.LoginSession;
import sunrise.release.rohan.sunriseseller.R;

public class BillDetailFragment extends Fragment {
    TextView order_productname,order_mrp,order_rewards,order_date,order_orderno,oid,redeemtext;
    ImageView order_image;
    Button proceed;
    CheckBox verify,verify1;
    String userid,status,pushid;
    int userrewards;
    EditText redeempoints;
    int amount;
    int random;

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();

        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            final View mView= inflater.inflate(R.layout.fragment_bill_detail, container, false);
            final Bundle bundle=getArguments();

            final String name=bundle.getString("name");
            order_productname=(TextView)mView.findViewById(R.id.oproduct);
            order_mrp=(TextView)mView.findViewById(R.id.omrp);
            order_rewards=(TextView)mView.findViewById(R.id.orewards);
            oid=(TextView)mView.findViewById(R.id.oid);
            order_date=(TextView)mView.findViewById(R.id.odate);
            order_orderno=(TextView)mView.findViewById(R.id.oorderno);
            redeemtext=(TextView)mView.findViewById(R.id.redeemtext);
            order_image=(ImageView)mView.findViewById(R.id.oimage);
            proceed=(Button)mView.findViewById(R.id.proceed);
            verify=(CheckBox)mView.findViewById(R.id.verifychechbox);
            verify1=(CheckBox)mView.findViewById(R.id.verifychechbox1);
            redeempoints=(EditText)mView.findViewById(R.id.redeempoints);
//
//            if(Status.equalsIgnoreCase("Processing")) {
//                order_status.setText(Status);
//                order_status.setTextColor(Color.parseColor("#E90303"));
//            }
//            if(Status.equalsIgnoreCase("Delivered")) {
//                order_status.setText(Status);
//                order_status.setTextColor(Color.parseColor("#23C348"));
//            }
//            Glide.with(ctx)
//                    .load(Image1)
//                    .into(order_image);

            database.child("Orders").orderByChild("OrderNo").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot v:dataSnapshot.getChildren())
                        {
                            order_productname.setText(v.child("ProductName").getValue().toString());
                            order_mrp.setText(v.child("Mrp").getValue().toString());
                            order_rewards.setText(v.child("Rewards").getValue().toString());
                            order_date.setText(v.child("Date").getValue().toString());
                            order_orderno.setText(name);
                            status=v.child("OrderStatus").getValue().toString();
                            userid=v.child("Userid").getValue().toString();
                            oid.setText(userid);
                            Glide.with(getContext()).load(v.child("Image1").getValue().toString()).into(order_image);
                            pushid=v.child("Pushid").getValue().toString();
                        }

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            proceed.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!status.equalsIgnoreCase("Processing")){
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setTitle("Sucessful");
                        builder.setMessage("The Order has Been Delivered... Thank You")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {

                                        Fragment fragment=new BillFragment();
                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                        fragmentManager.beginTransaction()
                                                .addToBackStack(null)
                                                .replace(R.id.frame_container, fragment).commit();

                                    }
                                })
                                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                        return;
                    }




                    if(!verify.isChecked())
                    {
                        Toast.makeText(getContext(),"Please Verify the details and accept it",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    final String[] m_Text = new String[1];

                    if(verify1.isChecked())
                    {
                        if(redeempoints.getVisibility()==View.GONE) {
                            redeempoints.setVisibility(View.VISIBLE);
                            redeemtext.setVisibility(View.VISIBLE);
                            DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
                            ref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {

                                    String a = "User " + userid + " has ";

                                    userrewards= Integer.parseInt(dataSnapshot.child("Rewards").getValue().toString());

                                    a +=userrewards+" Points";
                                    redeemtext.setText(a);
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });
                            return;
                        }
                        else if(redeempoints.getVisibility()==View.VISIBLE){
                            int rewards=Integer.parseInt(redeempoints.getText().toString());
                            if(rewards>userrewards){
                                Toast.makeText(getContext(),"Enter Redeemption points Properly",Toast.LENGTH_LONG).show();
                                return;
                            }
                        }
                    }




                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("MPIN");
                    final LoginSession session=new LoginSession(getContext());
                        // Set up the input
                        final EditText input = new EditText(getContext());
//                        int paddingDp = 25;
//                        float density = getContext().getResources().getDisplayMetrics().density;
//                        int paddingPixel = (int)(paddingDp * density);
//                        input.setPadding(paddingPixel,0,paddingPixel,0);
                        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        builder.setView(input);

                        // Set up the buttons
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                m_Text[0] = input.getText().toString();
                                final String[] mpin = new String[1];
                                DatabaseReference pref=FirebaseDatabase.getInstance().getReference().child("SellerUsers").child("+91"+session.getusename());
                                pref.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        if(dataSnapshot.hasChild("Passcode")) {
                                            mpin[0] = dataSnapshot.child("Passcode").getValue().toString();
                                            try {
                                                mpin[0] = Encryption.decrypt(mpin[0]);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                            if (m_Text[0].equalsIgnoreCase(mpin[0])) {


                                                FirebaseDatabase.getInstance().getReference().child("SellerUsers").child("+91"+session.getusename())
                                                        .addListenerForSingleValueEvent(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                amount = Integer.parseInt(dataSnapshot.child("Rewards").getValue().toString());
                                                                if(amount>=Integer.parseInt(order_rewards.getText().toString())){
                                                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("Orders").child(pushid);
                                                                    ref1.child("OrderStatus").setValue("Delivered");

                                                                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
                                                                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                                                                        @Override
                                                                        public void onDataChange(DataSnapshot dataSnapshot) {
                                                                            int a = Integer.parseInt(dataSnapshot.child("Rewards").getValue().toString());
                                                                            a = a + Integer.parseInt(order_rewards.getText().toString());
                                                                            if(redeempoints.getVisibility()==View.VISIBLE&&Integer.parseInt(redeempoints.getText().toString())>0){
                                                                                a=a-Integer.parseInt(redeempoints.getText().toString());
                                                                            }


                                                                            final int finalA = a;

                                                                            Date currentDate = new Date(System.currentTimeMillis());
                                                                            SimpleDateFormat df = new SimpleDateFormat("ddMMyy");
                                                                            String date1 = df.format(currentDate);
                                                                            random = new Random().nextInt(10000);
                                                                            String abc = "TRANS" + date1+random;

                                                                                    //Redemption Occuring here
                                                                                    if(redeempoints.getVisibility()==View.VISIBLE&&Integer.parseInt(redeempoints.getText().toString())>0){

                                                                                        int b=finalA-Integer.parseInt(order_rewards.getText().toString());


                                                                                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
                                                                                        ref1.child("Rewards").setValue(Integer.toString(b));

                                                                                        final DatabaseReference rref = FirebaseDatabase.getInstance().getReference().child("Rewards").push();
                                                                                        rref.child("PushId").setValue(rref.getKey().toString());
                                                                                        rref.child("UserId").setValue(userid);
                                                                                        rref.child("Amount").setValue(redeempoints.getText().toString());
                                                                                        rref.child("Balance").setValue(Integer.toString(b));
                                                                                        rref.child("TransactionId").setValue(abc);
                                                                                        Date currentDate1 = new Date(System.currentTimeMillis());
                                                                                        SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
                                                                                        String date2 = df1.format(currentDate1);
                                                                                        rref.child("Date").setValue(date2);
                                                                                        rref.child("Authorised").setValue("+91" + session.getusename());

                                                                                        rref.child("AuthorisedType").setValue("Cr");
                                                                                        rref.child("AuthorisedName").setValue("Redemption");
                                                                                        rref.child("Name").setValue("Redemption");
                                                                                        rref.child("Type").setValue("Dr");
                                                                                        amount = amount + Integer.parseInt(redeempoints.getText().toString());
                                                                                        DatabaseReference ref2 = FirebaseDatabase.getInstance().getReference().child("SellerUsers").child("+91" + session.getusename());
                                                                                        ref2.child("Rewards").setValue(Integer.toString(amount));
                                                                                        rref.child("AuthorisedBalance").setValue(Integer.toString(amount));
                                                                                    }


                                                                                    DatabaseReference ref10 = FirebaseDatabase.getInstance().getReference().child("Users").child(userid);
                                                                                      ref10.child("Rewards").setValue(Integer.toString(finalA));

                                                                                    random = new Random().nextInt(10000);
                                                                                    abc = "TRANS" + date1+random;
                                                                                    final DatabaseReference rref = FirebaseDatabase.getInstance().getReference().child("Rewards").push();
                                                                                    rref.child("PushId").setValue(rref.getKey().toString());
                                                                                    rref.child("UserId").setValue(userid);
                                                                                    rref.child("Amount").setValue(order_rewards.getText().toString());
                                                                                    rref.child("Balance").setValue(Integer.toString(finalA));
                                                                                    rref.child("TransactionId").setValue(abc);
                                                                                    Date currentDate1 = new Date(System.currentTimeMillis());
                                                                                    SimpleDateFormat df1 = new SimpleDateFormat("dd-MM-yyyy");
                                                                                    String date2 = df1.format(currentDate1);
                                                                                    rref.child("Date").setValue(date2);
                                                                                    rref.child("Authorised").setValue("+91" + session.getusename());

                                                                                    rref.child("AuthorisedType").setValue("Dr");
                                                                                    rref.child("AuthorisedName").setValue("Order");

                                                                                    rref.child("Type").setValue("Cr");
                                                                                    rref.child("Name").setValue("Order");
                                                                                    rref.child("OrderNumber").setValue(order_orderno.getText().toString());

                                                                                    amount = amount - Integer.parseInt(order_rewards.getText().toString());
                                                                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference().child("SellerUsers").child("+91" + session.getusename());
                                                                                    ref1.child("Rewards").setValue(Integer.toString(amount));
                                                                                    rref.child("AuthorisedBalance").setValue(Integer.toString(amount));


                                                                                        SuccessFragment fragment = new SuccessFragment();
                    //                                                                    int cash=0;
                    //                                                                    if(redeempoints.getVisibility()==View.VISIBLE&&Integer.parseInt(redeempoints.getText().toString())>0) {
                    //                                                                        cash = Integer.parseInt(order_mrp.getText().toString().substring(1)) - Integer.parseInt(redeempoints.getText().toString());
                    //                                                                    }
                    //                                                                    else
                    //                                                                    {
                    //                                                                        cash=Integer.parseInt(order_mrp.getText().toString().substring(1));
                    //                                                                    }
                    //                                                                    bundle.putString("amount",Integer.toString(cash));
                    //                                                                    fragment.setArguments(bundle);
                                                                                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                                                                        fragmentManager.beginTransaction()
                                                                                                .replace(R.id.frame_container, fragment).commit();









                                                                        }

                                                                        @Override
                                                                        public void onCancelled(DatabaseError databaseError) {

                                                                        }
                                                                    });

                                                                }
                                                                else{
                                                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                                    builder.setTitle("TOPUP");
                                                                    builder.setMessage("Please Add Rewards points to ur Wallet")
                                                                            .setCancelable(false)
                                                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                                public void onClick(DialogInterface dialog, int id) {
                                                                                    BillFragment fragment = new BillFragment();
                                                                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                                                                    fragmentManager.beginTransaction()
                                                                                            .replace(R.id.frame_container, fragment).commit();

                                                                                }
                                                                            });


                                                                    AlertDialog alertDialog = builder.create();
                                                                    alertDialog.show();
                                                                }

                                                            }
                                                            @Override
                                                            public void onCancelled(DatabaseError databaseError) {

                                                            }
                                                        });











                                            } else {
                                                Toast.makeText(getContext(), "Mpin Doesnt Match", Toast.LENGTH_SHORT).show();
                                                return;
                                            }
                                        }
                                        else{
                                            Toast.makeText(getContext(),"Please Set Mpin First",Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });


                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        input.requestFocus();
                        builder.show();
                }
            });

            return mView;
            }


}
