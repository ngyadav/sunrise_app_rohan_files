package sunrise.release.rohan.sunriseseller.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import sunrise.release.rohan.sunriseseller.Functionality.LoginSession;
import sunrise.release.rohan.sunriseseller.R;
import sunrise.release.rohan.sunriseseller.Rewards.Rewards;
import sunrise.release.rohan.sunriseseller.Rewards.ViewHolder;


public class RewardsFragment extends Fragment {
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    private LoginSession session;

    public RewardsFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_rewards, container, false);
        mRecyclerView = v.findViewById(R.id.Recyclerrewards);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mref = mFirebaseDatabase.getReference("Rewards");
        session = new LoginSession(getContext());
        final TextView Rewards=(TextView)v.findViewById(R.id.Rewards);
        DatabaseReference mref1=FirebaseDatabase.getInstance().getReference().child("SellerUsers");
        mref1.child("+91"+session.getusename()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Rewards.setText("\u20B9"+dataSnapshot.child("Rewards").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        String abc = session.getusename();
        Query firebasequery = mref.orderByChild("Authorised").equalTo("+91" + abc);
        FirebaseRecyclerAdapter<Rewards, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Rewards, ViewHolder>(
                        Rewards.class,
                        R.layout.cardview_rewards,
                        ViewHolder.class,
                        firebasequery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder,Rewards rewards, int position) {
                        viewHolder.setDetails(getContext(),rewards.Amount,rewards.AuthorisedBalance,rewards.Date,rewards.AuthorisedName,rewards.TransactionId,rewards.AuthorisedType,rewards.OrderNumber,position);

                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
//                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
//                            @Override
//                            public void onItemClick(View v, int position) {
//                                TextView status = v.findViewById(R.id.ostatus);
//
//                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                                builder.setTitle("Order Status");
//                                builder.setMessage("Your Order Status is "+status.getText().toString())
//                                        .setCancelable(false)
//                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialog, int id) {
//                                                // TODO: handle the OK
//                                            }
//                                        })
//                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                                            public void onClick(DialogInterface dialog, int id) {
//                                                dialog.cancel();
//                                            }
//                                        });
//
//                                AlertDialog alertDialog = builder.create();
//                                alertDialog.show();
//
//                            }
//
//                            @Override
//                            public void onItemLongClick(View v, int position) {
//
//                            }
//                        });
                        return viewHolder;
                    }
//                    @Override
//                    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
//
//                        if(position%2 == 0){
//                            viewHolder.itemView.setBackgroundColor(Color.parseColor("#ffffff"));
//                        } else {
//                            viewHolder.itemView.setBackgroundColor(Color.parseColor("#CCC9C9"));
//
//                        }}
                };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}