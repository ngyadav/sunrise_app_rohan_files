package rohan.groups.sunrise.Fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import java.util.ArrayList;

import rohan.groups.sunrise.Address.Address;
import rohan.groups.sunrise.Address.MapsActivity;
import rohan.groups.sunrise.Address.ViewHolder;
import rohan.groups.sunrise.Entity.DummyAddress;
import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.GroceryCart.Cart1;
import rohan.groups.sunrise.GroceryCart.CartAdapter1;
import rohan.groups.sunrise.Payment.PaymentActivity;
import rohan.groups.sunrise.Payment.PaymentActivityGrocery;
import rohan.groups.sunrise.R;


public class GroceryCartFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private BottomSheetDialog bottomSheetDialog;

    private ProgressBar progressBar;
    private TextView changeaddress;

    private Session session;
    private CartAdapter1 cartAdapter;
    private ArrayList<Cart1> cart=new ArrayList<Cart1>();


    private TextView total,daname,address,subscribe,delivery;
    private Button proceed;
    private LinearLayout l1;
    String type="";

    public GroceryCartFragment() {
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
        View v=inflater.inflate(R.layout.fragment_grocery_cart, container, false);
        LinearLayout bottomnavigation=(getActivity()).findViewById(R.id.bottomnavigation);
        bottomnavigation.setVisibility(View.GONE);

        final ImageView back = v.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });


        Checkout.preload(getActivity().getApplicationContext());

        mRecyclerView=v.findViewById(R.id.recyclerView);
        progressBar=v.findViewById(R.id.progressbar);
        total=v.findViewById(R.id.total);
        changeaddress=v.findViewById(R.id.changeaddress);
        proceed=v.findViewById(R.id.proceed);
        l1=v.findViewById(R.id.l1);
        delivery=v.findViewById(R.id.delivery);

        session = new Session(getActivity());

        bottomSheetDialog=new BottomSheetDialog(getContext());
        final View bottomSheetDialogView=getLayoutInflater().inflate(R.layout.bottom_location,null);
        bottomSheetDialog.setContentView(bottomSheetDialogView);
        RecyclerView addressview=bottomSheetDialogView.findViewById(R.id.addressview);


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




        cart.clear();
        cartAdapter = new CartAdapter1(cart);


        session =  new Session(getContext());

        FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("CartGrocery")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        cart.clear();
                        if(dataSnapshot.exists()){
                            for(DataSnapshot v: dataSnapshot.getChildren()){
                                if(v.exists()) {
                                    cart.add(new Cart1(
                                            v.child("Name").getValue().toString(),
                                            v.child("Price").getValue().toString(),
                                            v.child("PushId").getValue().toString(),
                                            v.child("Qty").getValue().toString(),
                                            v.child("Total").getValue().toString(),
                                            v.child("Units").getValue().toString(),
                                            v.child("Image").getValue().toString()));
                                }


                            }
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            cartAdapter = new CartAdapter1(cart);
                            mRecyclerView.setAdapter(cartAdapter);
                            progressBar.setVisibility(View.GONE);


                        }
                        else{
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                            mRecyclerView.setLayoutManager(mLayoutManager);
                            cartAdapter = new CartAdapter1(cart);
                            mRecyclerView.setAdapter(cartAdapter);
                            progressBar.setVisibility(View.GONE);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });





        FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("CartGrocery")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            DecimalFormat form = new DecimalFormat("0.00");
                            total.setText("\u20b9"+form.format(Double.parseDouble(session.getcarttotal())));
                        }
                        else{
                            total.setText("\u20b90.00");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

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


        changeaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               bottomSheetDialog.show();
            }
        });





        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(delivery.getText().toString().equals("Select Address")){
                    Toast.makeText(getContext(),"Please Select Delivery Address",Toast.LENGTH_LONG).show();
                    return;
                }
                else if(Double.parseDouble(total.getText().toString().substring(1))<=0){
                    Toast.makeText(getContext(),"Your cart seems to be empty,Add items to proceed",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {


                    FirebaseDatabase.getInstance().getReference().child("Users").child(session.getusername()).child("CartGrocery").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){

                                Intent i = new Intent(getContext(), PaymentActivityGrocery.class);
                                i.putExtra("amount", new DecimalFormat("##.##").format(Double.parseDouble(total.getText().toString().substring(1))));
                                startActivity(i);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });


                }

            }
        });



        return v;
    }
}

