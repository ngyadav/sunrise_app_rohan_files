package rohan.groups.sunrise.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.razorpay.Checkout;

import java.text.DecimalFormat;

import rohan.groups.sunrise.Address.Address;
import rohan.groups.sunrise.Address.MapsActivity;
import rohan.groups.sunrise.Address.ViewHolder;
import rohan.groups.sunrise.Cart.Cart;
import rohan.groups.sunrise.Entity.DummyAddress;
import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.Payment.PaymentActivity;
import rohan.groups.sunrise.R;


public class CartFragment extends Fragment {

    private BottomSheetDialog bottomSheetDialog;
    Session session;
    RecyclerView mRecyclerView;
    DatabaseReference mref;
    TextView subtotal,total,delivery,rewards;
    LinearLayout l1;
    EditText promocode;
    Button paybutton,apply;
    CheckBox rewardsbox;
    double tot=0;
    int temp=0;
    private double ttot,trewards,tpay,tdeduct,tcashback=0;




    public CartFragment() {
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
        View v=inflater.inflate(R.layout.fragment_cart, container, false);

        Toolbar toolbar=(getActivity()).findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);

        LinearLayout bottomnavigation=(getActivity()).findViewById(R.id.bottomnavigation);
        bottomnavigation.setVisibility(View.GONE);

        Checkout.preload(getActivity().getApplicationContext());


        session=new Session(getContext());
        mRecyclerView = v.findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        l1=v.findViewById(R.id.l1);

        subtotal=v.findViewById(R.id.subtotal);
        total=v.findViewById(R.id.total);
        delivery=v.findViewById(R.id.delivery);
        rewards=v.findViewById(R.id.rewards);
        rewardsbox=v.findViewById(R.id.rewardsbox);


        bottomSheetDialog=new BottomSheetDialog(getContext());
        final View bottomSheetDialogView=getLayoutInflater().inflate(R.layout.bottom_location,null);
        bottomSheetDialog.setContentView(bottomSheetDialogView);
        RecyclerView addressview=bottomSheetDialogView.findViewById(R.id.addressview);

        paybutton=v.findViewById(R.id.paybutton);

