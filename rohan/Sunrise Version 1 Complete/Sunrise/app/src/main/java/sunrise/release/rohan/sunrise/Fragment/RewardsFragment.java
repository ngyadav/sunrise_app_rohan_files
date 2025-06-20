package sunrise.release.rohan.sunrise.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.Orders.OrdersFragment;
import sunrise.release.rohan.sunrise.R;
import sunrise.release.rohan.sunrise.Rewards.Rewards;
import sunrise.release.rohan.sunrise.Rewards.ViewHolder;

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
        DatabaseReference mref1=FirebaseDatabase.getInstance().getReference().child("Users");
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
        Query firebasequery = mref.orderByChild("UserId").equalTo("+91" + abc);
        FirebaseRecyclerAdapter<Rewards, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Rewards, ViewHolder>(
                        Rewards.class,
                        R.layout.cardview_rewards,
                        ViewHolder.class,
                        firebasequery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder,Rewards rewards, int position) {
                        viewHolder.setDetails(getContext(),rewards.Amount,rewards.Balance,rewards.Date,rewards.Name,rewards.TransactionId,rewards.Type,rewards.OrderNumber,position);

                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                ImageView rightarrow= (ImageView)v.findViewById(R.id.Rewardsarrow);
                                if(rightarrow.getVisibility()==View.VISIBLE){
                                Fragment fragment= new OrdersFragment();
                                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                fragmentManager.beginTransaction()
                                        .addToBackStack(null)
                                        .replace(R.id.frame_container, fragment).commit();
                                }
                            }

                            @Override
                            public void onItemLongClick(View v, int position) {

                            }
                        });
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

