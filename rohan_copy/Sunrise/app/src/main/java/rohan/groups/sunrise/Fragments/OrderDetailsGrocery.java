package rohan.groups.sunrise.Fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.OrderDetails.OrderDetails;
import rohan.groups.sunrise.OrderDetails.ViewHolder;
import rohan.groups.sunrise.R;


public class OrderDetailsGrocery extends Fragment {

    String pushid = "";


    Session session;
    RecyclerView mRecyclerView;
    DatabaseReference mref;
    TextView orderid, date, total, daname, address, status;


    public OrderDetailsGrocery() {
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
        View v=inflater.inflate(R.layout.fragment_order_details_grocery, container, false);
        orderid = v.findViewById(R.id.orderid);
        date =v.findViewById(R.id.date);
        total = v.findViewById(R.id.total);
        daname = v.findViewById(R.id.daname);
        address = v.findViewById(R.id.address);

        session = new Session(getContext());
        mRecyclerView = v.findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        pushid = getArguments().getString("pushid");


        FirebaseDatabase.getInstance().getReference().child("Orders1").child(pushid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            orderid.setText("Order ID : #"+dataSnapshot.child("OrderNo").getValue().toString());
                            date.setText(dataSnapshot.child("OrderDateTime").getValue().toString());
                            total.setText("\u20b9"+dataSnapshot.child("Total").getValue().toString());
                            address.setText(dataSnapshot.child("Address").getValue().toString());
                            daname.setText(dataSnapshot.child("CName").getValue().toString());

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        mref = FirebaseDatabase.getInstance().getReference().child("Orders1").child(pushid).child("Cart");

        FirebaseRecyclerAdapter<OrderDetails, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<OrderDetails, ViewHolder>(
                        OrderDetails.class,
                        R.layout.orders_details_row,
                        ViewHolder.class,
                        mref
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, OrderDetails orderDetails, int position) {
                        viewHolder.setDetails(getContext(),orderDetails.Image,orderDetails.Name,orderDetails.Price,orderDetails.PushId,orderDetails.Qty,orderDetails.Total,orderDetails.Units);

                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        final  ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
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

}