        l1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();
            }
        });

        addressview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


        if(!TextUtils.isEmpty(session.getdaname())) {
            String temp=(session.getdaname()+"\n"+session.getdaf()+"\n"+session.getdaaddress()+"\n"+session.getnumber());
            delivery.setText(temp);
            delivery.setTextSize(12f);
        }



        DummyAddress dummyAddress=new DummyAddress("","","","","Dummy","Dummy");

        FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("DAddress").child("Dummy").setValue(dummyAddress);



        DatabaseReference mref3 = FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("DAddress");
        FirebaseRecyclerAdapter<Address, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Address,ViewHolder>(
                        Address.class,
                        R.layout.address_row1,
                        ViewHolder.class,
                        mref3
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Address address, int position) {
                        viewHolder.setDetails(getContext(),address.Name,address.Flat,address.Landmark,address.Pushid,address.Location,address.LocationCoord,address.Pincode,address.Number);
                    }
                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        final ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View v, final int position){
                                LinearLayout abc=v.findViewById(R.id.abc);
                                TextView name=v.findViewById(R.id.Name);
                                TextView rest=v.findViewById(R.id.Rest);
                                TextView flat = v.findViewById(R.id.Flat);
                                TextView landmark = v.findViewById(R.id.Landmark);
                                TextView location = v.findViewById(R.id.Location);
                                TextView locationcoord = v.findViewById(R.id.LocationCoord);
                                TextView pincode = v.findViewById(R.id.pincode);
                                TextView number = v.findViewById(R.id.number);

                                if(name.getText().toString().equals("Add New Address")){
                                    Intent i = new Intent(getContext(), MapsActivity.class);
                                    startActivity(i);
                                }
                                else {
                                    session.setdaname(name.getText().toString());
                                    session.setdaaddress(location.getText().toString());
                                    session.setdaf(flat.getText().toString());
                                    session.setdal(landmark.getText().toString());
                                    session.setdaloc(locationcoord.getText().toString());
                                    session.setpincode(pincode.getText().toString());
                                    session.setnumber(number.getText().toString());
                                    bottomSheetDialog.dismiss();
                                    String temp=(session.getdaname()+"\n"+session.getdaf()+"\n"+session.getdaaddress()+"\n"+session.getnumber());
                                    delivery.setText(temp);
                                    delivery.setTextSize(12f);
                                }

                            }

                            @Override
                            public void onItemLongClick(View v, int position) {

                            }
                        });
                        return viewHolder;
                    }

                };

        addressview.setAdapter(firebaseRecyclerAdapter);



        FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("Cart1")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot v:dataSnapshot.getChildren()){
                            tot+=Integer.parseInt(v.child("Total").getValue().toString());

                            temp+=Integer.parseInt(v.child("Qty").getValue().toString());

                            tcashback+=Integer.parseInt(v.child("RewardsTotal").getValue().toString());


                        }


                        subtotal.setText("\u20b9"+tot);


                        total.setText("\u20b9"+tot);

                        tpay=Double.parseDouble(total.getText().toString().substring(1));
                        tdeduct=0;


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



      DatabaseReference  mref2=FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("Rewards1");
        mref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    rewards.setText(dataSnapshot.getValue().toString());
                    trewards=Double.parseDouble(dataSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        rewardsbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                @Override
                                                public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {

                                                        Double a = Double.parseDouble(rewards.getText().toString());
                                                        Double b = Double.parseDouble(subtotal.getText().toString().substring(1));
                                                        if (isChecked) {
                                                            if (a >= b) {
                                                                total.setText("\u20b90");
                                                                tdeduct = b;
                                                                tpay = 0;
                                                            } else if (a < b) {
                                                                total.setText("\u20b9" + (b - a));
                                                                tdeduct = a;
                                                                tpay = Double.parseDouble(total.getText().toString().substring(1));
                                                            }
                                                        } else {
                                                            total.setText("\u20b9" + subtotal.getText().toString().substring(1));
                                                            rewards.setText(Double.toString(trewards));
                                                            tpay = Double.parseDouble(total.getText().toString().substring(1));
                                                            tdeduct = 0;
                                                        }

                                                    }
                                                }
        );




        paybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(delivery.getText().toString().equals("Select Address")){
                    Toast.makeText(getContext(),"Please Select Delivery Address",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(Double.parseDouble(subtotal.getText().toString().substring(1))<=0){
                    Toast.makeText(getContext(),"Your cart seems to be empty,Add items to proceed",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {

                    Intent i = new Intent(getContext(), PaymentActivity.class);
                    i.putExtra("amount", new DecimalFormat("##.##").format(tpay));
                    i.putExtra("total", new DecimalFormat("##.##").format(Double.parseDouble(subtotal.getText().toString().substring(1))));
                    i.putExtra("deduct", new DecimalFormat("##.##").format(tdeduct));
                    i.putExtra("rewards", new DecimalFormat("##.##").format(tcashback));
                    startActivity(i);

                }

            }
        });

        return v;
    }


    @Override
    public void onStart() {
        super.onStart();

        session=new Session(getContext());
        mref= FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("Cart1");

        FirebaseRecyclerAdapter<Cart, rohan.groups.sunrise.Cart.ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Cart, rohan.groups.sunrise.Cart.ViewHolder>(
                        Cart.class,
                        R.layout.items_row,
                        rohan.groups.sunrise.Cart.ViewHolder.class,
                        mref
                ) {
                    @Override
                    protected void populateViewHolder(rohan.groups.sunrise.Cart.ViewHolder viewHolder, Cart cart, int position) {
                        viewHolder.setDetails(getContext(),cart.ItemName,cart.Qty,cart.Price,cart.Total,cart.Image,cart.PushId,cart.Color,cart.Size,cart.Rewards);

                    }
                    @Override
                    public  rohan.groups.sunrise.Cart.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        final rohan.groups.sunrise.Cart.ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListener(new rohan.groups.sunrise.Cart.ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View v, final int position) {
                                final DatabaseReference re=FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("Cart1");
                                final TextView itemprice=v.findViewById(R.id.itemprice);
                                final TextView itemname=v.findViewById(R.id.itemname);
                                final LinearLayout qty=v.findViewById(R.id.qty);
                                final TextView add=v.findViewById(R.id.add);
                                final ImageView minus=v.findViewById(R.id.minus);
                                final ImageView plus=v.findViewById(R.id.plus);
                                final ImageView delete=v.findViewById(R.id.delete);
                                final TextView qty1=v.findViewById(R.id.qty1);
                                final TextView pushid=v.findViewById(R.id.pushid);
                                final TextView rewards=v.findViewById(R.id.rewards);

                                if(add.isShown()) {
                                    add.setVisibility(View.GONE);
                                    qty.setVisibility(View.VISIBLE);
                                }

                                delete.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        int a=Integer.parseInt(qty1.getText().toString());
                                        int p=Integer.parseInt(itemprice.getText().toString().substring(1));
                                        int r=Integer.parseInt(rewards.getText().toString());
                                        int sub=p*a;
                                        int rew=r*a;

                                        tot=tot-sub;
                                        temp=temp-a;

                                        tcashback=tcashback-rew;

                                        re.child(pushid.getText().toString()).removeValue();

                                        subtotal.setText("\u20b9"+tot);
                                        total.setText("\u20b9"+tot);

                                        tpay=Double.parseDouble(total.getText().toString().substring(1));
                                        tdeduct=0;


                                        rewardsbox.setChecked(false);

                                    }
                                });

                                plus.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        add.setVisibility(View.GONE);
                                        qty.setVisibility(View.VISIBLE);

                                        int a=Integer.parseInt(qty1.getText().toString());
                                        int r=Integer.parseInt(rewards.getText().toString());
                                        a++;
                                        qty1.setText(""+a);

                                        temp++;

                                        int p=Integer.parseInt(itemprice.getText().toString().substring(1));



                                        re.child(pushid.getText().toString()).child("Qty").setValue(qty1.getText().toString());
                                        re.child(pushid.getText().toString()).child("Price").setValue(itemprice.getText().toString().substring(1));
                                        re.child(pushid.getText().toString()).child("Total").setValue(""+(p*a));
                                        re.child(pushid.getText().toString()).child("RewardsTotal").setValue(""+(r*a));

                                        tot=tot+p;
                                        tcashback=tcashback+r;

                                        subtotal.setText("\u20b9"+tot);
                                        total.setText("\u20b9"+tot);

                                        tpay=Double.parseDouble(total.getText().toString().substring(1));
                                        tdeduct=0;


                                        rewardsbox.setChecked(false);

                                    }
                                });

                                minus.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        add.setVisibility(View.GONE);
                                        qty.setVisibility(View.VISIBLE);

                                        if(Integer.parseInt(qty1.getText().toString())>1) {


                                            int a=Integer.parseInt(qty1.getText().toString());
                                            a--;
                                            qty1.setText(""+a);

                                            int p=Integer.parseInt(itemprice.getText().toString().substring(1));

                                            int r=Integer.parseInt(rewards.getText().toString());

                                            temp--;

                                            re.child(pushid.getText().toString()).child("Qty").setValue(qty1.getText().toString());
                                            re.child(pushid.getText().toString()).child("Price").setValue(itemprice.getText().toString().substring(1));
                                            re.child(pushid.getText().toString()).child("Total").setValue(""+(p*a));
                                            re.child(pushid.getText().toString()).child("RewardsTotal").setValue(""+(r*a));

                                            tot=tot-p;

                                            subtotal.setText("\u20b9"+tot);
                                            total.setText("\u20b9"+tot);

                                            tcashback=tcashback-r;

                                            tpay=Double.parseDouble(total.getText().toString().substring(1));
                                            tdeduct=0;

                                            rewardsbox.setChecked(false);
                                        }



                                    }
                                });





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


