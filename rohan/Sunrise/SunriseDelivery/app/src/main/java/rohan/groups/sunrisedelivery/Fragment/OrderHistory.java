package rohan.groups.sunrisedelivery.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import rohan.groups.sunrisedelivery.Functionality.Session;
import rohan.groups.sunrisedelivery.Orders.Order;
import rohan.groups.sunrisedelivery.Orders.ViewHolder;
import rohan.groups.sunrisedelivery.R;


public class OrderHistory extends Fragment {


    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    Session sessions;


    public OrderHistory() {
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
        View v=inflater.inflate(R.layout.fragment_order_history, container, false);


        sessions = new Session(getContext());
        mRecyclerView = v.findViewById(R.id.ordersview);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mref = mFirebaseDatabase.getReference().child("Orders1");


        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        Query firebasequery = mref.orderByChild("DeliveryBoy").equalTo(sessions.getusername());
        FirebaseRecyclerAdapter<Order, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Order, ViewHolder>(
                        Order.class,
                        R.layout.orders_row,
                        ViewHolder.class,
                        firebasequery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Order order, int position) {
                        viewHolder.setDetails1(getContext(),order.Items,order.OrderDateTime,order.OrderNo,order.Total,order.Status,order.Pushid,order.Cashback);

                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {


                                TextView pushid = v.findViewById(R.id.pushid);

                                Bundle bundle=new Bundle();
                                OrderDetailsFragment fragment= new OrderDetailsFragment();
                                bundle.putString("pushid",pushid.getText().toString());
                                fragment.setArguments(bundle);
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                fragmentManager.beginTransaction()
                                        .addToBackStack(null)
                                        .replace(R.id.frame_container, fragment).commit();


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