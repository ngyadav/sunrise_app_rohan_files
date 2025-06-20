package rohan.groups.sunrise.Fragments;

import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import rohan.groups.sunrise.Coins.Coins;
import rohan.groups.sunrise.Coins.ViewHolder;
import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.R;

public class RewardsDetails extends Fragment {

    TextView amount;
    DatabaseReference ref;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    Session sessions;
    RecyclerView mRecyclerView;



    public RewardsDetails() {
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
        View v=inflater.inflate(R.layout.fragment_rewards_details, container, false);
        LinearLayout add=(LinearLayout)v.findViewById(R.id.add);
        Toolbar toolbar=(getActivity()).findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        LinearLayout bottomnavigation=(getActivity()).findViewById(R.id.bottomnavigation);
        bottomnavigation.setVisibility(View.GONE);




        amount=v.findViewById(R.id.amount);


        TextView spend=v.findViewById(R.id.spend);
        TextView available=v.findViewById(R.id.available);
        TextView particular=v.findViewById(R.id.particular);
        TextView cr=v.findViewById(R.id.cr);
        TextView balance=v.findViewById(R.id.balance);



        mRecyclerView = v.findViewById(R.id.Recyclerrewards);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        sessions = new Session(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mref = mFirebaseDatabase.getReference().child("Users").child(sessions.getusername()).child("Transactions");

        ref=FirebaseDatabase.getInstance().getReference();
        ref.child("Users").child(sessions.getusername()).child("Rewards1").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                amount.setText("#"+dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return  v;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Coins, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Coins, ViewHolder>(
                        Coins.class,
                        R.layout.cardview_coins,
                        ViewHolder.class,
                        mref
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Coins rewards, int position) {
                        viewHolder.setDetails(getContext(), rewards.Date, rewards.Generated, rewards.PushId, rewards.TransactionName, rewards.TransactionType, rewards.UserId, rewards.UserBalance, rewards.Amount, position,rewards.Status);

                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
//
                        return viewHolder;
                    }

                };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

}

