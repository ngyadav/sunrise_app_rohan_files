package rohan.groups.sunrisedelivery.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import rohan.groups.sunrisedelivery.Cart.Cart;
import rohan.groups.sunrisedelivery.Cart.ViewHolder;
import rohan.groups.sunrisedelivery.Functionality.Session;
import rohan.groups.sunrisedelivery.R;



public class OrderDetailsFragment extends Fragment {


    private BottomSheetDialog bottomSheetDialog;
    Session session;
    RecyclerView mRecyclerView;
    DatabaseReference mref;
    TextView tprice,tpacking,ttotal;
    Button paybutton;
    int tot=0;
    String Pushid;


    public OrderDetailsFragment() {
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
        View v=inflater.inflate(R.layout.fragment_order_details, container, false);

        session = new Session(getContext());
        mRecyclerView = v.findViewById(R.id.cartview);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        final TextView deliveryaddress=v.findViewById(R.id.deliveryaddress);
        tprice=v.findViewById(R.id.tprice);
        tpacking=v.findViewById(R.id.tpacking);
        ttotal=v.findViewById(R.id.ttotal);

        final TextView ono=v.findViewById(R.id.ono);

        Bundle bundle=new Bundle();
        bundle=getArguments();
        Pushid=bundle.getString("pushid");





        FirebaseDatabase.getInstance().getReference().child("Orders1").child(Pushid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()) {
                            deliveryaddress.setText(dataSnapshot.child("CName").getValue().toString() + "\n" + dataSnapshot.child("Address").getValue().toString());
                            tprice.setText("\u20b9" + dataSnapshot.child("Amount").getValue().toString());
                            tpacking.setText("\u20b9" + dataSnapshot.child("DeliveryCharges").getValue().toString());
                            ttotal.setText("\u20b9" + dataSnapshot.child("Total").getValue().toString());
                            ono.setText("Order No:" + dataSnapshot.child("OrderNo").getValue().toString());
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



        mref= FirebaseDatabase.getInstance().getReference().child("Orders1").child(Pushid).child("Cart");




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
                    public  ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

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


