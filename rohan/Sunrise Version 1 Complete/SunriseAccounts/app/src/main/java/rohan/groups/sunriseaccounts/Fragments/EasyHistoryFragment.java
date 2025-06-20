package rohan.groups.sunriseaccounts.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import rohan.groups.sunriseaccounts.EasyRecharge.ViewHolder;
import rohan.groups.sunriseaccounts.Entity.EasyRecharge;
import rohan.groups.sunriseaccounts.Functionality.Sessions;
import rohan.groups.sunriseaccounts.R;

public class EasyHistoryFragment extends Fragment {

    TextView amount;
    DatabaseReference ref;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    Sessions sessions;
    RecyclerView mRecyclerView;

    public EasyHistoryFragment() {
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
        View v=inflater.inflate(R.layout.fragment_easy_history, container, false);

        amount=v.findViewById(R.id.amount);

        mRecyclerView = v.findViewById(R.id.Recyclerrewards);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        sessions = new Sessions(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mref = mFirebaseDatabase.getReference().child("EasyRecharge");

        ref=FirebaseDatabase.getInstance().getReference();
        ref.child("Users").child(sessions.getusername()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                amount.setText("\u20b9"+dataSnapshot.child("EasyRecharge").getValue().toString());
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
        Query firebasequery = mref.orderByChild("UserId").equalTo(sessions.getusername());
        FirebaseRecyclerAdapter<EasyRecharge, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<EasyRecharge, ViewHolder>(
                        EasyRecharge.class,
                        R.layout.transaction_row,
                        ViewHolder.class,
                        firebasequery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, EasyRecharge rewards, int position) {
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
