package rohan.groups.sunrisedelivery.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.maps.MapView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import rohan.groups.sunrisedelivery.Cart.Cart;
import rohan.groups.sunrisedelivery.Cart.ViewHolder;
import rohan.groups.sunrisedelivery.Entity.Rewards;
import rohan.groups.sunrisedelivery.Functionality.Session;
import rohan.groups.sunrisedelivery.Orders.Order;
import rohan.groups.sunrisedelivery.R;


public class CurrentDetails extends Fragment {

    Session session;
    private String Pushid,customername,address,cash1,number,latlng,payment,cashback,userid,verified;
    MapView mMapView;
    Double lat, lon;

    private BottomSheetDialog bottomSheetDialog;
    RecyclerView mRecyclerView;
    DatabaseReference mref;
    View v;
    double dbalance=0;

    Order orders;
    String a[];


    public CurrentDetails() {
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
        v=inflater.inflate(R.layout.fragment_current_details, container, false);

        session = new Session(getContext());
        final TextView cname = v.findViewById(R.id.customername);
        final TextView landmark = v.findViewById(R.id.landmark);
        TextView call = v.findViewById(R.id.call);
        TextView direction = v.findViewById(R.id.direction);
        final TextView cash = v.findViewById(R.id.cash);
        TextView verify = v.findViewById(R.id.verify);
        ProgressBar progressBar = v.findViewById(R.id.progressBar2);


        mRecyclerView = v.findViewById(R.id.cartview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);




        progressBar.setVisibility(View.GONE);



        Bundle bundle=new Bundle();
        bundle=getArguments();
        Pushid=bundle.getString("pushid");



        FirebaseDatabase.getInstance().getReference().child("Orders1")
                .child(Pushid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        orders = dataSnapshot.getValue(Order.class);

                        cname.setText(orders.CName);
                        landmark.setText(orders.Address);

                        if(orders.Payment.equals("CASH")){
                            cash.setText("Amount to be Collected: "+orders.Amount);
                        }

                        a= orders.LocationCoordinates.split(",");

                        number=orders.Number;
                        userid=orders.UserId;
                        cashback=orders.Cashback;


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(session.getverified().equals("No")) {
                    Intent intent = new Intent(getContext(), OtpVerfification.class);
                    intent.putExtra("mobile", number);
                    intent.putExtra("pushid", orders.Pushid);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(getContext(),"User Verified.Proceed with delivery",Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });








        final Date currentDate = new Date(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        final String date1 = df.format(currentDate);

        SimpleDateFormat df1 = new SimpleDateFormat("ddMMyyyyHHmmss");
        final String date2 = df1.format(currentDate);



        direction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" + a[0] + "," + a[1]));
                startActivity(intent);

            }
        });


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
        });


        mref= FirebaseDatabase.getInstance().getReference().child("Orders1").child(Pushid).child("Cart");



        TextView reached = v.findViewById(R.id.reached);

        reached.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(session.getverified().equals("No")){
                    Toast.makeText(getContext(),"Please verify the user first",Toast.LENGTH_SHORT).show();
                    return;
                }

                final Button ok, cancel;
                final TextView heading, content;
                final ImageView dialogimage;
                final EditText ono;
                ViewGroup viewGroup = v.findViewById(android.R.id.content);

                //then we will inflate the custom alert dialog xml that we created
                View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.my_dialog, viewGroup, false);
                ok = dialogView.findViewById(R.id.buttonOk);
                cancel = dialogView.findViewById(R.id.buttonCancel);
                heading = dialogView.findViewById(R.id.heading);
                content = dialogView.findViewById(R.id.content);
                dialogimage = dialogView.findViewById(R.id.dialogimage);

                //Now we need an AlertDialog.Builder object
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());

                //setting the view of the builder to our custom view that we already inflated
                builder.setView(dialogView);

                //finally creating the alert dialog and displaying it

                final android.app.AlertDialog alertDialog = builder.create();
                heading.setText("Confirmation");
                content.setText("Are you sure u have  arrived the delivery location???");

                dialogimage.setImageDrawable(getResources().getDrawable(R.drawable.ic_success));
                alertDialog.setCancelable(false);
                alertDialog.show();

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });


                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {




                        final int random = new Random().nextInt(10000);




                        FirebaseDatabase.getInstance().getReference().child("Orders1").child(Pushid).child("Status").setValue("5");

                        if(Integer.parseInt(cashback)>0) {

//                            Toast.makeText(getContext(),cashback,Toast.LENGTH_SHORT).show();

                            DatabaseReference dref = FirebaseDatabase.getInstance().getReference().child("Users").child(userid).child("Rewards1");
                            dref.runTransaction(new Transaction.Handler() {
                                @NonNull
                                @Override
                                public Transaction.Result doTransaction(@NonNull MutableData currentData) {
                                    double value = 0;
                                    if (currentData.getValue() != null) {
                                        value = Double.parseDouble(currentData.getValue().toString()) + Double.parseDouble(cashback);
                                        dbalance = Double.parseDouble(currentData.getValue().toString()) + Double.parseDouble(cashback);
                                    }
                                    currentData.setValue(value);


                                    return Transaction.success(currentData);
                                }

                                @Override
                                public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {

                                    DatabaseReference rref1 = FirebaseDatabase.getInstance().getReference().child("Users").child(userid).child("Transactions").push();
                                    Rewards dsponsorEarnings1 = new Rewards(rref1.getKey(), userid, Double.toString(dbalance), date2, cashback, "Rewards",
                                            "Cr", "Cashback" , userid.substring(3, 6) + date1+random, "Completed");
                                    rref1.setValue(dsponsorEarnings1);


                                    alertDialog.dismiss();

                                    final Button ok, cancel;
                                    final TextView heading, content;
                                    final ImageView dialogimage;
                                    final EditText ono;
                                    ViewGroup viewGroup = v.findViewById(android.R.id.content);

                                    //then we will inflate the custom alert dialog xml that we created
                                    View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.my_dialog, viewGroup, false);
                                    ok = dialogView.findViewById(R.id.buttonOk);
                                    cancel = dialogView.findViewById(R.id.buttonCancel);
                                    heading = dialogView.findViewById(R.id.heading);
                                    content = dialogView.findViewById(R.id.content);
                                    dialogimage = dialogView.findViewById(R.id.dialogimage);

                                    //Now we need an AlertDialog.Builder object
                                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());

                                    //setting the view of the builder to our custom view that we already inflated
                                    builder.setView(dialogView);

                                    //finally creating the alert dialog and displaying it

                                    final android.app.AlertDialog alertDialog = builder.create();
                                    heading.setText("Successful");
                                    content.setText("Order Delivered Sucessfully!!!");

                                    dialogimage.setImageDrawable(getResources().getDrawable(R.drawable.ic_success));
                                    alertDialog.setCancelable(false);
                                    alertDialog.show();
                                    cancel.setVisibility(View.GONE);

                                    ok.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {

                                            alertDialog.dismiss();
                                            getActivity().onBackPressed();

                                        }
                                    });


                                }
                            });

                        }
                        else{
                            alertDialog.dismiss();

                            final Button ok, cancel;
                            final TextView heading, content;
                            final ImageView dialogimage;
                            final EditText ono;
                            ViewGroup viewGroup = v.findViewById(android.R.id.content);

                            //then we will inflate the custom alert dialog xml that we created
                            View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.my_dialog, viewGroup, false);
                            ok = dialogView.findViewById(R.id.buttonOk);
                            cancel = dialogView.findViewById(R.id.buttonCancel);
                            heading = dialogView.findViewById(R.id.heading);
                            content = dialogView.findViewById(R.id.content);
                            dialogimage = dialogView.findViewById(R.id.dialogimage);

                            //Now we need an AlertDialog.Builder object
                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext());

                            //setting the view of the builder to our custom view that we already inflated
                            builder.setView(dialogView);

                            //finally creating the alert dialog and displaying it

                            final android.app.AlertDialog alertDialog = builder.create();
                            heading.setText("Successful");
                            content.setText("Order Delivered Sucessfully!!!");

                            dialogimage.setImageDrawable(getResources().getDrawable(R.drawable.ic_success));
                            alertDialog.setCancelable(false);
                            alertDialog.show();
                            cancel.setVisibility(View.GONE);

                            ok.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {

                                    alertDialog.dismiss();
                                    getActivity().onBackPressed();

                                }
                            });
                        }




                    }
                });
            }
        });



        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        session=new Session(getContext());

        FirebaseRecyclerAdapter<Cart, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Cart, ViewHolder>(
                        Cart.class,
                        R.layout.cart_row,
                        ViewHolder.class,
                        mref
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Cart cart, int position) {
                        viewHolder.setDetails1(getContext(),cart.ItemName,cart.Qty,cart.Price,cart.Total,cart.Image,cart.QtyDescription);

                    }
                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        final ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View v, final int position) {

                            }

                            @Override
                            public void onItemLongClick(View v, int position) {

                            }
                        });
                        return viewHolder;
                    }

                };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }




    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }



}


