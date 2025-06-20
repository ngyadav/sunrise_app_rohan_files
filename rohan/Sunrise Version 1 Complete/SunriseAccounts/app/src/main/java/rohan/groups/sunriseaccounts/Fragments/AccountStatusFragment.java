package rohan.groups.sunriseaccounts.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import rohan.groups.sunriseaccounts.AccountsStatus.ViewHolder;
import rohan.groups.sunriseaccounts.Entity.Accounts;
import rohan.groups.sunriseaccounts.Functionality.Sessions;
import rohan.groups.sunriseaccounts.R;


public class AccountStatusFragment extends Fragment {

    DatabaseReference ref;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    Sessions sessions;
    RecyclerView mRecyclerView;
    double total;

    public AccountStatusFragment() {
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
        View v=inflater.inflate(R.layout.fragment_account_status, container, false);



        mRecyclerView = v.findViewById(R.id.statuscycler);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);
        sessions = new Sessions(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mref = mFirebaseDatabase.getReference().child("Accounts");




        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Query firebasequery = mref.orderByChild("Status").equalTo("Processing");
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

                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {

                                final TextView Rewardsdetails,cr,dr ,Rewardsorder;
                                Rewardsdetails = (TextView) v.findViewById(R.id.Rewardsdetails);
                                Rewardsorder = (TextView) v.findViewById(R.id.Rewardsorder);
                                cr = (TextView) v.findViewById(R.id.cr);
                                dr = (TextView) v.findViewById(R.id.dr);


                                DatabaseReference dref=FirebaseDatabase.getInstance().getReference().child("Users").child(Rewardsdetails.getText().toString()).child("Accounts");
                                dref.runTransaction(new Transaction.Handler() {
                                    @NonNull
                                    @Override
                                    public Transaction.Result doTransaction(@NonNull MutableData currentData) {
                                        long value = 0;
                                        if(currentData.getValue() != null) {
                                            value = Long.parseLong(currentData.getValue().toString());
                                            if(cr.getText().length()!=0) {
                                                value = Long.parseLong(currentData.getValue().toString()) + Integer.parseInt(cr.getText().toString().substring(1));
                                                total = Long.parseLong(currentData.getValue().toString()) + Integer.parseInt(cr.getText().toString().substring(1));
                                            }

                                            if(dr.getText().length()!=0) {
                                                    value = Long.parseLong(currentData.getValue().toString()) - Integer.parseInt(dr.getText().toString().substring(1));
                                                    total = Long.parseLong(currentData.getValue().toString()) - Integer.parseInt(dr.getText().toString().substring(1));
                                            }

                                        }
                                        currentData.setValue(value);

                                       DatabaseReference rref=FirebaseDatabase.getInstance().getReference().child("Accounts").child(Rewardsorder.getText().toString());
                                       rref.child("Status").setValue("Approved");
                                       rref.child("UserBalance").setValue(Double.toString(total));

                                        return Transaction.success(currentData);
                                    }

                                    @Override
                                    public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                                        new AlertDialog.Builder(getContext())
                                                    .setTitle("Accounts")
                                                    .setMessage("Transaction Approved Successfully!!")
                                                    .setPositiveButton(android.R.string.ok, null)
                                                    .show();

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

}
