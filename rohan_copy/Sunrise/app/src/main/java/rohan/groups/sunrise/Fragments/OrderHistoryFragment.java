package rohan.groups.sunrise.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import rohan.groups.sunrise.Functionality.Session;
import rohan.groups.sunrise.Order.Order;
import rohan.groups.sunrise.Order.ViewHolder;
import rohan.groups.sunrise.R;

public class OrderHistoryFragment extends Fragment {


    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    Session sessions;

    public OrderHistoryFragment() {
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

        Toolbar toolbar=(getActivity()).findViewById(R.id.toolbar);
        toolbar.setVisibility(View.GONE);

        ImageView back=v.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        LinearLayout bottomnavigation=(getActivity()).findViewById(R.id.bottomnavigation);
        bottomnavigation.setVisibility(View.GONE);



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

        Query firebasequery = mref.orderByChild("UserId").equalTo(sessions.getusername());
        FirebaseRecyclerAdapter<Order, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Order, ViewHolder>(
                        Order.class,
                        R.layout.orders_row,
                        ViewHolder.class,
                        firebasequery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Order order, int position) {
                        viewHolder.setDetails(getContext(),order.OrderNo,order.OrderDateTime,order.Total,order.UserId,order.Pushid,order.Status,order.OrderType);

                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {


                                TextView pushid = v.findViewById(R.id.pushid);
                                TextView type = v.findViewById(R.id.type);


                                if(type.getText().toString().equals("Grocery")) {
                                    Bundle bundle = new Bundle();
                                    OrderDetailsGrocery fragment = new OrderDetailsGrocery();
                                    bundle.putString("pushid", pushid.getText().toString());
                                    fragment.setArguments(bundle);
                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                    fragmentManager.beginTransaction()
                                            .addToBackStack(null)
                                            .replace(R.id.frame_container, fragment).commit();

                                }
                                else{


                                    Bundle bundle = new Bundle();
                                    OrderDetailsFragment fragment = new OrderDetailsFragment();
                                    bundle.putString("pushid", pushid.getText().toString());
                                    fragment.setArguments(bundle);
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

                };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}