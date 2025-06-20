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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import rohan.groups.sunriseaccounts.Accounts.ViewHolder;
import rohan.groups.sunriseaccounts.Entity.Accounts;
import rohan.groups.sunriseaccounts.Functionality.Sessions;
import rohan.groups.sunriseaccounts.R;

public class AdminAccountsFragment extends Fragment {

    TextView amount;
    DatabaseReference ref;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    Sessions sessions;
    Spinner username;
    Button search;
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    RecyclerView mRecyclerView;
    List<String> zone = new ArrayList<String>();

    public AdminAccountsFragment() {
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
       View v=inflater.inflate(R.layout.fragment_admin_accounts, container, false);

        amount=v.findViewById(R.id.amount);
        search=v.findViewById(R.id.search);
        username=v.findViewById(R.id.username);

        mRecyclerView = v.findViewById(R.id.Recyclerrewards);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        sessions = new Sessions(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mref = mFirebaseDatabase.getReference().child("Accounts");

        database.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot v : dataSnapshot.getChildren()) {
                    zone.add(v.child("UserName").getValue().toString());
                }

                ArrayAdapter<String> areasAdapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_layout, zone);
                areasAdapter.setDropDownViewResource(R.layout.spinner_layout);
                username.setAdapter(areasAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ref=FirebaseDatabase.getInstance().getReference();
                ref.child("Users").child(username.getSelectedItem().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        amount.setText("\u20b9"+dataSnapshot.child("Accounts").getValue().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {



                    }
                });
                Query firebasequery = mref.orderByChild("UserId").equalTo(username.getSelectedItem().toString());
                FirebaseRecyclerAdapter<Accounts, ViewHolder> firebaseRecyclerAdapter =
                        new FirebaseRecyclerAdapter<Accounts, ViewHolder>(
                                Accounts.class,
                                R.layout.transaction_row,
                                ViewHolder.class,
                                firebasequery
                        ) {
                            @Override
                            protected void populateViewHolder(ViewHolder viewHolder, Accounts rewards, int position) {
                                viewHolder.setDetails(getContext(), rewards.Date, rewards.Generated, rewards.PushId, rewards.TransactionName, rewards.TransactionType, rewards.UserId, rewards.UserBalance, rewards.Amount, position,rewards.Status,rewards.TransactionParticulars);

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
        });



       return v;
    }

}
