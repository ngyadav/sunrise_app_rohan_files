package sunrise.release.rohan.sunrise.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import sunrise.release.rohan.sunrise.DatabaseFetch.Offers;
import sunrise.release.rohan.sunrise.DatabaseFetch.Profile;
import sunrise.release.rohan.sunrise.DatabaseFetch.ViewHolder;
import sunrise.release.rohan.sunrise.DatabaseFetch.ViewHolderOffers;
import sunrise.release.rohan.sunrise.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class OffersFragment extends Fragment {

    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mref;

    public OffersFragment() {
        // Required empty public constructor
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
        final View v = inflater.inflate(R.layout.fragment_offers, container, false);

        mRecyclerView=v.findViewById(R.id.recyclerviewoffers);
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        mRecyclerView.setNestedScrollingEnabled(false);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mref = mFirebaseDatabase.getReference("Offers");


        return v;
    }

    @Override
    public  void onStart()
    {
        super.onStart();
//        registersearch("");
        Query ref=mref.orderByChild("Image");
        FirebaseRecyclerAdapter<Offers,ViewHolderOffers> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Offers,ViewHolderOffers>(
                        Offers.class,
                        R.layout.offers_row,
                        ViewHolderOffers.class,
                        ref
                ){
                    @Override
                    protected void populateViewHolder(ViewHolderOffers viewHolder,Offers profile,int position)
                    {
                        viewHolder.setDetails(getContext(),profile.Image,profile.Text);

                    }

                    @Override
                    public ViewHolderOffers onCreateViewHolder(ViewGroup parent, int viewType) {

                        ViewHolderOffers viewHolder=super.onCreateViewHolder(parent,viewType);
                        viewHolder.setOnClickListener(new ViewHolder.ClickListener() {
                            @Override
                            public void onItemClick(View v, int position) {
                                TextView offerdetails=(TextView)v.findViewById(R.id.offerdetails);


                                new AlertDialog.Builder(getContext())
                                        .setTitle("Terms")
                                        .setMessage(offerdetails.getText().toString())
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface dialog, int whichButton) {

                                            }})
                                        .setNegativeButton(android.R.string.no, null).show();

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
