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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import rohan.groups.sunrisedelivery.Functionality.Session;
import rohan.groups.sunrisedelivery.Orders.Order;
import rohan.groups.sunrisedelivery.Orders.ViewHolder;
import rohan.groups.sunrisedelivery.R;


public class CurrentFragment extends Fragment {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    Session sessions;
    ProgressBar progressBar;


    public CurrentFragment() {
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
        View v=inflater.inflate(R.layout.fragment_current, container, false);


        sessions = new Session(getContext());
        mRecyclerView = v.findViewById(R.id.ordersview);
        progressBar = v.findViewById(R.id.progressBar);

        progressBar.setVisibility(View.VISIBLE);


        sessions.setverified("No");

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
                        viewHolder.setDetails(getContext(),order.Items,order.OrderDateTime,order.OrderNo,order.Total,order.Status,order.Pushid,order.Number,order.CName,order.LocationCoordinates,order.Flat,order.Address,order.Landmark,order.Payment,order.Cashback,order.UserId,order.Verified);

                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {


                                TextView pushid = v.findViewById(R.id.pushid);
                                TextView total = v.findViewById(R.id.total);

                                TextView cname=v.findViewById(R.id.cname);
                                TextView number=v.findViewById(R.id.number);
                                TextView latlng=v.findViewById(R.id.latlng);
                                TextView address=v.findViewById(R.id.address);
                                TextView payment=v.findViewById(R.id.payment);
                                TextView cashback=v.findViewById(R.id.cashback);
                                TextView userid=v.findViewById(R.id.userid);
                                TextView verified=v.findViewById(R.id.verified);
                                TextView items=v.findViewById(R.id.itemsname);


                                if(items.getText().toString().equals("Grocery")) {

                                    Bundle bundle = new Bundle();
                                    CurrentDetailsGrocery fragment = new CurrentDetailsGrocery();
                                    bundle.putString("pushid", pushid.getText().toString());
                                    sessions.setverified(verified.getText().toString());
                                    fragment.setArguments(bundle);
                                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                    fragmentManager.beginTransaction()
                                            .addToBackStack(null)
                                            .replace(R.id.frame_container, fragment).commit();
                                }
                                else{

                                    Bundle bundle = new Bundle();
                                    CurrentDetails fragment = new CurrentDetails();
                                    bundle.putString("pushid", pushid.getText().toString());
                                    sessions.setverified(verified.getText().toString());
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

                    @Override
                    protected void onDataChanged() {
                        super.onDataChanged();

                        progressBar.setVisibility(View.GONE);
                    }
                };

        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}