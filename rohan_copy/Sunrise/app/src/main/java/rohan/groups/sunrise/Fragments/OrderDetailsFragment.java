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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.zopim.android.sdk.api.ZopimChat;
import com.zopim.android.sdk.model.VisitorInfo;
import com.zopim.android.sdk.prechat.ZopimChatActivity;

import rohan.groups.sunrise.Cart.Cart;
import rohan.groups.sunrise.Cart.ViewHolder;
import rohan.groups.sunrise.Functionality.AccountKey;
import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.Order.OrderFull;
import rohan.groups.sunrise.R;


public class OrderDetailsFragment extends Fragment {

    String pushid="";
    OrderFull orderFull;


    Session session;
    RecyclerView mRecyclerView;
    DatabaseReference mref;
    TextView subtotal,total,delivery,rewards;
    Button paybutton;


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


        final Toolbar toolbar=(getActivity()).findViewById(R.id.toolbar);
        toolbar.setVisibility(View.VISIBLE);

        LinearLayout bottomnavigation=(getActivity()).findViewById(R.id.bottomnavigation);
        bottomnavigation.setVisibility(View.GONE);


        session=new Session(getContext());
        mRecyclerView = v.findViewById(R.id.recyclerView);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        subtotal=v.findViewById(R.id.subtotal);
        total=v.findViewById(R.id.total);
        delivery=v.findViewById(R.id.delivery);
        rewards=v.findViewById(R.id.rewards);
        paybutton=v.findViewById(R.id.paybutton);

        ZopimChat.init(AccountKey.ACCOUNT_KEY);



        pushid=getArguments().getString("pushid");

        FirebaseDatabase.getInstance().getReference().child("Orders1").child(pushid)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        orderFull=dataSnapshot.getValue(OrderFull.class);

                        subtotal.setText("\u20b9"+orderFull.Total);
                        rewards.setText("#"+orderFull.Rewards);
                        total.setText("\u20b9"+orderFull.Amount);

                        String temp=(orderFull.CName+"\n"+orderFull.Flat+","+orderFull.Landmark+","+orderFull.Address);
                        delivery.setText(temp);
                        delivery.setTextSize(14);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


        paybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                VisitorInfo visitorData = new VisitorInfo.Builder()
                        .name(session.getname())
                        .phoneNumber(session.getusername())
                        .note("Past Order Help, Order Number:"+orderFull.OrderNo)
                        .build();

                ZopimChat.setVisitorInfo(visitorData);



                startActivity(new Intent(getActivity(), ZopimChatActivity.class));

                ZopimChat.setPushToken(session.gettoken());

            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        session=new Session(getContext());
        mref= FirebaseDatabase.getInstance().getReference().child("Orders1").child(pushid).child("Cart");

        FirebaseRecyclerAdapter<Cart, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Cart, rohan.groups.sunrise.Cart.ViewHolder>(
                        Cart.class,
                        R.layout.items_row,
                        rohan.groups.sunrise.Cart.ViewHolder.class,
                        mref
                ) {
                    @Override
                    protected void populateViewHolder(rohan.groups.sunrise.Cart.ViewHolder viewHolder, Cart cart, int position) {
                        viewHolder.setDetails1(getContext(),cart.ItemName,cart.Qty,cart.Price,cart.Total,cart.Image,cart.PushId,cart.Color,cart.Size,cart.Rewards);

                    }
                    @Override
                    public  rohan.groups.sunrise.Cart.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        final rohan.groups.sunrise.Cart.ViewHolder viewHolder=super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListener(new rohan.groups.sunrise.Cart.ViewHolder.ClickListener() {
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


