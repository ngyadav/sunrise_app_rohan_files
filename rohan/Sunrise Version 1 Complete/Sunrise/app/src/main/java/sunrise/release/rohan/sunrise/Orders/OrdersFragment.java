package sunrise.release.rohan.sunrise.Orders;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import sunrise.release.rohan.sunrise.Functionality.LoginSession;
import sunrise.release.rohan.sunrise.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class OrdersFragment extends Fragment {
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;
    private LoginSession session;
    private AdView mAdView;
    public OrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        MobileAds.initialize(getContext(), "ca-app-pub-5167989951191945~5928872897");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_orders, container, false);

        mAdView = v.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        AdRequest adRequest1 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mRecyclerView = v.findViewById(R.id.recyclervieworders);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mref = mFirebaseDatabase.getReference("Orders");
        session = new LoginSession(getContext());
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        String abc = session.getusename();
        Query firebasequery = mref.orderByChild("Userid").equalTo("+91" + abc);
        FirebaseRecyclerAdapter<Orders, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Orders, ViewHolder>(
                        Orders.class,
                        R.layout.cardview_order,
                        ViewHolder.class,
                        firebasequery
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Orders orders, int position) {
                        viewHolder.setDetails(getContext(), orders.ProductName, orders.Mrp, orders.Rewards, orders.Image1, orders.OrderStatus, orders.OrderNo, orders.Date,orders.Quantity,orders.DeliveryName,orders.DeliveryAddress,orders.DeliveryNumber,orders.Size,orders.Colour);

                    }

                    @Override
                    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                TextView status = v.findViewById(R.id.ostatus);

                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Order Status");
                                builder.setMessage("Your Order Status is "+status.getText().toString())
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                // TODO: handle the OK
                                            }
                                        })
                                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });

                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();

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
